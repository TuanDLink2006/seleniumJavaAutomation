package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.IFactoryAnnotation;
import utils.ElementUtils;

import java.time.Duration;
import java.util.List;

public class ImmigrationRecordsPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public ImmigrationRecordsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Assigned Immigration Records']")
    WebElement titleAssignedImmigrationRecords;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Add'])[1]")
    WebElement btnAddAssignedImmigrationRecords;

    @FindBy(xpath = "//h6[normalize-space()='Add Immigration']")
    WebElement titleAddImmigration;

    @FindBy(xpath = "//label[normalize-space()='Passport']")
    WebElement radioButtonPassport;

    @FindBy(xpath = "//label[normalize-space()='Visa']")
    WebElement radioButtonVisa;

    @FindBy(xpath = "//label[text()='Number']/following::input[1]")
    WebElement inputNumber;

    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[1]")
    WebElement issuedDate;

    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[2]")
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

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageDateFormat;

    @FindBy(xpath = "//span[normalize-space()='Expiry date should be after issued date']")
    WebElement messageExpiryDate;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 30 characters']")
    WebElement messageExceed30Characters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 250 characters']")
    WebElement messageExceed250Characters;

    @FindBy(xpath = "//label[text()='Eligible Status']/following::input[1]")
    WebElement inputEligibleStatus;

    @FindBy(xpath = "//div[@class='oxd-select-text oxd-select-text--active']")
    WebElement dropdownIssuedBy;

    @FindBy(xpath = "//div[@role='option']")
    List<WebElement> issues;

    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[3]")
    WebElement eligibleReviewDate;

    @FindBy(xpath = "//textarea[@placeholder='Type Comments here']")
    WebElement comments;

    @FindBy(xpath = "(//button[normalize-space()='Cancel'])[1]")
    WebElement btnCancelAddImmigration;

    @FindBy(xpath = "(//button[normalize-space()='Save'])[1]")
    WebElement btnSaveAddImmigration;

    @FindBy(xpath = "(//div[@role='table'])[1]")
    WebElement tableImmigration;

    @FindBy(xpath = "//div[@role='row']")
    List<WebElement> rowsImmigration;

    @FindBy(xpath = "//h6[normalize-space()='Attachments']")
    WebElement titleAttachments;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Add'])[2]")
    WebElement btnAddAttachments;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageRequired;

    @FindBy(xpath = "(//div[@role='table'])[2]")
    WebElement tableAttachment;

    @FindBy(xpath = "(//div[@role='row'])[2]")
    List<WebElement> rowAttachment;

    @FindBy(xpath = "//h6[normalize-space()='Edit Immigration']")
    WebElement titleEditImmigration;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleAssignedImmigrationRecords(){
        return elementUtils.isElementDisplayed(titleAssignedImmigrationRecords);
    }

    public String getTextTitleAssignedImmigrationRecords(){
        return elementUtils.getTextOfElement(titleAssignedImmigrationRecords);
    }

    public boolean displayTitleAttachments(){
        return elementUtils.isElementDisplayed(titleAttachments);
    }

    public String getTextTitleAttachments(){
        return elementUtils.getTextOfElement(titleAttachments);
    }

    public boolean displayBtnAddAssignedImmigrationRecords(){
        return elementUtils.isElementDisplayed(btnAddAssignedImmigrationRecords);
    }

    public boolean displayBtnAddAttachments(){
        return elementUtils.isElementDisplayed(btnAddAttachments);
    }

    public void clickBtnAddAssignedImmigrationRecords(){
        elementUtils.clickOnElement(btnAddAssignedImmigrationRecords);
    }

    public boolean displayTitleAddImmigration(){
        return elementUtils.isElementDisplayed(titleAddImmigration);
    }

    public String getTextTitleAddImmigration(){
        return elementUtils.getTextOfElement(titleAddImmigration);
    }

    public boolean displayBtnCancelImmigration(){
        return elementUtils.isElementDisplayed(btnCancelAddImmigration);
    }

    public boolean displayBtnSaveImmigration(){
        return elementUtils.isElementDisplayed(btnSaveAddImmigration);
    }

    public void enterIssuedDate(String textIssuedDate){
        elementUtils.enterInputElement(issuedDate, textIssuedDate);
    }

    public void enterExpiryDate(String textExpiryDate){
        elementUtils.enterInputElement(expiryDate, textExpiryDate);
    }

    public void enterEligibleReviewDate(String textEligibleReviewDate){
        elementUtils.enterInputElement(eligibleReviewDate, textEligibleReviewDate);
    }

    public void clickRadioButtonPassport(){
        elementUtils.clickRadioButton(radioButtonPassport);
    }

    public void clickRadioButtonVisa(){
       elementUtils.clickRadioButton(radioButtonVisa);
    }

    public void enterInputNumber(String textNumber){
        elementUtils.enterInputElement(inputNumber, textNumber);
    }

    public void enterInputNumberEdit(String textNumber){
        elementUtils.enterInputEdit(inputNumber, textNumber);
    }

    public void selectIssuedDate(String day, String month, String year) {
        elementUtils.selectDate(issuedDate, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void selectExpiryDate(String day, String month, String year) {
       elementUtils.selectDate(expiryDate, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void enterEligibleStatus(String textStatus){
        elementUtils.enterInputElement(inputEligibleStatus, textStatus);
    }

    public void enterEligibleStatusEdit(String textStatus){
        elementUtils.enterInputEdit(inputEligibleStatus, textStatus);
    }

    public void selectIssuedByOption(String issueOption){
        elementUtils.selectOptionFromDropdown(dropdownIssuedBy, issueOption);
    }

    public void selectEligibleReviewDate(String day, String month, String year) {
        elementUtils.selectDate(eligibleReviewDate, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void enterComments(String textComments){
        elementUtils.enterTextarea(comments, textComments);
    }

    public boolean displayTableImmigration(){
        return elementUtils.isElementDisplayed(tableImmigration);
    }

    public int getSizeRowsImmigration(){
        return rowsImmigration.size();
    }

    public AddAttachmentPage clickBtnAddAttachment(){
        elementUtils.clickOnElement(btnAddAttachments);
        return new AddAttachmentPage(driver);
    }

    public boolean displayTableAttachment(){
        return elementUtils.isElementDisplayed(tableAttachment);
    }

    public int getSizeRowsAttachment(){
        return rowAttachment.size();
    }

    public void clickBtnSaveImmigration(){
        elementUtils.clickOnElement(btnSaveAddImmigration);
    }

    public void clickBtnCancelImmigration(){
        elementUtils.clickOnElement(btnCancelAddImmigration);
    }

    public void clickEditImmigration(){
        String number = "one";
        elementUtils.clickEditElement(rowsImmigration, number);
    }

    public boolean displayTitleEditImmigration(){
        return elementUtils.isElementDisplayed(titleEditImmigration);
    }

    public String getTextTitleEditImmigration(){
        return elementUtils.getTextOfElement(titleEditImmigration);
    }

    public DeletePopup clickDeleteImmigration(){
        String textImmigration = "two";
        elementUtils.clickDeleteElement(rowsImmigration, textImmigration);
        return new DeletePopup(driver);
    }

    public String getTextMessageRequiredNumber(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageFormatDateIssuedDate(){
        return elementUtils.getTextOfElement(messageDateFormat);
    }

    public String getTextMessageFormatDateExpiryDate(){
        return elementUtils.getTextOfElement(messageDateFormat);
    }

    public String getTextMessageFormatDateEligibleReviewDate(){
        return elementUtils.getTextOfElement(messageDateFormat);
    }

    public String getTextMessageExpiryDateShould(){
        return elementUtils.getTextOfElement(messageExpiryDate);
    }

    public String getTextMessageExceed30CharactersNumber(){
        return elementUtils.getTextOfElement(messageExceed30Characters);
    }

    public String getTextMessageExceed30CharactersEligibleStatus(){
        return elementUtils.getTextOfElement(messageExceed30Characters);
    }

    public String getTextMessageExceed250Characters(){
        return elementUtils.getTextOfElement(messageExceed250Characters);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
