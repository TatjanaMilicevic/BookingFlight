package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.bind.Element;
import java.util.List;

public class SelectSeatsPage extends BasePage{
   WebDriver driver;
    public SelectSeatsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".Accordion-module__icon___2j9RS")
    List<WebElement> viewSeatsEl;

    @FindBy (css ="[data-testid='trip_summary_passenger_count']")
    WebElement passNumber;

    @FindBy (css ="[data-testid='breakdown_list_price']")
    WebElement priceSection;

    public static String priceFinal;
    public static Double priceFinalAcc;

    private By allSeats = By.xpath("//button[@aria-expanded='true']//..//div[contains(@style, 'grid-column: COLUMN')]//div[@class='css-dcom7u' or @class='css-1koab2d']");

    @FindBy(css = ".css-b07tw6")
    WebElement nextBtn;


    public void selectSeat () throws InterruptedException {
        String passNumberSeats = passNumber.getText().substring(0,1);
        Integer passNumberSeatsCorrect = Integer.parseInt(passNumberSeats);
        for (int i = 0; i < viewSeatsEl.size();i++) {
            clickElement(viewSeatsEl.get(i));
            //List of available seats must be out of second
            List<WebElement> totalSeats= driver.findElements(allSeats);
            Thread.sleep(1000);

            for (int j = 0; j < passNumberSeatsCorrect; j++) {
                clickElement(totalSeats.get(j));

            }
        }
        priceFinal = priceSection.getText().substring(1);

    }

    public void clickNext() throws InterruptedException {
        clickElement(nextBtn);
    }

}
