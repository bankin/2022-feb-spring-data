package com.example.gamestore.exceptions;

public class UserNotLoggedInException extends RuntimeException {

    public UserNotLoggedInException() {
        super("Execute Login command first!");
    }
}
