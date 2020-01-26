package com.mk.twinodemo.boundary.loan.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class CreateLoanRequest {
    @NotNull
    private OffsetDateTime endDate;
    @NotNull
    private BigDecimal amount;
}
