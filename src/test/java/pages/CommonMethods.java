package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class CommonMethods {
    WebDriver driver;
    int waitTime = 10;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
    }

    //Selenium wrapper methods START
    public void clickElement(WebElement element) throws InterruptedException {
        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));

      //  scrollToElement(element);
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            element.click();
        } catch (StaleElementReferenceException e) {
            Thread.sleep(1000);
            element.click();
        }
    }


    public void inputElement(WebElement element, String value) {
        element.clear();
        element.click();
        element.sendKeys(value);
    }

    public void typeText(WebElement element, String text) throws InterruptedException {
        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));

        try {
//            scrollToElement(element);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            element.sendKeys(text);
        } catch (StaleElementReferenceException e) {
            Thread.sleep(1000);
            element.click();
        }
    }

    public String getText(WebElement element) {
//        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
//        wdWait.until(ExpectedConditions.visibilityOf(element));

        return element.getText();
    }

    public String getValue(WebElement element) {
        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
        wdWait.until(ExpectedConditions.visibilityOf(element));

        return element.getAttribute("value");
    }

    public void selectByValue(WebElement element, String value) {
//        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
//        wdWait.until(ExpectedConditions.visibilityOf(element));
//        wdWait.until(ExpectedConditions.elementToBeClickable(element));

        try {
            Select select = new Select(element);
            select.selectByValue(value);
        } catch (StaleElementReferenceException e) {
            Select select = new Select(element);
            select.selectByValue(value);
        }
    }

    public void clickByIndex(List<WebElement> elements, int index) {
        WebElement element = elements.get(index);
        element.click();
    }

    public WebElement findElementCss(String text) {
        return driver.findElement(By.cssSelector(text));
    }

    public WebElement findElementXpath(String text) {
        return driver.findElement(By.xpath(text));
    }

    public List <WebElement> findElementsXpath(String text) {
        return driver.findElements(By.xpath(text));
    }

    public void scroll(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public void scroll(String pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ")");
    }

    public void hoverElement(WebElement element) {
        WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
        wdWait.until(ExpectedConditions.visibilityOf(element));
        wdWait.until(ExpectedConditions.elementToBeClickable(element));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public boolean isElementsPresent(List<WebElement> elements) {
        return elements.size() != 0;
    }

    public void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        element.click();
    }


    public String randomName(int lenght) {

        String[] strings = {"q", "w", "r", "y", "g", "a", "c", "e", "v", "f", "c"};
        String result = "";
        for (int i = 0; i <= lenght; i++) {
            Random random = new Random();
            int index = random.nextInt(strings.length);
            result = result + strings[index];
        }
        return result;
    }

    public String randomEmail(int lenght) {
        String[] strings = {"a", "b", "c", "d", "e", "f"};
        String result = "";

        for (int i = 0; i <= strings.length; i++) {
            Random random = new Random();
            int index = random.nextInt(strings.length);
            result = result + strings[index];
        }
        return result + "@gmail.com";

    }

    public String randomPhoneNumber(int lenght) {
        String[] strings = {"1", "2", "3", "4", "5", "6", "7"};
        String resultNum = "";

        for (int i = 0; i <= strings.length; i++) {
            Random random = new Random();
            int index = random.nextInt(strings.length);
            resultNum = resultNum + strings[index];
        }
        return "69" + resultNum;

    }

    public static String getExpectedDate(String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentOne = LocalDate.now();
        LocalDate compareOne = LocalDate.parse(date, formatter);
        if (compareOne.isBefore(currentOne)|| compareOne.isEqual(currentOne)) {
            return formatter.format(currentOne.plusDays(1));
        } else {
            return formatter.format(compareOne);
        }
    }
}


    //Selenium wrapper methods END
