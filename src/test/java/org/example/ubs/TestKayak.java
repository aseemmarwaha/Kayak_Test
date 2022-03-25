package org.example.ubs;

import org.example.ubs.page_objects.KayakPage;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Configuration.browser;

public class TestKayak {
    @Test
    public void userCanSearch() throws InterruptedException {
        browser = "firefox";
        open("https://kayak.com");
        new KayakPage().acceptCookies();

    }
}
