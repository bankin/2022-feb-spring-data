package com.example.workshop1.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCompanyDTO {
    @XmlAttribute
    private String name;

    public ImportCompanyDTO() {}

    public String getName() {
        return name;
    }
}
