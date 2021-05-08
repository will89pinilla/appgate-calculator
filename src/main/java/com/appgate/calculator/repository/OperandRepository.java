package com.appgate.calculator.repository;

import com.appgate.calculator.domain.OperandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperandRepository extends JpaRepository<OperandEntity, Long> {}


