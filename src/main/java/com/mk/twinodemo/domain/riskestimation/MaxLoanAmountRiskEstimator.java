package com.mk.twinodemo.domain.riskestimation;

import com.mk.twinodemo.domain.loan.dto.CreateLoanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
class MaxLoanAmountRiskEstimator implements RiskEstimator {
    public static final BigDecimal MAX_LOAN_AMOUNT = new BigDecimal(1000);
    private static final OffsetTime RISKY_TIME_START = OffsetTime.of(LocalTime.of(0, 0), ZoneOffset.UTC);
    private static final OffsetTime RISKY_TIME_END = OffsetTime.of(LocalTime.of(6, 0), ZoneOffset.UTC);

    private final Clock clock;

    @Override
    public void checkRisk(CreateLoanDto dto) {
        if (amountLessThanMax(dto)) {
            return;
        }

        if (amountMoreThanMax(dto)) {
            throw new RiskEstimationException("Cannot grant a loan with amount bigger than max.");
        }

        var now = OffsetTime.now(clock);

        if (amountEqualsMax(dto) && now.isAfter(RISKY_TIME_START) && now.isBefore(RISKY_TIME_END)) {
            throw new RiskEstimationException("Cannot grant a loan at time between 0:00 and 6:00 with max amount.");
        }
    }

    private boolean amountLessThanMax(CreateLoanDto dto) {
        return MAX_LOAN_AMOUNT.compareTo(dto.getAmount()) > 0;
    }

    private boolean amountMoreThanMax(CreateLoanDto dto) {
        return MAX_LOAN_AMOUNT.compareTo(dto.getAmount()) < 0;
    }

    private boolean amountEqualsMax(CreateLoanDto dto) {
        return MAX_LOAN_AMOUNT.compareTo(dto.getAmount()) == 0;
    }
}
