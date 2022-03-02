package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

//    @FindBy(xpath = "//button[@aria-expanded='true']//..//div[@class = 'css-suxfu6']")
//    List<WebElement> passengers;

    @FindBy (css ="[data-testid='trip_summary_passenger_count']")
    WebElement passNumber;

    @FindBy(xpath = "//button[@aria-expanded='true']//..//div[contains(@style, 'grid-column: COLUMN')]//div[@class='css-dcom7u' or @class='css-1koab2d']")
    List<WebElement> allSeats;
//    @FindBy (css= "[style*='grid-column: COLUMN'] .css-dcom7u, .css-1koab2d")
//    List<WebElement> allSeats;
    @FindBy(css = ".css-b07tw6")
    WebElement nextBtn;


    public void selectSeat () throws InterruptedException {
        String passNumberSeats = passNumber.getText().substring(0,1);
        Integer passNumberSeatsCorrect = Integer.parseInt(passNumberSeats);
        for (int i = 0; i < viewSeatsEl.size();i++) {
            clickElement(viewSeatsEl.get(i));

            for (int j = 0; j < passNumberSeatsCorrect; j++) {
                Thread.sleep(2000);
                clickElement(allSeats.get(j));
            }
        }
    }

    public void clickNext() throws InterruptedException {
        clickElement(nextBtn);
    }

}
