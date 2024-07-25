package com.devsuperior.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {


    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getFieldErrors() {
        return errors;
    }

    public void addFieldError(String fieldName, String message) {

        errors.removeIf(x -> x.getFieldName().equals(fieldName));

        errors.add(new FieldMessage(fieldName, message));
    }

}
