package com.mk.twinodemo.domain.loan

import com.mk.twinodemo.domain.loan.dto.CreateLoanDto
import com.mk.twinodemo.domain.loan.model.Loan
import com.mk.twinodemo.domain.riskestimation.RiskEstimationException
import com.mk.twinodemo.domain.riskestimation.RiskEstimator
import spock.lang.Specification
import spock.lang.Subject

import java.time.Clock
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset

class LoanServiceImplTest extends Specification {
    @Subject
    LoanServiceImpl loanService

    LoanRepository loanRepository = Mock(LoanRepository)
    Clock clock
    RiskEstimator riskEstimator1 = Mock(RiskEstimator)
    RiskEstimator riskEstimator2 = Mock(RiskEstimator)

    def setup() {
        clock = Clock.fixed(Instant.ofEpochMilli(1561630353000), ZoneOffset.UTC)

        loanService = new LoanServiceImpl(
                List.of(riskEstimator1, riskEstimator2),
                loanRepository,
                clock)
    }

    def 'createLoan - should create loan and save'() {
        given:
        def loanEndDate = OffsetDateTime.of(2020, 1, 1, 1, 0, 0, 0, ZoneOffset.UTC)
        def input = new CreateLoanDto(
                loanEndDate,
                BigDecimal.TEN,
                "some ip"
        )

        when:
        loanService.createLoan(input)

        then:
        1 * loanRepository.save({
            it.amount == BigDecimal.TEN
            it.endDate == loanEndDate
            it.startDate == OffsetDateTime.now(clock)
        })
    }

    def 'createLoan - should return created loan'() {
        given:
        def loanEndDate = OffsetDateTime.of(2020, 1, 1, 1, 0, 0, 0, ZoneOffset.UTC)
        def input = new CreateLoanDto(
                loanEndDate,
                BigDecimal.TEN,
                "some ip"
        )

        loanRepository.save(_ as Loan) >> new Loan(
                amount: BigDecimal.TEN,
                endDate: loanEndDate,
                startDate: OffsetDateTime.now(clock)
        )

        when:
        def result = loanService.createLoan(input)

        then:
        result.amount == BigDecimal.TEN
        result.startDate == OffsetDateTime.now(clock)
        result.endDate == loanEndDate
    }

    def 'createLoan - should validate risk by calling every risk estimators'() {
        given:
        def input = new CreateLoanDto(
                OffsetDateTime.now(),
                BigDecimal.TEN,
                "some ip"
        )

        when:
        loanService.createLoan(input)

        then:
        1 * riskEstimator1.checkRisk(input)
        1 * riskEstimator2.checkRisk(input)
    }

    def 'createLoan - should not create loan if any of riskEstimators threw exception'() {
        given:
        def input = new CreateLoanDto(
                OffsetDateTime.now(),
                BigDecimal.TEN,
                "some ip"
        )

        riskEstimator1.checkRisk(input) >> { throw new RiskEstimationException("some meaningful info") }
        when:
        loanService.createLoan(input)

        then:
        0 * loanRepository.save(_ as Loan)
        thrown(RiskEstimationException)
    }
}
