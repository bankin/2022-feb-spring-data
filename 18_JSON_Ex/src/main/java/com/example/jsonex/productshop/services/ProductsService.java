package com.example.jsonex.productshop.services;

import com.example.jsonex.productshop.entities.categories.CategoryStatsDTO;
import com.example.jsonex.productshop.entities.products.ProductWithoutBuyerDTO;

import java.util.List;

public interface ProductsService {
    List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(
            float from, float to);

    List<CategoryStatsDTO> getCategoryStatistics();
}
