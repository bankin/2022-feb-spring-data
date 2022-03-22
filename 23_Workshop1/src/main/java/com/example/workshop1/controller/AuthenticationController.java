package com.example.workshop1.controller;

import com.example.workshop1.models.dto.RegistrationDTO;
import com.example.workshop1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String registerView() {
        return "user/register";
    }

    @PostMapping(value = "/users/register", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String doRegister() {
        RegistrationDTO dto = new RegistrationDTO("user", "pass", "pass", "mail@some.com");

        this.userService.register(dto);

        return "user/register";
    }


}
