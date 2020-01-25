package com.mk.twinodemo.domain.riskestimation;

import com.mk.twinodemo.domain.loan.dto.CreateLoanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class IpAddressRiskEstimator implements RiskEstimator {
    @Override
    public void checkRisk(CreateLoanDto request) {

    }
}
