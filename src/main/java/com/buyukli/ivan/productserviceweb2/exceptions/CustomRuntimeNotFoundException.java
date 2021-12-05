package com.buyukli.ivan.productserviceweb2.exceptions;

public class CustomRuntimeNotFoundException extends RuntimeException{
    public CustomRuntimeNotFoundException(String message) {
        super(message);
    }
}
