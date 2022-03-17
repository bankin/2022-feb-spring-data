package com.example.xml_ex.productshop.entities.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportDTO {

    @XmlElement
    private String name;

    @XmlElement
    private BigDecimal price;

    public ProductImportDTO() {}

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
