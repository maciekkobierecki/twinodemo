package com.mk.twinodemo.domain.loan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OffsetDateTime startDate;

    private OffsetDateTime endDate;

    private BigDecimal amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id")
    private List<LoanProlong> prolongs;

    public void prolong(LoanProlong prolong) {
        prolongs.add(prolong);
    }

    @JsonIgnore
    public Integer getProlongsCount() {
        return prolongs.size();
    }
}
