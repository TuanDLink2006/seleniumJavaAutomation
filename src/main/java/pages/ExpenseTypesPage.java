package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;
import java.util.List;

public class ExpenseTypesPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public ExpenseTypesPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h5[normalize-space()='Expense Types']")
    WebElement titleExpenseTypes;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement inputEventName;

    By option = By.xpath("//div[@role='option']");

    @FindBy(xpath = "//div[@role='listbox']")
    WebElement listBox;

    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
    WebElement dropdownStatus;

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    WebElement btnReset;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement btnSearch;

    @FindBy(xpath = "//div[@id='oxd-toaster_1']")
    WebElement messageNoRecord;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement btnAdd;

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    List<WebElement> jobRows;

    @FindBy(xpath = "//i[@class='oxd-icon bi-pencil-fill']")
    WebElement btnEdit;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageDeleteSuccess;

    public String getTextTitleExpenseTypes(){
        return elementUtils.getTextOfElement(titleExpenseTypes);
    }

    public boolean displayTitleExpenseTypes(){
        return elementUtils.isElementDisplayed(titleExpenseTypes);
    }

    public boolean displayBtnReset(){
        return elementUtils.isElementDisplayed(btnReset);
    }

    public boolean displayBtnSearch(){
        return elementUtils.isElementDisplayed(btnSearch);
    }

    public boolean displayBtnAdd(){
        return elementUtils.isElementDisplayed(btnAdd);
    }

    public boolean displayTable(){
        return elementUtils.isElementDisplayed(table);
    }

    public int getSizeRows(){
        return jobRows.size();
    }

    public void enterInputEventName(String textName, String expectedEventName) throws InterruptedException {
        elementUtils.inputEmployeeElement(inputEventName, listBox, option, textName, expectedEventName);
    }

    public void selectStatusOption(String status){
       elementUtils.selectOptionFromDropdown(dropdownStatus, status);
    }

    public void clickBtnSearch(){
        elementUtils.clickOnElement(btnSearch);
    }

    public String getTextMessageNoRecord(){
        return elementUtils.getTextOfElement(messageNoRecord);
    }

    public AddExpenseTypePage clickBtnAdd(){
        elementUtils.clickOnElement(btnAdd);
        return new AddExpenseTypePage(driver);
    }

    public EditExpenseTypePage clickEditExpenseWhenSearch(){
        elementUtils.clickOnElement(btnEdit);
        return new EditExpenseTypePage(driver);
    }

    public EditExpenseTypePage clickEditExpense(){
        String event = "Travel";
        elementUtils.clickEditElement(jobRows, event);
        return new EditExpenseTypePage(driver);
    }

    public DeletePopup clickDeleteExpense(){
        String event = "Travel";
        elementUtils.clickDeleteElement(jobRows, event);
        return new DeletePopup(driver);
    }

    public String getTextDeleteSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageDeleteSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
