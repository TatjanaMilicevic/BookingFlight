package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class FlightsPage extends BasePage {
    WebDriver driver;

    public FlightsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-decider-header='flights']")
    WebElement Flights;
    @FindBy(css = "select.css-1k0jlfl")
    WebElement flightClass;
    @FindBy(xpath = "//div[contains(text(), 'adult')]")
    WebElement adultEl;
    @FindBy(css = ".css-153jucu")
    List<WebElement> incAdultNum;
    @FindBy(css = ".css-1bal7l4 .css-ya5gr9")
    WebElement addAdultNumBtn;
    @FindBy(css = "[data-testid='searchbox_destination']")
    WebElement whereTo;
    @FindBy(css = "[data-testid='searchbox_destination_input']")
    WebElement whereToDestination;
    @FindBy(css = "[class='bui-checkbox']>[type='checkbox']")
    List<WebElement> allAirports;
    @FindBy(xpath = "//input[@placeholder='Depart']")
    WebElement departBtn;
    @FindBy(xpath = "//input[@placeholder='Return']")
    WebElement returnBtn;
    @FindBy(css = ".InputCheckbox-module__field___1mRcZ")
    WebElement directFlights;
    @FindBy(css = "button.css-ya5gr9")
    WebElement search;
    @FindBy(css = "button.css-1p87hp6")
    WebElement modifySearch;
    @FindBy(css = "button[aria-controls='__bui-39'][aria-selected='true']")
    WebElement mostFavorable;
    @FindBy(css = "span.InputRadio-module__field___16hZ8")
    List<WebElement> chairRadioBtn;
    @FindBy(css = "[data-testid='searchresults_card']")
    List<WebElement> flightsCard;



    public void openFlightsPage() throws InterruptedException {
        clickElement(Flights);
    }

    public void selectFlightClass(String classType) {
        selectByValue(flightClass, classType);
    }

    public void addAdults(String num) throws InterruptedException {
        clickElement(adultEl);
        Thread.sleep(1000);
        if (num.equals("1")) {
            //do nothing
        } else if (Integer.parseInt(num) > 1) {
            for (int i = 2; i <= Integer.parseInt(num); i++) {
                clickElement(incAdultNum.get(0));
            }
        }
        clickElement(addAdultNumBtn);
    }


    public void checkInOut(String departDate, String returnDate) throws InterruptedException {
        clickElement(departBtn);
        clickElement(driver.findElement(By.xpath("//span[@data-date-cell='" + departDate + "']")));
        clickElement(returnBtn);
        Thread.sleep(1000);
        clickElement(driver.findElement(By.xpath("//span[@data-date-cell='" + returnDate + "']")));
    }

    public void enterWhereToDestination() throws InterruptedException {
        clickElement(whereTo);
        inputElement(whereToDestination, "MIAMI");
        clickByIndex(allAirports, 0);
    }

    public void checkDirectFlights() throws InterruptedException {
        clickElement(directFlights);
        Thread.sleep(2000);

    }

    public void clickSearchResults() throws InterruptedException {
        clickElement(search);
        Thread.sleep(5000);

    }

    public void chooseStops(String stops) throws InterruptedException {
        if ((modifySearch.isDisplayed())) {
            clickElement(directFlights);
            Thread.sleep(2000);
            clickElement(driver.findElement(By.xpath("//div[text() ='" + stops + "']")));
        } else {
            clickElement(driver.findElement(By.xpath("//div[text() ='" + stops + "']")));
        }

    }

    public void chooseFlightTime(String time) throws InterruptedException {
            List<WebElement> flightTime = driver.findElements(By.xpath("//*[contains(text(),'" + time + "')]"));
            clickElement(flightTime.get(0));
        }

    }



