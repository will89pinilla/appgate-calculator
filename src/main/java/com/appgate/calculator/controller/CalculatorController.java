package com.appgate.calculator.controller;

import com.appgate.calculator.dto.ArithmeticOperation;
import com.appgate.calculator.dto.Operand;
import com.appgate.calculator.service.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    CalculatorController(final CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/new")
    ResponseEntity<Long> createEnv() {
        return ResponseEntity.ok(calculatorService.newEnvId());
    }

    @PostMapping("/add")
    ResponseEntity<String> addOperand(final @RequestBody Operand operand ) {
        return ResponseEntity.ok(calculatorService.addOperand(operand));
    }

    @PostMapping("/operate")
    ResponseEntity<Double> operate(final @RequestBody ArithmeticOperation arithmeticOperation) {
        return ResponseEntity.ok(calculatorService.operate(arithmeticOperation));
    }

}
