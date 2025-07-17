package pages;

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

public class AddLicenseQualificationsPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;

    public AddLicenseQualificationsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add License']")
    WebElement titleAddLicense;

    @FindBy(xpath = "//label[text()='License Type']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement dropdownLicenseType;

    @FindBy(xpath = "//div[@role='option']")
    List<WebElement> licenses;

    @FindBy(xpath = "//label[text()='License Number']/following::input[1]")
    WebElement inputLicenseNumber;

    @FindBy(xpath = "//label[text()='Issued Date']/following::input[1][@placeholder='yyyy-dd-mm']")
    WebElement issuedDate;

    @FindBy(xpath = "//label[text()='Expiry Date']/following::input[1][@placeholder='yyyy-dd-mm']")
    WebElement expiryDate;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-icon-button__icon'])[1]")
    WebElement monthDropdown;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-icon-button__icon'])[2]")
    WebElement yearDropdown;

    @FindBy(xpath = "//ul//li")
    List<WebElement> years;

    @FindBy(xpath = "//ul//li")
    List<WebElement> months;

    @FindBy(xpath = "//div[contains(@class,'oxd-calendar-date-wrapper')]")
    List<WebElement> days;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[normalize-space()='Should be a valid date in yyyy-dd-mm format']")
    WebElement messageShouldBe;

    @FindBy(xpath = "//span[normalize-space()='Expiry date should be after issued date']")
    WebElement messageExpiryDate;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Cancel'])[5]")
    WebElement btnCancelAddLicense;

    @FindBy(xpath = "(//button[@type='submit'][normalize-space()='Save'])[5]")
    WebElement btnSaveAddLicense;

    public boolean displayTitleAddLicense(){
        return elementUtils.isElementDisplayed(titleAddLicense);
    }

    public String getTextTitleAddLicense(){
        return elementUtils.getTextOfElement(titleAddLicense);
    }

    public boolean displayBtnCancelAddLicense(){
        return elementUtils.isElementDisplayed(btnCancelAddLicense);
    }

    public boolean displayBtnSaveAddLicense(){
        return elementUtils.isElementDisplayed(btnSaveAddLicense);
    }

    public void selectLicenseTypeOption(String licenseOption) {
        elementUtils.selectOptionFromDropdown(dropdownLicenseType, licenseOption);
    }

    public void enterInputLicenseNumber(String textLicenseNumber){
        elementUtils.enterInputElement(inputLicenseNumber, textLicenseNumber);
    }

    public void clickIssuedDate(){
        elementUtils.clickOnElement(issuedDate);
    }

    public void clickExpiryDate(){
        elementUtils.clickOnElement(expiryDate);
    }

    public void enterIssuedDate(String textIssuedDate){
        clickIssuedDate();
        elementUtils.enterInputElement(issuedDate, textIssuedDate);
    }

    public void enterExpiryDate(String textExpiryDate){
        clickExpiryDate();
        elementUtils.enterInputElement(expiryDate, textExpiryDate);
    }

    public void selectIssuedDate(String day, String month, String year) {
        elementUtils.selectDate(issuedDate, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void selectExpiryDate(String day, String month, String year) {
        elementUtils.selectDate(expiryDate, yearDropdown, years, monthDropdown, months, days, day,month, year);
    }

    public String getTextMessageRequired(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageShouldBeFormatIssuedDate(){
        return elementUtils.getTextOfElement(messageShouldBe);
    }

    public String getTextMessageShouldBeFormatExpiryDate(){
        return elementUtils.getTextOfElement(messageShouldBe);
    }

    public String getTextMessageExpiryDate(){
        return elementUtils.getTextOfElement(messageExpiryDate);
    }

    public QualificationsPage clickBtnCancelAddLicense(){
        elementUtils.clickOnElement(btnCancelAddLicense);
        return new QualificationsPage(driver);
    }

    public QualificationsPage clickBtnSaveAddLicense(){
        elementUtils.clickOnElement(btnSaveAddLicense);
        return new QualificationsPage(driver);
    }

}
