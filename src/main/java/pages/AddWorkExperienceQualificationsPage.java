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

public class AddWorkExperienceQualificationsPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public AddWorkExperienceQualificationsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Work Experience']")
    WebElement titleAddWorkExperience;

    @FindBy(xpath = "//label[text()='Company']/following::input[1]")
    WebElement inputCompany;

    @FindBy(xpath = "//label[text()='Job Title']/following::input[1]")
    WebElement inputJobTitle;

    @FindBy(xpath = "//label[text()='From']/following::input[1][@placeholder='yyyy-dd-mm']")
    WebElement fromDate;

    @FindBy(xpath = "//label[text()='To']/following::input[1][@placeholder='yyyy-dd-mm']")
    WebElement toDate;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-icon-button__icon'])[1]")
    WebElement monthDropdown;

    @FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-icon-button__icon'])[2]")
    WebElement yearDropdown;

    @FindBy(xpath = "//ul//li")
    List<WebElement> years;

    @FindBy(xpath = "//ul//li")
    List<WebElement> months;

    @FindBy(xpath = "//div[contains(@class,'oxd-calendar-date-wrapper')]")
    List<WebElement> days;

    @FindBy(xpath = "//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']")
    WebElement commentAddWorkExperience;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancelAddWorkExperience;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSaveAddWorkExperience;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[normalize-space()='Should be a valid date in yyyy-dd-mm format']")
    WebElement messageShouldBeFormat;

    @FindBy(xpath = "//span[normalize-space()='To date should be after from date']")
    WebElement messageToDate;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 200 characters']")
    WebElement messageExceed200Characters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 100 characters']")
    WebElement messageExceed100Characters;

    public boolean displayTitleAddWorkExperience(){
        return elementUtils.isElementDisplayed(titleAddWorkExperience);
    }

    public String getTextTitleAddWorkExperience(){
        return elementUtils.getTextOfElement(titleAddWorkExperience);
    }

    public boolean displayBtnCancelAddWorkExperience(){
        return elementUtils.isElementDisplayed(btnCancelAddWorkExperience);
    }

    public boolean displayBtnSaveAddWorkExperience(){
        return elementUtils.isElementDisplayed(btnSaveAddWorkExperience);
    }

    public void enterInputCompany(String textCompany){
        elementUtils.enterInputElement(inputCompany, textCompany);
    }

    public void enterInputJobTitle(String textJobTitle){
        elementUtils.enterInputElement(inputJobTitle, textJobTitle);
    }

    public void enterFromDate(String textFromDate){
        elementUtils.enterInputElement(fromDate, textFromDate);
    }

    public void enterToDate(String textToDate){
        elementUtils.enterInputElement(toDate, textToDate);
        commentAddWorkExperience.click();
    }

    public void selectFromDate(String day, String month, String year) {
        elementUtils.selectDate(fromDate, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void selectToDate(String day, String month, String year) {
        elementUtils.selectDate(toDate, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void enterCommentAddWorkExperience(String textComments){
        elementUtils.enterInputElement(commentAddWorkExperience, textComments);
    }

    public String getTextMessageRequiredCompany(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageRequiredJobTitle(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageShouldBeFrom(){
        return elementUtils.getTextOfElement(messageShouldBeFormat);
    }

    public String getTextMessageShouldBeTo(){
        return elementUtils.getTextOfElement(messageShouldBeFormat);
    }

    public String getTextMessageToDate(){
        return elementUtils.getTextOfElement(messageToDate);
    }

    public String getTextMessageExceed100CharactersCompany(){
        return elementUtils.getTextOfElement(messageExceed100Characters);
    }

    public String getTextMessageExceed100CharactersJobTitle(){
        return elementUtils.getTextOfElement(messageExceed100Characters);
    }

    public String getTextMessageExceed200Characters(){
        return elementUtils.getTextOfElement(messageExceed200Characters);
    }

    public QualificationsPage clickBtnCancelAddWorkExperience(){
        elementUtils.clickOnElement(btnCancelAddWorkExperience);
        return new QualificationsPage(driver);
    }

    public QualificationsPage clickBtnSaveAddWorkExperience(){
        elementUtils.clickOnElement(btnSaveAddWorkExperience);
        return new QualificationsPage(driver);
    }

}
