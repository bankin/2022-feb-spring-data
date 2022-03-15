package com.example.jsonex.productshop.services;

import com.example.jsonex.productshop.entities.categories.CategoryStats;
import com.example.jsonex.productshop.entities.products.ProductWithoutBuyerDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductsService {
    List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(
            float from, float to);

    List<CategoryStats> getCategoryStatistics();
}
