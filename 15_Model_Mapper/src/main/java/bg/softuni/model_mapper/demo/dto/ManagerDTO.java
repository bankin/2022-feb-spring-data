package bg.softuni.model_mapper.demo.dto;

import java.util.Set;
import java.util.stream.Collectors;

public class ManagerDTO {
    private String firstName;
    private String lastName;
    private Set<EmployeeDTO> subordinates;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSubordinates(Set<EmployeeDTO> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public String toString() {
        String employees = this.subordinates
            .stream()
            .map(EmployeeDTO::toString)
            .map(s -> "    - " + s)
            .collect(Collectors.joining("\n"));

        return String.format("%s %s | Employees: %d%n%s%n",
            this.firstName, this.lastName, this.subordinates.size(),
                employees);
    }
}
