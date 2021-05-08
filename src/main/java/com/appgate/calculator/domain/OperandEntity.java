package com.appgate.calculator.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@RequiredArgsConstructor
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
    private final Long number;

    @Column(name = "IS_APPLIED", columnDefinition = "boolean default false")
    private boolean isApplied;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ENV_ID")
    private final EnviromentEntity env;

    public OperandEntity() {
        id = null;
        number = null;
        env = null;
        isApplied = false;
    }
    public OperandEntity(Long id, Long number, boolean isApplied, EnviromentEntity env) {
        this.id = id;
        this.number = number;
        this.isApplied = isApplied;
        this.env = env;
    }
}
