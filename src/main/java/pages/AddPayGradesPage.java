package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class AddPayGradesPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public AddPayGradesPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Pay Grade']")
    WebElement titleAddPayGrades;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active']")
    WebElement inputName;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageRequiredName;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageExist;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageExceedCharacters;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleAddPayGrades(){
        return elementUtils.isElementDisplayed(titleAddPayGrades);
    }

    public String getTextTitleAddPayGrades(){
        return elementUtils.getTextOfElement(titleAddPayGrades);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public void enterInputName(String textName){
        elementUtils.enterInputElement(inputName, textName);
    }

    public String getMessageRequiredName(){
        return elementUtils.getTextOfElement(messageRequiredName);
    }

    public String getMessageExistPayGrades(){
        return elementUtils.getTextOfElement(messageExist);
    }

    public String getMessageExceedCharacters(){
        return elementUtils.getTextOfElement(messageExceedCharacters);
    }

    public PayGradesPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new PayGradesPage(driver);
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

