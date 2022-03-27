package org.example.ubs;
import org.example.ubs.page_objects.KayakSearchPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Configuration.browser;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestKayak {

    public String fromLocation;
    public String toLocation;
    public String fromDate;
    public String returnDate;
    public int maxPrice;

    @BeforeAll
    public void setUp(){
        fromLocation = System.getProperty("fromLocation");
        toLocation = System.getProperty("toLocation");
        fromDate = System.getProperty("fromDate");
        returnDate = System.getProperty("returnDate");
        maxPrice = Integer.parseInt(System.getProperty("maxPrice"));

    }
    @Test
    public void userCanSearchFlights() throws Exception {
        browser = "firefox";
        open("https://kayak.ch");
        new KayakSearchPage().acceptCookies();
        new KayakSearchPage().searchFlights(fromLocation,toLocation,fromDate,returnDate,maxPrice);
    }

}
