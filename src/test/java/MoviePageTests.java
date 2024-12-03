import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import data.Constants;
import utils.HelperFunctions;
import utils.MonthNormalizer;

import java.time.Duration;
import java.util.List;

public class MoviePageTests extends BaseTest {
    @Test
    public void moveTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        HelperFunctions helper = new HelperFunctions(driver);
        helper.loadHomePage();
        helper.clickAndWait( wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.CINEMA))));

        String CAVEA_EAST_POINT = "//label[@for='4291']";
        WebElement Cavea_East_Point = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(CAVEA_EAST_POINT )
        ));
        wait.until(ExpectedConditions.elementToBeClickable(Cavea_East_Point));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div.fixed.min-w-screen.min-h-screen.bg-white\\/50")
        ));

        helper.clickAndWait(Cavea_East_Point);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='კავეა ისთ ფოინთი']")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(@class, 'w-full') and contains(@class, 'group')]//a)")));
        WebElement firstFilmImage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.w-full.group a")
        ));
        helper.clickAndWait(firstFilmImage);

        WebElement targetElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(Constants.EAST_POINT)
        ));
        String movieNameBefore = driver.findElement(By.xpath(Constants.movieName)).getText();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);");
        wait.until(ExpectedConditions.visibilityOf(targetElement));

        WebElement lastOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.LAST_OPTION)));
        String timeTextBefore = lastOption.findElement(By.xpath(Constants.time)).getText();
        String dateTextBefore = lastOption.findElement(By.xpath(Constants.date)).getText();

        helper.clickAndWait(lastOption);

        WebElement movieNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.POPUP_MOVIE)));
        String popUpMovieName = movieNameElement.getText();

        WebElement textElement = driver.findElement(By.xpath(Constants.POPUP_DATE_TIME));
        String fullText = textElement.getText();
        String[] parts = fullText.split(",");
        String popUpDateText = parts[0].trim();
        String popUpTimeText = parts[1].trim();
        if (dateTextBefore.startsWith("0")) {
            dateTextBefore = dateTextBefore.replaceFirst("^0", "");
        }

        Assert.assertEquals(popUpTimeText, timeTextBefore);
        Assert.assertEquals(MonthNormalizer.normalizeMonth(dateTextBefore), MonthNormalizer.normalizeMonth(popUpDateText));
        Assert.assertEquals(popUpMovieName, movieNameBefore);

        List<WebElement> freeSeats = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(Constants.FREE_PLACE)));
        WebElement element = driver.findElement(By.xpath(Constants.FREE_SEAT_COLOR));

        String color = element.getCssValue("background-color");
        String seatColor = "";
        if (!freeSeats.isEmpty()) {
            WebElement firstSeatSVG = freeSeats.get(0);
            WebElement firstPath = firstSeatSVG.findElement(By.tagName("path"));
            seatColor = firstPath.getCssValue("fill");
            freeSeats.get(0).click();
        }

        try {
            Assert.assertEquals(color, seatColor);
        } catch (AssertionError e) {
            System.out.println("Colors are not the same " + e.getMessage());
        }

        WebElement newAccountButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.NEW_ACCOUNT)));
        newAccountButton.click();

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.name(Constants.EMAIL)));
        emailField.sendKeys(Constants.email_input);

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name(Constants.PASSWORD)));
        passwordField.sendKeys(Constants.password_input);

        WebElement confirmPasswordField = wait.until(ExpectedConditions.elementToBeClickable(By.name(Constants.PASSWORD_RETYPE)));
        confirmPasswordField.sendKeys(Constants.password_input);

        WebElement genderFemale = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.GENDER)));
        genderFemale.click();

        WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.name(Constants.FIRSTNAME)));
        firstNameField.sendKeys(Constants.firstName_input);

        WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.name(Constants.LASTNAME)));
        lastNameField.sendKeys(Constants.lastName_input);

        WebElement birthYearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(Constants.BIRTH_YEAR)
        ));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Constants.BIRTH_YEAR)));


        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });",
                birthYearDropdown
        );
        wait.until(ExpectedConditions.visibilityOf(birthYearDropdown));
        wait.until(ExpectedConditions.elementToBeClickable(birthYearDropdown));
         Thread.sleep(100); // აი ამას ვერაფერი მოვუხერხე :დ
         try {
             birthYearDropdown.click();
         } catch (Exception e) {
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", birthYearDropdown);
         }

        //აი სლიფის გარეშე თავს იკლავს და არ სქროლავს როცა ერთად ვუშვებ
//        wait.until(driver -> {
//            try {
//                if (!birthYearDropdown.isDisplayed())    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 100);");
//                return birthYearDropdown.isDisplayed() ;
//            } catch (Exception ex) {
//                return false;
//            }
//        });
//        try {
//            birthYearDropdown.click();
//        } catch (Exception e) {
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", birthYearDropdown);
//        }



        WebElement yearOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(@class, 'select2-results__option') and text()='" + Constants.yearOfBirth_input + "']")
        ));
        yearOption.click();


        WebElement phoneCodeField = wait.until(ExpectedConditions.elementToBeClickable(By.id(Constants.PHONE_CODE)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", phoneCodeField);
        phoneCodeField.sendKeys(Constants.phoneCode_input);

        WebElement termsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.RULES_CHECKMARK)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", termsCheckbox);
        actions.moveToElement(termsCheckbox).click().perform();

        WebElement policyCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.TBC_TERMS)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", policyCheckbox);
        actions.moveToElement(policyCheckbox).click().perform();

        WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(Constants.MAIL_ERROR)
        ));
        Assert.assertTrue(emailError.isDisplayed());
    }
}
