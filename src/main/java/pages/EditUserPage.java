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

public class EditUserPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public EditUserPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
    WebElement dropdownUserRole;

    @FindBy(xpath = "//div[@role='listbox']")
    WebElement listBox;

    By option = By.xpath("//div[@role='option']");

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement inputEmployeeName;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
    WebElement dropdownStatus;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    WebElement username;

    @FindBy(xpath = "//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']")
    WebElement checkYesPassword;

    @FindBy(xpath = "(//input[@type='password'])[1]")
    WebElement password;

    @FindBy(xpath = "(//input[@type='password'])[2]")
    WebElement confirmPass;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public void selectUserRole(String userRole){
        elementUtils.selectOptionFromDropdown(dropdownUserRole, userRole);
    }

    public void inputEmployee(String textName, String expectedFullName) throws InterruptedException {
        elementUtils.inputEmployeeElement(inputEmployeeName, listBox, option ,textName, expectedFullName);
    }

    public void selectStatusOption(String statusOption){
        elementUtils.selectOptionFromDropdown(dropdownStatus, statusOption);
    }

    public void enterUserName(String textUserName){
        elementUtils.enterInputElement(username, textUserName);
    }

    public void clickYesPassword(){
        elementUtils.clickOnCheckbox(checkYesPassword);
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

    public String getTextEditSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }
}
