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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class CalculatorServiceImplTest {

    private final static String ERROR_MESSAGE = "Could not find the env 1";
    private static final String OK = "OK";
    private static final String MESSAGE_NOT_FOUND_OPERANDS = "Could not find operands to be applied on the env 1";


    @Mock
    private EnvironmentRepository environmentRepository;

    @Mock
    private OperandRepository operandRepository;

    @Mock
    private ExecutorService executorService;

    private CalculatorServiceImpl calculatorService;

    @BeforeEach
    public void setUp(){
        calculatorService = new CalculatorServiceImpl(environmentRepository, operandRepository, executorService);
    }

    @Test
    public void newEnvId(){
        //GIVEN
        EnvironmentEntity env = EnvironmentEntity.builder().id(1L).curTotal(0).build();
        given(environmentRepository.save(any(EnvironmentEntity.class))).willReturn(env);

        //WHEN
        Long id = calculatorService.newEnvId();

        //THEN
        assertEquals(env.getId(), id);
        verify(environmentRepository).save(any(EnvironmentEntity.class));
    }

    @Test
    public void whenItSavesTheOperand() {
        //GIVEN
        EnvironmentEntity env = EnvironmentEntity.builder().id(1L).curTotal(0).build();
        OperandEntity operandEntity = OperandEntity.builder().env(env).number(5.0).build();

        given(environmentRepository.findById(any(Long.class))).willReturn(Optional.of(env));
        given(operandRepository.save(any(OperandEntity.class))).willReturn(operandEntity);

        //WHEN
        String response = calculatorService.addOperand(Operand.builder().id(1L).operand(5.0).build());

        //THEN
        assertEquals(response, OK);
        verify(operandRepository).save(any(OperandEntity.class));

    }

    @Test
    public void whenItTriesToSaveButItCannotFoundTheEnv() {
        //GIVEN
        given(environmentRepository.findById(any(Long.class))).willThrow(new EnvNotFoundException(1L));

        //WHEN
        Exception exception = assertThrows(EnvNotFoundException.class, () -> {
            calculatorService.addOperand(Operand.builder().id(1L).operand(5.0).build());
        });

        //THEN
        assertEquals(exception.getMessage(), ERROR_MESSAGE);
        verify(environmentRepository).findById(any(Long.class));
    }


    @Test
    public void whenItExecutesOperate() {
        //GIVEN
        EnvironmentEntity env = EnvironmentEntity.builder().id(1L).curTotal(5).build();
        EnvironmentEntity updatedEnv = EnvironmentEntity.builder().id(1L).curTotal(7).build();

        List<OperandEntity> operandEntities = Collections.singletonList(
                OperandEntity.builder().id(1L).env(env).isApplied(0).number(2).build()
        );

        List<OperandEntity> updateOperands = Collections.singletonList(
                OperandEntity.builder().id(1L).env(env).isApplied(1).number(2).operation("ADD").build()
        );

        given(environmentRepository.findById(any(Long.class))).willReturn(Optional.of(env));
        given(operandRepository.findByOperandsByIsAppliedDisable(any(Long.class))).willReturn(operandEntities);
        given(operandRepository.save(any(OperandEntity.class))).willReturn(updateOperands.get(0));
        given(environmentRepository.save(any(EnvironmentEntity.class))).willReturn(updatedEnv);

        //WHEN
        Double result = calculatorService.operate(ArithmeticOperation.builder().operation(Operation.ADD).id(1L).build());

        //THEN
        verify(environmentRepository).findById(any(Long.class));
        verify(operandRepository).findByOperandsByIsAppliedDisable(any(Long.class));
        verify(environmentRepository).save(any(EnvironmentEntity.class));
        verify(operandRepository).save(any(OperandEntity.class));
        assertEquals(result, 7.0);
    }

    @Test
    public void whenItDoesntFindEnv() {
        //GIVEN
        EnvironmentEntity env = EnvironmentEntity.builder().id(1L).curTotal(5).build();
        given(environmentRepository.findById(any(Long.class))).willThrow(new EnvNotFoundException(1L));

        //WHEN
        Exception exception = assertThrows(EnvNotFoundException.class, () -> {
            calculatorService.operate(ArithmeticOperation.builder().operation(Operation.ADD).id(1L).build());
        });

        //THEN
        verify(environmentRepository).findById(any(Long.class));

        assertEquals(exception.getMessage(), ERROR_MESSAGE);

    }

    @Test
    public void whenItDoesntFindOperandsToBeApplied(){

        //GIVEN
        EnvironmentEntity env = EnvironmentEntity.builder().id(1L).curTotal(5).build();

        given(environmentRepository.findById(any(Long.class))).willReturn(Optional.of(env));
        given(operandRepository.findByOperandsByIsAppliedDisable(any(Long.class))).willReturn(new ArrayList<>());


        //WHEN
        Exception exception = assertThrows(OperandsNotFoundException.class, () -> {
            calculatorService.operate(ArithmeticOperation.builder().operation(Operation.ADD).id(1L).build());
        });

        //THEN
        verify(environmentRepository).findById(any(Long.class));
        verify(operandRepository).findByOperandsByIsAppliedDisable(any(Long.class));
        assertEquals(exception.getMessage(), MESSAGE_NOT_FOUND_OPERANDS);
    }
}