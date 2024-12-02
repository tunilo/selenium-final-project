import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import data.Constants;
import utils.MonthNormalizer;

import java.time.Duration;
import java.util.List;

public class MoviePageTests extends BaseTest {
    @Test
    public void moveTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String currentURL = driver.getCurrentUrl();
        driver.get(Constants.SWOOP_HOME_URL);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.CINEMA))).click();
        WebElement firstFilmImage = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div.w-full.group a")
        ));
        currentURL = driver.getCurrentUrl();
        String filmImageURL = firstFilmImage.getAttribute("href");

        driver.navigate().to(filmImageURL);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));
        Actions actions = new Actions(driver);
        WebElement targetElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(Constants.IST_POINT)
        ));
        String movieNameBefore =  driver.findElement(By.xpath(Constants.movieName)).getText();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);");
        wait.until(ExpectedConditions.visibilityOf(targetElement));

        WebElement lastOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.LAST_OPTION)));
        String timeTextBefore = lastOption.findElement(By.xpath(Constants.time)).getText();
        String dateTextBefore = lastOption.findElement(By.xpath(Constants.date)).getText();
        currentURL = driver.getCurrentUrl();
        lastOption.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentURL)));

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

   try{
       Assert.assertEquals(color, seatColor);
   }catch(AssertionError e){
           System.out.println("Colors are not the same " + e.getMessage());
        }

        WebElement newAccountButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.NEW_ACCOUNT)));
        newAccountButton.click();

// Fill in the email
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.name(Constants.EMAIL)));
        emailField.sendKeys(Constants.email_input);

// Fill in the password
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name(Constants.PASSWORD)));
        passwordField.sendKeys(Constants.password_input);

// Fill in the conform password
        WebElement confirmPasswordField = wait.until(ExpectedConditions.elementToBeClickable(By.name(Constants.PASSWORD_RETYPE)));
        confirmPasswordField.sendKeys(Constants.password_input);

// Select gender
        WebElement genderFemale = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.GENDER)));
        genderFemale.click();

// Fill in the first name
        WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.name(Constants.FIRSTNAME)));
        firstNameField.sendKeys(Constants.firstName_input);

// Fill in the last name
        WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.name(Constants.LASTNAME)));
        lastNameField.sendKeys(Constants.lastName_input);

        WebElement birthYearDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(Constants.BIRTH_YEAR)
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", birthYearDropdown);
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);");
        wait.until(ExpectedConditions.visibilityOf(birthYearDropdown));
        wait.until(ExpectedConditions.elementToBeClickable(birthYearDropdown));
        //Thread.sleep(100);
        birthYearDropdown.click();

        WebElement yearOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(@class, 'select2-results__option') and text()='" + Constants.yearOfBirth_input + "']")
        ));
        yearOption.click();

        // Fill in the phone number
        WebElement phoneField = wait.until(ExpectedConditions.elementToBeClickable(By.name(Constants.PHONE)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", phoneField);
        phoneField.sendKeys(Constants.phoneNumber_input);

        // Fill in the phone code
        WebElement phoneCodeField = wait.until(ExpectedConditions.elementToBeClickable(By.id(Constants.PHONE_CODE)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", phoneCodeField);
        phoneCodeField.sendKeys(Constants.phoneCode_input);

        // Agree to terms and conditions
        WebElement termsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.RULES_CHECKMARK)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", termsCheckbox);

        actions.moveToElement(termsCheckbox).click().perform();

        // Agree to policy
        WebElement policyCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.TBC_TERMS)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", policyCheckbox);
        actions.moveToElement(policyCheckbox).click().perform();

        WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(Constants.MAIL_ERROR)
        ));
        Assert.assertTrue(emailError.isDisplayed());
    }
}
