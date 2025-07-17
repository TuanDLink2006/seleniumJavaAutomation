package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class AddEmploymentStatusPage {
    WebDriver driver;
    ElementUtils elementUtils;
    WebDriverWait wait;
    public AddEmploymentStatusPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Employment Status']")
    WebElement titleAddEmploymentStatus;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active']")
    WebElement inputName;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageExceedCharacter;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageExist;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleAddEmployeeStatus(){
        return elementUtils.isElementDisplayed(titleAddEmploymentStatus);
    }

    public String getTextTitleAddEmployeeStatus(){
        return elementUtils.getTextOfElement(titleAddEmploymentStatus);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public String getMessageRequiredEmployeeStatus(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageExistEmployeeStatus(){
        return elementUtils.getTextOfElement(messageExist);
    }

    public String getMessageExceedCharacter(){
        return elementUtils.getTextOfElement(messageExceedCharacter);
    }

    public void enterInputEmployeeStatus(String textEmployeeStatus){
        elementUtils.enterInputElement(inputName, textEmployeeStatus);
    }

    public EmploymentStatusPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new EmploymentStatusPage(driver);
    }

    public EmploymentStatusPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new EmploymentStatusPage(driver);
    }

    public String getTextAddEmployeeStatusSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
