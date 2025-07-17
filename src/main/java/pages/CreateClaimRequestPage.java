package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.ElementUtils;

import java.time.Duration;
import java.util.List;

public class CreateClaimRequestPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public CreateClaimRequestPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Create Claim Request']")
    WebElement titleCreateClaimRequest;

    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[1]")
    WebElement dropdownEvent;

    @FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[2]")
    WebElement dropdownCurrency;

    @FindBy(xpath = "//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']")
    WebElement remarks;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Create']")
    WebElement btnCreate;

    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required'])[1]")
    WebElement messageRequiredEvent;

    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required'])[2]")
    WebElement messageRequiredCurrency;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 1000 characters']")
    WebElement messageExceed1000Characters;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleCreateClaimRequest(){
        return elementUtils.isElementDisplayed(titleCreateClaimRequest);
    }

    public String getTextTitleCreateClaimRequest(){
        return elementUtils.getTextOfElement(titleCreateClaimRequest);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnCreate(){
        return elementUtils.isElementDisplayed(btnCreate);
    }

    public void selectEventOptions(String optionEvent){

        elementUtils.selectOptionFromDropdown(dropdownEvent, optionEvent);
    }

    public void selectCurrencyOption(String optionCurrency){
        elementUtils.selectOptionFromDropdown(dropdownCurrency, optionCurrency);
    }

    public void enterRemarks(String textRemarks){
        elementUtils.enterTextarea(remarks, textRemarks);
    }

    public MyClaimsPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new MyClaimsPage(driver);
    }

    public SubmitClaimPage clickBtnCreate(){
        elementUtils.clickOnElement(btnCreate);
        return new SubmitClaimPage(driver);
    }

    public String getTextMessageRequiredEvent(){
        return elementUtils.getTextOfElement(messageRequiredEvent);
    }

    public String getTextMessageRequiredCurrency(){
        return elementUtils.getTextOfElement(messageRequiredCurrency);
    }

    public String getTextMessageExceed1000Characters(){
        return elementUtils.getTextOfElement(messageExceed1000Characters);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
