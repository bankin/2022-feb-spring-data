package com.example.jsonex.productshop.services;

import com.example.jsonex.productshop.entities.users.User;
import com.example.jsonex.productshop.entities.users.UserWithSoldProductsDTO;

import java.util.List;

public interface UserService {
    List<UserWithSoldProductsDTO> getUsersWithSoldProducts();

    List<User> getUsersWithSoldProductsOrderByCount();
}
