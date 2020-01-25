package com.mk.twinodemo.domain.loan.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateLoanDto {
    private Long loanId;
    private BigDecimal amount;
}
