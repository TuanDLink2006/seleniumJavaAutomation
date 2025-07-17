package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class SubmitClaimPage {

    Properties prop;
    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public SubmitClaimPage(WebDriver driver){
        this.driver = driver;
        prop = new Properties();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Submit Claim']")
    WebElement titleSubmitClaim;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    WebElement referenceId;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[3]")
    WebElement event;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[4]")
    WebElement status;

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[5]")
    WebElement currency;

    @FindBy(xpath = "//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']")
    WebElement remarks;

    @FindBy(xpath = "//h6[normalize-space()='Expenses']")
    WebElement titleExpenses;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Add'])[1]")
    WebElement btnAddExpenses;

    @FindBy(xpath = "(//div[@class='oxd-table'])[1]")
    WebElement tableExpenses;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    List<WebElement> rows;

    @FindBy(xpath = "//div[@class='oxd-table-card-cell-checkbox']//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']")
    WebElement checkboxDeleteExpenses;

    @FindBy(xpath = "//button[normalize-space()='Delete Selected']")
    WebElement btnDeleteSelected;

    @FindBy(xpath = "//h6[normalize-space()='Attachments']")
    WebElement titleAttachments;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Add'])[2]")
    WebElement btnAddAttachments;

    @FindBy(xpath = "//button[normalize-space()='Back']")
    WebElement btnBack;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    List<WebElement> btnCancelList;

    @FindBy(xpath = "//button[normalize-space()='Submit']")
    WebElement btnSubmit;

    @FindBy(xpath = "//button[normalize-space()='Submit']")
    List<WebElement> btnSubmitList;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    @FindBy(xpath = "//div[@class='oxd-table orangehrm-card-table']//div[@role='columnheader']//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']")
    WebElement checkBoxDelete;

    public boolean displayTitleSubmitClaim(){
        return elementUtils.isElementDisplayed(titleSubmitClaim);
    }

    public String getTextSubmitClaim(){
        return elementUtils.getTextOfElement(titleSubmitClaim);
    }

    public boolean disableReferenceId(){
        return elementUtils.isElementDisabled(referenceId);
    }

    public boolean disableEvent(){
        return elementUtils.isElementDisabled(event);
    }

    public boolean disableStatus(){
        return elementUtils.isElementDisabled(status);
    }

    public boolean disableCurrency(){
        return elementUtils.isElementDisabled(currency);
    }

    public boolean disableRemarks(){
        return elementUtils.isElementDisabled(remarks);
    }

    public boolean displayTitleExpenses(){
        return elementUtils.isElementDisplayed(titleExpenses);
    }

    public String getTextTitleExpenses(){
        return elementUtils.getTextOfElement(titleExpenses);
    }

    public AddExpensesPopUp clickBtnAddExpenses(){
        elementUtils.clickOnElement(btnAddExpenses);
        return new AddExpensesPopUp(driver);
    }

    public boolean displayTableExpenses(){
        return elementUtils.isElementDisplayed(tableExpenses);
    }

    public int getSizeRowExpenses(){
        return rows.size();
    }

    public EditExpensesPopUp clickEditExpensesType() {
        String expensesType = "Planned Surgery";
        elementUtils.clickEditElement(rows, expensesType);

        return new EditExpensesPopUp(driver);
    }

    public DeletePopup clickDeleteExpensesType(){
        String expensesType = "Transport";
        elementUtils.clickDeleteElement(rows, expensesType);
        return new DeletePopup(driver);
    }

    public String getTextDeletedSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

    public String getTextSavedSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

    public boolean displayTitleAttachments(){
        return elementUtils.isElementDisplayed(titleAttachments);
    }

    public String getTextTitleAttachments(){
        return elementUtils.getTextOfElement(titleAttachments);
    }

    public AddAttachmentPage clickBtnAddAttachments(){
        elementUtils.clickOnElement(btnAddAttachments);
        return new AddAttachmentPage(driver);
    }

    public void clickCheckboxDelete(){
        elementUtils.clickOnCheckbox(checkBoxDelete);
    }

    public DeletePopup clickBtnDeleteSelected(){
        elementUtils.clickOnElement(btnDeleteSelected);
        return new DeletePopup(driver);
    }

    public MyClaimsPage clickBtnBack(){
        elementUtils.clickOnElement(btnBack);
        return new MyClaimsPage(driver);
    }

    public void clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
    }

    public boolean isBtnCancelDisappeared() {
        return btnCancelList.isEmpty() || !btnCancelList.get(0).isDisplayed();
    }

    public void clickBtnSubmit(){
        elementUtils.clickOnElement(btnSubmit);
    }

    public boolean isBtnSubmitDisappeared() {
        return btnSubmitList.isEmpty() || !btnSubmitList.get(0).isDisplayed();
    }

}
