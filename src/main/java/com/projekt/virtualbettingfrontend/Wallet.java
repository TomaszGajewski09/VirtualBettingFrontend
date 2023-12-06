package com.projekt.virtualbettingfrontend;

import lombok.Getter;

@Getter
public class Wallet {
    private double balance;

    public Wallet(double balance) {
        this.balance = balance;
    }

}

