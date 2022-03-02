package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ContactDataPage extends BasePage {
    WebDriver driver;

    public ContactDataPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[name='email']")
    WebElement emailInput;
    @FindBy(css = "select.css-1k0jlfl")
    WebElement countryCodeEl;
    @FindBy(css = "[name='phone']")
    WebElement phoneNm;
    @FindBy(css = "[name='day']")
    List<WebElement> dayEl;
    @FindBy(css = "[name='month']")
    List<WebElement> monthEl;
    @FindBy(css = "[name='year']")
    List<WebElement> yearEl;
    @FindBy(css = "div[data-testid='checkout_extras_inner_next']")
    WebElement nextBtn;


    String firstName = randomName(5);
    String lastName = randomName(6);
    String email = randomEmail(6);
    String phoneNumber = randomPhoneNumber(7);


    public void enterEmail() {
        inputElement(emailInput, email);

    }

    public void enterCountyCode(String countryCodeValue) {
        selectByValue(countryCodeEl, countryCodeValue);
    }

    public void enterPhoneNumber() {
        inputElement(phoneNm, phoneNumber);
    }

    public void enterPassengerData(String passNum, String genderValue, String day, String month, String year) {
        for (int i = 0; i <= Integer.parseInt(passNum); i++) {
            inputElement(findElementCss("[name='passengers." + i + ".firstName']"), firstName);
            inputElement(findElementCss("[name='passengers." + i + ".lastName']"),lastName);
            WebElement genderSelect = findElementCss("[name='passengers." + i + ".gender']");
            genderSelect.click();
            selectByValue(genderSelect, genderValue);
            inputElement(dayEl.get(i), day);
            inputElement(monthEl.get(i), month);
            inputElement(yearEl.get(i), year);

        }



    }

    public void clickNextbutton() throws InterruptedException {
        clickElement(nextBtn);
    }
}

