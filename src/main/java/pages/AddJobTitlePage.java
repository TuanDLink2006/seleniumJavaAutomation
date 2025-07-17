package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.ElementUtils;

import java.time.Duration;

public class AddJobTitlePage {
    WebDriver driver;
    ElementUtils elementUtils;
    WebDriverWait wait;

    public AddJobTitlePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Job Title']")
    WebElement titleAddJobTitle;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active']")
    WebElement inputJobTitle;

    @FindBy(xpath = "//textarea[@placeholder='Type description here']")
    WebElement jobDescription;

    @FindBy(xpath = "//input[@type='file']")
    WebElement fileJobSpecification;

    @FindBy(xpath = "//textarea[@placeholder='Add note']")
    WebElement addNote;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageExist;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageExceedCharacters;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageFileType;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public String getTextTitleAddJobTitle(){
        return elementUtils.getTextOfElement(titleAddJobTitle);
    }

    public boolean displayAddJobTitle(){
        return elementUtils.isElementDisplayed(titleAddJobTitle);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public String getMessageRequiredJobTitle(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageExistJobTitle(){
        return elementUtils.getTextOfElement(messageExist);
    }

    public String getMessageFileType(){
        return elementUtils.getTextOfElement(messageFileType);
    }

    public String getMessageExceedCharactersJobTitle(){
        return elementUtils.getTextOfElement(messageExceedCharacters);
    }

    public String getMessageExceedCharactersJobDescription(){
        return elementUtils.getTextOfElement(messageExceedCharacters);
    }

    public String getMessageExceedCharactersNote(){
        return elementUtils.getTextOfElement(messageExceedCharacters);
    }

    public void enterInputJobTitle(String textJobTitle){
        elementUtils.enterInputElement(inputJobTitle, textJobTitle);
    }

    public void enterJobDescription(String textJobDescription){
        elementUtils.enterInputElement(jobDescription, textJobDescription);
    }

    public void uploadFileJobSpecification(String filePath){
        elementUtils.selectElementFile(fileJobSpecification, filePath);
    }

    public void enterAddNote(String textAddNote){
        elementUtils.enterTextarea(addNote, textAddNote);
    }

    public JobTitleAdminPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new JobTitleAdminPage(driver);
    }

    public JobTitleAdminPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new JobTitleAdminPage(driver);
    }

    public String getTextAddTitleSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
