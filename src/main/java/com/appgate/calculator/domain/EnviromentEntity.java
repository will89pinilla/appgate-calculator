package com.appgate.calculator.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "ENVIRONMENTS")
public class EnviromentEntity {

    @Id
    @GeneratedValue
    @Column(name = "ENV_ID")
    private final Long id;

    @Column(name = "CUR_TOTAL")
    private final Long curTotal;

    // Empty constructor for JSON/JPA
    public EnviromentEntity() {
        this.id = null;
        this.curTotal = 0L;
    }
}
