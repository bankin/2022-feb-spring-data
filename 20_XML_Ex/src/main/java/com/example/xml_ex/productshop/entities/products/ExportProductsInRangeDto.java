package com.example.xml_ex.productshop.entities.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportProductsInRangeDto {

    @XmlElement(name = "product")
    List<ProductWithAttributesDTO> products;

    public ExportProductsInRangeDto() {}

    public ExportProductsInRangeDto(List<ProductWithAttributesDTO> products) {
        this.products = products;
    }
}
