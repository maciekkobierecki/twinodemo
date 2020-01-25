package com.mk.twinodemo.domain.loan.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class CreateLoanDto {
    @NotNull
    private OffsetDateTime endDate;
    @NotNull
    private BigDecimal amount;
}
