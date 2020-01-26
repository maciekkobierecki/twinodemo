package com.mk.twinodemo.domain.riskestimation

import com.mk.twinodemo.domain.loan.dto.CreateLoanDto
import com.mk.twinodemo.domain.loan.model.LoanPetition
import com.mk.twinodemo.domain.loanpetition.LoanPetitionService
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.OffsetDateTime

class IpAddressRiskEstimatorTest extends Specification {
    @Subject
    IpAddressRiskEstimator ipAddressRiskEstimator

    LoanPetitionService loanPetitionService

    def setup() {
        loanPetitionService = Mock(LoanPetitionService)

        ipAddressRiskEstimator = new IpAddressRiskEstimator(loanPetitionService)
    }

    @Unroll
    def 'checkRisk - should not throw if number of petitions from the same ip address is other than 3'() {
        given:
        loanPetitionService.getPetitionsByIpAddress("some ip") >> petitions

        def input = new CreateLoanDto(
                OffsetDateTime.now(),
                BigDecimal.TEN,
                "some ip"
        )

        when:
        ipAddressRiskEstimator.checkRisk(input)

        then:
        noExceptionThrown()

        where:
        petitions << [
                List.of(),
                List.of(
                        someLoanPetition(ipAddress: "some ip"),
                        someLoanPetition(ipAddress: "some ip")
                ),
                List.of(
                        someLoanPetition(ipAddress: "some ip"),
                        someLoanPetition(ipAddress: "some ip"),
                        someLoanPetition(ipAddress: "some ip"),
                        someLoanPetition(ipAddress: "some ip"),
                        someLoanPetition(ipAddress: "some ip"),
                        someLoanPetition(ipAddress: "some ip"),
                        someLoanPetition(ipAddress: "some ip")
                )
        ]
    }

    def 'checkRisk - should throw RiskEstimationException if number of petitions from the same ip address is 3'() {
        given:
        loanPetitionService.getPetitionsByIpAddress("some ip") >> List.of(
                someLoanPetition(ipAddress: "some ip"),
                someLoanPetition(ipAddress: "some ip"),
                someLoanPetition(ipAddress: "some ip")
        )

        def input = new CreateLoanDto(
                OffsetDateTime.now(),
                BigDecimal.TEN,
                "some ip"
        )

        when:
        ipAddressRiskEstimator.checkRisk(input)

        then:
        thrown(RiskEstimationException)
    }

    def 'checkRisk - should throw RiskEstimationException if loanPetitionService returned null'() {
        given:
        loanPetitionService.getPetitionsByIpAddress("some ip") >> null

        def input = new CreateLoanDto(
                OffsetDateTime.now(),
                BigDecimal.TEN,
                "some ip"
        )

        when:
        ipAddressRiskEstimator.checkRisk(input)

        then:
        thrown(RiskEstimationException)
    }

    def 'checkRisk - should throw RiskEstimationException if passed null'() {
        given:
        def input = null

        when:
        ipAddressRiskEstimator.checkRisk(input)

        then:
        thrown(RiskEstimationException)
    }

    def 'checkRisk - should throw RiskEstimationException if passed null ipAddress'() {
        given:
        def input = new CreateLoanDto(
                OffsetDateTime.now(),
                BigDecimal.TEN,
                null
        )

        when:
        ipAddressRiskEstimator.checkRisk(input)

        then:
        thrown(RiskEstimationException)
    }

    def someLoanPetition(Map params = [:]) {
        new LoanPetition(
                id: params.getOrDefault('id', 1L),
                createDate: params.getOrDefault('createDate', OffsetDateTime.now()),
                ipAddress: params.getOrDefault('ipAddress', "127.0.0.1")
        )
    }
}
