package org.example.ubs.page_objects;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
public class KayakPage {
    //private SelenideElement acceptCookieBtn = $$(By.tagName("Button")).first();
    public void enterFrom(String text){
        //$(By.name)
    }

    //This action will accept all the cookies by default
    public void acceptCookies() throws InterruptedException {


        Thread.sleep(5000); //This is needed to let the pop-up for accepting the cookies to come up.

        getWebDriver().switchTo().activeElement();
        SelenideElement cookieText = $(By.tagName("a"));
        if(cookieText.has(Condition.ownText("Datenschutzrichtlinien"))){
        SelenideElement acceptBtn = $$(By.tagName("Button")).filterBy(Condition.text("Akzeptieren")).first();
        acceptBtn.shouldBe(Condition.visible).click();
        }
    }
}
