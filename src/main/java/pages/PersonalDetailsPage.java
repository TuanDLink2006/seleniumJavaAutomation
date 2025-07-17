package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import javax.print.DocFlavor;
import java.time.Duration;
import java.util.List;

public class PersonalDetailsPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    ElementUtils elementUtils;
    public PersonalDetailsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Personal Details']")
    WebElement titlePersonalDetails;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement inputFirstName;

    @FindBy(xpath = "//input[@placeholder='Middle Name']")
    WebElement inputMiddleName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement inputLastName;

    @FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
    WebElement inputEmployeeId;

    @FindBy(xpath = "//label[text()='Other Id']/following::input[1]")
    WebElement inputOtherId;

    @FindBy(xpath = "//label[text()=concat(\"Driver\", \"'\", \"s License Number\")]/following::input[1]")
    WebElement inputDriveLicense;

    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[1]")
    WebElement licenseDate;

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

    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[1]")
    WebElement dropdownNationality;

    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[2]")
    WebElement dropdownMaritalStatus;

    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[2]")
    WebElement dateOfBirth;

    @FindBy(xpath = "//label[normalize-space()='Male']")
    WebElement male;

    @FindBy(xpath = "//label[normalize-space()='Female']")
    WebElement female;

    @FindBy(xpath = "(//button[@type='submit'][normalize-space()='Save'])[1]")
    WebElement btnSavePersonalDetail;

    @FindBy(xpath = "//span[normalize-space()='Required']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 30 characters']")
    WebElement messageExceed30Characters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 10 characters']")
    WebElement messageExceed10Characters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 250 characters']")
    WebElement messageExceed250Characters;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageShouldBeFormat;

    @FindBy(xpath = "//h6[normalize-space()='Custom Fields']")
    WebElement titleCustomFields;

    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[3]")
    WebElement dropdownBloodType;

    @FindBy(xpath = "//div[@role='option']")
    List<WebElement> options;

    @FindBy(xpath = "//label[text()='Test_Field']/following::input[1]")
    WebElement testField;

    @FindBy(xpath = "(//button[@type='submit'][normalize-space()='Save'])[2]")
    WebElement btnSaveCustomFields;

    @FindBy(xpath = "//h6[normalize-space()='Attachments']")
    WebElement titleAttachments;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement btnAddAttachments;

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    List<WebElement> rows;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitlePersonalDetail(){
        return elementUtils.isElementDisplayed(titlePersonalDetails);
    }

    public String getTextTitlePersonalDetail(){
        return elementUtils.getTextOfElement(titlePersonalDetails);
    }

    public String getTextMessageRequiredFirstName(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageRequiredLastName(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageExceed30CharactersFirstName(){
        return elementUtils.getTextOfElement(messageExceed30Characters);
    }

    public String getTextMessageExceed30CharactersMiddleName(){
        return elementUtils.getTextOfElement(messageExceed30Characters);
    }

    public String getTextMessageExceed30CharactersLastName(){
        return elementUtils.getTextOfElement(messageExceed30Characters);
    }

    public String getTextMessageExceed30CharactersOtherId(){
        return elementUtils.getTextOfElement(messageExceed30Characters);
    }

    public String getTextMessageExceed30CharactersDriverLicenseNumber(){
        return elementUtils.getTextOfElement(messageExceed30Characters);
    }

    public String getTextMessageExceed10Characters(){
        return elementUtils.getTextOfElement(messageExceed10Characters);
    }

    public String getTextMessageExceed250Characters(){
        return elementUtils.getTextOfElement(messageExceed250Characters);
    }

    public String getTextMessageShouldBeFormatLicenseExpiryDate(){
        return elementUtils.getTextOfElement(messageShouldBeFormat);
    }

    public String getTextMessageShouldBeFormatDateOfBirth(){
        return elementUtils.getTextOfElement(messageShouldBeFormat);
    }

    public void clearInputFirstName(){
        elementUtils.clearInput(inputFirstName);
    }

    public void clearInputLastName(){
        elementUtils.clearInput(inputLastName);
    }

    public void enterInputFirstName(String textFirstName){
        elementUtils.enterInputEdit(inputFirstName, textFirstName);
    }

    public void enterInputMiddleName(String textName){
        elementUtils.enterInputEdit(inputMiddleName, textName);
    }

    public void enterInputLastName(String textName){
        elementUtils.enterInputEdit(inputLastName, textName);
    }

    public void enterInputEmployeeId(String textEmployeeId){
        elementUtils.enterInputEdit(inputEmployeeId, textEmployeeId);
    }

    public void enterInputOtherId(String id){
        elementUtils.enterInputEdit(inputOtherId, id);
    }

    public void enterInputDriveLicense(String driveLicense){
        elementUtils.enterInputEdit(inputDriveLicense, driveLicense);
    }

    public void enterLicenseExpiryDate(String textLicense){
        elementUtils.enterInputElement(licenseDate, textLicense);
    }

    public void enterDateOfBirth(String textBirth){
        elementUtils.enterInputElement(dateOfBirth, textBirth);
        elementUtils.clickRadioButton(male);
    }

    public void selectLicenseExpiryDate(String day, String month, String year) {
        elementUtils.selectDate(licenseDate, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void selectNationalityOptions(String optionNationality){
        elementUtils.selectOptionFromDropdown(dropdownNationality, optionNationality);
    }

    public void selectMaritalStatusOptions(String optionMaritalStatus){
        elementUtils.selectOptionFromDropdown(dropdownMaritalStatus, optionMaritalStatus);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        elementUtils.selectDate(dateOfBirth, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void clickRadioButtonMale(){
        elementUtils.clickRadioButton(male);
    }

    public void clickRadioButtonFemale(){
        elementUtils.clickRadioButton(female);
    }

    public void clickBtnSavePersonalDetails(){

        wait.until(ExpectedConditions.elementToBeClickable(btnSavePersonalDetail));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSavePersonalDetail);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnSavePersonalDetail);
    }

    public boolean displayTitleCustomFields(){
        return elementUtils.isElementDisplayed(titleCustomFields);
    }

    public String getTextTitleCustomFields(){
        return elementUtils.getTextOfElement(titleCustomFields);
    }

    public void selectBloodTypeOption(String bloodOption) {
        elementUtils.selectOptionFromDropdown(dropdownBloodType, bloodOption);
    }

    public void enterInputTestField(String textTest){
        elementUtils.enterInputEdit(testField, textTest);
    }

    public void clickBtnSaveCustomFields(){

        wait.until(ExpectedConditions.elementToBeClickable(btnSaveCustomFields));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSaveCustomFields);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnSaveCustomFields);
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

    public boolean displayTitleAttachments(){
        return elementUtils.isElementDisplayed(titleAttachments);
    }

    public String getTextTitleAttachments(){
        return elementUtils.getTextOfElement(titleAttachments);
    }

    public boolean displayBtnAddAttachment(){
        return elementUtils.isElementDisplayed(btnAddAttachments);
    }

    public EditAttachmentPage clickEditAttachment(){
        String textAttachment = "clientLogo.jpg";
        elementUtils.clickEditElement(rows, textAttachment);
        return new EditAttachmentPage(driver);
    }

    public DeletePopup clickDeleteAttachment(){
        String textAttachment = "importDataRecord.csv";
        elementUtils.clickDeleteElement(rows, textAttachment);
        return new DeletePopup(driver);
    }

    public AddAttachmentPage clickBtnAddAttachment(){
        elementUtils.clickOnElement(btnAddAttachments);
        return new AddAttachmentPage(driver);
    }
}
