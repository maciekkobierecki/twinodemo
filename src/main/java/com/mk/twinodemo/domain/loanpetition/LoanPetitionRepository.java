package com.mk.twinodemo.domain.loanpetition;

import com.mk.twinodemo.domain.loan.model.LoanPetition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanPetitionRepository extends JpaRepository<LoanPetition, Long> {
    List<LoanPetition> findAllByIpAddress(String ipAddress);
}
