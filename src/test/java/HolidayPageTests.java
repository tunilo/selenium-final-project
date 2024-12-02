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
        String currentURL = driver.getCurrentUrl();
        driver.get(Constants.SWOOP_HOME_URL);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));

        currentURL = driver.getCurrentUrl();
        wait.until(ExpectedConditions.urlToBe(Constants.SWOOP_HOME_URL));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.HOLIDAY_PAGE_LINK))).click();
        //driver.findElement(By.xpath(Constants.HOLIDAY_PAGE_LINK)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));

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
        String currentURL = driver.getCurrentUrl();
        driver.get(Constants.SWOOP_HOME_URL);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));

        currentURL = driver.getCurrentUrl();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.HOLIDAY_PAGE_LINK))).click();

        //driver.findElement(By.xpath(Constants.HOLIDAY_PAGE_LINK)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));

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
        String currentURL = driver.getCurrentUrl();
        driver.get(Constants.SWOOP_HOME_URL);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));

        currentURL = driver.getCurrentUrl();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.HOLIDAY_PAGE_LINK))).click();

        //driver.findElement(By.xpath(Constants.HOLIDAY_PAGE_LINK)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));
            WebElement mountainResort;
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                mountainResort = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath(Constants.MOUNTAIN_RESORTS)
                ));
                currentURL = driver.getCurrentUrl();
                Thread.sleep(1000);
                mountainResort.click();
                wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));
                break;
            } catch (ElementClickInterceptedException e) {
                System.out.println("Element click intercepted. Retrying... Attempt " + attempt);
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element exception. Retrying... Attempt " + attempt);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
            WebElement fullPaymentOption = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(Constants.FULL_PAYMENT_OPTION)
            ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fullPaymentOption);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);");
            for (int attempt = 1; attempt <= 3; attempt++) {
                try {
                    fullPaymentOption = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath(Constants.FULL_PAYMENT_OPTION)
                    ));
                    currentURL = driver.getCurrentUrl();

                    fullPaymentOption.click();
                    wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));

                    System.out.println("Clicked successfully on attempt: " + attempt);
                    break;
                } catch (ElementClickInterceptedException e) {
                    System.out.println("Element click intercepted. Retrying... Attempt " + attempt);
                } catch (StaleElementReferenceException e) {
                    System.out.println("Stale element exception. Retrying... Attempt " + attempt);
                }
            }

            ///Plan B , If the swoop become more normal and have no need to retry
//        driver.findElement(By.xpath(Constants.HOLIDAY_PAGE_LINK)).click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.MOUNTAIN_RESORTS))).click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Constants.FULL_PAYMENT_OPTION)));
//        WebElement fullPaymentOption = wait.until(ExpectedConditions.presenceOfElementLocated(
//                        By.xpath(Constants.FULL_PAYMENT_OPTION)
//                ));
//                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fullPaymentOption);
//                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);");
//                fullPaymentOption1.click();

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
        String currentURL = driver.getCurrentUrl();
        driver.get(Constants.SWOOP_HOME_URL);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));

        currentURL = driver.getCurrentUrl();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.HOLIDAY_PAGE_LINK))).click();

        //driver.findElement(By.xpath(Constants.HOLIDAY_PAGE_LINK)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));

        WebElement minPriceInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.MIN_PRICE_INPUT)));
        minPriceInput.clear();
        minPriceInput.sendKeys(Constants.MIN_PRICE);

        WebElement maxPriceInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.MAX_PRICE_INPUT)));
        maxPriceInput.clear();
        maxPriceInput.sendKeys(Constants.MAX_PRICE);

        WebElement filterButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.FILTER_BUTTON)));
        currentURL = driver.getCurrentUrl();
        filterButton.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));
        List<Double> allPrices = helper.collectAllPrices(Constants.PRICE_LOCATOR);
        boolean areAllPricesInRange = allPrices.stream().allMatch(price -> price >= Constants.MIN_PRICE_INT && price <= Constants.MAX_PRICE_INT);
       Assert.assertTrue(areAllPricesInRange);

    }

}
