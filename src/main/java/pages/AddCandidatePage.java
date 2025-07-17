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

public class AddCandidatePage {
    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public AddCandidatePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Candidate']")
    WebElement titleAddCandidate;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement inputFirstName;

    @FindBy(xpath = "//input[@placeholder='Middle Name']")
    WebElement inputMiddleName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement inputLastName;

    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
    WebElement dropdownVacancy;

    @FindBy(xpath = "//label[text()='Email']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputEmail;

    @FindBy(xpath = "//label[text()='Contact Number']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputContactNumber;

    @FindBy(xpath = "//input[@type='file']")
    WebElement fileResume;

    @FindBy(xpath = "//input[@placeholder='Enter comma seperated words...']")
    WebElement inputKeywords;

    @FindBy(xpath = "//label[normalize-space()='Date of Application']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement dateOfApplication;

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

    @FindBy(xpath = "//textarea[@placeholder='Type here']")
    WebElement notes;

    @FindBy(xpath = "//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']")
    WebElement consentToKeepData;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageFormatDatePicker;

    @FindBy(xpath = "//span[normalize-space()='Should be the current date or a previous date']")
    WebElement messageCurrentDatePicker;

    @FindBy(xpath = "//span[normalize-space()='Allows numbers and only + - / ( )']")
    WebElement messageAllowsNumbers;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 30 characters']")
    WebElement messageShouldExceed30XCharacters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 50 characters']")
    WebElement messageShouldExceed50XCharacters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 25 characters']")
    WebElement messageShouldExceed25XCharacters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 250 characters']")
    WebElement messageShouldExceed250XCharacters;;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//div[normalize-space()='Required']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[normalize-space()='Expected format: admin@example.com']")
    WebElement expectedFormatEmail;

    @FindBy(xpath = "//span[normalize-space()='File type not allowed']")
    WebElement messageFileType;

    @FindBy(xpath = "//span[normalize-space()='Attachment Size Exceeded']")
    WebElement messageAttachment;

    public String getTextTitleAddCandidate(){
        return elementUtils.getTextOfElement(titleAddCandidate);
    }

    public boolean displayTitleAddCandidate(){
         return elementUtils.isElementDisplayed(titleAddCandidate);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public void enterInputFirstName(String textName){
        elementUtils.enterInputElement(inputFirstName, textName);
    }

    public void enterInputMiddleName(String textName){
        elementUtils.enterInputElement(inputMiddleName, textName);
    }

    public void enterInputLastName(String textName){
        elementUtils.enterInputElement(inputLastName, textName);
    }

    public void selectOptionVacancy(String vacancyOption){
        elementUtils.selectOptionFromDropdown(dropdownVacancy, vacancyOption);
    }

    public void enterInputEmail(String textEmail){
        elementUtils.enterInputElement(inputEmail, textEmail);
    }

    public void enterInputContactNumber(String textContactNumber){
        elementUtils.enterInputElement(inputContactNumber, textContactNumber);
    }

    public void uploadFileResume(String filePath){
        elementUtils.selectElementFile(fileResume, filePath);
    }

    public void enterInputKeywords(String textKeyword){
        elementUtils.enterInputElement(inputKeywords, textKeyword);
    }

    public void enterInputDateOfApplication(String text){
        elementUtils.enterInputElement(dateOfApplication, text);
        elementUtils.clickOnElement(inputKeywords);
    }
    
    public void selectDateOfApplication(String day, String month, String year){
        elementUtils.selectDate(dateOfApplication, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public String getTextMessageAllowsNumbers(){
        return elementUtils.getTextOfElement(messageAllowsNumbers);
    }

    public String getTextMessageShouldExceed30XCharactersFirstName(){
        return elementUtils.getTextOfElement(messageShouldExceed30XCharacters);
    }

    public String getTextMessageShouldExceed30XCharactersMiddleName(){
        return elementUtils.getTextOfElement(messageShouldExceed30XCharacters);
    }

    public String getTextMessageShouldExceed30XCharactersLastName(){
        return elementUtils.getTextOfElement(messageShouldExceed30XCharacters);
    }

    public String getTextMessageShouldExceed50XCharacters(){
        return elementUtils.getTextOfElement(messageShouldExceed50XCharacters);
    }

    public String getTextMessageShouldExceed25XCharacters(){
        return elementUtils.getTextOfElement(messageShouldExceed25XCharacters);
    }

    public String getTextMessageShouldExceed250XCharactersKeywords(){
        return elementUtils.getTextOfElement(messageShouldExceed250XCharacters);
    }

    public String getTextMessageShouldExceed250XCharactersNotes(){
        return elementUtils.getTextOfElement(messageShouldExceed250XCharacters);
    }

    public String getTextMessageFormatDatePicker(){
        return elementUtils.getTextOfElement(messageFormatDatePicker);
    }

    public String getTextMessageCurrentDatePicker(){
        return elementUtils.getTextOfElement(messageCurrentDatePicker);
    }

    public String getMessageRequiredFirstName(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageRequiredLastName(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageRequiredEmail(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageExpectedFormatEmail(){
        return elementUtils.getTextOfElement(expectedFormatEmail);
    }

    public String getMessageFileType(){
        return elementUtils.getTextOfElement(messageFileType);
    }

    public String getMessageAttachment(){
        return elementUtils.getTextOfElement(messageAttachment);
    }

    public void enterNotes(String textNotes){
        elementUtils.enterTextarea(notes, textNotes);
    }

    public void clickOnCheckboxConsent(){
       elementUtils.clickOnCheckbox(consentToKeepData);
    }

    public void clickOffCheckboxConsent(){
        elementUtils.clickOffCheckbox(consentToKeepData);
    }

    public CandidatesPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new CandidatesPage(driver);
    }

    public ViewCandidatesPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new ViewCandidatesPage(driver);
    }
}
