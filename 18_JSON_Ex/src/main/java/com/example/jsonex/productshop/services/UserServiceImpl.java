package com.example.jsonex.productshop.services;

import com.example.jsonex.productshop.entities.users.User;
import com.example.jsonex.productshop.entities.users.UserWithSoldProductsDTO;
import com.example.jsonex.productshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public List<UserWithSoldProductsDTO> getUsersWithSoldProducts() {
        List<User> allWithSoldProducts = this.userRepository.findAllWithSoldProducts();

        return allWithSoldProducts
            .stream()
            .map(user -> this.mapper.map(user, UserWithSoldProductsDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<User> getUsersWithSoldProductsOrderByCount() {
        List<User> all = this.userRepository.findAllWithSoldProductsOrderByCount();

        all.get(0).getSellingItems().size();

        return null;
    }
}
