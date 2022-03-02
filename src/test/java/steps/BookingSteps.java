package steps;

import excell_core.ExcelUtilities;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.eo.Se;
import org.testng.Reporter;
import pages.*;
import tests.BaseTest;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public class BookingSteps extends BaseTest {

    String BROWSER = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("BROWSER");
    String WAIT = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WAIT");
    String ENV = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("ENV");

    Map<String, String> data;
    String testDataPath = "src/test/test_data/";

    @Before
    public void setUp() throws Exception {
        setUPTest(BROWSER, Integer.parseInt(WAIT));
    }

//    @After
//    public void tearDown() throws IOException, InterruptedException {
//        reportScreenshot("end", "screenshot on end or fail");
//        quit();
//    }

    @Given("I load test data from {string} {string} {string}")
    public void iLoadTestDataFrom(String fileName, String sheetName, String rowNum) throws IOException {
        ExcelUtilities excelUtilities = new ExcelUtilities();
        data = excelUtilities.getRowData(testDataPath + fileName + ".xlsx", sheetName, rowNum);
        System.out.println(data.get("Location"));
    }

    @Given("I navigate to Booking")
    public void iNavigateToBooking() throws Exception {
        startApplication(ENV);
    }

    @Then("I navigate to flights page")
    public void iNavigateToFlightsPage() throws InterruptedException {
        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.openFlightsPage();


    }

    @And("I add destination")
    public void iAddDestination() throws InterruptedException {
        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.enterWhereToDestination();
    }

    @And("I select flight class {string}")
    public void iSelectFlightClass(String classType) {
        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.selectFlightClass(classType);
    }

    @And("I select number of adults {string}")
    public void iSelectNumberOfAdults(String num) throws InterruptedException {
        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.addAdults(num);
    }


    @And("I add departure and return date {string} {string}")
    public void iAddDepartureAndReturnDate(String departDate, String returnDate) throws InterruptedException, ParseException {
        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.checkInOut(departDate,returnDate);
    }

    @Then("I check direct flights only")
    public void iCheckDirectFlightsOnly() throws InterruptedException {
        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.checkDirectFlights();
    }

    @And("I click search")
    public void iClickSearch() throws InterruptedException {
        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.clickSearchResults();
    }

    @Then("I choose stops and flight time {string} {string}")
    public void iChooseStopsAndFlightTime(String stops, String time) throws InterruptedException {
        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.chooseStops(stops);
        flightsPage.chooseFlightTime(time);
    }

    @Then("I choose presentation {string}")
    public void iChoosePresentation(String presentation) throws InterruptedException {
        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.choosePresentation(presentation);
    }

    @Then("I check price")
    public void iCheckPriceAndSelectFlight() throws InterruptedException {
        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.verifySelectedFlight();

    }

    @And("select flight")
    public void selectFlight() throws InterruptedException {
        FlightsPage flightsPage = new FlightsPage(driver);
        flightsPage.selectFlight();
    }
    @And("I select ticket type {string}")
    public void iSelectTicketType(String ticketType) throws InterruptedException {
        TicketTypePage ticketTypePage = new TicketTypePage(driver);
        ticketTypePage.checkTicketType(ticketType);
    }

    @And("I enter contact data {string}")
    public void iEnterContactData(String countryCodeValue) throws InterruptedException {
        ContactDataPage contactDataPage = new ContactDataPage(driver);
        contactDataPage.enterEmail();
        contactDataPage.enterCountyCode(countryCodeValue);
        contactDataPage.enterPhoneNumber();
    }


    @Then("I enter passenger data {string} {string} {string} {string} {string}")
    public void iEnterPassengerData(String passNum, String genderValue,String day,String month,String year) {
        ContactDataPage contactDataPage = new ContactDataPage(driver);
        contactDataPage.enterPassengerData(passNum,genderValue,day,month,year);
    }

    @Then("I click next button")
    public void iClickNextButton() throws InterruptedException {
        ContactDataPage contactDataPage = new ContactDataPage(driver);
        contactDataPage.clickNextbutton();

    }

    @Then("I select mealOption {string} {string}")
    public void iSelectMealOption(String passNum, String mealValue) throws InterruptedException {
        MealChoicePage mealChoicePage = new MealChoicePage(driver);
        mealChoicePage.selectMealAndBaggage(passNum,mealValue);
        mealChoicePage.clickNextButton();
    }

    @Then("I select seats")
    public void iSelectSeats() throws InterruptedException {
        SelectSeatsPage selectSeatsPage = new SelectSeatsPage(driver);
        selectSeatsPage.selectSeat();
    }
}





