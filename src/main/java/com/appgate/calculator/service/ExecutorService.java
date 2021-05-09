package com.appgate.calculator.service;

import com.appgate.calculator.domain.EnvironmentEntity;
import com.appgate.calculator.domain.OperandEntity;
import org.springframework.stereotype.Service;

import java.util.function.BinaryOperator;

@Service
public class ExecutorService {

    BinaryOperator<Long> biSub = (x,y) -> x - y;
    BinaryOperator<Long> biDiv = (x,y) -> x / y;
    BinaryOperator<Long> biMul = (x,y) -> x * y;
    BinaryOperator<Long> biPow = (x,y) -> x ^ y;

    protected Long applyBinaryOperation(EnvironmentEntity env,
                                        BinaryOperator<Long> binaryOperator ) {
        return env.getOperandEntities().stream()
                .map(OperandEntity::getNumber)
                .reduce(env.getCurTotal(), binaryOperator);
    }

    protected Long multiplication(EnvironmentEntity env){
        return applyBinaryOperation(env, biMul);
    }

    protected Long subtraction(EnvironmentEntity env){
        return applyBinaryOperation(env, biSub);
    }

    protected Long empowerment(EnvironmentEntity env){
        return applyBinaryOperation(env, biPow);
    }

    protected Long division(EnvironmentEntity env){
        return applyBinaryOperation(env, biDiv);
    }

    protected Long addition(EnvironmentEntity env){ return applyBinaryOperation(env, Long::sum); }

    protected Long noValid(EnvironmentEntity env){
        return -1L;
    }

    public Long execute(Executor.Type type, EnvironmentEntity env){
        return type.calculation.apply(this, env);
    }
}
