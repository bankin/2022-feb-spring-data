package com.example.gamestore.services;

public interface ExecutorService {
    String REGISTER_USER_COMMAND = "RegisterUser";
    String LOGIN_USER_COMMAND = "LoginUser";
    String LOGOUT_USER_COMMAND = "Logout";

    String ADD_GAME_COMMAND = "AddGame";

    String execute(String command);
}
