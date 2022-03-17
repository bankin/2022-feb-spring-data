package com.example.xml_ex.productshop.services;

import com.example.xml_ex.productshop.entities.users.ExportSellersDTO;
import com.example.xml_ex.productshop.entities.users.ExportUserWithSoldProductsDTO;
import com.example.xml_ex.productshop.entities.users.User;
import com.example.xml_ex.productshop.repositories.UserRepository;
import com.sun.xml.bind.v2.schemagen.episode.SchemaBindings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public ExportSellersDTO findAllWithSoldProducts() {
        List<User> users = this.userRepository.findAllWithSoldProducts();

        List<ExportUserWithSoldProductsDTO> dtos =
            users
                .stream()
                .map(u -> this.mapper.map(u, ExportUserWithSoldProductsDTO.class))
                .collect(Collectors.toList());

        return new ExportSellersDTO(dtos);
    }
}
