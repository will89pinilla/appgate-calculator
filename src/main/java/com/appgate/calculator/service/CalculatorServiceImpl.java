package com.appgate.calculator.service;

import com.appgate.calculator.domain.EnviromentEntity;
import com.appgate.calculator.domain.OperandEntity;
import com.appgate.calculator.dto.ArithmeticOperation;
import com.appgate.calculator.dto.Operand;
import com.appgate.calculator.exception.EnvNotFoundException;
import com.appgate.calculator.repository.EnvironmentRepository;
import com.appgate.calculator.repository.OperandRepository;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    private final EnvironmentRepository environmentRepository;
    private final OperandRepository operandRepository;

    CalculatorServiceImpl(final EnvironmentRepository environmentRepository, final OperandRepository operandRepository) {
        this.environmentRepository = environmentRepository;
        this.operandRepository = operandRepository;
    }

    @Override
    public Long newEnvId() {
        return environmentRepository.save(EnviromentEntity.builder().curTotal(0L).build()).getId();
    }

    EnviromentEntity getEnvById(Long id){
        return environmentRepository.findById(id)
                .orElseThrow(() -> new EnvNotFoundException(id));
    }


    @Override
    public String addOperand(Operand operand) {
        operandRepository.save(OperandEntity.builder()
                .env(getEnvById(operand.getId()))
                .number(operand.getOperand())
                .build());
        return "OK";
    }

    @Override
    public Long operate(ArithmeticOperation operation) {
        return null;
    }
}
