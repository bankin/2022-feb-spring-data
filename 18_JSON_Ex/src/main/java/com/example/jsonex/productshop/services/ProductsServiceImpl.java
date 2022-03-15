package com.example.jsonex.productshop.services;

import com.example.jsonex.productshop.entities.categories.CategoryStatsDTO;
import com.example.jsonex.productshop.entities.products.ProductWithoutBuyerDTO;
import com.example.jsonex.productshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(float from, float to) {
        BigDecimal rangeStart = BigDecimal.valueOf(from);
        BigDecimal rangeEnd = BigDecimal.valueOf(to);

        return this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(rangeStart, rangeEnd);
    }

    @Override
    public List<CategoryStatsDTO> getCategoryStatistics() {
        return this.productRepository.getCategoryStats();
    }
}
