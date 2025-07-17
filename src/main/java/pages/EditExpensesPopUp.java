package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;
import java.util.List;

public class  EditExpensesPopUp {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public EditExpensesPopUp(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        elementUtils = new ElementUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//div[@role='document']")
    WebElement documentEditExpenses;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--card-title']")
    WebElement titleEditExpense;

    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
    WebElement dropdownExpenseType;

    @FindBy(xpath = "//input[@placeholder='yyyy-mm-dd']")
    WebElement dateEditExpenses;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-icon-button__icon'])[2]")
    WebElement yearDropdown;

    @FindBy(xpath = "//ul[@role='menu']//li")
    List<WebElement> years;

    @FindBy(xpath = "//ul[@role='menu']//li")
    List<WebElement> months;

    @FindBy(xpath = "//div[contains(@class,'oxd-calendar-date-wrapper')]")
    List<WebElement> days;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-icon-button__icon'])[1]")
    WebElement monthDropdown;

    @FindBy(xpath = "//label[text()='Amount']/parent::div/following-sibling::div//input")
    WebElement amountInput;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSaveEditExpense;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayEditDocumentExpenses(){
        return elementUtils.isElementDisplayed(documentEditExpenses);
    }

    public boolean displayTitleEditExpenses(){
        return elementUtils.isElementDisplayed(titleEditExpense);
    }

    public String getTextTitleEditExpenses(){
        return elementUtils.getTextOfElement(titleEditExpense);
    }

    public void selectExpensesTypeOption(String expensesType){
        elementUtils.selectOptionFromDropdown(dropdownExpenseType, expensesType);
    }

    public void selectDate(String day, String month, String year) {
        elementUtils.selectDate(dateEditExpenses, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void enterAmount(String newAmount) {
        elementUtils.enterInputAdmin(amountInput, newAmount);
    }

    public void clickBtnSaveEditExpenses(){
        elementUtils.clickOnElement(btnSaveEditExpense);
    }

    public String getTextEditExpenseSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }
}
