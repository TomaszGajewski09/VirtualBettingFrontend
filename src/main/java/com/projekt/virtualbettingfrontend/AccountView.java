package com.projekt.virtualbettingfrontend;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "moje-konto", layout = MainLayout.class)
@PageTitle("Moje Konto")
public class AccountView extends HorizontalLayout {

    public AccountView() {
        ProfileView profileView = new ProfileView();
        WalletView walletView = new WalletView();

        profileView.setWidth("50%");
        walletView.setWidth("50%");

        add(profileView, walletView);

        setSizeFull();
        setMargin(true);
        setSpacing(true);
    }
}
