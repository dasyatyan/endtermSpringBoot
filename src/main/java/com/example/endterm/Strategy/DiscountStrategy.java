package com.example.endterm.Strategy;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal applyDiscount(BigDecimal amount);
}