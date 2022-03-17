package com.example.xml_ex.productshop.entities.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// 	<category>
//		<name>Electronics</name>
//	</category>
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryNameDTO {

    @XmlElement
    private String name;

    public CategoryNameDTO() {}

    public String getName() {
        return name;
    }
}
