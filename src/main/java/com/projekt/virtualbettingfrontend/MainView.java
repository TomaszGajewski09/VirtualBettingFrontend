package com.projekt.virtualbettingfrontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Main View")
public class MainView extends VerticalLayout {


    //    private Grid<Match> matchesGrid = new Grid<>(Match.class);
    private Grid<Match> matchesGrid = new Grid<>();
    private List<BetSelection> betSelections = new ArrayList<>();
    private ComboBox<String> leagueFilter = new ComboBox<>("Liga");
    private DatePicker startDateFilter = new DatePicker("Od");
    private DatePicker endDateFilter = new DatePicker("Do");

    BettingSlipForm bettingSlipForm = new BettingSlipForm();

    public MainView() {
        setSizeFull();
        configureFilters();
        configureGrid();
        bettingSlipForm.configureBettingSlip();
        add(constructFilterLayout(), matchesGrid, bettingSlipForm.getBetsLayout());
        updateList();

        HorizontalLayout bettingSlipLayout = new HorizontalLayout();
        bettingSlipLayout.add(bettingSlipForm.getStakesLayout(), bettingSlipForm.getBetsLayout());
        bettingSlipLayout.setWidthFull();

        add(constructFilterLayout(), matchesGrid, bettingSlipLayout);
    }

    private void configureFilters() {
        leagueFilter.setItems(FakeDataService.getAvailableLeagues());
//        leagueFilter.setClearButtonVisible(true);
        leagueFilter.setValue(FakeDataService.getAvailableLeagues().get(0));
        leagueFilter.addValueChangeListener(e -> updateList());

        LocalDate today = LocalDate.now();
        startDateFilter.setValue(today);
        endDateFilter.setValue(today.plusMonths(1));

        startDateFilter.addValueChangeListener(e -> updateList());
        endDateFilter.addValueChangeListener(e -> updateList());
    }

    private void configureGrid() {
        matchesGrid.addColumn(Match::getHomeTeam).setHeader("Drużyna Gospodarzy");
        matchesGrid.addColumn(Match::getAwayTeam).setHeader("Drużyna Gości");
        matchesGrid.addColumn(Match::getLeague).setHeader("Liga");
        matchesGrid.addColumn(Match::getMatchTime).setHeader("Data Meczu");

        Map<Match, Map<String, Checkbox>> checkboxesPerMatch = new HashMap<>();

        matchesGrid.setItems(FakeDataService.getMatches()); // Załóżmy, że metoda zwraca listę meczów

        matchesGrid.addColumn(new ComponentRenderer<>(match -> {
            Checkbox checkbox = new Checkbox(Double.toString(match.getOddsHome()));
            checkbox.addValueChangeListener(event -> handleOutcomeSelection(match, "home", match.getOddsHome(), event.getValue(), checkboxesPerMatch));
            checkboxesPerMatch.computeIfAbsent(match, k -> new HashMap<>()).put("home", checkbox);
            return checkbox;
        })).setHeader("Home Win");

        matchesGrid.addColumn(new ComponentRenderer<>(match -> {
            Checkbox checkbox = new Checkbox(Double.toString(match.getOddsDraw()));
            checkbox.addValueChangeListener(event -> handleOutcomeSelection(match, "draw", match.getOddsDraw(),event.getValue(), checkboxesPerMatch));
            checkboxesPerMatch.computeIfAbsent(match, k -> new HashMap<>()).put("draw", checkbox);
            return checkbox;
        })).setHeader("Draw");

        matchesGrid.addColumn(new ComponentRenderer<>(match -> {
            Checkbox checkbox = new Checkbox(Double.toString(match.getOddsAway()));
            checkbox.addValueChangeListener(event -> handleOutcomeSelection(match, "away", match.getOddsAway(), event.getValue(), checkboxesPerMatch));
            checkboxesPerMatch.computeIfAbsent(match, k -> new HashMap<>()).put("away", checkbox);
            return checkbox;
        })).setHeader("Away Win");

    }

    private HorizontalLayout constructFilterLayout() {
        Button clearFiltersButton = new Button("Wyczyść filtry", e -> {
//            leagueFilter.clear();
            startDateFilter.clear();
            endDateFilter.clear();
        });

        HorizontalLayout filterLayout = new HorizontalLayout(leagueFilter, startDateFilter, endDateFilter, clearFiltersButton);
        filterLayout.setPadding(true);
        filterLayout.setSpacing(true);
        return filterLayout;
    }


    private void updateList() {
        List<Match> matches = FakeDataService.getMatchesByFilters(
                leagueFilter.getValue(),
                startDateFilter.getValue(),
                endDateFilter.getValue()
        );
        matchesGrid.setItems(matches);
    }

    private void handleOutcomeSelection(Match match, String selectedOutcome, double odds, Boolean isSelected, Map<Match, Map<String, Checkbox>> checkboxesPerMatch) {
        if (isSelected) {
            betSelections.add(new BetSelection(match, selectedOutcome, odds));
            bettingSlipForm.addBetSelection(match, selectedOutcome, odds);
        } else {
            betSelections.removeIf(selection -> selection.getMatch().equals(match) && selection.getOutcome().equals(selectedOutcome));
            bettingSlipForm.removeBetSelection(match, selectedOutcome);
        }

        Map<String, Checkbox> matchCheckboxes = checkboxesPerMatch.get(match);
        matchCheckboxes.forEach((outcome, checkbox) -> {
            if (!outcome.equals(selectedOutcome)) {
                checkbox.setValue(false);
            }
        });

        bettingSlipForm.calculateWinnings();
    }
}
