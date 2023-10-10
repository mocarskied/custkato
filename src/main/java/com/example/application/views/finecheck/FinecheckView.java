package com.example.application.views.finecheck;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Finecheck")
@Route(value = "finecheck", layout = MainLayout.class)
public class FinecheckView extends VerticalLayout { 


    H1 pageTitle = new H1("Czy wystawiono mi opłatę?");
    Paragraph pageDescription = new Paragraph("Wprowadź swoje tablice rejestracyjne aby zweryfikować czy nie otrzymałeś zawiadomienia o opłacie dodatkowej. Z przyczyn niezależnych nie można sprawdzić zawiadomień przed godziną 8:00 kolejnego dnia.");

    public FinecheckView() {
        addClassName("main-content"); //[class]

        add(
            pageTitle,
            pageDescription,
            getFeatureFine()
        );
    }

    private VerticalLayout getFineSearch() {
        Button searchButton = new Button("Szukaj", VaadinIcon.SEARCH.create());
        searchButton.addClassNames("btn","btn-dark", "btn-form-inline"); //[class]
        searchButton.setIconAfterText(true);

        TextField plateNumber = new TextField("Tablice rejestracyjne");

        FormLayout fineSearchFormLayout = new FormLayout();

        fineSearchFormLayout.add(plateNumber, searchButton);
        fineSearchFormLayout.setResponsiveSteps(
        new ResponsiveStep("0", 8)
        );
        fineSearchFormLayout.setColspan(plateNumber, 6);
        fineSearchFormLayout.setColspan(searchButton, 2);
        fineSearchFormLayout.setWidth(66, Unit.PERCENTAGE);
        fineSearchFormLayout.addClassName("sm-center-form"); //[class]

        VerticalLayout fineSearch = new VerticalLayout(
            fineSearchFormLayout
            );

        fineSearch.setPadding(false); //[class]
        return fineSearch;
    }


    private VerticalLayout getFeatureFine() {

        VerticalLayout featureFine = new VerticalLayout(
            getFineSearch()
        );

        featureFine.addClassName("feature-wrapper"); //[class]
        return featureFine;
    }
}