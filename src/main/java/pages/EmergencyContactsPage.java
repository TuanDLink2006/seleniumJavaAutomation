package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;
import java.util.List;

public class EmergencyContactsPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public EmergencyContactsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Assigned Emergency Contacts']")
    WebElement titleAssignedEmergencyContacts;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Add'])[1]")
    WebElement btnAddAssignedEmergencyContacts;

    @FindBy(xpath = "//h6[normalize-space()='Save Emergency Contact']")
    WebElement titleSaveEmergencyContact;

    @FindBy(xpath = "//label[text()='Name']/following::input[1]")
    WebElement inputName;

    @FindBy(xpath = "//label[text()='Relationship']/following::input[1]")
    WebElement inputRelationship;

    @FindBy(xpath = "//label[text()='Home Telephone']/following::input[1]")
    WebElement inputHomeTelephone;

    @FindBy(xpath = "//label[text()='Mobile']/following::input[1]")
    WebElement inputMobile;

    @FindBy(xpath = "//label[text()='Work Telephone']/following::input[1]")
    WebElement inputWorkTelephone;

    @FindBy(xpath = "//span[normalize-space()='At least one phone number is required']")
    WebElement messageAtLeast;

    @FindBy(xpath = "//span[normalize-space()='Allows numbers and only + - / ( )']")
    WebElement messageAllowNumber;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 100 characters']")
    WebElement messageShould100Characters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 30 characters']")
    WebElement messageShould30Characters;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancelSaveEmergencyContact;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSaveEmergencyContact;

    @FindBy(xpath = "(//div[@role='table'])[1]")
    WebElement tableEmergencyContact;

    @FindBy(xpath = "//div[@role='row']")
    List<WebElement> rows;

    @FindBy(xpath = "//h6[normalize-space()='Edit Emergency Contact']")
    WebElement titleEditEmergencyContact;

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

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleAssignedEmergencyContacts(){
        return elementUtils.isElementDisplayed(titleAssignedEmergencyContacts);
    }

    public String getTextTitleAssignedEmergencyContacts(){
        return elementUtils.getTextOfElement(titleAssignedEmergencyContacts);
    }

    public boolean displayBtnAddAssignedEmergencyContacts(){
        return elementUtils.isElementDisplayed(btnAddAssignedEmergencyContacts);
    }

    public boolean displayTitleAttachments(){
        return elementUtils.isElementDisplayed(titleAttachments);
    }

    public String getTextTitleAttachments(){
        return elementUtils.getTextOfElement(titleAttachments);
    }

    public boolean displayBtnAddAttachments(){
        return elementUtils.isElementDisplayed(btnAddAttachments);
    }

    public void clickBtnAddAssignedEmergencyContacts(){
        elementUtils.clickOnElement(btnAddAssignedEmergencyContacts);
    }

    public boolean displayTitleSaveEmergencyContact(){
        return elementUtils.isElementDisplayed(titleSaveEmergencyContact);
    }

    public String getTextTitleSaveEmergencyContact(){
        return elementUtils.getTextOfElement(titleSaveEmergencyContact);
    }

    public boolean displayBtnCancelSaveEmergencyContact(){
        return elementUtils.isElementDisplayed(btnCancelSaveEmergencyContact);
    }

    public boolean displayBtnSaveEmergencyContact(){
        return elementUtils.isElementDisplayed(btnSaveEmergencyContact);
    }

    public String getTextMessageRequiredName(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageRequiredRelationship(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageAllowNumberHomeTelephone(){
        return elementUtils.getTextOfElement(messageAllowNumber);
    }

    public String getTextMessageAllowNumberMobile(){
        return elementUtils.getTextOfElement(messageAllowNumber);
    }

    public String getTextMessageAllowNumberWorkTelephone(){
        return elementUtils.getTextOfElement(messageAllowNumber);
    }

    public String getTextMessageShould100CharactersName(){
        return elementUtils.getTextOfElement(messageShould100Characters);
    }

    public String getTextMessageShould100CharactersRelationship(){
        return elementUtils.getTextOfElement(messageShould100Characters);
    }

    public String getTextMessageShould30CharactersHomeTelephone(){
        return elementUtils.getTextOfElement(messageShould30Characters);
    }

    public String getTextMessageShould30CharactersMobile(){
        return elementUtils.getTextOfElement(messageShould30Characters);
    }

    public String getTextMessageShould30CharactersWorkTelephone(){
        return elementUtils.getTextOfElement(messageShould30Characters);
    }

    public String getTextMessageNumberRequiredHomeTelephone(){
        return elementUtils.getTextOfElement(messageAtLeast);
    }

    public void enterInputName(String textName){
        elementUtils.enterInputElement(inputName, textName);
    }

    public void enterInputNameEdit(String textName){
        elementUtils.enterInputNameForceJS(inputName, textName);
    }

    public void enterInputRelationship(String textRelationship){
        elementUtils.enterInputElement(inputRelationship, textRelationship);
    }

    public void enterInputRelationshipEdit(String textRelationship){
        elementUtils.enterInputEdit(inputRelationship, textRelationship);
    }

    public void enterInputHomeTelephone(String textHomeTelephone){
        elementUtils.enterInputElement(inputHomeTelephone, textHomeTelephone);
    }

    public void enterInputHomeTelephoneEdit(String textHomeTelephone){
        elementUtils.enterInputEdit(inputHomeTelephone, textHomeTelephone);
    }

    public void enterInputMobile(String textMobile){
        elementUtils.enterInputElement(inputMobile, textMobile);
    }

    public void enterInputMobileEdit(String textMobile){
        elementUtils.enterInputEdit(inputMobile, textMobile);
    }

    public void enterWorkTelephone(String textWorkTelephone){
        elementUtils.enterInputElement(inputWorkTelephone, textWorkTelephone);
    }

    public void enterWorkTelephoneEdit(String textWorkTelephone){
        elementUtils.enterInputEdit(inputWorkTelephone, textWorkTelephone);
    }

    public void clickBtnCancelSaveEmergencyContact(){
        elementUtils.clickOnElement(btnCancelSaveEmergencyContact);
    }

    public void clickBtnSaveEmergencyContact(){
        elementUtils.clickOnElement(btnSaveEmergencyContact);
    }

    public boolean displayTableEmergencyContact(){
        return elementUtils.isElementDisplayed(tableEmergencyContact);
    }

    public int getSizeRowEmergencyContacts(){
        return rows.size();
    }

    public void clickEditEmergencyContacts(){
        String emergencyContacts = "Tuan Linh";
        elementUtils.clickEditElement(rows, emergencyContacts);
    }

    public String getTextTitleEditEmergencyContact(){
        return elementUtils.getTextOfElement(titleEditEmergencyContact);
    }

    public boolean displayTitleEditEmergencyContact(){
        return elementUtils.isElementDisplayed(titleEditEmergencyContact);
    }

    public DeletePopup clickDeleteEmergencyContacts(){
        String emergencyContacts = "Dieu Linh";
        elementUtils.clickDeleteElement(rows, emergencyContacts);
        return new DeletePopup(driver);
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

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
