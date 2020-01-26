package com.mk.twinodemo.domain.riskestimation

import com.mk.twinodemo.domain.loan.dto.CreateLoanDto
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.Clock
import java.time.OffsetDateTime
import java.time.ZoneOffset

class MaxLoanAmountRiskEstimatorTest extends Specification {
    @Subject
    MaxLoanAmountRiskEstimator estimator

    Clock clock

    def 'checkRisk - should throw if amount have max value and time is in range 0:00 - 6:00'() {
        given:
        def input = new CreateLoanDto(
                OffsetDateTime.of(2020, 2, 1, 4, 0, 0, 0, ZoneOffset.UTC),
                MaxLoanAmountRiskEstimator.MAX_LOAN_AMOUNT,
                "some ip"
        )

        clock = Clock.fixed(OffsetDateTime.of(2020, 1, 1, 4, 0, 0, 0, ZoneOffset.UTC).toInstant(), ZoneOffset.UTC)
        estimator = new MaxLoanAmountRiskEstimator(clock)

        when:
        estimator.checkRisk(input)

        then:
        thrown(RiskEstimationException)
    }

    def 'checkRisk - should throw if amount is bigger than max value (regardless of the time)'() {
        given:
        def input = new CreateLoanDto(
                OffsetDateTime.of(2020, 2, 1, 4, 0, 0, 0, ZoneOffset.UTC),
                MaxLoanAmountRiskEstimator.MAX_LOAN_AMOUNT.add(BigDecimal.ONE),
                "some ip"
        )

        estimator = new MaxLoanAmountRiskEstimator(clock)

        when:
        estimator.checkRisk(input)

        then:
        thrown(RiskEstimationException)

        where:
        clock << [
                Clock.fixed(OffsetDateTime.of(2020, 1, 1, 15, 0, 0, 0, ZoneOffset.UTC).toInstant(), ZoneOffset.UTC),
                Clock.fixed(OffsetDateTime.of(2020, 1, 1, 4, 0, 0, 0, ZoneOffset.UTC).toInstant(), ZoneOffset.UTC)
        ]
    }

    @Unroll
    def "checkRisk - should not throw if amount iis less than max value (regardless of the time)"() {
        given:
        def input = new CreateLoanDto(
                OffsetDateTime.of(2020, 2, 1, 4, 0, 0, 0, ZoneOffset.UTC),
                MaxLoanAmountRiskEstimator.MAX_LOAN_AMOUNT.subtract(BigDecimal.ONE),
                "some ip"
        )

        estimator = new MaxLoanAmountRiskEstimator(clock)

        when:
        estimator.checkRisk(input)

        then:
        noExceptionThrown()

        where:
        clock << [
                Clock.fixed(OffsetDateTime.of(2020, 1, 1, 15, 0, 0, 0, ZoneOffset.UTC).toInstant(), ZoneOffset.UTC),
                Clock.fixed(OffsetDateTime.of(2020, 1, 1, 4, 0, 0, 0, ZoneOffset.UTC).toInstant(), ZoneOffset.UTC)
        ]
    }

    def "checkRisk - should not throw if amount have max value and time is out of range 0:00-6:00"() {
        given:
        def input = new CreateLoanDto(
                OffsetDateTime.of(2020, 2, 1, 4, 0, 0, 0, ZoneOffset.UTC),
                MaxLoanAmountRiskEstimator.MAX_LOAN_AMOUNT.subtract(BigDecimal.ONE),
                "some ip"
        )

        clock = Clock.fixed(OffsetDateTime.of(2020, 1, 1, 15, 0, 0, 0, ZoneOffset.UTC).toInstant(), ZoneOffset.UTC)
        estimator = new MaxLoanAmountRiskEstimator(clock)

        when:
        estimator.checkRisk(input)

        then:
        noExceptionThrown()
    }
}
