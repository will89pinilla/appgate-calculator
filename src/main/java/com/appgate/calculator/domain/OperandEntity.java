package com.appgate.calculator.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@ToString
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "OPERANDS")
public class OperandEntity {

    @Id
    @GeneratedValue
    @Column(name = "OPE_ID")
    private final Long id;

    @Column(name = "NUMBER")
    private final double number;

    @Column(name = "IS_APPLIED", columnDefinition = "integer default 0")
    private final int isApplied;

    @Column(name = "OPERATION")
    private final String operation;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ENV_ID")
    private final EnvironmentEntity env;

    public OperandEntity() {
        id = null;
        number = 0L;
        env = null;
        isApplied = 0;
        operation = null;
    }
    public OperandEntity(Long id, double number, int isApplied, String operation, EnvironmentEntity env) {
        this.id = id;
        this.number = number;
        this.isApplied = isApplied;
        this.env = env;
        this.operation = operation;
    }
}
