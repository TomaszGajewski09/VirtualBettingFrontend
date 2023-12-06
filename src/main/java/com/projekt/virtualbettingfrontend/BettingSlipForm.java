package com.projekt.virtualbettingfrontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BettingSlipForm {
    private List<BetSelection> betSelections = new ArrayList<>();
    private NumberField stakeField = new NumberField("Stawka");
    private TextField potentialWinnings = new TextField("Możliwa wygrana");
    private Button acceptButton = new Button("Akceptuj kupon");
    private VerticalLayout stakesLayout = new VerticalLayout();
    private VerticalLayout betsLayout = new VerticalLayout();

    public BettingSlipForm() {
        configureBettingSlip();
        acceptButton.addClickListener(e -> acceptBetSlip());
    }

    public void addBetSelection(Match match, String outcome, double odds) {
        BetSelection selection = new BetSelection(match, outcome, odds);
        betSelections.add(selection);
        updateBettingSlip();
        calculateWinnings();
    }

    public void removeBetSelection(Match match, String outcome) {
        betSelections.removeIf(selection -> selection.getMatch().equals(match) && selection.getOutcome().equals(outcome));
        updateBettingSlip();
        calculateWinnings();
    }

    private void updateBettingSlip() {
        getBetsLayout().removeAll();
        for (BetSelection selection : betSelections) {
            getBetsLayout().add(new Span(selection.getMatch().getHomeTeam() + " vs " + selection.getMatch().getAwayTeam() +
                    " : " + selection.getOutcome() + " - " + selection.getOdds()));
        }
    }

    private void acceptBetSlip() {
        if (betSelections.isEmpty()) {
            Notification.show("Nie wybrano żadnych zakładów.", 3000, Notification.Position.MIDDLE);
            return;
        }

        double stake = stakeField.getValue() != null ? stakeField.getValue() : 0.0;
        if (stake <= 0) {
            Notification.show("Stawka musi być większa niż 0.", 3000, Notification.Position.MIDDLE);
            return;
        }

        Notification.show("Kupon zaakceptowany!", 3000, Notification.Position.MIDDLE);

        betSelections.clear();
        updateBettingSlip();
        calculateWinnings();
        stakeField.clear();
    }

    public void calculateWinnings() {
        double totalOdds = betSelections.stream()
                .mapToDouble(BetSelection::getOdds)
                .reduce(1, (a, b) -> a * b);
        double stake = stakeField.getValue() != null ? stakeField.getValue() : 0.0;
        potentialWinnings.setValue(String.format("%.2f PLN", totalOdds * stake));
    }

    public void configureBettingSlip() {
        stakeField.setPrefixComponent(new Span("PLN"));
        stakeField.setValue(10.0);
        stakeField.addValueChangeListener(e -> calculateWinnings());
        potentialWinnings.setReadOnly(true);
//        getBetsLayout().add(stakeField, potentialWinnings);
        stakesLayout.add(stakeField, potentialWinnings, acceptButton);
    }

}
