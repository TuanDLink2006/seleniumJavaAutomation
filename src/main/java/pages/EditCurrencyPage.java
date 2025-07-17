package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class EditCurrencyPage {

    WebDriver driver;
    ElementUtils elementUtils;
    WebDriverWait wait;
    public EditCurrencyPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Edit Currency']")
    WebElement titleEditCurrent;

    @FindBy(xpath = "//div[@class='oxd-input oxd-input--active']")
    WebElement inputCurrency;

    @FindBy(xpath = "//label[text()='Minimum Salary']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputMinimumSalary;

    @FindBy(xpath = "//label[text()='Maximum Salary']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputMaximumSalary;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public String getTextTitleEditCurrent(){
        return elementUtils.getTextOfElement(titleEditCurrent);
    }

    public boolean displayTitleEditCurrent(){
        return elementUtils.isElementDisplayed(titleEditCurrent);
    }

    public boolean disableInputCurrency(){
        return elementUtils.isElementDisabled(inputCurrency);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public void enterMinimumSalary(long textMinimumSalary){
        elementUtils.enterInputAdmin(inputMinimumSalary, String.valueOf(textMinimumSalary));
    }

    public void enterMaximumSalary(long textMaximumSalary){
        elementUtils.enterInputAdmin(inputMaximumSalary, String.valueOf(textMaximumSalary));
    }

    public EditPayGradesPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new EditPayGradesPage(driver);
    }

    public EditPayGradesPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new EditPayGradesPage(driver);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }
}
