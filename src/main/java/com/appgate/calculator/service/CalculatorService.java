package com.appgate.calculator.service;

import com.appgate.calculator.dto.ArithmeticOperation;
import com.appgate.calculator.dto.Operand;

public interface CalculatorService {
    Long newEnvId();
    String addOperand(Operand operand);
    Double operate(ArithmeticOperation operation);
}