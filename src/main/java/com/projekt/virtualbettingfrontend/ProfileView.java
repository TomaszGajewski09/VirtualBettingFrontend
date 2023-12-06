package com.projekt.virtualbettingfrontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "profil", layout = MainLayout.class)
@PageTitle("Profil")
public class ProfileView extends VerticalLayout {

    private final TextField nameField = new TextField("Imię i nazwisko");
    private final TextField countryField = new TextField("Country");
    private final TextField cityField = new TextField("City");
    private final TextField emailField = new TextField("Email");
    private final Button editButton = new Button("Edit");
    private final Button saveButton = new Button("Save");
    private final Button cancelButton = new Button("Cancel");

    public ProfileView() {
        UserProfile userProfile = FakeDataService.getUserProfile();

        nameField.setValue(userProfile.getFirstName() + " " + userProfile.getSecondName());
        countryField.setValue(userProfile.getCountry());
        cityField.setValue(userProfile.getCity());
        emailField.setValue(userProfile.getEmail());

        setReadOnly(true);

        editButton.addClickListener(e -> setReadOnly(false));
        saveButton.addClickListener(e -> {
            // todo: Logika zapisywania danych
            setReadOnly(true);
        });
        cancelButton.addClickListener(e -> {
            nameField.setValue(userProfile.getFirstName() + " " + userProfile.getSecondName());
            countryField.setValue(userProfile.getCountry());
            cityField.setValue(userProfile.getCity());
            emailField.setValue(userProfile.getEmail());
            setReadOnly(true);
        });

        saveButton.setVisible(false);
        cancelButton.setVisible(false);

        HorizontalLayout actionsLayout = new HorizontalLayout(editButton, saveButton, cancelButton);
        add(nameField, countryField, cityField, emailField, actionsLayout);
    }

    private void setReadOnly(boolean readOnly) {
        nameField.setReadOnly(readOnly);
        countryField.setReadOnly(readOnly);
        cityField.setReadOnly(readOnly);
        emailField.setReadOnly(readOnly);

        saveButton.setVisible(!readOnly);
        cancelButton.setVisible(!readOnly);
        editButton.setVisible(readOnly);
    }
}