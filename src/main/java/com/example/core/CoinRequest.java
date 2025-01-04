package com.example.core;

import java.util.List;

public class CoinRequest {
    private double targetAmount;
    private List<Double> value;

    public double getTargetAmount() {
        return targetAmount;
    }

    public List<Double> getValue() {
        return value;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public void setValue(List<Double> value) {
        this.value = value;
    }
}
