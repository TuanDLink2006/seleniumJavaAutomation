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

public class AddEmployeePage {
    WebDriver driver;
    ElementUtils elementUtils;
    WebDriverWait wait;
    public AddEmployeePage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Employee']")
    WebElement titleAddEmployee;

    @FindBy(xpath = "//input[@type='file']")
    WebElement addImage;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-input-hint']")
    WebElement messageAccept;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement inputFirstName;

    @FindBy(xpath = "//input[@placeholder='Middle Name']")
    WebElement inputMiddleName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement inputLastName;

    @FindBy(xpath = "//input[contains(@class, 'oxd-input')]")
    WebElement inputEmployeeId;

    @FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
    WebElement checkBoxCreateLogin;

    @FindBy(xpath = "//label[text()='Username']/ancestor::div[contains(@class,'oxd-input-group__label-wrapper')]/following-sibling::div//input")
    WebElement inputUserName;

    @FindBy(xpath = "//label[normalize-space()='Enabled']")
    WebElement enabled;

    @FindBy(xpath = "//label[normalize-space()='Disabled']")
    WebElement disabled;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']")
    WebElement inputPassword;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']")
    WebElement inputConfirmPass;

    @FindBy(xpath = "//span[normalize-space()='Passwords do not match']")
    WebElement messagePasswordDoNot;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//div[@class='oxd-input-group']//div[1]//span[1]")
    WebElement messageRequired;

    @FindBy(xpath = "//span[normalize-space()='File type not allowed']")
    WebElement messageFileType;

    @FindBy(xpath = "//span[normalize-space()='Attachment Size Exceeded']")
    WebElement messageAttachment;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleAddEmployee(){
        return elementUtils.isElementDisplayed(titleAddEmployee);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public String getTextMessageAccept(){
        return elementUtils.getTextOfElement(messageAccept);
    }

    public String getMessageFileType(){
        return elementUtils.getTextOfElement(messageFileType);
    }

    public String getMessageAttachment(){
        return elementUtils.getTextOfElement(messageAttachment);
    }

    public String getMessageRequiredFirstName(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageRequiredLastName(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageRequiredUserName(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageRequiredPassword(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageDoNotMatchPassword(){
        return elementUtils.getTextOfElement(messagePasswordDoNot);
    }

    public String getTextAddEmployeeSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

    public void enterImageEmployee(String filePath){
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", addImage);
        elementUtils.selectElementFile(addImage, filePath);
    }

    public void enterFirstName(String textName){
        elementUtils.enterInputElement(inputFirstName, textName);
    }

    public void enterMiddleName(String textName){
        elementUtils.enterInputElement(inputMiddleName, textName);
    }

    public void enterLastName(String textName){
        elementUtils.enterInputElement(inputLastName, textName);
    }

    public void employeeId(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String inputValue = (String) js.executeScript("return arguments[0].value;", inputEmployeeId);

        System.out.println("Giá trị trong input là: " + inputValue);
    }

    public void clickCheckBoxCreateLoginDetail(){
        elementUtils.clickOnCheckbox(checkBoxCreateLogin);
    }

    public void enterInputUsername(String textUsername){
        elementUtils.enterInputElement(inputUserName, textUsername);
    }

    public void enterPassword(String textPass){
        elementUtils.enterInputElement(inputPassword, textPass);
    }

    public void clickEnable(){
        elementUtils.clickRadioButton(enabled);
    }

    public void clickDisable(){
        elementUtils.clickRadioButton(disabled);
    }

    public void enterConfirmPass(String textPass){
        elementUtils.enterInputElement(inputConfirmPass, textPass);
    }

    public MyInfoPage clickBtnSave(){
        wait.until(ExpectedConditions.elementToBeClickable(btnSave));
        elementUtils.clickOnElement(btnSave);
        return new MyInfoPage(driver);
    }


}
