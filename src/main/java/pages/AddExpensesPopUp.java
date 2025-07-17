package pages;

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

public class AddExpensesPopUp {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public AddExpensesPopUp(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@role='document']")
    WebElement documentAddExpenses;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--card-title']")
    WebElement titleAddExpense;

    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
    WebElement dropdownExpenseType;

    @FindBy(xpath = "//div[@class='oxd-date-input']")
    WebElement dateAddExpenses;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-icon-button__icon'])[2]")
    WebElement yearDropdown;

    @FindBy(xpath = "//ul//li")
    List<WebElement> years;

    @FindBy(xpath = "//ul//li")
    List<WebElement> months;

    @FindBy(xpath = "//div[contains(@class,'oxd-calendar-date-wrapper')]")
    List<WebElement> days;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-icon-button__icon'])[1]")
    WebElement monthDropdown;

    @FindBy(xpath = "//label[text()='Amount']/parent::div/following-sibling::div//input")
    WebElement amount;

    @FindBy(xpath = "(//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical'])[2]")
    WebElement note;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancelAddExpense;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSaveAddExpense;

    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required'])[1]")
    WebElement messageRequiredExpenseType;

    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required'])[2]")
    WebElement messageRequiredDate;

    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required'])[3]")
    WebElement messageRequiredAmount;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageShouldBe;

    @FindBy(xpath = "//span[normalize-space()='Should be less than 10,000,000,000']")
    WebElement messageShouldBeLessThan;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayAddDocumentExpenses(){
        return elementUtils.isElementDisplayed(documentAddExpenses);
    }

    public boolean displayTitleAddExpenses(){
        return elementUtils.isElementDisplayed(titleAddExpense);
    }

    public String getTextTitleAddExpenses(){
        return elementUtils.getTextOfElement(titleAddExpense);
    }

    public void clickBtnCancelAddExpenses(){
        elementUtils.clickOnElement(btnCancelAddExpense);
    }

    public void clickBtnSaveAddExpenses(){
        elementUtils.clickOnElement(btnSaveAddExpense);
    }

    public String getTextMessageRequiredExpenseType(){
        return elementUtils.getTextOfElement(messageRequiredExpenseType);
    }

    public String getTextMessageRequiredDate(){
        return elementUtils.getTextOfElement(messageRequiredDate);
    }

    public String getTextMessageRequiredAmount(){
        return elementUtils.getTextOfElement(messageRequiredAmount);
    }

    public String getTextMessageShouldBe(){
        return elementUtils.getTextOfElement(messageShouldBe);
    }

    public String getTextMessageShouldBeLessThan(){
        return elementUtils.getTextOfElement(messageShouldBeLessThan);
    }

    public void selectExpensesTypeOption(String expensesType){
        elementUtils.selectOptionFromDropdown(dropdownExpenseType, expensesType);
    }

    public void selectDate(String day, String month, String year) {
        elementUtils.selectDate(dateAddExpenses, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void enterAmount(String textAmount){
        elementUtils.enterInputElement(amount, textAmount);
    }


    public void enterNote(String textNote){
        elementUtils.enterTextarea(note, textNote);
    }

    public String getTextAddExpenseSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }
}
