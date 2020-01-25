package com.mk.twinodemo.domain.loan;

import com.mk.twinodemo.domain.loan.dto.CreateLoanDto;
import com.mk.twinodemo.domain.loan.dto.LoanDto;
import com.mk.twinodemo.domain.loan.dto.UpdateLoanDto;
import com.mk.twinodemo.domain.loan.model.LoanProlong;

import java.util.List;

public interface LoanService {
    List<LoanDto> findLoans();

    LoanDto findLoan(Long id);

    LoanDto createLoan(CreateLoanDto dto);

    LoanDto updateLoan(UpdateLoanDto dto);

    LoanProlong prolongLoan(Long id);

    void completeLoan(Long id);
}
