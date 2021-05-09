package com.appgate.calculator.controller;

import com.appgate.calculator.dto.ArithmeticOperation;
import com.appgate.calculator.dto.Operand;
import com.appgate.calculator.service.CalculatorService;
import com.appgate.calculator.service.ValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/calculator")
class CalculatorController {

    private final CalculatorService calculatorService;
    private final ValidationService validationService;

    CalculatorController(final CalculatorService calculatorService, final ValidationService validationService) {
        this.calculatorService = calculatorService;
        this.validationService = validationService;
    }

    @GetMapping("/new")
    ResponseEntity<Long> createEnv() {
        return ResponseEntity.ok(calculatorService.newEnvId());
    }

    @PostMapping("/add")
    ResponseEntity<String> addOperand(final @RequestBody Operand operand ) {
        validationService.validate(operand);
        return ResponseEntity.ok(calculatorService.addOperand(operand));
    }

    @PostMapping("/operate")
    ResponseEntity<Double> operate(final @RequestBody ArithmeticOperation arithmeticOperation) {
        validationService.validate(arithmeticOperation);
        return ResponseEntity.ok(calculatorService.operate(arithmeticOperation));
    }

}
