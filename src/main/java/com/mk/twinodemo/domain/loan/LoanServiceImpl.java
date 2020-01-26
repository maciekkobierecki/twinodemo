package com.mk.twinodemo.domain.loan;

import com.mk.twinodemo.domain.loan.dto.CreateLoanDto;
import com.mk.twinodemo.domain.loan.exception.LoanNotFoundException;
import com.mk.twinodemo.domain.loan.exception.ProlongProhibitedException;
import com.mk.twinodemo.domain.loan.model.Loan;
import com.mk.twinodemo.domain.loan.model.LoanProlong;
import com.mk.twinodemo.domain.riskestimation.RiskEstimator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
class LoanServiceImpl implements LoanService {
    private static final Integer PROLONG_PERIOD_IN_DAYS = 14;
    private final List<RiskEstimator> riskEstimators;
    private final LoanRepository loanRepository;
    private final Clock clock;

    @Override
    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan getLoan(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(LoanNotFoundException::new);
    }

    @Override
    public Loan createLoan(CreateLoanDto dto) {
        checkRisk(dto);

        Loan loan = new Loan();
        loan.setAmount(dto.getAmount());
        loan.setEndDate(dto.getEndDate());
        loan.setStartDate(OffsetDateTime.now(clock));
        return loanRepository.save(loan);
    }

    private void checkRisk(CreateLoanDto dto) {
        for (RiskEstimator riskEstimator : riskEstimators) {
            riskEstimator.checkRisk(dto);
        }
    }

    @Override
    public Loan prolongLoan(Long id) {
        var loan = loanRepository.findById(id)
                .orElseThrow(LoanNotFoundException::new);
        checkIfProlongPermitted(loan);

        var newLoanEndDate = loan.getEndDate().plusDays(PROLONG_PERIOD_IN_DAYS);

        var prolong = new LoanProlong();
        prolong.setProlongPeriod(PROLONG_PERIOD_IN_DAYS);
        prolong.setNewLoanEndDate(newLoanEndDate);
        prolong.setOldLoanEndDate(loan.getEndDate());

        loan.setEndDate(newLoanEndDate);
        loan.prolong(prolong);

        return loanRepository.save(loan);
    }

    private void checkIfProlongPermitted(Loan loan) {
        if (loan.getProlongsCount() > 0) {
            throw new ProlongProhibitedException();
        }
    }
}
