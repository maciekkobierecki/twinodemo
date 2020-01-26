package com.mk.twinodemo.domain.loan;

import com.mk.twinodemo.domain.loan.dto.CreateLoanDto;
import com.mk.twinodemo.domain.loan.model.Loan;

import java.util.List;

public interface LoanService {
    List<Loan> getLoans();

    Loan getLoan(Long id);

    Loan createLoan(CreateLoanDto dto);

    Loan prolongLoan(Long id);
}
