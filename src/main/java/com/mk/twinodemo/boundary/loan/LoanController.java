package com.mk.twinodemo.boundary.loan;

import com.mk.twinodemo.boundary.loan.request.CreateLoanRequest;
import com.mk.twinodemo.domain.loan.LoanService;
import com.mk.twinodemo.domain.loan.dto.CreateLoanDto;
import com.mk.twinodemo.domain.loan.model.Loan;
import com.mk.twinodemo.domain.loanpetition.LoanPetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController("/api")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;
    private final LoanPetitionService loanPetitionService;

    @GetMapping("/loans")
    public List<Loan> getLoans() {
        return loanService.getLoans();
    }

    @GetMapping("/loans/{id}")
    public Loan getLoan(@PathVariable("id") Long id) {
        return loanService.getLoan(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/loans")
    public Loan createLoan(@RequestBody @Valid CreateLoanRequest request) {
        String ipAddress = getClientIpAddress();
        loanPetitionService.rememberPetition(ipAddress);

        CreateLoanDto dto = CreateLoanDto.builder()
                .amount(request.getAmount())
                .endDate(request.getEndDate())
                .ipAddress(ipAddress)
                .build();

        var loan = loanService.createLoan(dto);
        return loan;
    }

    private String getClientIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        return request.getRemoteAddr();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/loans/{id}/prolongs")
    public void createLoanProlong(@PathVariable("id") Long id) {
        loanService.prolongLoan(id);
    }
}
