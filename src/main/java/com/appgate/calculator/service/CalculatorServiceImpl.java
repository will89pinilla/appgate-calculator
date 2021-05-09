package com.appgate.calculator.service;

import com.appgate.calculator.domain.EnvironmentEntity;
import com.appgate.calculator.domain.OperandEntity;
import com.appgate.calculator.dto.ArithmeticOperation;
import com.appgate.calculator.dto.Operand;
import com.appgate.calculator.dto.Operation;
import com.appgate.calculator.exception.EnvNotFoundException;
import com.appgate.calculator.exception.OperandsNotFoundException;
import com.appgate.calculator.repository.EnvironmentRepository;
import com.appgate.calculator.repository.OperandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CalculatorServiceImpl implements CalculatorService {

    private final EnvironmentRepository environmentRepository;
    private final OperandRepository operandRepository;
    private final ExecutorService executorService;

    CalculatorServiceImpl(final EnvironmentRepository environmentRepository,
                          final OperandRepository operandRepository,
                          final ExecutorService executorService) {
        this.environmentRepository = environmentRepository;
        this.operandRepository = operandRepository;
        this.executorService = executorService;
    }

    @Override
    public Long newEnvId() {
        return environmentRepository.save(EnvironmentEntity.builder().curTotal(0L).build()).getId();
    }

    private EnvironmentEntity getEnvById(Long id){
        return environmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("It could not find the id: {}", id);
                    throw new EnvNotFoundException(id);
                });
    }

    private List<OperandEntity> getOperandEntities(EnvironmentEntity env) {
        List<OperandEntity> operandsByIsAppliedDisable = operandRepository.findByOperandsByIsAppliedDisable(env.getId());

        if(operandsByIsAppliedDisable.isEmpty()){
            log.error("There is not operands to be applied to the current result in the env: {}", env.getId());
            throw new OperandsNotFoundException(env.getId());
        }

        return operandsByIsAppliedDisable;

    }


    @Override
    public String addOperand(Operand operand) {
        operandRepository.save(OperandEntity.builder()
                .env(getEnvById(operand.getId()))
                .number(operand.getOperand())
                .build());
        return "OK";
    }

    void updateOperands(List<OperandEntity> operandEntities, Operation operation) {
        for (OperandEntity o :operandEntities) {
            operandRepository.save(OperandEntity.builder()
                    .id(o.getId())
                    .number(o.getNumber())
                    .env(o.getEnv())
                    .isApplied(1)
                    .operation(operation.name())
                    .build());
        }
    }

    @Override
    public Double operate(ArithmeticOperation operation) {

        EnvironmentEntity env = getEnvById(operation.getId());
        List<OperandEntity> isAppliedDisable = getOperandEntities(env);

        EnvironmentEntity updateEnv = getEnvironmentEntity(env, isAppliedDisable);

        updateOperands(updateEnv.getOperandEntities(), operation.getOperation());

        return environmentRepository.save(
                EnvironmentEntity.builder()
                        .id(env.getId())
                        .curTotal(executorService.execute(Executor.of(operation.getOperation()), updateEnv))
                        .build())
                .getCurTotal();
    }

    private EnvironmentEntity getEnvironmentEntity(EnvironmentEntity env, List<OperandEntity> isAppliedDisable) {
        return EnvironmentEntity.builder()
                .id(env.getId())
                .operandEntities(isAppliedDisable)
                .curTotal(env.getCurTotal())
                .build();
    }
}