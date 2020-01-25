package com.mk.twinodemo.domain.loan.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
@Data
public class LoanProlong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int prolongPeriod;
    private OffsetDateTime oldLoanEndDate;
    private OffsetDateTime currentLoanEndDate;
}
