package com.example.application.views.finepayment;

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

@PageTitle("Finepayment")
@Route(value = "finepayment", layout = MainLayout.class)
public class FinepaymentView extends VerticalLayout { 


    H1 pageTitle = new H1("Upomnienia");
    Paragraph pageDescription = new Paragraph("Wprowadź nr rejestracyjny swojego pojazdu oraz numer upomnienia.");

    public FinepaymentView() {
        addClassName("main-content"); //[class]

        add(
            pageTitle,
            pageDescription,
            getFeatureFinePayment()
        );
    }

    private VerticalLayout getFinePaymentSearch() {
        Button searchButton = new Button("Szukaj", VaadinIcon.SEARCH.create());
        searchButton.addClassNames("btn","btn-dark", "btn-form-inline"); //[class]
        searchButton.setIconAfterText(true);

        TextField plateNumber = new TextField("Tablice rejestracyjne");
        TextField fineNumber = new TextField("Numer wezwania");


        FormLayout finePaymentSearchFormLayout = new FormLayout();

        finePaymentSearchFormLayout.add(plateNumber, fineNumber, searchButton);
        finePaymentSearchFormLayout.setResponsiveSteps(
        new ResponsiveStep("0", 8)
        );
        finePaymentSearchFormLayout.setColspan(plateNumber, 3);
        finePaymentSearchFormLayout.setColspan(fineNumber, 3);
        finePaymentSearchFormLayout.setColspan(searchButton, 2);
        finePaymentSearchFormLayout.setWidth(66, Unit.PERCENTAGE);
        finePaymentSearchFormLayout.addClassName("sm-center-form"); //[class]

        VerticalLayout finePaymentSearch = new VerticalLayout(
            finePaymentSearchFormLayout
            );

        finePaymentSearch.setPadding(false); //[class]
        return finePaymentSearch;
    }


    private VerticalLayout getFeatureFinePayment() {

        VerticalLayout featureFinePayment = new VerticalLayout(
            getFinePaymentSearch()
        );

        featureFinePayment.addClassName("feature-wrapper"); //[class]
        return featureFinePayment;
    }
}