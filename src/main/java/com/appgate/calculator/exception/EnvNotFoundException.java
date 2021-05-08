package com.appgate.calculator.exception;

public class EnvNotFoundException extends RuntimeException {
    public EnvNotFoundException(Long id) {
        super(String.format("Could not find the env %d",id));
    }
}
