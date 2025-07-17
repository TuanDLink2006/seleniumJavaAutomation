package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import javax.print.DocFlavor;
import java.time.Duration;

public class AddNationalityPage {
    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public AddNationalityPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Nationality']")
    WebElement titleAddNationality;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active']")
    WebElement inputName;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageExist;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageExceedCharacters;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    public String getTextTitleAddNationality(){
        return elementUtils.getTextOfElement(titleAddNationality);
    }

    public boolean displayTitleAddNationality(){
        return elementUtils.isElementDisplayed(titleAddNationality);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public void enterInputName(String textName){
        elementUtils.enterInputElement(inputName, textName);
    }

    public String getMessageRequired(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageExist(){
        return elementUtils.getTextOfElement(messageExist);
    }

    public String getMessageExceedCharacters(){
        return elementUtils.getTextOfElement(messageExceedCharacters);
    }

    public NationalitiesPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new NationalitiesPage(driver);
    }

    public NationalitiesPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new NationalitiesPage(driver);
    }

}
