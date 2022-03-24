package com.example.workshop1.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProjectDTO implements Serializable {
    @XmlElement
    private String name;

    @XmlElement
    @Size(min = 14)
    private String description;

    @XmlElement(name = "start-date")
    private String startDate;

    @XmlElement(name = "is-finished")
    private boolean isFinished;

    @XmlElement
    @Positive
    private BigDecimal payment;

    @XmlElement
    private ImportCompanyDTO company;

    public ImportProjectDTO() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public ImportCompanyDTO getCompany() {
        return company;
    }
}
