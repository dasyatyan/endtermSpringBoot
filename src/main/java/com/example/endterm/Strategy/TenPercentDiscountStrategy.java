package com.example.endterm.Strategy;

import com.example.endterm.Strategy.DiscountStrategy;

import java.math.BigDecimal;

public class TenPercentDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal applyDiscount(BigDecimal amount) {
        return amount.multiply(new BigDecimal("0.90")); // 10% discount
    }
}