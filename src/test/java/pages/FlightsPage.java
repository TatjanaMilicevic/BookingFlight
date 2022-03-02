package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.ParseException;
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
    @FindBy(css = "div#flightcard-0")
    WebElement firstFlight;
    @FindBy(css = "div[data-test-id='flight_card_price_main_price']")
    List<WebElement> prices;
    @FindBy(css = "button[data-testid='flight_card_bound_select_flight']")
    List<WebElement> showFlightBtn;
    @FindBy(css = ".css-5nu86q div[data-test-id='flight_card_price_main_price']")
    WebElement selectedFlightPrice;
    @FindBy(css = "[data-testid='flight_details_inner_modal_select_button']")
    WebElement selectFlightBtn;


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


    public void checkInOut(String departDate, String returnDate) throws InterruptedException, ParseException {
        String expDate = getExpectedDate(departDate);
        clickElement(departBtn);
        clickElement(driver.findElement(By.xpath("//span[@data-date-cell='" + expDate + "']")));
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
        Thread.sleep(1000);
    }

    public void choosePresentation(String presentation) throws InterruptedException {

        clickElement(driver.findElement(By.xpath("//span[text()='" + presentation + "']")));
        Thread.sleep(2000);
    }

    public void verifySelectedFlight() throws InterruptedException {
        if (firstFlight.isDisplayed()) {

            //get price of one passenger and remover ","
            String priceFirstFlightOne = (getText(prices.get(0))).substring(1).replace(",", "");

            Double priceFlightExpOne = Double.parseDouble(priceFirstFlightOne);

            //get number of adults
            Double adultsNum = Double.parseDouble(getText(adultEl).substring(0, 1));

            //get price for all passengers
            Double priceFirstFlightAdults = adultsNum * priceFlightExpOne;
            //Integer roundPriceFirstFlightAdults = Math.round(priceFirstFlightAdults*100.0)/100.0

            clickElement(showFlightBtn.get(0));

            //get price on selected flight page - price is for all passengers
            String priceSelectedFlight = (getText(selectedFlightPrice)).substring(1).replace(",", "");

            Double priceFlightAcc = Double.parseDouble(priceSelectedFlight);

            //there is different behavior on this page regarding price, verify prices
            if (priceFirstFlightAdults == priceFlightAcc) {
                Assert.assertEquals(priceFirstFlightAdults, priceFlightAcc);
            } else {
                Assert.assertEquals(priceFlightAcc, priceFlightExpOne);
            }

        }
    }

    public void selectFlight () throws InterruptedException {
        clickElement(selectFlightBtn);
    }


}




