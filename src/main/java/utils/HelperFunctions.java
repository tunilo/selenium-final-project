package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HelperFunctions {

    private WebDriver driver;

    public HelperFunctions(WebDriver driver) {
        this.driver = driver;
    }

    public List<Double> collectAllPrices(String selector) {
        List<Double> prices = new ArrayList<>();
        int currentPage = 1;

        navigateToNextPage(currentPage);
        do {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            List<WebElement> priceElements = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(selector))
            );

            prices.addAll(priceElements.stream()
                    .map(el -> extractPrice(el.getText()))
                    .collect(Collectors.toList()));

            currentPage++;
        } while (navigateToNextPage(currentPage));

        return prices;
    }

    private boolean navigateToNextPage(int currentPage) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement nextPageButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[text()='" + currentPage + "']")
            ));

            String currentURL = driver.getCurrentUrl();
            nextPageButton.click();
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public double extractPrice(String priceText) {
        return Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
    }
}
