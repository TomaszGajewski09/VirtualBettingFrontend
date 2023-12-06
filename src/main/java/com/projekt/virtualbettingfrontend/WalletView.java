package com.projekt.virtualbettingfrontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "portfel", layout = MainLayout.class)
@PageTitle("Portfel")
public class WalletView extends VerticalLayout {

    private final TextField depositField = new TextField("Kwota do wpłaty");
    private final TextField withdrawField = new TextField("Kwota do wypłaty");
    private final Button depositButton = new Button("Wpłać");
    private final Button withdrawButton = new Button("Wypłać");
    private final Button confirmDepositButton = new Button("Potwierdź wpłatę");
    private final Button confirmWithdrawButton = new Button("Potwierdź wypłatę");

    public WalletView() {
        Wallet wallet = FakeDataService.getWalletData();

        Span balanceLabel = new Span("Saldo: " + wallet.getBalance() + " PLN");

        depositField.setVisible(false);
        withdrawField.setVisible(false);
        confirmDepositButton.setVisible(false);
        confirmWithdrawButton.setVisible(false);

        depositButton.addClickListener(e -> {
            depositField.setVisible(true);
            confirmDepositButton.setVisible(true);
            withdrawField.setVisible(false);
            confirmWithdrawButton.setVisible(false);
        });

        withdrawButton.addClickListener(e -> {
            withdrawField.setVisible(true);
            confirmWithdrawButton.setVisible(true);
            depositField.setVisible(false);
            confirmDepositButton.setVisible(false);
        });

        confirmDepositButton.addClickListener(e -> {
            // todo: Tutaj logika wpłaty
            depositField.setVisible(false);
            confirmDepositButton.setVisible(false);
        });

        confirmWithdrawButton.addClickListener(e -> {
            // todo: Tutaj logika wypłaty
            withdrawField.setVisible(false);
            confirmWithdrawButton.setVisible(false);
        });

        HorizontalLayout actionsLayout = new HorizontalLayout(depositButton, withdrawButton);
        HorizontalLayout depositLayout = new HorizontalLayout(depositField, confirmDepositButton);
        HorizontalLayout withdrawLayout = new HorizontalLayout(withdrawField, confirmWithdrawButton);

        add(balanceLabel, actionsLayout, depositLayout, withdrawLayout);
    }
}
