package com.example.gamestore.services.impl;

import com.example.gamestore.entities.users.LoginDTO;
import com.example.gamestore.entities.users.RegisterDTO;
import com.example.gamestore.entities.users.User;
import com.example.gamestore.entities.users.UserBasicInfoDTO;
import com.example.gamestore.services.ExecutorService;
import com.example.gamestore.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecutorServiceImpl implements ExecutorService {

    private final UserService userService;

    private final Gson gson;

    private final ModelMapper mapper;

    @Autowired
    public ExecutorServiceImpl(UserService userService) {
        this.userService = userService;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        this.mapper = new ModelMapper();
    }

    @Override
    public String execute(String commandName, String data) {

        Object commandOutput = switch (commandName) {
            case REGISTER_USER_COMMAND -> registerUser(data);
            case LOGIN_USER_COMMAND -> loginUser(data);
            case LOGOUT_USER_COMMAND -> logoutUser();
            case ADD_GAME_COMMAND -> addGame();

            default -> "unknown command";
        };

        return this.gson.toJson(commandOutput);
    }

    private String addGame() {
        User loggedUser = this.userService.getLoggedUser();

        if (!loggedUser.isAdmin()) {
//            throw new
        }

        //
        return null;
    }

    private UserBasicInfoDTO logoutUser() {
        User loggedUser = this.userService.getLoggedUser();

        this.userService.logout();

        return this.mapper.map(loggedUser, UserBasicInfoDTO.class);
    }

    private UserBasicInfoDTO loginUser(String data) {
        LoginDTO loginData = this.gson.fromJson(data, LoginDTO.class);

        Optional<User> user = userService.login(loginData);

        if (user.isPresent()) {
            return this.mapper.map(user.get(), UserBasicInfoDTO.class);
        }

        // FIXME:
        return null;
    }

    private UserBasicInfoDTO registerUser(String data) {
        RegisterDTO registerData = this.gson.fromJson(data, RegisterDTO.class);
        registerData.validate();

        User user = userService.register(registerData);

        return this.mapper.map(user, UserBasicInfoDTO.class);
    }
}
