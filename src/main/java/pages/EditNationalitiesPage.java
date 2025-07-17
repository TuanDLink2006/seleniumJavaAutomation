package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class EditNationalitiesPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public EditNationalitiesPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Edit Nationality']")
    WebElement titleEditNationality;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active']")
    WebElement inputNationalities;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleEditNationality(){
        return elementUtils.isElementDisplayed(titleEditNationality);
    }

    public String getTextTitleEditNationality(){
        return elementUtils.getTextOfElement(titleEditNationality);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public void enterNationalities(String textNationalities){
        elementUtils.enterInputAdmin(inputNationalities, textNationalities);
    }

    public NationalitiesPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new NationalitiesPage(driver);
    }

    public NationalitiesPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new NationalitiesPage(driver);
    }

    public String getTextEditSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }
}
