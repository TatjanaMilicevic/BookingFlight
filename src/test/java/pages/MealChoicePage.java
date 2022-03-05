package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MealChoicePage extends BasePage {
    WebDriver driver;

    public MealChoicePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[name='meal_choice_1']")
    List<WebElement> mealChoiceEl;


    @FindBy(css = "span.InputRadio-module__field___16hZ8")
    List<WebElement> baggageEl;

    @FindBy (css="div[data-testid='checkout_extras_inner_next']")
    WebElement nextBtn;

    @FindBy(css = "'div[data-testid='ticket_type_radio_flexible']'")
    List<WebElement> flexibleTicket;


    public void selectMealAndBaggage(String passNum, String mealValue) throws InterruptedException {
        Thread.sleep(1000);
        String checkOutExtrasUrl = driver.getCurrentUrl();
        if (checkOutExtrasUrl.contains("/extras/")) {
            if (isElementsPresent(mealChoiceEl)) {
                for (int i = 1; i <= Integer.parseInt(passNum); i++)
                    selectByValue(findElementCss("[name='meal_choice_" + i + "']"), mealValue);
            }
            if (isElementsPresent(baggageEl)) {
                for (int i = 1; i <= Integer.parseInt(passNum); i++)
                clickElement(baggageEl.get(i));
            }
        }
    }

    public void clickNextButton() throws InterruptedException {
        clickElement(nextBtn);
    }
}


