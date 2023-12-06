package com.projekt.virtualbettingfrontend;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FakeDataService {

    public static UserProfile getUserProfile() {
        return new UserProfile(
                "Jan",
                "Kowalski",
                "0223649401",
                LocalDate.of(1990,5,15),
                "Poland",
                "Warsaw",
                "jankowalski12905@gmail.com",
                "12345667890"
        );
    }

    public static Wallet getWalletData() {
        return new Wallet(250.75);
    }

    public static CurrencyRates getCurrencyRates() {
        Random random = new Random();
        return new CurrencyRates(random.nextDouble() * 4, random.nextDouble() * 4);
    }

    public static List<String> getAvailableLeagues() {
        return Arrays.asList("Non League Premier", "Ghańska Premier League");
    }

    public static List<Match> getMatchesByLeague(String league) {
        return Arrays.asList(
                new Match(1, "Non League Premier" ,"Team A", "Team B", LocalDate.now(), 1.5, 2.0, 2.5),
                new Match(2, "Non League Premier", "Team C", "Team D", LocalDate.now().plusDays(2), 1.8, 1.9, 3.0)
        );
    }

    public static List<Match> getMatches() {
        return Arrays.asList(
                new Match(1, "Non League Premier" ,"Team A", "Team B", LocalDate.now(), 1.5, 2.0, 2.5),
                new Match(2, "Non League Premier", "Team C", "Team D", LocalDate.now().plusDays(2), 1.8, 1.9, 3.0),
                new Match(3, "Ghańska Premier League", "Team A", "Team D", LocalDate.now().plusDays(3), 1.8, 1.9, 3.0),
                new Match(4, "Ghańska Premier League", "Team B", "Team C", LocalDate.now().plusDays(4), 1.8, 1.9, 3.0)
        );
    }

    public static Match getMatchById(int id) {
        return getMatches().stream()
                .filter(match -> match.getMatchId() == id)
                .findFirst()
                .orElse(null);
    }

    public static List<Match> getMatchesByFilters(String league, LocalDate startDate, LocalDate endDate) {
        return getMatches().stream()
                .filter(match -> (league == null || match.getLeague().equals(league)) &&
                        (startDate == null || !match.getMatchTime().isBefore(startDate)) &&
                        (endDate == null || !match.getMatchTime().isAfter(endDate)))
                .collect(Collectors.toList());
    }
}

