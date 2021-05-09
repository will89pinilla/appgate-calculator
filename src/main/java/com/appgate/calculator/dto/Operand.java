package com.appgate.calculator.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;


@Getter
@Builder
public class Operand implements Dto {

    @NotNull(message = "Id cannot be null")
    private final Long id;

    @NotNull(message = "Operand cannot be null")
    private final Double operand;
}
