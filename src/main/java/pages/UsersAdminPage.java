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
import java.util.TreeMap;

public class UsersAdminPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public UsersAdminPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h5[normalize-space()='System Users']")
    WebElement titleSystemUsers;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active']")
    WebElement userName;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
    WebElement dropdownUserRole;

    By option = By.xpath("//div[@role='option']");

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement inputEmployeeName;

    @FindBy(xpath = "//div[@role='listbox']")
    WebElement listBox;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
    WebElement dropdownStatus;

    @FindBy(xpath = "//span[text()='No Records Found']")
    List<WebElement> noRecordElements;

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    WebElement btnReset;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement btnSearch;

    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@class='oxd-table-card']//div[2]")
    List<WebElement> userNameCells;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement btnAdd;

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//i[@class='oxd-icon bi-pencil-fill']")
    WebElement btnEdit;

    @FindBy(xpath = "//i[@class='oxd-icon bi-trash']")
    WebElement btnDelete;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleSystemUsers(){
        return elementUtils.isElementDisplayed(titleSystemUsers);
    }

    public String getTextTitleSystemUsers(){
        return elementUtils.getTextOfElement(titleSystemUsers);
    }

    public boolean displayBtnReset(){
        return elementUtils.isElementDisplayed(btnReset);
    }

    public boolean displayBtnSearch(){
        return elementUtils.isElementDisplayed(btnSearch);
    }

    public boolean displayTable(){
        return elementUtils.isElementDisplayed(table);
    }

    public boolean displayBtnAdd(){
        return elementUtils.isElementDisplayed(btnAdd);
    }

    public void enterUserName(String textUsername){
        elementUtils.enterInputElement(userName, textUsername);
    }

    public void selectUserRole(String userRole){
        elementUtils.selectOptionFromDropdown(dropdownUserRole, userRole);
    }

    public void inputEmployee(String textName, String expectedFullName) throws InterruptedException {
        elementUtils.inputEmployeeElement(inputEmployeeName, listBox, option, textName, expectedFullName);
    }

    public void selectStatus(String status){
        elementUtils.selectOptionFromDropdown(dropdownStatus, status);
    }

    public boolean isNoRecordsFoundDisplayed(){
        return !noRecordElements.isEmpty();
    }

    public boolean isUsernameDisplayedInResult(String expectedUsername){
        if (isNoRecordsFoundDisplayed()){
            return false;
        }

        for (WebElement cell : userNameCells){
            if (cell.getText().equalsIgnoreCase(expectedUsername)){
                System.out.println(cell.getText());
                return true;
            }
        }

        return false;
    }

    public void clickBtnSearch(){
        elementUtils.clickOnElement(btnSearch);
    }

    public AddUserNamePage clickBtnAdd(){
        elementUtils.clickOnElement(btnAdd);
        return new AddUserNamePage(driver);
    }

    public EditUserPage clickEditUser(){
        elementUtils.clickOnElement(btnEdit);
        return new EditUserPage(driver);
    }

    public DeletePopup clickDeleteUser(){
        elementUtils.clickOnElement(btnDelete);
        return new DeletePopup(driver);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
