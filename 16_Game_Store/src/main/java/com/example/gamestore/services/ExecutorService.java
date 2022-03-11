package com.example.gamestore.services;

public interface ExecutorService {
    String REGISTER_USER_COMMAND = "RegisterUser";
    String LOGIN_USER_COMMAND = "LoginUser";
    String LOGOUT_USER_COMMAND = "Logout";

    String ADD_GAME_COMMAND = "AddGame";

    String execute(String command, String data);

//    {
//        "email": "pesho22@mail.com",
//        "password": "1234567",
//        "confirmPassword: "1234567",
//        "fillName": "Pesho 22334"
//    }
    String REGISTER_USER_JSON = "{\"email\":\"pesho22@mail.com\",\"password\":\"1234567\",\"confirmPassword\":\"1234567\",\"fullName\":\"Pesho 22334\"}";

    String LOGIN_USER_JSON = """
            {
                "email": "pesho22@mail.com",
                "password": "1234567",
            }
            """;
}
