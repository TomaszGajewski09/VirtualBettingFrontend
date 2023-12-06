package com.projekt.virtualbettingfrontend;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class Match {
    private int matchId;
    private String league;
    private String homeTeam;
    private String awayTeam;
    private LocalDate matchTime;
    private double oddsHome;
    private double oddsDraw;
    private double oddsAway;

    public Match() {
    }

    public Match(int matchId, String league, String homeTeam, String awayTeam, LocalDate matchTime, double oddsHome, double oddsDraw, double oddsAway) {
        this.matchId = matchId;
        this.league = league;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchTime = matchTime;
        this.oddsHome = oddsHome;
        this.oddsDraw = oddsDraw;
        this.oddsAway = oddsAway;
    }

}
