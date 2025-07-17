package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class EditEmployeeStatusPage {

    WebDriver driver;
    ElementUtils elementUtils;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions actions;
    public EditEmployeeStatusPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor)driver;
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Edit Employment Status']")
    WebElement titleEditEmploymentStatus;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active']")
    WebElement inputEmployeeStatus;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleEditEmploymentStatus(){
        return elementUtils.isElementDisplayed(titleEditEmploymentStatus);
    }

    public String getTextTitleEditEmploymentStatus(){
        return elementUtils.getTextOfElement(titleEditEmploymentStatus);
    }

    public boolean displayBtnCancelEditEmploymentStatus(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSaveEditEmploymentStatus(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public void enterEmployeeStatus(String textEmployeeStatus) {
        elementUtils.enterInputAdmin(inputEmployeeStatus, textEmployeeStatus);
    }

    public EmploymentStatusPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new EmploymentStatusPage(driver);
    }

    public EmploymentStatusPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new EmploymentStatusPage(driver);
    }

    public String getTextEditSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }


}
