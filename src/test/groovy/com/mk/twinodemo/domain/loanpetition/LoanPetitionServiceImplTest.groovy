package com.mk.twinodemo.domain.loanpetition


import spock.lang.Specification
import spock.lang.Subject

import java.time.Clock
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset

class LoanPetitionServiceImplTest extends Specification {
    @Subject
    LoanPetitionService loanPetitionService

    LoanPetitionRepository repository = Mock(LoanPetitionRepository)
    Clock clock

    def setup() {
        clock = Clock.fixed(Instant.ofEpochMilli(1561630353000), ZoneOffset.UTC)

        loanPetitionService = new LoanPetitionServiceImpl(repository, clock)
    }

    def 'createPetition - should save loan petition'() {
        given:
        String input = "some ip address"

        when:
        loanPetitionService.rememberPetition(input)

        then:
        1 * repository.save({
            it.createDate == OffsetDateTime.now(clock)
            it.ipAddress == input
        })
    }
}
