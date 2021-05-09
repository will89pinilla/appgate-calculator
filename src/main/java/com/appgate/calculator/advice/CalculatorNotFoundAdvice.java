package com.appgate.calculator.advice;

import com.appgate.calculator.exception.EnvNotFoundException;
import com.appgate.calculator.exception.OperandsNotFoundException;
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
    String envNotFoundHandler(EnvNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(OperandsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String operandsNotFoundHandler(OperandsNotFoundException ex) {
        return ex.getMessage();
    }
}
