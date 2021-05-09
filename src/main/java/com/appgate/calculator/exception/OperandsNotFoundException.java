package com.appgate.calculator.exception;

public class OperandsNotFoundException extends RuntimeException {
    public OperandsNotFoundException(Long id) {
        super(String.format("Could not find operands to be applied on the env %d", id));
    }
}
