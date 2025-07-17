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

public class EditCustomFieldPage {
    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public EditCustomFieldPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Custom Field']")
    WebElement titleAddCustomField;

    @FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
    WebElement fieldName;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div/div/div[2]/div/div/div[2]")
    WebElement dropdownScreen;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters organization-name-container']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")
    WebElement dropdownType;

    @FindBy(xpath = "//div[@role='option']")
    List<WebElement> options;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
    WebElement selectOption;

    public String getTextTitleAddCustomField(){
        return elementUtils.getTextOfElement(titleAddCustomField);
    }

    public boolean displayTitleAddCustomField(){
        return elementUtils.isElementDisplayed(titleAddCustomField);
    }

    public void clearFieldName(){
        elementUtils.clearInput(fieldName);
    }

    public void enterFieldName(String textName) {
        clearFieldName();
        wait.until(ExpectedConditions.visibilityOf(fieldName));
        fieldName.sendKeys(textName);
    }

    public void selectOptionScreen(String screenOption){
        elementUtils.selectOptionFromDropdown(dropdownScreen, screenOption);
    }

    public void selectOptionType(String typeOption){
        elementUtils.selectOptionFromDropdown(dropdownType, typeOption);
    }

    public void enterSelectOption(String textSelectOption){
        elementUtils.enterInputElement(selectOption, textSelectOption);
    }

    public CustomFieldsPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new CustomFieldsPage(driver);
    }

    public String getMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }


}
