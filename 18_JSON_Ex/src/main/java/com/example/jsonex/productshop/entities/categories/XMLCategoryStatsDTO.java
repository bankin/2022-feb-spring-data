package com.example.jsonex.productshop.entities.categories;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLCategoryStatsDTO implements Serializable {
    @XmlElement(name = "name")
    private String category;

    private long productCount;

    private double averagePrice;

    private BigDecimal totalRevenue;

    /**
     * <?xml version="1.0" encoding="UTF-8"?>
     * <category>
     *    <name>Drugs</name>
     *    <product-count>68</product-count>
     *    <averagePrice>836.952941</averagePrice>
     *    <totalRevenue>56912.80</totalRevenue>
     * </category>
     */
    public XMLCategoryStatsDTO() {}

    public XMLCategoryStatsDTO(CategoryStatsDTO other) {
        this.category = other.getCategory();
        this.productCount = other.getProductCount();
        this.averagePrice = other.getAveragePrice();
        this.totalRevenue = other.getTotalRevenue();
    }

    @Override
    public String toString() {
        return "XMLCategoryStatsDTO{" +
                "category='" + category + '\'' +
                ", productCount=" + productCount +
                ", averagePrice=" + averagePrice +
                ", totalRevenue=" + totalRevenue +
                '}';
    }
}
