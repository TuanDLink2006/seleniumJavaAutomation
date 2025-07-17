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

public class DependentsPage {
    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public DependentsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Assigned Dependents']")
    WebElement titleAssignedDependents;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Add'])[1]")
    WebElement btnAddAssignedDependents;

    @FindBy(xpath = "//h6[normalize-space()='Add Dependent']")
    WebElement titleAddDependent;

    @FindBy(xpath = "//label[text()='Name']/following::input[1]")
    WebElement inputName;

    @FindBy(xpath = "//div[@class='oxd-select-text oxd-select-text--active']")
    WebElement dropdownRelationship;

    @FindBy(xpath = "//label[text()='Please Specify']/following::input[1]")
    WebElement inputPleaseSpecify;

    @FindBy(xpath = "//input[@placeholder='yyyy-dd-mm']")
    WebElement dateOfBirth;

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

    @FindBy(xpath = "(//button[normalize-space()='Cancel'])[1]")
    WebElement btnCancelAddDependent;

    @FindBy(xpath = "(//button[normalize-space()='Save'])[1]")
    WebElement btnSaveAddDependent;

    @FindBy(xpath = "(//div[@role='table'])[1]")
    WebElement tableDependent;

    @FindBy(xpath = "//div[@role='row']")
    List<WebElement> rowDependents;

    @FindBy(xpath = "//h6[normalize-space()='Edit Dependent']")
    WebElement titleEditDependent;

    @FindBy(xpath = "//span[normalize-space()='Required']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 100 characters']")
    WebElement messageExceed100Characters;

    @FindBy(xpath = "//span[normalize-space()='Should be a valid date in yyyy-dd-mm format']")
    WebElement messageValidDateFormat;

    @FindBy(xpath = "//h6[normalize-space()='Attachments']")
    WebElement titleAttachments;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Add'])[2]")
    WebElement btnAddAttachments;

    @FindBy(xpath = "(//div[@role='table'])[2]")
    WebElement tableAttachment;

    @FindBy(xpath = "(//div[@role='row'])[2]")
    List<WebElement> rowAttachment;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleAssignedDependents(){
        return elementUtils.isElementDisplayed(titleAssignedDependents);
    }

    public String getTextTitleAssignedDependents(){
        return elementUtils.getTextOfElement(titleAssignedDependents);
    }

    public boolean displayTitleAttachments(){
        return elementUtils.isElementDisplayed(titleAttachments);
    }

    public String getTextTitleAttachments(){
        return elementUtils.getTextOfElement(titleAttachments);
    }

    public boolean displayBtnAddAssignedDependents(){
        return elementUtils.isElementDisplayed(btnAddAssignedDependents);
    }

    public boolean displayBtnAddAttachments(){
        return elementUtils.isElementDisplayed(btnAddAttachments);
    }

    public void clickBtnAddAssignedDependents(){
        elementUtils.clickOnElement(btnAddAssignedDependents);
    }

    public boolean displayTitleAddDependent(){
        return elementUtils.isElementDisplayed(titleAddDependent);
    }

    public String getTextTitleAddDependent(){
        return elementUtils.getTextOfElement(titleAddDependent);
    }

    public void enterInputName(String textName){
        elementUtils.enterInputElement(inputName, textName);
    }

    public void selectRelationshipOption(String relationshipOption) {
        elementUtils.selectOptionFromDropdown(dropdownRelationship, relationshipOption);
    }

    public void enterInputPleaseSpecify(String textPleaseSpecify){
        elementUtils.enterInputElement(inputPleaseSpecify, textPleaseSpecify);
    }

    public void enterDateOfBirth(String textDate){
        elementUtils.enterInputElement(dateOfBirth, textDate);
        elementUtils.clickOnElement(inputName);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        elementUtils.selectDate(dateOfBirth, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public boolean displayBtnCancelAddDependent(){
        return elementUtils.isElementDisplayed(btnCancelAddDependent);
    }

    public boolean displayBtnSaveAddDependent(){
        return elementUtils.isElementDisplayed(btnSaveAddDependent);
    }

    public void clickBtnCancelDependent(){
        elementUtils.clickOnElement(btnCancelAddDependent);
    }

    public void clickBtnSaveDependent(){
        elementUtils.clickOnElement(btnSaveAddDependent);
    }

    public void clickEditDependent(){
        String textDependent = "Tuan Linh";
        elementUtils.clickEditElement(rowDependents, textDependent);
    }

    public boolean displayTitleEditDependent(){
        return elementUtils.isElementDisplayed(titleEditDependent);
    }

    public String getTextTitleEditDependent(){
        return elementUtils.getTextOfElement(titleEditDependent);
    }

    public DeletePopup clickDeleteDependent(){
        String textDependent = "Tuan Linh";
        elementUtils.clickDeleteElement(rowDependents, textDependent);
        return new DeletePopup(driver);
    }

    public boolean displayTableDependent(){
        return elementUtils.isElementDisplayed(tableDependent);
    }

    public int getSizeRowDependents(){
        return rowDependents.size();
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

    public String getMessageRequiredName(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageRequiredRelationship(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageRequiredPleaseSpecify(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageExceed100Characters(){
        return elementUtils.getTextOfElement(messageExceed100Characters);
    }

    public String getMessageValidDateFormat(){
        return elementUtils.getTextOfElement(messageValidDateFormat);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
