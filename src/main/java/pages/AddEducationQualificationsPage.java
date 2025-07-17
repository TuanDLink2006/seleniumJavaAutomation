package pages;

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

public class AddEducationQualificationsPage {

    WebDriverWait wait;
    WebDriver driver;
    ElementUtils elementUtils;

    public AddEducationQualificationsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Education']")
    WebElement titleAddEducation;

    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
    WebElement dropdownLevel;

    @FindBy(xpath = "//label[text()='Institute']/following::input[1]")
    WebElement inputInstitute;

    @FindBy(xpath = "//label[text()='Major/Specialization']/following::input[1]")
    WebElement inputMajor;

    @FindBy(xpath = "//label[text()='Year']/following::input[1]")
    WebElement inputYear;

    @FindBy(xpath = "//label[text()='GPA/Score']/following::input[1]")
    WebElement inputGPA;

    @FindBy(xpath = "//label[text()='Start Date']/following::input[1][@placeholder='yyyy-dd-mm']")
    WebElement startDate;

    @FindBy(xpath = "//label[text()='End Date']/following::input[1][@placeholder='yyyy-dd-mm']")
    WebElement endDate;

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

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancelAddEducation;

    @FindBy(xpath = "//button[@type='submit'][normalize-space()='Save']")
    WebElement btnSaveAddEducation;

    @FindBy(xpath = "//span[normalize-space()='Required']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 100 characters']")
    WebElement messageExceed100Characters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 4 characters']")
    WebElement messageExceed4Characters;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 25 characters']")
    WebElement messageExceed25Characters;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageShouldBeFormat;

    @FindBy(xpath = "//span[normalize-space()='To date should be after from date']")
    WebElement messageToDate;

    @FindBy(xpath = "//span[normalize-space()='Should be a number']")
    WebElement messageShouldNumber;

    public boolean displayTitleAddEducation(){
        return elementUtils.isElementDisplayed(titleAddEducation);
    }

    public boolean displayBtnCancelAddEducation(){
        return elementUtils.isElementDisplayed(btnCancelAddEducation);
    }

    public boolean displayBtnSaveAddEducation(){
        return elementUtils.isElementDisplayed(btnSaveAddEducation);
    }

    public String getTextTitleAddEducation(){
        return elementUtils.getTextOfElement(titleAddEducation);
    }

    public void selectLevelOption(String levelOption) {
        elementUtils.selectOptionFromDropdown(dropdownLevel, levelOption);
    }

    public void enterInputInstitute(String textInstitute){
        elementUtils.enterInputElement(inputInstitute, textInstitute);
    }

    public void enterInputMajor(String textMajor){
        elementUtils.enterInputElement(inputMajor, textMajor);
    }

    public void enterInputYear(String textYear){
        elementUtils.enterInputElement(inputYear, textYear);
    }

    public void enterInputGPA(String textGPA){
        elementUtils.enterInputElement(inputGPA, textGPA);
    }

    public void enterStartDate(String textFromDate){
        elementUtils.enterInputElement(startDate, textFromDate);
    }

    public void selectStartDate(String day, String month, String year) {
        elementUtils.selectDate(startDate, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void enterEndDate(String textToDate){
       elementUtils.enterInputElement(endDate, textToDate);
       elementUtils.clickOnElement(inputGPA);
    }

    public void selectEndDate(String day, String month, String year) {
        elementUtils.selectDate(endDate, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public String getTextMessageRequired(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageExceed100CharactersInstitute(){
        return elementUtils.getTextOfElement(messageExceed100Characters);
    }

    public String getTextMessageExceed100CharactersMajor(){
        return elementUtils.getTextOfElement(messageExceed100Characters);
    }

    public String getTextMessageExceed4Characters(){
        return elementUtils.getTextOfElement(messageExceed4Characters);
    }

    public String getTextMessageExceed25Characters(){
        return elementUtils.getTextOfElement(messageExceed25Characters);
    }

    public String getTextMessageShouldBeFormatStartDate(){
        return elementUtils.getTextOfElement(messageShouldBeFormat);
    }

    public String getTextMessageShouldBeFormatEndDate(){
        return elementUtils.getTextOfElement(messageShouldBeFormat);
    }

    public String getTextMessageStartDate(){
        return elementUtils.getTextOfElement(messageToDate);
    }

    public String getTextMessageShouldBeNumber(){
        return elementUtils.getTextOfElement(messageShouldNumber);
    }

    public QualificationsPage clickBtnCancelAddEducation(){
        elementUtils.clickOnElement(btnCancelAddEducation);
        return new QualificationsPage(driver);
    }

    public QualificationsPage clickBtnSaveAddEducation(){
        elementUtils.clickOnElement(btnSaveAddEducation);
        return new QualificationsPage(driver);
    }
}
