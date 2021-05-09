package com.appgate.calculator.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class ArithmeticOperation implements Dto {

    @NotNull(message = "Id cannot be null")
    @Digits(integer = 15, fraction = 15)
    private final Long id;

    @NotNull(message = "Operation cannot be null")
    private final Operation operation;
}
