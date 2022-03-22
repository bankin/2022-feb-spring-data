package com.example.workshop1.service;

import com.example.workshop1.models.User;
import com.example.workshop1.models.dto.RegistrationDTO;
import com.example.workshop1.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(@Qualifier("alt") ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public void register(RegistrationDTO dto) {
        User user = this.modelMapper.map(dto, User.class);

        this.userRepository.save(user);
    }
}
