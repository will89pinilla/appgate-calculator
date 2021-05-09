package com.appgate.calculator.service;

import com.appgate.calculator.domain.EnvironmentEntity;
import com.appgate.calculator.domain.OperandEntity;
import com.appgate.calculator.dto.Operand;
import com.appgate.calculator.repository.EnvironmentRepository;
import com.appgate.calculator.repository.OperandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class CalculatorServiceImplTest {

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
    public void addOperand() {
        //GIVEN
        EnvironmentEntity env = EnvironmentEntity.builder().id(1L).curTotal(0).build();
        OperandEntity operandEntity = OperandEntity.builder().env(env).number(5L).build();

        given(environmentRepository.findById(any(Long.class))).willReturn(Optional.of(env));
        given(operandRepository.save(any(OperandEntity.class))).willReturn(operandEntity);

        //WHEN
        String response = calculatorService.addOperand(Operand.builder().id(1L).operand(5L).build());

        //THEN
        assertEquals(response, "OK");
        verify(operandRepository).save(any(OperandEntity.class));

    }

}