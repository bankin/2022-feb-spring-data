package bg.softuni.model_mapper.entities.dto;

import bg.softuni.model_mapper.entities.Employee;

public class CustomDTO {
    private String firstName;
    private String lastName;

    private int managerLastNameLength;

    public CustomDTO(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.managerLastNameLength = employee.getManager() == null
                ? 0 : employee.getManager().getLastName().length();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setManagerLastNameLength(int managerLastNameLength) {
        this.managerLastNameLength = managerLastNameLength;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", managerLastNameLength=" + managerLastNameLength +
                '}';
    }
}
