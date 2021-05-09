package com.appgate.calculator.service;

import com.appgate.calculator.domain.EnvironmentEntity;
import com.appgate.calculator.domain.OperandEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ExecutorServiceTest {

    private ExecutorService executorService;
    private List<OperandEntity> operandEntities;

    @BeforeEach
    public void setUp(){
        executorService = new ExecutorService();
        operandEntities = Arrays
                .asList(OperandEntity.builder().number(5).build(),
                        OperandEntity.builder().number(7).build(),
                        OperandEntity.builder().number(2).build());
    }

    @Test
    void multiplication() {

        Double result = executorService.execute(Executor.Type.MUL,
                EnvironmentEntity.builder().curTotal(1).operandEntities(operandEntities).build());

        assertEquals(70.0, result);
    }

    @Test
    void zeroMultiplicationWhenTheCurrentResultIsZero() {
        Double result = executorService.execute(Executor.Type.MUL,
                EnvironmentEntity.builder().curTotal(0).operandEntities(operandEntities).build());

        assertEquals(0.0, result);
    }

    @Test
    void subtraction() {
        Double result = executorService.execute(Executor.Type.SUB,
                EnvironmentEntity.builder().curTotal(0).operandEntities(operandEntities).build());

        assertEquals(-14.0, result);
    }

    @Test
    void empowermentByTheList() {
        operandEntities = Arrays
                .asList(OperandEntity.builder().number(7).build(),
                        OperandEntity.builder().number(2).build());
        Double result = executorService.execute(Executor.Type.POW,
                EnvironmentEntity.builder().curTotal(5).operandEntities(operandEntities).build());

        assertEquals(6103515625.0, result);
    }

    @Test
    void empowermentByZeroWillBeZeroForTheRest() {
        Double result = executorService.execute(Executor.Type.POW,
                EnvironmentEntity.builder().curTotal(0).operandEntities(operandEntities).build());

        assertEquals(0.0, result);
    }

    @Test
    void division() {
        Double result = executorService.execute(Executor.Type.DIV,
                EnvironmentEntity.builder().curTotal(20).operandEntities(operandEntities).build());

        assertEquals(0.2857142857142857, result);
    }

    @Test
    void divisionByZero() {
        operandEntities = Collections.singletonList(OperandEntity.builder().number(0).build());

        Double result = executorService.execute(Executor.Type.DIV,
                EnvironmentEntity.builder().curTotal(20).operandEntities(operandEntities).build());

        assertEquals(Double.POSITIVE_INFINITY, result);
    }

    @Test
    void addition() {
        Double result = executorService.execute(Executor.Type.ADD,
                EnvironmentEntity.builder().curTotal(1).operandEntities(operandEntities).build());

        assertEquals(15.0, result);
    }
}