package com.example.core;

import java.util.*;

public class CoinService {
    public static List<Double> calculate(double targetAmount, List<Double> value){
        value.sort(Collections.reverseOrder());

        List<Double> result = new ArrayList<>();

        int targetCents = (int) (targetAmount * 100);

        for(double v : value){
            int Cents = (int) (v * 100);

            while(targetCents>=Cents){
                result.add(v);
                targetCents -= Cents;
            }
        }

        if(targetCents>0){
            throw new IllegalArgumentException("Cannot find the exact change");
        }
        Collections.sort(result);

        return result;
    }

}
