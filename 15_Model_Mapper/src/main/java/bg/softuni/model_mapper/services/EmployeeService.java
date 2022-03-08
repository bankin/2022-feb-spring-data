package bg.softuni.model_mapper.services;

import bg.softuni.model_mapper.entities.Employee;
import bg.softuni.model_mapper.entities.dto.EmployeeSpringDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findOneById(int id);

    void save(Employee employee);

    List<EmployeeSpringDTO> findEmployeesBornBefore(int year);

    List<Employee> findAll();
}
