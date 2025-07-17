package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class AddCurrencyPage {
    WebDriver driver;
    ElementUtils elementUtils;
    Properties prop;
    WebDriverWait wait;
    JavascriptExecutor js;

    public AddCurrencyPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor)driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Currency']")
    WebElement titleAddCurrency;

    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
    WebElement dropdownCurrency;

    @FindBy(xpath = "//label[text()='Minimum Salary']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputMinimumSalary;

    @FindBy(xpath = "//label[text()='Maximum Salary']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputMaximumSalary;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[normalize-space()='Should be a valid number (xxx.xx)']")
    WebElement messageShouldBeNumber;

    @FindBy(xpath = "//span[normalize-space()='Should be lower than Maximum Salary']")
    WebElement messageLowerThan;

    @FindBy(xpath = "//span[normalize-space()='Should be higher than Minimum Salary']")
    WebElement messageHigherThan;

    @FindBy(xpath = "//span[normalize-space()='Should be less than 1,000,000,000']")
    WebElement messageShouldBeLessThan;

    @FindBy(xpath = "//div[contains(@class,'oxd-toast') or contains(@class,'orangehrm-toast')]")
    WebElement infoPopup;

    @FindBy(xpath = "(//button[@type='submit'][normalize-space()='Save'])[2]")
    WebElement btnSave;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Cancel'])[2]")
    WebElement btnCancel;

    public String getTextTitleAddCurrency(){
        return elementUtils.getTextOfElement(titleAddCurrency);
    }

    public boolean displayTitleAddCurrency(){
        return elementUtils.isElementDisplayed(titleAddCurrency);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public void selectOptionCurrency(String optionText) {
        elementUtils.selectOptionFromDropdown(dropdownCurrency, optionText);
    }


    public void enterInputMinimumSalary(String textMinimumSalary) {
        elementUtils.enterInputElement(inputMinimumSalary, textMinimumSalary);
    }

    public void enterInputMaximumSalary(String textMaximumSalary) {
        elementUtils.enterInputElement(inputMaximumSalary, textMaximumSalary);
    }

    public String getTextMessageRequired(){
        wait.until(ExpectedConditions.visibilityOf(messageRequired));
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageShouldBeNumberMinimumSalary(){
        wait.until(ExpectedConditions.visibilityOf(infoPopup));
        return elementUtils.getTextOfElement(messageShouldBeNumber);
    }

    public String getTextMessageShouldBeNumberMaximumSalary(){
        wait.until(ExpectedConditions.visibilityOf(infoPopup));
        return elementUtils.getTextOfElement(messageShouldBeNumber);
    }

    public String getTextMessageLowerThan(){
        wait.until(ExpectedConditions.visibilityOf(infoPopup));
        return elementUtils.getTextOfElement(messageLowerThan);
    }

    public String getTextMessageHigherThan(){
        wait.until(ExpectedConditions.visibilityOf(infoPopup));
        return elementUtils.getTextOfElement(messageHigherThan);
    }

    public String getTextMessageShouldBeLessThanMinimumSalary(){
        wait.until(ExpectedConditions.visibilityOf(infoPopup));
        return elementUtils.getTextOfElement(messageShouldBeLessThan);
    }

    public String getTextMessageShouldBeLessThanMaximumSalary(){
        wait.until(ExpectedConditions.visibilityOf(infoPopup));
        return elementUtils.getTextOfElement(messageShouldBeLessThan);
    }

    public EditPayGradesPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new EditPayGradesPage(driver);
    }

    public EditPayGradesPage clickBtnSave(){
        wait.until(ExpectedConditions.visibilityOf(infoPopup));
        elementUtils.clickOnElement(btnSave);
        return new EditPayGradesPage(driver);
    }


}
