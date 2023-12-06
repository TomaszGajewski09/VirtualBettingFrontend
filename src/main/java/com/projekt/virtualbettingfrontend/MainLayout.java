package com.projekt.virtualbettingfrontend;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;



public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Aplikacja Zakładów");
        logo.addClassNames("text-l", "m-m");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }

    private void createDrawer() {
        Button homeButton = createNavigationButton("Strona Główna", VaadinIcon.HOME.create(), MainView.class);
        Button profileButton = createNavigationButton("Profil", VaadinIcon.USER.create(), AccountView.class);
//        Button profileButton = createNavigationButton("Profil", VaadinIcon.USER.create(), ProfileView.class);
//        Button walletButton = createNavigationButton("Portfel", VaadinIcon.WALLET.create(), WalletView.class);
        Button ratesButton = createNavigationButton("Kursy Walut", VaadinIcon.MONEY_EXCHANGE.create(), CurrencyRatesView.class);


        VerticalLayout menuLayout = new VerticalLayout(homeButton, profileButton, ratesButton);
//        VerticalLayout menuLayout = new VerticalLayout(homeButton, profileButton, walletButton, ratesButton);
        menuLayout.setSizeFull();
        menuLayout.setPadding(false);
        menuLayout.setSpacing(false);
        menuLayout.setAlignItems(FlexComponent.Alignment.START);
        menuLayout.addClassNames("menu-layout");

        addToDrawer(menuLayout);
    }

    private Button createNavigationButton(String text, Icon icon, Class<? extends Component> navigationTarget) {
        RouterLink link = new RouterLink("", navigationTarget);
        link.add(icon, new Text(text));
        link.setClassName("router-link");

        Button button = new Button(link);
        button.setWidthFull();
        button.setClassName("menu-button");

        return button;
    }
}