package com.example.demo.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetDataErrorUtils {
    public List<?> DataError(BindingResult rs) {
        List<FieldError> fieldErrors = rs.getFieldErrors();
        List<String> errors = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return errors;
    }
}
