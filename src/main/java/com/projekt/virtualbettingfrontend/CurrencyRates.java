package com.projekt.virtualbettingfrontend;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRates {
    private double usdToEurRate;
    private double gbpToEurRate;

    public CurrencyRates(double usdToEurRate, double gbpToEurRate) {
        this.usdToEurRate = usdToEurRate;
        this.gbpToEurRate = gbpToEurRate;
    }

}

