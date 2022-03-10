package com.example.gamestore.services.impl;

import com.example.gamestore.entities.users.LoginDTO;
import com.example.gamestore.entities.users.RegisterDTO;
import com.example.gamestore.entities.users.User;
import com.example.gamestore.exceptions.UserNotLoggedInException;
import com.example.gamestore.repositories.UserRepository;
import com.example.gamestore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private User currentUser;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.currentUser = null;

        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterDTO registerData) {
        if (this.currentUser != null) {
            // throw exception / return;
        }

        ModelMapper mapper = new ModelMapper();
        User toRegister = mapper.map(registerData, User.class);

        long userCount = this.userRepository.count();

        if (userCount == 0) {
            toRegister.setAdmin(true);
        }

        return this.userRepository.save(toRegister);
    }

    @Override
    public Optional<User> login(LoginDTO loginData) {
        if (this.currentUser != null) {
            // throw exception / return;
        }

        Optional<User> user = this.userRepository.findByEmailAndPassword(
                loginData.getEmail(), loginData.getPassword());

        user.ifPresent(value -> this.currentUser = value);

        return user;
    }

    public User getLoggedUser() {
        if (this.currentUser == null) {
            throw new UserNotLoggedInException();
        }
        return this.currentUser;
    }

    @Override
    public void logout() {
        // TODO: Cannot log out. No user was logged in.
        this.currentUser = null;
    }
}
