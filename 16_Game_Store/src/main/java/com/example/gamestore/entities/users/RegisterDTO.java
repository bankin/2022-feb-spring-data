package com.example.gamestore.entities.users;

import com.example.gamestore.exceptions.ValidationException;
import org.springframework.validation.annotation.Validated;

/**
 * Validate the data for registering a user.
 *
 * Email must be ...
 * Password must be ...
 *
 */
@Validated
public class RegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void validate() {
        int indexOfAt = email.indexOf("@");
        int indexOfDot = email.lastIndexOf(".");
        if (indexOfAt < 0 || indexOfDot < 0 || indexOfAt > indexOfDot) {
             throw new ValidationException("Email must contain @ and .");
        }

        // TODO: Validate password

        if (!password.equals(confirmPassword)) {
            throw new ValidationException("Password and confirm password must match!");
        }
    }
}
