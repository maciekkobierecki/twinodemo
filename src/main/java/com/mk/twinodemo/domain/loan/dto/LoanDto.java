package com.mk.twinodemo.domain.loan.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class LoanDto {
    private OffsetDateTime loanDate;
    private BigDecimal amount;
}
