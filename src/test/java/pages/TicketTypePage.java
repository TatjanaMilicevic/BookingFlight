package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TicketTypePage extends BasePage{
    WebDriver driver;

    public TicketTypePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="span.InputRadio-module__field___16hZ8")
    List<WebElement> ticketTypes;
    @FindBy (css="div[data-testid='checkout_ticket_type_inner_next']")
    WebElement nextBtn;


    public void checkTicketType(String ticketType) throws InterruptedException {
        //maybe better create switch method to check different URL

//        String ticketTypeUrl = driver.getCurrentUrl();
//
//        if(ticketTypeUrl.contains( "+ /checkout/ticket-type +")){
            clickElement(driver.findElement(By.xpath("//div[contains(text(),'"+ ticketType + "')]")));
            clickElement(nextBtn);
            Thread.sleep(10000);
//        }
//        else{
//            System.out.println("This is not expected behavior");
//        }

        }
    }

