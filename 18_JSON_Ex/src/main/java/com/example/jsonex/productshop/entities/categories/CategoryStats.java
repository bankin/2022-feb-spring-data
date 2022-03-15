package com.example.jsonex.productshop.entities.categories;

import java.math.BigDecimal;

public class CategoryStats {
    private String category;
    private long productCount;
    private double averagePrice;
    private BigDecimal totalRevenue;

    public CategoryStats(String category, long productCount, double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productCount = productCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return category;
    }

    public long getProductCount() {
        return productCount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
}
