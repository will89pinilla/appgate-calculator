package com.appgate.calculator.service;

import com.appgate.calculator.domain.EnvironmentEntity;
import com.appgate.calculator.domain.OperandEntity;
import org.springframework.stereotype.Service;

import java.util.function.BinaryOperator;

@Service
public class ExecutorService {

    BinaryOperator<Double> biSub = (x,y) -> x - y;
    BinaryOperator<Double> biDiv = (x,y) -> x / y;
    BinaryOperator<Double> biMul = (x,y) -> x * y;

    protected Double applyBinaryOperation(EnvironmentEntity env,
                                        BinaryOperator<Double> binaryOperator ) {
        return env.getOperandEntities().stream()
                .map(OperandEntity::getNumber)
                .reduce(env.getCurTotal(), binaryOperator);
    }

    protected Double multiplication(EnvironmentEntity env){
        return applyBinaryOperation(env, biMul);
    }

    protected Double subtraction(EnvironmentEntity env) { return applyBinaryOperation(env, biSub); }

    protected Double empowerment(EnvironmentEntity env) { return applyBinaryOperation(env, Math::pow); }

    protected Double division(EnvironmentEntity env){
        return applyBinaryOperation(env, biDiv);
    }

    protected Double addition(EnvironmentEntity env){ return applyBinaryOperation(env, Double::sum); }

    protected Double noValid(EnvironmentEntity env){ return (double) -1; }

    public Double execute(Executor.Type type, EnvironmentEntity env){
        return type.calculation.apply(this, env);
    }
}
