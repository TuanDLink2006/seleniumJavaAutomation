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

public class ContactDetailPage {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;
    ElementUtils elementUtils;

    public ContactDetailPage(WebDriver driver){
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Contact Details']")
    WebElement titleContactDetails;

    @FindBy(xpath = "//h6[normalize-space()='Address']")
    WebElement titleAddress;

    @FindBy(xpath = "//label[text()='Street 1']/following::input[1]")
    WebElement inputStreet1;

    @FindBy(xpath = "//label[text()='Street 2']/following::input[1]")
    WebElement inputStreet2;

    @FindBy(xpath = "//label[text()='City']/following::input[1]")
    WebElement inputCity;

    @FindBy(xpath = "//label[text()='State/Province']/following::input[1]")
    WebElement inputState;

    @FindBy(xpath = "//label[text()='Zip/Postal Code']/following::input[1]")
    WebElement inputZip;

    @FindBy(xpath = "//div[@class='oxd-select-text oxd-select-text--active']")
    WebElement dropdownCountry;

    @FindBy(xpath = "//h6[normalize-space()='Telephone']")
    WebElement titleTelephone;

    @FindBy(xpath = "(//input[@modelmodifiers='[object Object]'])[1]")
    WebElement inputHome;

    @FindBy(xpath = "//label[text()='Mobile']/following::input[1]")
    WebElement inputMobile;

    @FindBy(xpath = "//label[text()='Work']/following::input[1]")
    WebElement inputWork;

    @FindBy(xpath = "//h6[normalize-space()='Email']")
    WebElement titleEmail;

    @FindBy(xpath = "//label[text()='Work Email']/following::input[1]")
    WebElement inputWorkEmail;

    @FindBy(xpath = "//label[text()='Other Email']/following::input[1]")
    WebElement inputOtherEmail;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 70 characters']")
    WebElement messageExceed70Characters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 10 characters']")
    WebElement messageExceed10Characters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 25 characters']")
    WebElement messageExceed25Characters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 50 characters']")
    WebElement messageExceed50Characters;

    @FindBy(xpath = "//span[normalize-space()='Expected format: admin@example.com']")
    WebElement messageExpectedFormatWorkEmail;

    @FindBy(xpath = "//span[normalize-space()='Allows numbers and only + - / ( )']")
    WebElement messageAllowNumber;

    @FindBy(xpath = "//span[normalize-space()='Expected format: admin@example.com']")
    WebElement messageExpectedFormatOtherEmail;

    @FindBy(xpath = "//span[normalize-space()='Already exists']")
    WebElement messageEmailExist;

    @FindBy(xpath = "//span[normalize-space()='Work Email and Other Email cannot be the same']")
    WebElement messageEmailSame;

    @FindBy(xpath = "(//button[@type='submit'][normalize-space()='Save'])[1]")
    WebElement btnSaveContactDetail;

    @FindBy(xpath = "//h6[normalize-space()='Attachments']")
    WebElement titleAttachment;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement btnAddAttachment;

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    List<WebElement> rows;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleContactDetails(){
        return elementUtils.isElementDisplayed(titleContactDetails);
    }

    public String getTextTitleContactDetails(){
        return elementUtils.getTextOfElement(titleContactDetails);
    }

    public boolean displayTitleAddress(){
        return elementUtils.isElementDisplayed(titleAddress);
    }

    public String getTextTitleAddress(){
        return elementUtils.getTextOfElement(titleAddress);
    }

    public boolean displayTitleTelephone(){
        return elementUtils.isElementDisplayed(titleTelephone);
    }

    public String getTextTitleTelephone(){
        return elementUtils.getTextOfElement(titleTelephone);
    }

    public boolean displayTitleEmail(){
        return elementUtils.isElementDisplayed(titleEmail);
    }

    public String getTextTitleEmail(){
        return elementUtils.getTextOfElement(titleEmail);
    }

    public boolean displayTitleAttachment(){
        return elementUtils.isElementDisplayed(titleAttachment);
    }

    public String getTextTitleAttachment(){
        return elementUtils.getTextOfElement(titleAttachment);
    }

    public void clearInputStreet1(){
        elementUtils.clearInput(inputStreet1);
    }

    public void clearInputStreet2(){
        elementUtils.clearInput(inputStreet2);
    }

    public void clearInputCity(){
        elementUtils.clearInput(inputCity);
    }

    public void clearInputState(){
        elementUtils.clearInput(inputState);
    }

    public void clearInputZip(){
        elementUtils.clearInput(inputZip);
    }

    public void clearInputHome(){
        elementUtils.clearInput(inputHome);
    }

    public void clearInputMobile(){
        elementUtils.clearInput(inputMobile);
    }

    public void clearInputWork(){
        elementUtils.clearInput(inputWork);
    }

    public void enterInputStreet1(String textStreet){
        elementUtils.enterInputElement(inputStreet1, textStreet);
    }

    public void enterInputStreet2(String textStreet){
        elementUtils.enterInputElement(inputStreet2, textStreet);
    }

    public void enterInputCity(String textCity){
        elementUtils.enterInputElement(inputCity, textCity);
    }

    public void enterInputState(String textState){
        elementUtils.enterInputElement(inputState, textState);
    }

    public void enterInputZip(String textZip){
        elementUtils.enterInputElement(inputZip, textZip);
    }

    public void enterInputHome(String textHome){
        elementUtils.enterInputElement(inputHome, textHome);
    }

    public void enterInputMobile(String textMobile){
        elementUtils.enterInputElement(inputMobile, textMobile);
    }

    public void enterInputWork(String textWork){
        elementUtils.enterInputElement(inputWork, textWork);
    }

    public void enterInputWorkEmail(String textWorkEmail){
        elementUtils.enterInputAdmin(inputWorkEmail, textWorkEmail);
    }

    public void enterInputOtherEmail(String textOtherEmail){
        elementUtils.enterInputAdmin(inputOtherEmail, textOtherEmail);
    }

    public void selectCountryOption(String countryOption) {
        elementUtils.selectOptionFromDropdown(dropdownCountry, countryOption);
    }

    public void clickBtnSaveContactDetail(){
        elementUtils.clickOnElement(btnSaveContactDetail);
    }

    public String getTextMessageExceed70CharactersInputStreet1(){
        return elementUtils.getTextOfElement(messageExceed70Characters);
    }

    public String getTextMessageExceed70CharactersInputStreet2(){
        return elementUtils.getTextOfElement(messageExceed70Characters);
    }

    public String getTextMessageExceed70CharactersInputCity(){
        return elementUtils.getTextOfElement(messageExceed70Characters);
    }

    public String getTextMessageExceed70CharactersInputState(){
        return elementUtils.getTextOfElement(messageExceed70Characters);
    }

    public String getTextMessageExceed10Characters(){
        return elementUtils.getTextOfElement(messageExceed10Characters);
    }

    public String getTextMessageExceed25CharactersHome(){
        return elementUtils.getTextOfElement(messageExceed25Characters);
    }

    public String getTextMessageExceed25CharactersMobile(){
        return elementUtils.getTextOfElement(messageExceed25Characters);
    }

    public String getTextMessageExceed25CharactersWork(){
        return elementUtils.getTextOfElement(messageExceed25Characters);
    }

    public String getTextMessageExceed50CharactersWorkEmail(){
        return elementUtils.getTextOfElement(messageExceed50Characters);
    }

    public String getTextMessageExceed50CharactersOtherEmail(){
        return elementUtils.getTextOfElement(messageExceed50Characters);
    }

    public String getTextMessageExpectedFormatWorkEmail(){
        return elementUtils.getTextOfElement(messageExpectedFormatWorkEmail);
    }

    public String getTextMessageExpectedFormatOtherEmail(){
        return elementUtils.getTextOfElement(messageExpectedFormatOtherEmail);
    }

    public String getTextMessageEmailExist(){
        return elementUtils.getTextOfElement(messageEmailExist);
    }

    public String getTextMessageEmailSame(){
        wait.until(ExpectedConditions.visibilityOf(messageEmailSame));
        return elementUtils.getTextOfElement(messageEmailSame);
    }

    public String getTextMessageAllowNumberHome(){
        return elementUtils.getTextOfElement(messageAllowNumber);
    }

    public String getTextMessageAllowNumberMobile(){
        return elementUtils.getTextOfElement(messageAllowNumber);
    }

    public String getTextMessageAllowNumberWork(){
        return elementUtils.getTextOfElement(messageAllowNumber);
    }

    public AddAttachmentPage clickBtnAddAttachment(){
        elementUtils.clickOnElement(btnAddAttachment);
        return new AddAttachmentPage(driver);
    }

    public boolean displayTable(){
        return elementUtils.isElementDisplayed(table);
    }

    public int getSizeRows(){
        return rows.size();
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }
}
