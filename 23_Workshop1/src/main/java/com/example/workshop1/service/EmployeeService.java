package com.example.workshop1.service;

import com.example.workshop1.models.Employee;
import com.example.workshop1.models.Project;
import com.example.workshop1.models.dto.ImportEmployeeDTO;
import com.example.workshop1.models.dto.ImportEmployeesRootDTO;
import com.example.workshop1.repositories.EmployeeRepository;
import com.example.workshop1.repositories.ProjectRepository;
import com.example.workshop1.util.MyValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Path xmlPath =
            Path.of("src/main/resources/files/xmls/employees.xml");

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final MyValidator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           ProjectRepository projectRepository, MyValidator validator,
                           ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    public boolean areImported() {
        return this.employeeRepository.count() > 0;
    }

    public String getEmployeesText() throws IOException {
        return Files.readString(xmlPath);
    }

    public String importEmployees() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ImportEmployeesRootDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ImportEmployeesRootDTO rootDTO =
            (ImportEmployeesRootDTO) unmarshaller.unmarshal(new File(xmlPath.toAbsolutePath().toString()));

        return rootDTO
            .getEmployees()
            .stream()
            .map(this::importEmployee)
            .collect(Collectors.joining("\n"));
    }

    private String importEmployee(ImportEmployeeDTO dto) {
        if (!validator.isValid(dto)) {
            return "Invalid Employee or Project";
        }

        Employee employee = this.modelMapper.map(dto, Employee.class);

        Optional<Project> optProject =
                this.projectRepository.findByName(dto.getProject().getName());

        if (optProject.isEmpty()) {
            return "Invalid Project Name";
        }

        employee.setProject(optProject.get());

        this.employeeRepository.save(employee);

        return "Import Employee - " + employee.getFirstName() +
                " " + employee.getLastName();
    }


    public List<ExportEmployeeDTO> getEmployeesAbove() {
        List<Employee> employees = this.employeeRepository
                .findByAgeGreaterThanOrderByProjectNameAsc(25);

        return employees
            .stream()
            .map(ExportEmployeeDTO::new)
            .collect(Collectors.toList());

    }
}
