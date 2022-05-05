package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.JsonOutput;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperSearch extends HelperBase{
    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public void searchCurrentMonth(String city, String dataFrom, String dataTo) {

        typeCity(city);
        selectPeriod(dataFrom, dataTo);

    }

    private void typeCity(String city) {
        pause(1000);
        type(By.id("city"), city);
        click(By.cssSelector(".pac-item"));

    }

    private void selectPeriod(String dataFrom, String dataTo) {
        click(By.id("dates"));

        String[] from = dataFrom.split("/");
        String dayF = from [1];
        String locatorFrom = String.format("//div[text()=' %s ']", dayF);
        click(By.xpath(locatorFrom));

        String [] to = dataTo.split("/");
        String dayT = to [1];
        String locatorTo = String.format("//div[text()=' %s ']", dayT);
        click(By.xpath(locatorTo));

        //split
    }

    public void searchAnyPeriod(String city, String dataFrom, String dataTo) {

        typeCity(city);
        selectPeriodAnyData(dataFrom, dataTo);

    }

    private void selectPeriodAnyData(String dataFrom, String dataTo) {
        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate to = LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        click(By.id("dates"));
        int diffYear;
        int diffMonth;

        diffYear = from.getYear() - now.getYear();
        if(diffYear == 0){
            diffMonth = from.getMonthValue() - now.getMonthValue();
        } else{
            diffMonth = 12 - now.getMonthValue() + from.getMonthValue();
        }

        clickByNextMonth(diffMonth);

        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));

//***********************************************************

        diffYear = to.getYear() - from.getYear();
        if(diffYear == 0){
            diffMonth = to.getMonthValue() - from.getMonthValue();
        } else {
            diffMonth = 12 - from.getMonthValue() + to.getMonthValue();
        }

        clickByNextMonth(diffMonth);
        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));


    }

    private void clickByNextMonth(int count) {

        for(int i = 0; i < count; i++){
            click(By.xpath("//button[@aria-label='Next month']"));
        }
    }

    private void selectPeriodAnyData2(String dataFrom, String dataTo) {
        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dataFrom, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate to = LocalDate.parse(dataTo, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        click(By.id("dates"));

      //  Condition?  expression1: expression2;

        int diffMonth= from.getYear() - now.getYear()
                == 0 ? from.getMonthValue() - now.getMonthValue(): 12 - now.getMonthValue() + from.getMonthValue();

        clickByNextMonth(diffMonth);
        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));

//***********************************************************

        diffMonth = to.getYear()-from.getYear() == 0 ? to.getMonthValue() - from.getMonthValue(): 12 - from.getMonthValue() + to.getMonthValue();
        clickByNextMonth(diffMonth);
        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));


    }

      public void searchPeriodInPast(String city, String dataFrom, String dataTo) {

        typeCity(city);
        typePeriodInPast(dataFrom, dataTo);
           }

    private void typePeriodInPast(String dataFrom, String dataTo) {
        // wd.findElement(By.id("dates")).sendKeys(dataFrom + " - " + dataTo);
       WebElement el = wd.findElement(By.id("dates"));

       //os?
        String osname= System.getProperty("os.name");
        if(osname.startsWith("Mac")){
            System.out.println(osname);
            el.sendKeys(Keys.COMMAND + "a");
        } else{
            el.sendKeys(Keys.CONTROL + "a");
        }
        el.sendKeys(Keys.DELETE);
        pause(2000);

        type(By.id("dates"), dataFrom + " - " + dataTo);
        click(By.cssSelector(".cdk-overlay-container"));

        takeScreenShot("src/test/screenshots/screen.png");
    }

    public boolean isPeriodInPast() {
        pause(1000);
        WebElement el = wd.findElement(By.xpath("//div[@class='ng-star-inserted']"));
        String error = el.getText();
        System.out.println(error);
        return error.equals("You can't pick date before today");

    }

    public void returnToHomePage() {
        pause(2000);
        click(By.cssSelector(".header a[href='/']"));
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector(".cars-container"));
    }
}
