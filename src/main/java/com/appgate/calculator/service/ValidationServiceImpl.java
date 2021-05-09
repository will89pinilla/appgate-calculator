package com.appgate.calculator.service;

import com.appgate.calculator.dto.Dto;
import com.appgate.calculator.exception.ValidationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;

import javax.validation.Validator;
import java.util.Set;

@Service
public class ValidationServiceImpl implements ValidationService {

    private final Validator validator;

    ValidationServiceImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <T extends Dto> void validate(T dto) {
        Set<ConstraintViolation<Dto>> violations = validator.validate(dto);
        for (ConstraintViolation<Dto> violation : violations) {
            throw new ValidationException(violation.getMessage());
        }
    }
}
