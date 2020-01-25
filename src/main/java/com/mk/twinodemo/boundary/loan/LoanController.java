package com.mk.twinodemo.boundary.loan;

import com.mk.twinodemo.domain.loan.LoanService;
import com.mk.twinodemo.domain.loan.dto.CreateLoanDto;
import com.mk.twinodemo.domain.loan.dto.LoanDto;
import com.mk.twinodemo.domain.loan.dto.UpdateLoanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @GetMapping
    public List<LoanDto> getLoans() {
        return loanService.findLoans();
    }

    @GetMapping("/{id}")
    public LoanDto getLoan(@PathVariable("id") Long id) {
        return loanService.findLoan(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public LoanDto createLoan(CreateLoanDto dto) {
        return loanService.createLoan(dto);
    }

    @PutMapping("/{id}")
    public void updateLoan(@RequestBody UpdateLoanDto dto) {
        loanService.updateLoan(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(Long id) {
        loanService.completeLoan(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/{id}/prolongs")
    public void createLoanProlong(@PathVariable("id") Long id) {
        loanService.prolongLoan(id);
    }
}
