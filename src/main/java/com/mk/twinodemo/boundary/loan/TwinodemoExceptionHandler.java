package com.mk.twinodemo.boundary.loan;

import com.mk.twinodemo.domain.riskestimation.RiskEstimationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TwinodemoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RiskEstimationException.class)
    public ResponseEntity<Object> handle(RiskEstimationException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
