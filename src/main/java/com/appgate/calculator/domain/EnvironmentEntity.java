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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "ENVIRONMENTS")
public class EnvironmentEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "ENV_ID")
    private final Long id;

    @Column(name = "CUR_TOTAL")
    private final double curTotal;

    @OneToMany(mappedBy="env", cascade = CascadeType.ALL)
    private List<OperandEntity> operandEntities;

    // Empty constructor for JSON/JPA
    public EnvironmentEntity() {
        this.id = null;
        this.curTotal = 0L;
    }

    public EnvironmentEntity(Long id, double curTotal, List<OperandEntity> operandEntities) {
        this.id = id;
        this.curTotal = curTotal;
        this.operandEntities = operandEntities;
    }
}
