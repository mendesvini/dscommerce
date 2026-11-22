package com.devsuperior.dscommerce.services.excepitons;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message) {
        super(message);
    }
}
