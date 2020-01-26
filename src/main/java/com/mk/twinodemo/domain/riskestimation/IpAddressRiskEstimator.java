package com.mk.twinodemo.domain.riskestimation;

import com.mk.twinodemo.domain.loan.dto.CreateLoanDto;
import com.mk.twinodemo.domain.loan.model.LoanPetition;
import com.mk.twinodemo.domain.loanpetition.LoanPetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
class IpAddressRiskEstimator implements RiskEstimator {
    private final LoanPetitionService loanPetitionService;

    @Override
    public void checkRisk(CreateLoanDto dto) {
        validateRequest(dto);

        List<LoanPetition> petitions = loanPetitionService.getPetitionsByIpAddress(dto.getIpAddress());

        if (isNull(petitions) || petitions.size() == 3) {
            throw new RiskEstimationException("This is third client petition.");
        }
    }

    private void validateRequest(CreateLoanDto dto) {
        if (isNull(dto)) {
            throw new RiskEstimationException("CreateLoanDto cannot be null.");
        }
        if (isNull(dto.getIpAddress())) {
            throw new RiskEstimationException("Ip address cannot be null.");
        }
    }
}
