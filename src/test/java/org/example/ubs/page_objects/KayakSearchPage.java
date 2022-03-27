package org.example.ubs.page_objects;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
public class KayakSearchPage {


    //This action will accept all the cookies by default
    public void acceptCookies() throws Exception {
        Thread.sleep(5000); //This is needed to let the pop-up for accepting the cookies to come up.

        getWebDriver().switchTo().activeElement();
        SelenideElement cookieText = $$(By.tagName("a")).filterBy(Condition.ownText("Datenschutzrichtlinien")).first();
        if(cookieText.has(Condition.ownText("Datenschutzrichtlinien"))){
        SelenideElement acceptBtn = $$(By.tagName("Button")).filterBy(Condition.text("Akzeptieren")).first();
        acceptBtn.shouldBe(Condition.visible).click();
        }
        else
            throw new Exception();
    }

    public void searchFlights(String from, String to, String fromDate, String returnDate,int maxPrice) throws InterruptedException {

        enterFromData(from);
        enterToData(to);
        enterFromDate(fromDate);
        enterReturnDate(returnDate);
        clickOnSearchButton();
        System.out.println("Total Flights Found="+checkForResults(maxPrice));
    }

    private int checkForResults(int maxPrice) throws InterruptedException {
        ElementsCollection flightsInPriceRange;

        closePopUp();
        flightsInPriceRange =  $$(By.cssSelector("span[id*='-price-text']"));

        ArrayList<String> priceFlights = new ArrayList<>();
        for (int i = 0; i< flightsInPriceRange.size(); i++){
            priceFlights.add(i, flightsInPriceRange.get(i).getText());}

        //cleanup of the ArrayList
        ArrayList<String> cleanPriceFlights = removeEmptyPrices(priceFlights);

        //cleanup the Arraylist to remove CHF and space
        for (String element :cleanPriceFlights){
            priceFlights.set(priceFlights.indexOf(element),element.substring(4));
        }

        //cleanup the Arraylist to format strings and remove ' from prices

        cleanPriceFlights = removeQuotes(cleanPriceFlights);


        ArrayList<Integer> flightPrices = new ArrayList<>();


        for (String element:cleanPriceFlights){

            if (Integer.parseInt(element)<maxPrice)
                flightPrices.add(Integer.parseInt(element));
        }
        return (int) flightPrices.stream().count();
    }

    private ArrayList<String> removeQuotes(ArrayList<String> priceFlights) {
        Pattern checkPattern;
        Matcher match;
        checkPattern = Pattern.compile("[^a-zA-Z0-9]");
        for (String element:priceFlights){
            match = checkPattern.matcher(element);

            if(match.find()){
                String temp_str = element;
                priceFlights.set(priceFlights.indexOf(element),temp_str.substring(0,1)+temp_str.substring(2,temp_str.length()));
            }
        }
        return priceFlights;
    }

    private ArrayList<String> removeEmptyPrices(ArrayList<String> priceFlights) {
        Iterator iter = priceFlights.iterator();
        while (iter.hasNext()){
            if(iter.next().equals("")){
                iter.remove();
            }
        }
        return priceFlights;
    }

    private void closePopUp() throws InterruptedException {
        Thread.sleep(5000); //This is needed to check if there is any pop-up window;
        getWebDriver().switchTo().activeElement();
        SelenideElement closePopup = $$(By.cssSelector("div[class*='-button']")).filterBy(Condition.text("X")).first();
        if(closePopup.isDisplayed()){
            closePopup.click();
        }
    }

    private void clickOnSearchButton() {
        $$(By.tagName("button")).filterBy(Condition.attribute("aria-label","Suchen")).first().should(Condition.visible).click();
    }

    private void enterReturnDate(String returnDate) {

        $$(By.tagName("span")).filterBy(Condition.attribute("role","button")).get(3).click(); //this is to date

        //Step2. Check if we need to calculate some clicks or not

        //Get the current date
        String currentDate = $$(By.cssSelector("div[class*='-monthName']")).first().getText();

        //Now Get the clicks
       String[] splittedCurrentDate = returnDate.split("[-]");
        int numberOfClicks = getNumberOfClicks(currentDate,returnDate);

        if(numberOfClicks == 0){
            //proceed to select the day
            selectDay(splittedCurrentDate[2]);
        }
        else if (Math.signum(numberOfClicks) == -1){
            //move the calendar leftwise and then select day
            SelenideElement previousArrow = $$(By.cssSelector("div[class*='-arrow']")).filterBy(Condition.attribute("aria-label","Vorheriger Monat")).first();
            int i = numberOfClicks;
            while (i!=0){
                previousArrow.click();
                i++;
            }
            selectDay(splittedCurrentDate[2]);
        }
        else if(Math.signum(numberOfClicks)==1){
            //move the calendar rightwise and then select day
            SelenideElement previousArrow = $$(By.cssSelector("div[class*='-arrow']")).filterBy(Condition.attribute("aria-label","Nächster Monat")).first();
            int i = numberOfClicks;
            while (i!=0){
                previousArrow.click();
                i--;
            }
            previousArrow.click();
            selectDay(splittedCurrentDate[2]);
        }
    }

    private void enterFromDate(String fromDate) {

        //Step 1. Open the from date Picker
        $$(By.tagName("span")).filterBy(Condition.attribute("role", "button")).first().click();

        //Step2. Check if we need to calculate some clicks or not

        //Get the current date
        String currentDate = $$(By.cssSelector("div[class*='-monthName']")).first().getText();

        //Now Get the clicks

        String[] splittedCurrentDate = fromDate.split("[-]");
        int numberOfClicks = getNumberOfClicks(currentDate,fromDate);

        if(numberOfClicks == 0){
            //proceed to select the day
            selectDay(splittedCurrentDate[2]);
        }
        else if (Math.signum(numberOfClicks) == -1){
            //move the calendar leftwise and then select day
            SelenideElement previousArrow = $$(By.cssSelector("div[class*='-arrow']")).filterBy(Condition.attribute("aria-label","Vorheriger Monat")).first();
            int i = numberOfClicks;
            while (i!=0){
                previousArrow.click();
                i++;
            }
            selectDay(splittedCurrentDate[2]);
        }
        else if(Math.signum(numberOfClicks)==1){
            //move the calendar rightwise and then select day
            SelenideElement previousArrow = $$(By.cssSelector("div[class*='-arrow']")).filterBy(Condition.attribute("aria-label","Nächster Monat")).first();
            int i = numberOfClicks;
            while (i!=0){
                previousArrow.click();
                i--;
            }
            selectDay(splittedCurrentDate[2]);
        }

        System.out.println("Entered From Date, now entering return date");
    }



    private int getNumberOfClicks(String currentDate, String fromDate) {
               String[] brokenCurrentDate = currentDate.split(" ");
        int currentMonthCount = convertMonthToCount(brokenCurrentDate[0]);
        int currentYearCount =  Integer.parseInt(brokenCurrentDate[1]);
        String[] splittedFromDate = fromDate.split("[-]");
        String formattedFromDate = splittedFromDate[0]+splittedFromDate[1];
        String formattedCurrentDate = String.valueOf(currentYearCount) + "0" +String.valueOf(currentMonthCount);
        return calculateDifference(formattedCurrentDate,formattedFromDate);
    }

    private int calculateDifference(String formattedCurrentDate, String formattedFromDate) {
        int sum=0;
        String signedInt;
        try{
        int difference = Integer.parseInt(formattedFromDate) - Integer.parseInt(formattedCurrentDate);
        char[] digits_difference = String.valueOf(difference).toCharArray();

        int lengthArray = digits_difference.length;

        if (Math.signum(difference) == -1){
                switch (lengthArray){
                    case 2:
                        sum =Integer.parseInt(String.valueOf(digits_difference[1]));
                        signedInt = "-"+String.valueOf(sum);
                        sum = Integer.parseInt(signedInt);
                        break;
                    case 4:
                        sum = Integer.parseInt(String.valueOf(digits_difference[1]))*12 + Integer.parseInt(String.valueOf(digits_difference[3]));
                        signedInt = "-"+String.valueOf(sum);
                        sum = Integer.parseInt(signedInt);
                        break;
                }
            }

            else {
                switch (lengthArray) {
                    case 1:
                        sum = Integer.parseInt(String.valueOf(digits_difference[0]));
                        break;
                    case 3:
                        sum = Integer.parseInt(String.valueOf(digits_difference[0])) * 12 + Integer.parseInt(String.valueOf(digits_difference[3]));
                        break;
                }
            }
        System.out.println(sum);}
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return sum;
    }

    private int convertMonthToCount(String monthInText) {
        int monthCount = 0;
        switch(monthInText){
            case "Januar":
                monthCount= 1;
            break;

            case "Februar":
                monthCount= 2;
            break;

            case "März":
                monthCount= 3;

            case "April":
                monthCount= 4;
            break;

            case "Mai":
                monthCount= 5;
            break;

            case "Juni":
                monthCount= 6;
            break;

            case "Juli":
                monthCount= 7;
            break;

            case "August":
                monthCount= 8;
            break;

            case "September":
                monthCount= 9;
            break;

            case "Oktober":
                monthCount= 10;
            break;

            case "November":
                monthCount= 11;
            break;

            case "Dezember":
                monthCount= 12;
            break;
        }
        return monthCount;
    }

    private void selectDay(String day) {
        SelenideElement dayElement = $$(By.cssSelector("div[class*='mkUa-pres-default']")).filterBy(Condition.text(day)).first();
        dayElement.click();
        if (dayElement.is(Condition.visible))
            dayElement.click();

    }


    private void enterToData(String to) {
        SelenideElement toField = $$(By.tagName("div")).filterBy(Condition.attribute("aria-label","Eingabe Flugziel")).first();
        toField.shouldBe(Condition.visible).click();
        SelenideElement toTextField = $(By.className("k_my-input")).shouldHave(Condition.attribute("placeholder","Nach?"));
        toTextField.setValue(to);
        SelenideElement resultDropDown = $$(By.cssSelector("span[class*='-subName']")).find(Condition.text(to));
        resultDropDown.shouldBe(Condition.visible).click();
        System.out.println("To city entered. Now proceeding to enter from Date");
    }

    private void enterFromData(String from) {
        SelenideElement fromField = $$(By.tagName("div")).filterBy(Condition.attribute("aria-label","Eingabe Abflughafen")).first();
        fromField.shouldBe(Condition.visible).click();
        SelenideElement fromTextField = $(By.className("k_my-input")).shouldHave(Condition.attribute("placeholder","Von?"));
        fromTextField.setValue(from);

        SelenideElement resultDropDown = $$(By.cssSelector("span[class*='-subName']")).find(Condition.text(from));
        resultDropDown.shouldBe(Condition.visible).click();
        System.out.println("from city entered. Now proceeding to enter to city");
    }
}
