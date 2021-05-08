package com.appgate.calculator.repository;

import com.appgate.calculator.domain.EnviromentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvironmentRepository extends JpaRepository<EnviromentEntity, Long> {
}


