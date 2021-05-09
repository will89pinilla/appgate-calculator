package com.appgate.calculator.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class ArithmeticOperation implements Dto {
    @NotNull(message = "Id cannot be null")
    private final Long id;
    @NotEmpty(message = "The operation cannot be empty or null")
    private final Operation operation;
}
