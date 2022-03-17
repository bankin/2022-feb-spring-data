package com.example.xml_ex.productshop.entities.users;

import com.example.xml_ex.productshop.entities.products.ExportSoldProductDTO;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExportUserWithSoldProductsDTO {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    List<ExportSoldProductDTO> sellingItems;

    public ExportUserWithSoldProductsDTO() {}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSellingItems(List<ExportSoldProductDTO> sellingItems) {
        this.sellingItems = sellingItems;
    }
}
