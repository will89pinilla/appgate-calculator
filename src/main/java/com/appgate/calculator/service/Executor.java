package com.appgate.calculator.service;

import com.appgate.calculator.domain.EnvironmentEntity;
import com.appgate.calculator.dto.Operation;

import java.util.function.BiFunction;

public final class Executor {

    enum Type {
        ADD(ExecutorService::addition),
        SUB(ExecutorService::subtraction),
        MUL(ExecutorService::multiplication),
        DIV(ExecutorService::division),
        POW(ExecutorService::empowerment),
        NO_VALID(ExecutorService::noValid);

        public final BiFunction<ExecutorService, EnvironmentEntity, Double> calculation;

        Type(BiFunction<ExecutorService, EnvironmentEntity, Double> calculation) {
            this.calculation = calculation;
        }
    }

    private final Type type;

    public Executor (Type type) {
        this.type = type;
    }

    public static Type of(Operation operation) {

        for(Type typ : Type.values()){
            if(typ.name().equals(operation.name()))
                return typ;
        }
        return Type.NO_VALID;

    }
}
