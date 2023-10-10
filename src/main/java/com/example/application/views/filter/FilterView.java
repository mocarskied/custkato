package com.example.application.views.filter;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;


@PageTitle("Filter")
@Route(value = "", layout = MainLayout.class)
public class FilterView extends VerticalLayout {

    H1 pageTitle = new H1("Abonamenty i karty mieszkańca/przedsiębiorcy");
    int currentStep = 0;
    FormLayout zoneSearchFormLayout = new FormLayout();


    public FilterView() {
        addClassName("main-content"); //[class]
        
        add(
            pageTitle,
            getFlowBoxes(),
            getFeatureTypes()
        );
    }

    private HorizontalLayout getFlowBoxes() {
        HorizontalLayout flowBoxes = new HorizontalLayout();
        flowBoxes.addClassName("flow-box-container"); //[class]

        for (int i = 1; i < 6; i++) {

            HorizontalLayout box = new HorizontalLayout();
            box.addClassName("flow-box"); //[class]

            VerticalLayout boxContent = new VerticalLayout(new Span("Krok "+i+""), new Span("Wybierz"));
            boxContent.addClassName("flow-box-content"); //[class]

            box.add(
                new Image("images/flow-"+i+".png", ""),
                boxContent
            );

            flowBoxes.add(box);
        }
        
        return flowBoxes;
    }

    private List<Component> getPermits() {
        List<Component> permits = new ArrayList<Component>();
        //products
        for (int i = 0; i < 3; i++) {
            Span periodLabel = new Span("Miesiąc");
            periodLabel.addClassName("period-label"); //[class]

            Span price = new Span("250,00 zł");
            price.addClassName("permit-price"); //[class]
            price.add(new Span(" /Kwartał"));

            Span zoneType = new Span("Strefa A");
            zoneType.addClassName("zone-type"); //[class]

            Button buyPermitButton = new Button("Zakup abonament/kartę", VaadinIcon.ARROW_RIGHT.create());
            buyPermitButton.addClassNames("btn", "btn-light"); //[class]
            buyPermitButton.setIconAfterText(true);
            buyPermitButton.setWidthFull();

            VerticalLayout feature = new VerticalLayout(
                    periodLabel,
                    new H3("Parkingowa karta mieszkańca"),
                    zoneType,
                    price,
                    buyPermitButton
                    
                );

            feature.addClassNames("feature", "permit"); //[class]
            permits.add(feature);
        }
        return permits;
    }

    private List<Component> getProducts() {
        List<Component> products = new ArrayList<Component>();
        //products
        for (int i = 0; i < 5; i++) {
            VerticalLayout feature = new VerticalLayout(
                    VaadinIcon.CALENDAR.create(),
                    new H3("Parkingowa karta mieszkańca")
                );
            feature.addClassName("feature"); //[class]
        
            products.add(feature);

            feature.addClickListener(click -> {
                if (feature.hasClassName("feature-active")) {
                    feature.removeClassName("feature-active");
                } else {
                    feature.addClassNames("feature-active");
                }
            });
        }

        return products;
    }

    private List<Component> getZones() {
        //zones
        List<Component> zones = new ArrayList<Component>();
        for (int i = 0; i < 2; i++) {
            VerticalLayout feature = new VerticalLayout(
                new H3("Strefa B")
            );
            feature.addClassName("feature"); //[class]

            feature.addClickListener(click -> {
                if (feature.hasClassName("feature-active")) {
                    feature.removeClassName("feature-active");
                } else {
                    feature.addClassNames("feature-active");
                }
            });

            zones.add(feature);
        }
        return zones;
    }

    private VerticalLayout getZoneSearch() {
        Button searchButton = new Button("Szukaj", VaadinIcon.SEARCH.create());
        searchButton.addClassNames("btn","btn-dark", "btn-form-inline"); //[class]
        searchButton.setIconAfterText(true);

        ComboBox<String> streetName = new ComboBox<>("Ulica");
        streetName.setPrefixComponent(VaadinIcon.MAP_MARKER.create());
        streetName.setItems("Królowej Jadwigi");


        TextField streetNumber = new TextField("Nr. budynku");

        zoneSearchFormLayout.add(streetName, streetNumber, searchButton);
        zoneSearchFormLayout.setResponsiveSteps(
        new ResponsiveStep("0", 8)
        );
        zoneSearchFormLayout.setColspan(streetName, 4);
        zoneSearchFormLayout.setColspan(streetNumber, 2);
        zoneSearchFormLayout.setColspan(searchButton, 2);
        zoneSearchFormLayout.setWidth(66, Unit.PERCENTAGE);
        zoneSearchFormLayout.addClassName("sm-center-form"); //[class]

        VerticalLayout zoneSearch = new VerticalLayout(
            zoneSearchFormLayout
            );

        zoneSearch.setPadding(false); //[class]
        return zoneSearch;
    }

    private VerticalLayout getFeatureTypes() {
        H2 featureTypeLabel = new H2("Wybierz produkt");
        // featureTypeLabel.addClassName("feature-label"); //[class]

        Button nextButton = new Button("Przejdź dalej", VaadinIcon.ARROW_RIGHT.create());
        nextButton.addClassNames("btn","btn-dark"); //[class]
        nextButton.setIconAfterText(true);

        //feature-controls
        HorizontalLayout featureControls = new HorizontalLayout(
            nextButton
        );

        featureControls.addClassName("feature-controls"); //[class]


        //feature-list
        HorizontalLayout featureList = new HorizontalLayout();
        featureList.addClassName("feature-container"); //[class]

        featureList.add(getProducts());

        //zone-search
        VerticalLayout zoneSearch = getZoneSearch();
        zoneSearch.setVisible(false);

        nextButton.addClickListener(click -> {
            currentStep++;
            switch(currentStep) {
                case 1:
                    featureList.removeAll();
                    featureTypeLabel.setText("Wybierz ulicę");
                    zoneSearch.setVisible(true);
                    break;
                case 2:
                    featureList.removeAll();
                    zoneSearchFormLayout.setEnabled(false);
                    featureTypeLabel.setText("Wybierz strefę");
                    featureList.add(getZones());
                    break;
                case 3:
                    featureTypeLabel.setText("Wybierz okres trwania usługi");
                    zoneSearch.setVisible(false);
                    nextButton.setVisible(false);
                    featureList.removeAll();
                    featureList.add(getPermits());
                    break;
                    
            }
        });

        //feature-types(container)
        VerticalLayout featureTypes = new VerticalLayout(
            featureTypeLabel,
            zoneSearch,
            featureList,
            featureControls
            );
        
        featureTypes.getThemeList().add("spacing-l");
        featureTypes.addClassName("feature-wrapper"); //[class]
        return featureTypes;

    }


}
