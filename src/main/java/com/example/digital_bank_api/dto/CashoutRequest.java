package com.example.digital_bank_api.dto;

import java.math.BigDecimal;

public class CashoutRequest {
    private BigDecimal value;

    public CashoutRequest() {}

    public CashoutRequest(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() { return value; }
    public void setValue(BigDecimal value) { this.value = value; }
}