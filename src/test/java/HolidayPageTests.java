import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import data.Constants;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import utils.HelperFunctions;


public class HolidayPageTests extends BaseTest {
    @Test
    public void descendingOrderTest() {
        HelperFunctions helper = new HelperFunctions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper.loadHomePage();

        helper.clickAndWait(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.HOLIDAY_PAGE_LINK))));

        List<Double> allPrices = helper.collectAllPrices(Constants.PRICE_LOCATOR);
        double maxPrice = allPrices.stream().max(Double::compare).orElse(0.0);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.SORT_BUTTON))).click();
        WebElement descendingOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.DESCENDING_ORDER)));
        descendingOption.click();
        WebElement firstOfferPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.PRICE_LOCATOR)));
        double firstOfferPrice = helper.extractPrice(firstOfferPriceElement.getText());

        Assert.assertEquals(firstOfferPrice, maxPrice);
    }

    @Test
    public void ascendingOrderTest(){
        HelperFunctions helper = new HelperFunctions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper.loadHomePage();

        helper.clickAndWait(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.HOLIDAY_PAGE_LINK))));

        List<Double> allPrices = helper.collectAllPrices(Constants.PRICE_LOCATOR);
        double minPrice = allPrices.stream().min(Double::compare).orElse(0.0);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.SORT_BUTTON))).click();
        WebElement descendingOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.ASCENDING_ORDER)));
        descendingOption.click();

        WebElement firstOfferPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.PRICE_LOCATOR)));

        double firstOfferPrice = helper.extractPrice(firstOfferPriceElement.getText());

        Assert.assertEquals(firstOfferPrice, minPrice);
    }

    @Test
    public void filterTest() {
        HelperFunctions helper = new HelperFunctions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper.loadHomePage();

        helper.clickAndWait(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.HOLIDAY_PAGE_LINK))));

        helper.clickAndWait(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.MOUNTAIN_RESORTS))));

            WebElement fullPaymentOption = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(Constants.FULL_PAYMENT_OPTION)
            ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fullPaymentOption);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);");

        helper.clickAndWait(wait.until(ExpectedConditions.elementToBeClickable(fullPaymentOption)));

        WebElement fullPaymentIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Constants.FULL_PAYMENT_ICON)));
        Assert.assertTrue(fullPaymentIcon.isDisplayed());
        List<Double> allPrices = helper.collectAllPrices(Constants.PRICE_LOCATOR);

        double minPrice = allPrices.stream().min(Double::compare).orElse(0.0);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.SORT_BUTTON))).click();

        WebElement descendingOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.ASCENDING_ORDER)));
        descendingOption.click();

        WebElement firstOfferPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.PRICE_LOCATOR)));
        double firstOfferPrice = helper.extractPrice(firstOfferPriceElement.getText());

        Assert.assertEquals(firstOfferPrice, minPrice);
    }

    @Test
    public void priceRangeTest() {
        HelperFunctions helper = new HelperFunctions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper.loadHomePage();

        helper.clickAndWait(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.HOLIDAY_PAGE_LINK))));

        WebElement minPriceInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.MIN_PRICE_INPUT)));
        minPriceInput.clear();
        minPriceInput.sendKeys(Constants.MIN_PRICE);

        WebElement maxPriceInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.MAX_PRICE_INPUT)));
        maxPriceInput.clear();
        maxPriceInput.sendKeys(Constants.MAX_PRICE);

        WebElement filterButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.FILTER_BUTTON)));
        helper.clickAndWait(filterButton);

        List<Double> allPrices = helper.collectAllPrices(Constants.PRICE_LOCATOR);
        boolean areAllPricesInRange = allPrices.stream().allMatch(price -> price >= Constants.MIN_PRICE_INT && price <= Constants.MAX_PRICE_INT);
        Assert.assertTrue(areAllPricesInRange);

    }

}
