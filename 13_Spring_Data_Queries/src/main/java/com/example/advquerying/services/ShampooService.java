package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {

    List<Shampoo> selectBySize(Size size);

    List<Shampoo> selectBySizeOrLabelId(Size medium, int labelId);

    List<Shampoo> selectMoreExpensiveThan(BigDecimal price);

    int countPriceLowerThan(BigDecimal price);

    List<Shampoo> selectByIngredientsCount(int count);
}
