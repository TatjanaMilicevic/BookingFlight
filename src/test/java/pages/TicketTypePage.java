package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TicketTypePage extends BasePage {
    WebDriver driver;

    public TicketTypePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span.InputRadio-module__field___16hZ8")
    List<WebElement> ticketTypes;
    @FindBy(css = "div[data-testid='fare_switcher_radio_card_1']")
    List<WebElement> firstTicketType;
    @FindBy(css = "div[data-testid='checkout_ticket_type_inner_next']")
    WebElement nextBtn;
    @FindBy(css = "div[data-testid='checkout_fare_inner_next']")
    WebElement nextBtn2;


    public void checkTicketType(String ticketType) throws InterruptedException {
        Thread.sleep(2000);
        String ticketTypeUrl = driver.getCurrentUrl();

        if (ticketTypeUrl.contains("/ticket-type/")) {
            clickElement(findElementXpath("//div[contains(text(),'" + ticketType + "')]"));
            clickElement(nextBtn);
        }

        if (ticketTypeUrl.contains("/checkout/fare/")) {
            clickElement(findElementCss("div[data-testid='fare_switcher_radio_card_" + 2 + "']"));
            clickElement(nextBtn2);

        }
    }

}


