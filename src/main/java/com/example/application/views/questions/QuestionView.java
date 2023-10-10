package com.example.application.views.questions;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Question")
@Route(value = "question", layout = MainLayout.class)
public class QuestionView extends VerticalLayout {

    H1 pageTitle = new H1("Pytania i odpowiedzi");
    Paragraph pageDescription = new Paragraph("W uzasadnionych przypadkach jeśli uważasz, że opłata została naliczona omyłkowo możesz złożyć odwołanie, w prosty i szybki sposób, podając nr rejestracyjny oraz numer Upomnienia.");
    
    
    public QuestionView() {
        addClassName("main-content"); //[class]

        add(
            pageTitle,
            pageDescription,
            getFeatureQuestions()
        );
    
    }

    private Accordion getQuestionsAccordion() {
        Accordion questions = new Accordion();
        questions.setWidthFull();
        
        HorizontalLayout answer = new HorizontalLayout(
            new Paragraph("Od 1 Listopada w Katowicach zawiadomienia w formie papierowej nie są wystawiane. Powszechnie obowiązujące przepisy prawa, nie przewidują wymogu wystawiania zawiadomień. Na stronie XYZXYZ.MZUM.PL: − sprawdzisz, czy została wystawiona opłata dodatkowa, − sprawdzisz szczegóły dotyczące kontroli takie jak: zdjęcia ze zdarzenia, lokalizacja, data oraz godzina, − opłacisz upomnienie.")
        );
             HorizontalLayout answer2 = new HorizontalLayout(
            new Paragraph("Opłata za postój powinna być uiszczona niezwłocznie po rozpoczęciu postoju. Przez pojęcie to należy rozumieć „bez nieuzasadnionej zwłoki”, czyli bez zbędnych czynności. W tym czasie należy wykupić bilet parkingowy w parkomacie lub opłacić postój w jednej z aplikacji obsługiwanych w tut. strefie płatnego parkowania.")
        );
             HorizontalLayout answer3 = new HorizontalLayout(
            new Paragraph("Zarówno nieopłacenie postoju, jak też przekroczenie opłaconego czasu postoju skutkuje naliczeniem z mocy prawa opłaty dodatkowej, którą należy uiścić poprzez zapłatę na wskazany numer rachunku bankowego lub w kasie Miejskiego Zarządu Ulic i Mostów w Katowicach przy ul. Warszawskiej 19.")
        );
        questions.add("1. Otrzymałem listowne upomnienie, dlaczego nie miałem zawiadomienia za szybą?", answer);
        questions.add("2. Opłata została wniesiona kilka minut od zaparkowania pojazdu? Co w takiej sytuacji?", answer2);
        questions.add("3. Nastąpiło przekroczenie czasu postoju i przez to została naliczona opłata dodatkowa.", answer3);
        
        return questions;
    }

    private VerticalLayout getFeatureQuestions() {

        VerticalLayout questions = new VerticalLayout(
            new Paragraph("Wprowadź nr rejestracyjny swojego pojazdu oraz numer upomnienia:"),
            getQuestionsAccordion()
        );

        questions.addClassName("feature-wrapper"); //[class]
        return questions;
    }
  
}
