package pages;

import org.bouncycastle.jcajce.provider.asymmetric.X509;
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

public class AddUserNamePage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;

    public AddUserNamePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add User']")
    WebElement titleAddUser;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p user-password-hint']")
    WebElement textPassHint;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
    WebElement dropdownUserRole;

    By option = By.xpath("//div[@role='option']");

    @FindBy(xpath = "//div[@role='listbox']")
    WebElement listBox;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
    WebElement dropdownStatus;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement inputEmployeeName;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    WebElement username;

    @FindBy(xpath = "(//input[@type='password'])[1]")
    WebElement password;

    @FindBy(xpath = "(//input[@type='password'])[2]")
    WebElement confirmPass;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//span[normalize-space()='Required']")
    WebElement requiredUserRole;

    @FindBy(xpath = "//span[normalize-space()='Required']")
    WebElement requiredEmployeeName;

    @FindBy(xpath = "//span[normalize-space()='Invalid']")
    WebElement invalidEmployeeName;

    @FindBy(xpath = "//span[normalize-space()='Required']")
    WebElement requiredStatus;

    @FindBy(xpath = "//span[normalize-space()='Required']")
    WebElement requiredUserName;

    @FindBy(xpath = "//span[normalize-space()='Required']")
    WebElement requiredPassword;

    @FindBy(xpath = "//span[normalize-space()='Should be at least 5 characters']")
    WebElement shouldAt5CharactersUserName;

    @FindBy(xpath = "//span[normalize-space()='Already exists']")
    WebElement existUserName;

    @FindBy(xpath = "//span[normalize-space()='Should have at least 7 characters']")
    WebElement shouldAt7CharactersPassword;

    @FindBy(xpath = "//span[normalize-space()='Your password must contain minimum 1 lower-case letter']")
    WebElement passwordContainMinimum1LowerCase;

    @FindBy(xpath = "//span[normalize-space()='Your password must contain minimum 1 number']")
    WebElement passwordContainMinimum1Number;

    @FindBy(xpath = "//span[normalize-space()='Passwords do not match']")
    WebElement passwordNotMarch;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 40 characters']")
    WebElement shouldNotExceed40Characters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 64 characters']")
    WebElement shouldNotExceed64Characters;

    public String getTextTitleAddUser(){
        return elementUtils.getTextOfElement(titleAddUser);
    }

    public boolean displayTitleAddUser(){
        return elementUtils.isElementDisplayed(titleAddUser);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public String getTextPassHint(){
        return elementUtils.getTextOfElement(textPassHint);
    }

    public void selectUserRoleOption(String userRoleOption) {
        elementUtils.selectOptionFromDropdown(dropdownUserRole, userRoleOption);
    }

    public void inputEmployee(String textName, String expectedFullName) throws InterruptedException {
        elementUtils.inputEmployeeElement(inputEmployeeName, listBox, option, textName, expectedFullName);
    }

    public void selectStatusOption(String statusOption) {
        elementUtils.selectOptionFromDropdown(dropdownStatus, statusOption);
    }

    public void enterUserName(String textUserName){
        elementUtils.enterInputElement(username, textUserName);
    }

    public void enterPassword(String textPassword){
        elementUtils.enterInputElement(password, textPassword);
    }

    public void enterConfirmPass(String textConfirmPass){
        elementUtils.enterInputElement(confirmPass, textConfirmPass);
    }

    public UsersAdminPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new UsersAdminPage(driver);
    }

    public UsersAdminPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new UsersAdminPage(driver);
    }

    public String getTextMessageRequiredUserRole(){
        return elementUtils.getTextOfElement(requiredUserRole);
    }

    public String getTextMessageRequiredEmployeeName(){
        return elementUtils.getTextOfElement(requiredEmployeeName);
    }

    public String getTextMessageInvalidEmployeeName(){
        return elementUtils.getTextOfElement(invalidEmployeeName);
    }

    public String getTextMessageRequiredStatus(){
        return elementUtils.getTextOfElement(requiredStatus);
    }

    public String getTextMessageRequiredUserName(){
        return elementUtils.getTextOfElement(requiredUserName);
    }

    public String getTextMessageRequiredPassword(){
        return elementUtils.getTextOfElement(requiredPassword);
    }

    public String getTextMessageShouldAt5CharactersUserName(){
        return elementUtils.getTextOfElement(shouldAt5CharactersUserName);
    }

    public String getTextMessageExistUserName(){
        return elementUtils.getTextOfElement(existUserName);
    }

    public String getTextMessageShouldAt7CharactersPassword(){
        return elementUtils.getTextOfElement(shouldAt7CharactersPassword);
    }

    public String getTextMessagePasswordNotMarch(){
        return elementUtils.getTextOfElement(passwordNotMarch);
    }

    public String getTextMessageShouldNotExceed40Characters(){
        return elementUtils.getTextOfElement(shouldNotExceed40Characters);
    }

    public String getTextMessageShouldNotExceed64Characters(){
        return elementUtils.getTextOfElement(shouldNotExceed64Characters);
    }

    public String getTextMessagePasswordContainMinimum1LowerCase(){
        return elementUtils.getTextOfElement(passwordContainMinimum1LowerCase);
    }

    public String getTextMessagePasswordContainMinimum1Number(){
        return elementUtils.getTextOfElement(passwordContainMinimum1Number);
    }

    public void clickTitleAddUserName(){
        elementUtils.clickOnElement(titleAddUser);
    }

}
