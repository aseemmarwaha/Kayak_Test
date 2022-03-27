package org.example.ubs;
import org.example.ubs.page_objects.KayakSearchPage;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Configuration.browser;

public class TestKayak {
    @Test
    public void userCanSearchFlights() throws Exception {
        browser = "firefox";
        open("https://kayak.ch");
        new KayakSearchPage().acceptCookies();
        new KayakSearchPage().searchFlights("Zürich","Delhi","2022-03-28","2022-10-25",1500);

    }

}
