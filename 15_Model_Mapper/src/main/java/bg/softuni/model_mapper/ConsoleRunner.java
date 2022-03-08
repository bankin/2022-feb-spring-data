package bg.softuni.model_mapper;

import bg.softuni.model_mapper.entities.Employee;
import bg.softuni.model_mapper.entities.dto.CustomDTO;
import bg.softuni.model_mapper.entities.dto.EmployeeSpringDTO;
import bg.softuni.model_mapper.services.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private EmployeeService employeeService;

    @Autowired
    public ConsoleRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.persist();

//        this.employeeService.findEmployeesBornBefore(1990)
//                .forEach(System.out::println);

        List<Employee> all = this.employeeService.findAll();

        ModelMapper mapper = new ModelMapper();
        TypeMap<Employee, CustomDTO> employeeToCustom = mapper.typeMap(Employee.class, CustomDTO.class);

        Converter<Employee, Integer> getLastNameLength =
            ctx -> ctx.getSource() == null ? null : ctx.getSource().getLastName().length();

        employeeToCustom.addMappings(mapping ->
            mapping.when(Objects::nonNull).using(getLastNameLength).map(Employee::getManager, CustomDTO::setManagerLastNameLength)
        );

        all
            .stream()
            .map(employeeToCustom::map)
            .forEach(System.out::println);
    }

    private void persist() {
        Employee manager = new Employee(
                "Mrs.",
                "Manager",
                BigDecimal.ONE,
                LocalDate.now(),
                null);

        Employee first = new Employee(
                "first",
                "last",
                BigDecimal.TEN,
                LocalDate.now(),
                manager);

        Employee second = new Employee(
                "second",
                "last",
                BigDecimal.TEN,
                LocalDate.now(),
                manager);

        this.employeeService.save(first);
//        this.employeeService.save(second);
    }
}
