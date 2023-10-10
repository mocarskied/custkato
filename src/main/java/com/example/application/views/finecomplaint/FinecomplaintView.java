package com.example.application.views.finecomplaint;

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

@PageTitle("Finecomplaint")
@Route(value = "finecomplaint", layout = MainLayout.class)
public class FinecomplaintView extends VerticalLayout { 


    H1 pageTitle = new H1("Odwołania/Reklamacje");
    Paragraph pageDescription = new Paragraph("W uzasadnionych przypadkach jeśli uważasz, że opłata została naliczona omyłkowo możesz złożyć odwołanie, w prosty i szybki sposób, podając nr rejestracyjny oraz numer Upomnienia.");

    public FinecomplaintView() {
        addClassName("main-content"); //[class]

        add(
            pageTitle,
            pageDescription,
            getFeatureFineComplaint()
        );
    }

    private VerticalLayout getFineComplaintSearch() {
        Button searchButton = new Button("Szukaj", VaadinIcon.SEARCH.create());
        searchButton.addClassNames("btn","btn-dark", "btn-form-inline"); //[class]
        searchButton.setIconAfterText(true);

        TextField plateNumber = new TextField("Tablice rejestracyjne");
        TextField fineNumber = new TextField("Numer wezwania");


        FormLayout fineComplaintSearchFormLayout = new FormLayout();

        fineComplaintSearchFormLayout.add(plateNumber, fineNumber, searchButton);
        fineComplaintSearchFormLayout.setResponsiveSteps(
        new ResponsiveStep("0", 8)
        );
        fineComplaintSearchFormLayout.setColspan(plateNumber, 3);
        fineComplaintSearchFormLayout.setColspan(fineNumber, 3);
        fineComplaintSearchFormLayout.setColspan(searchButton, 2);
        fineComplaintSearchFormLayout.setWidth(66, Unit.PERCENTAGE);
        fineComplaintSearchFormLayout.addClassName("sm-center-form"); //[class]

        VerticalLayout fineComplaintSearch = new VerticalLayout(
            fineComplaintSearchFormLayout
            );

        fineComplaintSearch.setPadding(false); //[class]
        return fineComplaintSearch;
    }


    private VerticalLayout getFeatureFineComplaint() {

        VerticalLayout featureFineComplaint = new VerticalLayout(
            new Paragraph("Wprowadź nr rejestracyjny swojego pojazdu oraz numer upomnienia:"),
            getFineComplaintSearch()
        );

        featureFineComplaint.addClassName("feature-wrapper"); //[class]
        return featureFineComplaint;
    }
}