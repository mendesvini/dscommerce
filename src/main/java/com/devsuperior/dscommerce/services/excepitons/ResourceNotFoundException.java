package com.devsuperior.dscommerce.services.excepitons;

public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(String mensage){
        super(mensage);
    }
}
