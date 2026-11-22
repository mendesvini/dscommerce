package com.devsuperior.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ViolationData extends CustomError {


    public ViolationData(Instant moment, Integer status, String error, String path) {
        super(moment, status, error, path);
    }

    List<FildError> errors = new ArrayList<>();

    public void addError(String field, String message) {
        errors.add(new FildError(field, message));
    }

    public List<FildError> getErrors() {
        return errors;
    }
}
