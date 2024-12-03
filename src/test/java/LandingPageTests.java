import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import data.Constants;
import utils.HelperFunctions;

import java.time.Duration;
import java.util.List;

public class LandingPageTests extends BaseTest {
    @Test
    public void activeCategoryTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        HelperFunctions helper = new HelperFunctions(driver);
        helper.loadHomePage();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.CATEGORIES))).click();
        WebElement sportElement = driver.findElement(By.xpath(Constants.SPORT));

        Actions actions = new Actions(driver);
        actions.moveToElement(sportElement).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.KARTING))).click();
        boolean isURLMatched = wait.until(ExpectedConditions.urlToBe(Constants.expectedURL));
        Assert.assertTrue(isURLMatched);

        WebElement categoryChain = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Constants.CATEGORY_CHAIN)));

        List<WebElement> categories = categoryChain.findElements(By.xpath(Constants.CATEGORY_CHAIN_ELEMENTS));
        boolean kartingiFound = false;
        for (WebElement category : categories) {
            String categoryText = category.getText();
            if (categoryText.equals(Constants.Kartingi)) {
                kartingiFound = true;
                break;
            }
        }
        Assert.assertTrue(kartingiFound);
    }
    @Test
    public void logoTest(){
        HelperFunctions helper = new HelperFunctions(driver);

        helper.loadHomePage();
        helper.clickAndWait(driver.findElement(By.xpath(Constants.HOLIDAY_PAGE_LINK)));
        helper.clickAndWait(driver.findElement(By.xpath(Constants.LOGO)));

        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);
        Assert.assertEquals(currentURL, Constants.SWOOP_HOME_URL);
    }
}
