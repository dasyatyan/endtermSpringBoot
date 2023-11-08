package com.example.endterm.Strategy;

import java.math.BigDecimal;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal applyDiscount(BigDecimal amount) {
        return amount;
    }
}