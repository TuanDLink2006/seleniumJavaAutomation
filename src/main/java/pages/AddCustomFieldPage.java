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

import javax.print.DocFlavor;
import java.time.Duration;
import java.util.List;

public class AddCustomFieldPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public AddCustomFieldPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Custom Field']")
    WebElement titleAddCustomField;

    @FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
    WebElement fieldName;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//div[@class='oxd-select-text oxd-select-text--active']")
    WebElement dropdownScreen;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters organization-name-container']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//div[@class='oxd-select-text oxd-select-text--active']")
    WebElement dropdownType;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/span")
    WebElement messageRequired;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/span")
    WebElement messageExist;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleAddCustomField(){
        return elementUtils.isElementDisplayed(titleAddCustomField);
    }

    public String getTextTitleAddCustomField(){
        return elementUtils.getTextOfElement(titleAddCustomField);
    }

    public void enterInputFieldName(String textName){
        elementUtils.enterInputElement(fieldName, textName);
    }

    public void selectOptionScreen(String screenOption){
        elementUtils.selectOptionFromDropdown(dropdownScreen, screenOption);
    }

    public void selectOptionType(String typeOption){
        elementUtils.selectOptionFromDropdown(dropdownType, typeOption);
    }

    public CustomFieldsPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new CustomFieldsPage(driver);
    }

    public CustomFieldsPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new CustomFieldsPage(driver);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

    public String getTextMessageExist(){
        return elementUtils.getTextOfElement(messageExist);
    }

    public String getTextMessageRequiredFieldName(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageRequiredScreen(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageRequiredType(){
        return elementUtils.getTextOfElement(messageRequired);
    }

}
