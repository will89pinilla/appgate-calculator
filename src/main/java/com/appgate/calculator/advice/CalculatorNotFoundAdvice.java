package com.appgate.calculator.advice;

import com.appgate.calculator.exception.EnvNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CalculatorNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(EnvNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EnvNotFoundException ex) {
        return ex.getMessage();
    }
}
