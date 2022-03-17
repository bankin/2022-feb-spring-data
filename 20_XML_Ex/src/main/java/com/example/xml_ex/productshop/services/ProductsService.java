package com.example.xml_ex.productshop.services;

import com.example.xml_ex.productshop.entities.products.ExportProductsInRangeDto;

public interface ProductsService {
    ExportProductsInRangeDto getInRange(float from, float to);
}
