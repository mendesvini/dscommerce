package com.devsuperior.dscommerce.dto;

public class FildError {
    private String fild;
    private String message;

    public FildError(String fild, String message) {
        this.fild = fild;
        this.message = message;
    }

    public String getFild() {
        return fild;
    }

    public String getMessage() {
        return message;
    }
}
