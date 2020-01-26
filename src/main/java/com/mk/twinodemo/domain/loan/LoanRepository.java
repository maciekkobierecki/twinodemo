package com.mk.twinodemo.domain.loan;

import com.mk.twinodemo.domain.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
