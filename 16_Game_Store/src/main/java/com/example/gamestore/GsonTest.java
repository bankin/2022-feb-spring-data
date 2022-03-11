package com.example.gamestore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class GsonTest {

    private static final String json =
        """
            {
              "username": "mnogoqk",
              "address": "Mladost 4"
            }
        """;

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();

//        LoginData loginData = new LoginData(
//                "mnogoqk","1234");
//
//        String result = gson.toJson(loginData);

//        System.out.println(result);

        LoginData loginData = gson.fromJson(json, LoginData.class);

        System.out.println(loginData);
    }

    static class LoginData {
        @Expose
        private String username;

        @Expose
        private String password;

        public LoginData(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "LoginData{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}
