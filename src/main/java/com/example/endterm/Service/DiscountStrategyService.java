package com.example.endterm.Service;

import com.example.endterm.Strategy.DiscountStrategy;
import com.example.endterm.Strategy.NoDiscountStrategy;
import com.example.endterm.Strategy.TenPercentDiscountStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DiscountStrategyService {
    public NoDiscountStrategy getNoDiscountStrategy() {
        return noDiscountStrategy;
    }

    public TenPercentDiscountStrategy getTenPercentDiscountStrategy() {
        return tenPercentDiscountStrategy;
    }

    private final NoDiscountStrategy noDiscountStrategy = new NoDiscountStrategy();
    private final TenPercentDiscountStrategy tenPercentDiscountStrategy = new TenPercentDiscountStrategy();

    public DiscountStrategy getDiscountStrategy(BigDecimal totalCost) {
        if (totalCost.compareTo(new BigDecimal("100")) > 0) {
            return tenPercentDiscountStrategy;
        } else {
            return noDiscountStrategy;
        }
    }
}