package com.example.application.views;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.RouterLink;
import com.example.application.views.filter.FilterView;
import com.example.application.views.finecheck.FinecheckView;
import com.example.application.views.finecomplaint.FinecomplaintView;
import com.example.application.views.finepayment.FinepaymentView;
import com.example.application.views.questions.QuestionView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;



/**
 * The main view is a top-level placeholder for other views.
 */
@CssImport(value = "./styles/vaadin-app-layout.css", themeFor = "vaadin-app-layout")
@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    Dialog accessibilityDialog = getAccessibilityDialog();
    Button accessibilityButton = new Button(VaadinIcon.ACCESSIBILITY.create(), e -> accessibilityDialog.open());
    Boolean isContrastMode = false;
    double currentGlobalFontSize = 1;

    ListItem[] navigationItems = new ListItem[] {
        new ListItem(new RouterLink("Zamówienie abonamentu/karty", FilterView.class)),
        new ListItem(new RouterLink("Czy wystawiono opłatę?", FinecheckView.class)),
        new ListItem(new RouterLink("Opłać upomnienie", FinepaymentView.class)),
        new ListItem(new RouterLink("Reklamacje", FinecomplaintView.class)),
        new ListItem(new RouterLink("Pytania i odpowiedzi", QuestionView.class)),
        new ListItem(new RouterLink("Regulamin", QuestionView.class)),
        new ListItem(accessibilityButton)
    };


    public MainLayout() {
        createSidebar();
        createHeader();

        setPrimarySection(Section.DRAWER);
        accessibilityButton.addClassNames("btn", "btn-dark", "btn-small"); //[class]
    }

    private void createHeader() {

        UnorderedList primaryNavigation = new UnorderedList(navigationItems);
        primaryNavigation.addClassName("primary-navigation"); //[class]
        HorizontalLayout navigation = new HorizontalLayout(
            primaryNavigation
        );
        navigation.addClassName("navigation-container"); //[class]

        Button toggleMobileNavigationButton = new Button("MENU GŁÓWNE", VaadinIcon.MENU.create(), click -> { //[class]
            if (primaryNavigation.hasClassName("opened-navigation")) {
                primaryNavigation.removeClassName("opened-navigation");
            } else {
                primaryNavigation.addClassName("opened-navigation");
            }
            
        });
        toggleMobileNavigationButton.addClassNames("btn", "btn-light", "nav-trigger"); //[class]

        addToNavbar(toggleMobileNavigationButton, navigation);
    }

    private void createSidebar() {
        Image logotype = new Image("images/logo-katowice.png", "Katowice");
        logotype.setMaxWidth(100, Unit.PERCENTAGE);
        logotype.setWidth(10, Unit.EM);
        

        VerticalLayout sidebarLayout = new VerticalLayout(
            logotype
        );
        sidebarLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        addToDrawer(sidebarLayout);
    }



    private Dialog getAccessibilityDialog() {
        Dialog accessibilityDialog = new Dialog();
        Button increaseButton =  new Button(VaadinIcon.SEARCH_PLUS.create(), click -> {
            currentGlobalFontSize = currentGlobalFontSize + 0.2;
            if (currentGlobalFontSize > 1){
                changeLayoutSize(true, true);
                setDrawerOpened(false);
            } else {
                changeLayoutSize(true, false);
            }
        });
        Button reduceButton = new Button(VaadinIcon.SEARCH_MINUS.create(), click -> {
            currentGlobalFontSize = currentGlobalFontSize - 0.2;
            if (currentGlobalFontSize <= 1){
                changeLayoutSize(false, false);
                setDrawerOpened(true);
            } else{
                changeLayoutSize(false, true);  
            }
        });
        Button contrastButton = new Button(VaadinIcon.EYE.create(), click -> {
            isContrastMode = !isContrastMode;
            setContrastMode(isContrastMode);
        });
        increaseButton.addClassNames("btn", "btn-dark"); //[class]
        reduceButton.addClassNames("btn", "btn-dark");   //[class]
        contrastButton.addClassNames("btn", "btn-dark"); //[class]
        
        accessibilityDialog.add(
            new HorizontalLayout(increaseButton, reduceButton, contrastButton)
            );

        return accessibilityDialog;
    }

    private void changeLayoutSize(Boolean isBigger, Boolean isLarge) {
        var jsFont = String.format("document.documentElement.style.fontSize = '%sem'", currentGlobalFontSize);
        UI.getCurrent().getPage().executeJs(jsFont);

        var jsLayout = "document.querySelector('vaadin-app-layout').setAttribute('size', $0)";
        UI.getCurrent().getPage().executeJs(jsLayout, isLarge ? "large" : "");
 
    }

    private void setContrastMode(Boolean isContrastMode) {
        var js = "document.documentElement.setAttribute('theme', $0)";
        UI.getCurrent().getPage().executeJs(js, isContrastMode ? "contrast-mode" : "");
    }

}