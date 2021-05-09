package com.appgate.calculator.repository;

import com.appgate.calculator.domain.OperandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperandRepository extends JpaRepository<OperandEntity, Long> {
    @Query("select oe from OperandEntity oe join oe.env en  where en.id = ?1 and oe.isApplied = 0")
    List<OperandEntity> findByOperandsByIsAppliedDisable(Long id);
}


