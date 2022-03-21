package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class StatIdDTO {

    @XmlElement
    private long id;

    public StatIdDTO() {}

    public long getId() {
        return id;
    }
}
