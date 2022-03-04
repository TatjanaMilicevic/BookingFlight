package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PaymentPage extends BasePage {
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-testid='breakdown_list_price']")
    WebElement paymentPriceSection;


    public void verifyFinalPrice() {
        String paymentFinalPrice = paymentPriceSection.getText().substring(1);
        //Double paymentFinalPriceExp = Double.parseDouble(paymentFinalPrice);
        if (paymentFinalPrice.equals(SelectSeatsPage.priceFinal)) {
            Assert.assertEquals(paymentFinalPrice, SelectSeatsPage.priceFinal);
            System.out.println("Price is ok");
        }

    }
}




