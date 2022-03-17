package com.example.xml_ex.productshop.entities.categories;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesImportDTO {

    @XmlElement(name = "category")
    private List<CategoryNameDTO> categories;

    public CategoriesImportDTO() {}

    public List<CategoryNameDTO> getCategories() {
        return categories;
    }
}
