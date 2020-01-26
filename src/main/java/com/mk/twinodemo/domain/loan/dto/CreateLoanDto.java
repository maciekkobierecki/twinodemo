package com.mk.twinodemo.domain.loan.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
public class CreateLoanDto {
    private final OffsetDateTime endDate;
    private final BigDecimal amount;
    private final String ipAddress;
}
