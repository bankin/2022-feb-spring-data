package com.example.workshop1.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "companies")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCompaniesRootDTO {

    @XmlElement(name = "company")
    List<ImportCompanyDTO> companies;

    public ImportCompaniesRootDTO() {}

    public List<ImportCompanyDTO> getCompanies() {
        return companies;
    }
}
