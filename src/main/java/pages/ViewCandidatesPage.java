package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;
import java.util.List;

public class ViewCandidatesPage {

    WebDriver driver;
    ElementUtils elementUtils;
    WebDriverWait wait;
    public ViewCandidatesPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Application Stage']")
    WebElement titleApplicationStage;

    @FindBy(xpath = "//button[normalize-space()='Reject']")
    WebElement btnReject;

    @FindBy(xpath = "//button[normalize-space()='Shortlist']")
    WebElement btnShortlist;

    @FindBy(xpath = "//h6[normalize-space()='Candidate Profile']")
    WebElement titleCandidateProfile;

    @FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-left']")
    WebElement checkBoxEdit;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement inputFirstName;

    @FindBy(xpath = "//input[@placeholder='Middle Name']")
    WebElement inputMiddleName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement inputLastName;

    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
    WebElement jobVacancy;

    @FindBy(xpath = "//label[text()='Email']/ancestor::div[contains(@class,'oxd-input oxd-input--active')]")
    WebElement inputEmail;

    @FindBy(xpath = "//label[text()='Contact Number']/ancestor::div[contains(@class,'oxd-input oxd-input--active')]")
    WebElement inputContactNumber;

    @FindBy(xpath = "//input[@type='file']")
    WebElement fileResume;

    @FindBy(xpath = "//label[normalize-space()='Keep Current']")
    WebElement keepCurrentFile;

    @FindBy(xpath = "//label[normalize-space()='Delete Current']")
    WebElement deleteCurrentFile;

    @FindBy(xpath = "//div[@role='document']")
    WebElement document;

    @FindBy(xpath = "//label[normalize-space()='Replace Current']")
    WebElement replaceCurrentFile;

    @FindBy(xpath = "//input[@placeholder='Enter comma seperated words...']")
    WebElement inputKeywords;

    @FindBy(xpath = "//label[text()='Date of Application']/ancestor::div[contains(@class,'oxd-date-input')]//input")
    WebElement dateOfApplication;

    @FindBy(xpath = "//textarea[@placeholder='Type here']")
    WebElement notes;

    @FindBy(xpath = "//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']")
    WebElement consentToKeepData;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//h6[normalize-space()='Candidate History']")
    WebElement titleCandidateHistory;

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//div[@role='row']")
    List<WebElement> rows;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public String getTextTitleApplicationStage(){
        return elementUtils.getTextOfElement(titleApplicationStage);
    }

    public boolean displayTitleApplicationStage(){
        return elementUtils.isElementDisplayed(titleApplicationStage);
    }

    public RejectCandidatePage clickBtnReject(){
        elementUtils.clickOnElement(btnReject);
        return new RejectCandidatePage(driver);
    }

    public ShortlistCandidatePage clickBtnShortlist(){
        elementUtils.clickOnElement(btnShortlist);
        return new ShortlistCandidatePage(driver);
    }

    public String getTextTitleCandidateProfile(){
        return elementUtils.getTextOfElement(titleCandidateProfile);
    }

    public boolean displayTitleCandidateProfile() {
        return elementUtils.isElementDisplayed(titleCandidateProfile);
    }

    public String getTextTitleCandidateHistory(){
        return elementUtils.getTextOfElement(titleCandidateHistory);
    }

    public boolean displayTitleCandidateHistory(){
        return elementUtils.isElementDisplayed(titleCandidateHistory);
    }

    public boolean disableFirstName(){
        return elementUtils.isElementDisabled(inputFirstName);
    }

    public boolean disableMiddleName(){
        return elementUtils.isElementDisabled(inputMiddleName);
    }

    public boolean disableLastName(){
        return elementUtils.isElementDisabled(inputLastName);
    }

    public boolean disableJobVacancy(){
        return elementUtils.isElementDisabled(jobVacancy);
    }

    public boolean disableEmail(){
        return elementUtils.isElementDisabled(inputEmail);
    }

    public boolean disableContactNumber(){
        return elementUtils.isElementDisabled(inputContactNumber);
    }

    public boolean disableFileResume(){
        return elementUtils.isElementDisabled(fileResume);
    }

    public boolean disableKeywords(){
        return elementUtils.isElementDisabled(inputKeywords);
    }

    public boolean disableDateOfApplication(){
        return elementUtils.isElementDisabled(dateOfApplication);
    }

    public boolean disableNotes(){
        return elementUtils.isElementDisabled(notes);
    }

    public boolean disableConsentToKeepData(){
        return elementUtils.isElementDisabled(consentToKeepData);
    }

    public void clickOnCheckBoxEdit(){
        elementUtils.clickOnCheckbox(checkBoxEdit);
    }

    public boolean enableFirstName(){
        return elementUtils.isElementEnabled(inputFirstName);
    }

    public boolean enableMiddleName(){
        return elementUtils.isElementEnabled(inputMiddleName);
    }

    public boolean enableLastName(){
        return elementUtils.isElementEnabled(inputLastName);
    }

    public boolean enableJobVacancy(){
        return elementUtils.isElementEnabled(jobVacancy);
    }

    public boolean enableEmail(){
        return elementUtils.isElementEnabled(inputEmail);
    }

    public boolean enableContactNumber(){
        return elementUtils.isElementEnabled(inputContactNumber);
    }

    public boolean enableFileResume(){
        return elementUtils.isElementEnabled(fileResume);
    }

    public boolean enableKeywords(){
        return elementUtils.isElementEnabled(inputKeywords);
    }

    public boolean enableDateOfApplication(){
        return elementUtils.isElementEnabled(dateOfApplication);
    }

    public boolean enableNotes(){
        return elementUtils.isElementEnabled(notes);
    }

    public boolean enableConsentToKeepData(){
        return elementUtils.isElementEnabled(consentToKeepData);
    }

    public void enterFirstName(String textFirstName){
        elementUtils.enterInputEdit(inputFirstName, textFirstName);
    }

    public void clearMiddleName(){
        elementUtils.clearInput(inputMiddleName);
    }

    public void enterMiddleName(String textMiddleName){
        elementUtils.enterInputEdit(inputMiddleName, textMiddleName);
    }

    public void enterLastName(String textLastName){
        elementUtils.enterInputEdit(inputLastName, textLastName);
    }

    public void selectJobVacancyOption(String textOption){
        elementUtils.selectOptionFromDropdown(jobVacancy, textOption);
    }

    public void enterEmail(String textEmail){
        elementUtils.enterInputEdit(inputEmail, textEmail);
    }

    public void enterContactNumber(String textContactNumber){
        elementUtils.enterInputEdit(inputContactNumber, textContactNumber);
    }

    public void clickOnKeepCurrentFile(){
        elementUtils.clickRadioButton(keepCurrentFile);
    }

    public void clickOnDeleteCurrentFile(){
        elementUtils.clickRadioButton(deleteCurrentFile);
    }

    public void clickOnReplaceCurrentFile(){
        elementUtils.clickRadioButton(replaceCurrentFile);
    }

    public void uploadFileResume(String filePath){
        elementUtils.selectElementFile(fileResume, filePath);
    }

    public void enterKeywords(String textKeys){
        elementUtils.enterInputEdit(inputKeywords, textKeys);
    }

    public void clickOffCheckBoxConsentToKeepData(){
        elementUtils.clickOffCheckbox(consentToKeepData);
    }

    public ConfirmationRequiredPopup clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new ConfirmationRequiredPopup(driver);
    }

    public boolean displayDocument(){
        return elementUtils.isElementDisplayed(document);
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
