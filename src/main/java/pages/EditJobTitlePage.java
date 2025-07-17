package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class EditJobTitlePage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public EditJobTitlePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Edit Job Title']")
    WebElement tileEditJobTitle;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active']")
    WebElement inputJobTitle;

    @FindBy(xpath = "//div[@class='orangehrm-file-preview']")
    WebElement filePreview;

    @FindBy(xpath = "//i[@class='oxd-icon bi-download orangehrm-file-download']")
    WebElement iconDownload;

    @FindBy(xpath = "//label[normalize-space()='Keep Current']")
    WebElement radioButtonKeepCurrent;

    @FindBy(xpath = "//label[normalize-space()='Delete Current']")
    WebElement radioButtonDeleteCurrent;

    @FindBy(xpath = "//label[normalize-space()='Replace Current']")
    WebElement radioReplaceCurrent;

    @FindBy(xpath = "//input[@type='file']")
    WebElement fileUpload;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleEditJobTitle(){
        return elementUtils.isElementDisplayed(tileEditJobTitle);
    }

    public String getTextTitleEditJobTitle(){
        return elementUtils.getTextOfElement(tileEditJobTitle);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public void enterJobTitle(String textJobTitle){
        elementUtils.enterInputAdmin(inputJobTitle, textJobTitle);
    }

    public boolean displayFilePreview(){
        return elementUtils.isElementDisplayed(filePreview);
    }

    public void clickOnRadioButtonKeepCurrent(){
        elementUtils.clickRadioButton(radioButtonKeepCurrent);
    }

    public void clickOnRadioButtonDeleteCurrent(){
        elementUtils.clickRadioButton(radioButtonDeleteCurrent);
    }

    public void clickOnRadioReplaceCurrent(){
        elementUtils.clickRadioButton(radioReplaceCurrent);
    }

    public void uploadLoadJobSpecification(String filePath){
        elementUtils.selectElementFile(fileUpload, filePath);
    }

    public JobTitleAdminPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new JobTitleAdminPage(driver);
    }

    public JobTitleAdminPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new JobTitleAdminPage(driver);
    }

    public String getTextEditSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
