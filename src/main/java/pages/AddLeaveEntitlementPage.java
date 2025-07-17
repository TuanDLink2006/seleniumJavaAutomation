package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ElementUtils;

import javax.print.DocFlavor;
import java.time.Duration;
import java.util.List;
import java.util.logging.XMLFormatter;

public class AddLeaveEntitlementPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public AddLeaveEntitlementPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-main-title']")
    WebElement titleLeaveEntitlement;

    @FindBy(xpath = "//label[normalize-space()='Individual Employee']")
    WebElement individualEmployee;

    @FindBy(xpath = "//label[normalize-space()='Multiple Employees']")
    WebElement multipleEmployees;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement inputEmployeeName;

    @FindBy(xpath = "//div[@role='listbox']")
    WebElement listBox;

    @FindBy(xpath = "//div[@role='option']")
    List<WebElement> options;

    @FindBy(xpath = "//label[text()='Location']/following::div[contains(@class,'oxd-select-text-input')][1]")
    WebElement dropdownLocation;

    @FindBy(xpath = "//label[text()='Sub Unit']/following::div[contains(@class,'oxd-select-text-input')][1]")
    WebElement dropdownSubUnit;

    @FindBy(xpath = "//label[text()='Leave Type']/following::div[contains(@class,'oxd-select-text-input')][1]")
    WebElement dropdownLeaveType;

    @FindBy(xpath = "//label[text()='Leave Period']/following::div[contains(@class,'oxd-select-text-input')][1]")
    WebElement dropdownLeavePeriod;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active']")
    WebElement inputEntitlement;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//span[normalize-space()='Required']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[normalize-space()='Invalid']")
    WebElement messageInvalid;

    @FindBy(xpath = "//span[normalize-space()='Should be a number with upto 2 decimal places']")
    WebElement messageShouldBeNumber;

    @FindBy(xpath = "//span[normalize-space()='Should be less than 10000']")
    WebElement messageShouldBeLessThan;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    @FindBy(xpath = "//div[@role='document']")
    WebElement updatingEntitlement;

    @FindBy(xpath = "//button[normalize-space()='Confirm']")
    WebElement btnConfirm;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancelUpdatingEntitlement;

    public String getTextTitleAddEntitlement(){
        return elementUtils.getTextOfElement(titleLeaveEntitlement);
    }

    public boolean displayTitleEntitlement(){
        return elementUtils.isElementDisplayed(titleLeaveEntitlement);
    }

    public boolean isSelectedIndividualEmployee(){
        return elementUtils.isElementSelected(individualEmployee);
    }

    public void clickIndividualEmployee(){
        elementUtils.clickRadioButton(individualEmployee);
    }

    public void clickMultipleEmployees(){
        elementUtils.clickRadioButton(multipleEmployees);
    }

    public String getTextMessageRequiredEmployee(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageRequiredLeaveType(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageRequiredPeriod(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageRequiredEntitlement(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageInvalid(){
        return elementUtils.getTextOfElement(messageInvalid);
    }

    public String getTextMessageShouldBeNumber(){
        return elementUtils.getTextOfElement(messageShouldBeNumber);
    }

    public String getTextMessageShouldBeLessThan(){
        return elementUtils.getTextOfElement(messageShouldBeLessThan);
    }

    public void inputEmployee(String textName, String expectedFullName) throws InterruptedException {
        elementUtils.inputIdElement(inputEmployeeName, listBox, options, textName, expectedFullName);
    }

    public void selectLocation(String location){
        elementUtils.selectOptionFromDropdown(dropdownLocation, location);
    }

    public void selectSubUnit(String subUnit){
        elementUtils.selectOptionFromDropdown(dropdownSubUnit, subUnit);
    }

    public void selectLeaveType(String leaveType){
        elementUtils.selectOptionFromDropdown(dropdownLeaveType, leaveType);
    }

    public void selectLeavePeriod(String leavePeriod){
        elementUtils.selectOptionFromDropdown(dropdownLeavePeriod,leavePeriod);
    }

    public void enterEntitlement(String textEntitlement){
        elementUtils.enterInputElement(inputEntitlement, textEntitlement);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public void clickBtnSave(){
       elementUtils.clickOnElement(btnSave);
    }

    public boolean displayUpdatingEntitlement(){
        return elementUtils.isElementDisplayed(updatingEntitlement);
    }

    public void clickBtnConfirm(){
        elementUtils.clickOnElement(btnConfirm);
    }

    public AddLeaveEntitlementPage clickBtnCancelUpdatingEntitlement(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnCancelUpdatingEntitlement);
        return new AddLeaveEntitlementPage(driver);
    }

    public void clickTitle(){
        elementUtils.clickOnElement(titleLeaveEntitlement);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
