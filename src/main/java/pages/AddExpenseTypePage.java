package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class AddExpenseTypePage {

    WebDriver driver;
    ElementUtils elementUtils;
    WebDriverWait wait;
    public AddExpenseTypePage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Expense Type']")
    WebElement titleAddExpenseType;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active']")
    WebElement nameExpenseType;

    @FindBy(xpath = "//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']")
    WebElement description;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageExist;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 100 characters']")
    WebElement messageExceed100Characters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 1000 characters']")
    WebElement messageExceed1000Characters;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--error oxd-toast-container--toast']")
    WebElement messageError;

    public String getTextTitleAddExpenseType(){
        return elementUtils.getTextOfElement(titleAddExpenseType);
    }

    public boolean displayTitleAddExpenseType(){
        return elementUtils.isElementDisplayed(titleAddExpenseType);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public String getTextMessageRequired(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageExist(){
        return elementUtils.getTextOfElement(messageExist);
    }

    public String getTextMessageExceed100Characters(){
        return elementUtils.getTextOfElement(messageExceed100Characters);
    }

    public String getTextMessageExceed1000Characters(){
        return elementUtils.getTextOfElement(messageExceed1000Characters);
    }

    public void enterInputExpenseName(String expenseName){
        elementUtils.enterInputElement(nameExpenseType, expenseName);
    }

    public void enterDescription(String textDescription){
        elementUtils.enterTextarea(description, textDescription);
    }

    public ExpenseTypesPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new ExpenseTypesPage(driver);
    }

    public ExpenseTypesPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new ExpenseTypesPage(driver);
    }

    public String getTextAddExpenseSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

    public String getTextMessageError(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageError));
        return elementUtils.getTextOfElement(successToast);
    }

}
