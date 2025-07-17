package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class EditExpenseTypePage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public EditExpenseTypePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Edit Expense Type']")
    WebElement titleEditExpenseType;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active']")
    WebElement nameExpenseType;

    @FindBy(xpath = "//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']")
    WebElement description;

    @FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
    WebElement checkboxActive;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleEditExpenseType(){
        return elementUtils.isElementDisplayed(titleEditExpenseType);
    }

    public boolean isDisableInputExpenseTypeName(){
        return elementUtils.isElementDisabled(nameExpenseType);
    }

    public boolean isSelectedCheckboxActive(){
        return elementUtils.isElementSelected(checkboxActive);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public void clickOffCheckboxActive(){
        elementUtils.clickOffCheckbox(checkboxActive);
    }

    public void clickOnCheckboxActive(){
        elementUtils.clickOnCheckbox(checkboxActive);
    }

    public ExpenseTypesPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new ExpenseTypesPage(driver);
    }

    public ExpenseTypesPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new ExpenseTypesPage(driver);
    }

    public String getTextEditEventSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }
}
