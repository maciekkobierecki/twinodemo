package com.mk.twinodemo.domain.riskestimation;

import com.mk.twinodemo.domain.loan.dto.CreateLoanDto;

public interface RiskEstimator {
    void checkRisk(CreateLoanDto request);
}
