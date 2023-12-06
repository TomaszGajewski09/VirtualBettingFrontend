package com.projekt.virtualbettingfrontend;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "kursy-walut", layout = MainLayout.class)
@PageTitle("Kursy Walut")
public class CurrencyRatesView extends VerticalLayout {

    public CurrencyRatesView() {
        CurrencyRates rates = FakeDataService.getCurrencyRates();

        Span usdRateLabel = new Span("Kurs USD do EUR: " + rates.getUsdToEurRate());
        Span gbpRateLabel = new Span("Kurs GBP do EUR: " + rates.getGbpToEurRate());

        add(usdRateLabel, gbpRateLabel);
    }
}