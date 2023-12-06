package com.projekt.virtualbettingfrontend;

import lombok.Getter;

@Getter
public class BetSelection {
    private Match match;
    private String outcome;
    private double odds;

    public BetSelection(Match match, String outcome, double odds) {
        this.match = match;
        this.outcome = outcome;
        this.odds = odds;
    }
}
