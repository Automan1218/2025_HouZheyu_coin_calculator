package com.example.core;

import java.util.List;

public class CoinRequest {
    private double targetAmount;
    private List<Object> value; // 修改为 List<Object> 以兼容字符串和数字

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public List<Object> getValue() {
        return value;
    }

    public void setValue(List<Object> value) {
        this.value = value;
    }
}
