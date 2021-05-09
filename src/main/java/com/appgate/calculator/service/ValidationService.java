package com.appgate.calculator.service;

import com.appgate.calculator.dto.Dto;

import javax.validation.ValidationException;

public interface ValidationService {
    <T extends Dto> void  validate(T dto) throws ValidationException;
}
