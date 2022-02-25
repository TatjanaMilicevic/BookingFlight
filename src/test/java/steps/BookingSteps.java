package steps;

import excell_core.ExcelUtilities;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Reporter;
import pages.FlightsPage;
import tests.BaseTest;

import java.io.IOException;
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

    @After
    public void tearDown() throws IOException, InterruptedException {
        reportScreenshot("end", "screenshot on end or fail");
        quit();
    }

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
    public void iAddDepartureAndReturnDate(String departDate, String returnDate) throws InterruptedException {
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
}

//    }
//
//    @And("I select number of adults")
//    public void iSelectNumberOfAdults() {
//    }




