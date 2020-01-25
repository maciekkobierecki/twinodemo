package com.mk.twinodemo.domain.riskestimation;

public class RiskEstimationException extends RuntimeException{
    public RiskEstimationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
