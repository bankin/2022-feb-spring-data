package com.example.workshop1.models.dto;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType
public class ImportEmployeeDTO {

    @XmlElement(name = "first-name")
    private String firstName;

    @XmlElement(name = "last-name")
    private String lastName;

    @XmlElement
    @Min(18)
    private int age;

    @XmlElement
    private ImportProjectDTO project;

    public ImportEmployeeDTO() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public ImportProjectDTO getProject() {
        return project;
    }
}
