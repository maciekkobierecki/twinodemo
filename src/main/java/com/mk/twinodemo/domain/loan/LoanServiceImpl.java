package com.mk.twinodemo.domain.loan;

import com.mk.twinodemo.domain.loan.dto.CreateLoanDto;
import com.mk.twinodemo.domain.loan.dto.LoanDto;
import com.mk.twinodemo.domain.loan.dto.UpdateLoanDto;
import com.mk.twinodemo.domain.loan.model.LoanProlong;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class LoanServiceImpl implements LoanService {
    @Override
    public List<LoanDto> findLoans() {
        return null;
    }

    @Override
    public LoanDto findLoan(Long id) {
        return null;
    }

    @Override
    public LoanDto createLoan(CreateLoanDto dto) {
        return null;
    }

    @Override
    public LoanDto updateLoan(UpdateLoanDto dto) {
        return null;
    }

    @Override
    public LoanProlong prolongLoan(Long id) {
        return null;
    }

    @Override
    public void completeLoan(Long id) {

    }
}
