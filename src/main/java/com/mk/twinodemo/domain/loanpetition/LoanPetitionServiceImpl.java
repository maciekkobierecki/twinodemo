package com.mk.twinodemo.domain.loanpetition;

import com.mk.twinodemo.domain.loan.model.LoanPetition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanPetitionServiceImpl implements LoanPetitionService {
    private final LoanPetitionRepository petitionRepository;
    private final Clock clock;

    @Override
    public void rememberPetition(String ipAddress) {
        var petition = new LoanPetition();

        petition.setCreateDate(OffsetDateTime.now(clock));
        petition.setIpAddress(ipAddress);

        petitionRepository.save(petition);
    }

    @Override
    public List<LoanPetition> getPetitionsByIpAddress(String ipAddress) {
        return petitionRepository.findAllByIpAddress(ipAddress);
    }
}
