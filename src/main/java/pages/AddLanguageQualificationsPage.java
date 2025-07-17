package pages;

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

public class AddLanguageQualificationsPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public AddLanguageQualificationsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Language']")
    WebElement titleAddLanguage;

    @FindBy(xpath = "//label[text()='Language']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement dropdownLanguage;

    @FindBy(xpath = "//label[text()='Fluency']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement dropdownFluency;

    @FindBy(xpath = "//label[text()='Competency']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement dropdownCompetency;

    @FindBy(xpath = "//div[@role='option']")
    List<WebElement> options;

    @FindBy(xpath = "(//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical'])[2]")
    WebElement comments;

    @FindBy(xpath = "//span[normalize-space()='Required']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 100 characters']")
    WebElement messageShouldExceed100Characters;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Cancel'])[4]")
    WebElement btnCancelLanguage;

    @FindBy(xpath = "(//button[@type='submit'][normalize-space()='Save'])[4]")
    WebElement btnSaveLanguage;

    public boolean displayTitleAddLanguage(){
        return elementUtils.isElementDisplayed(titleAddLanguage);
    }

    public String getTextTitleAddLanguage(){
        return elementUtils.getTextOfElement(titleAddLanguage);
    }

    public boolean displayBtnCancelLanguage(){
        return elementUtils.isElementDisplayed(btnCancelLanguage);
    }

    public boolean displayBtnSaveLanguage(){
        return elementUtils.isElementDisplayed(btnSaveLanguage);
    }

    public void selectLanguageOption(String languageOption) {
       elementUtils.selectOptionFromDropdown(dropdownLanguage, languageOption);
    }

    public void selectFluencyOption(String fluencyOption) {
        elementUtils.selectOptionFromDropdown(dropdownFluency, fluencyOption);
    }

    public void selectCompetencyOption(String competencyOption) {
        elementUtils.selectOptionFromDropdown(dropdownCompetency, competencyOption);
    }

    public void enterComments(String textComments){
        elementUtils.enterTextarea(comments, textComments);
    }

    public String getTextMessageRequiredLanguage(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageRequiredFluency(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageRequiredCompetency(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageShouldExceed100Characters(){
        return elementUtils.getTextOfElement(messageShouldExceed100Characters);
    }

    public QualificationsPage clickBtnCancelLanguage(){
        elementUtils.clickOnElement(btnCancelLanguage);
        return new QualificationsPage(driver);
    }

    public QualificationsPage clickBtnSaveLanguage(){
        elementUtils.clickOnElement(btnSaveLanguage);
        return new QualificationsPage(driver);
    }
}
