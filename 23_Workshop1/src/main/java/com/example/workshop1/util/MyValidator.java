package com.example.workshop1.util;

import com.example.workshop1.models.dto.ImportProjectDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class MyValidator {
    private final Validator localValidator;

    public MyValidator() {
        this.localValidator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    public boolean isValid(Object object) {
        Set<ConstraintViolation<Object>> errors =
                this.localValidator.validate(object);

        return errors.isEmpty();
    }
}
