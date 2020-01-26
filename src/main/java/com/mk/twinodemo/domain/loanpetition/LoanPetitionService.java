package com.mk.twinodemo.domain.loanpetition;

import com.mk.twinodemo.domain.loan.model.LoanPetition;

import java.util.List;

public interface LoanPetitionService {
    void rememberPetition(String ipAddress);

    List<LoanPetition> getPetitionsByIpAddress(String ipAddress);

}
