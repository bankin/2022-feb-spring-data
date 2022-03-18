package com.example.xml_ex.productshop.services;

import com.example.xml_ex.productshop.entities.users.ExportSellersDTO;
import com.example.xml_ex.productshop.entities.users.ExportSellersWithCountsDTO;

public interface UserService {

    ExportSellersDTO findAllWithSoldProducts();

    ExportSellersWithCountsDTO findAllWithSoldProductsAndCounts();
}
