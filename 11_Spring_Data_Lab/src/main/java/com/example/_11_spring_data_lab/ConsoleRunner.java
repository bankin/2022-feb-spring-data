package com.example._11_spring_data_lab;

import com.example._11_spring_data_lab.models.User;
import com.example._11_spring_data_lab.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User first = new User("qna", 22);
        userService.registerUser(first);

        User second = new User("qna", 22);
        userService.registerUser(second);
    }
}
