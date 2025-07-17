package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import utils.ElementUtils;

import java.util.List;

public class EditEducationPage {
    WebDriver driver;
    ElementUtils elementUtils;

    public EditEducationPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Edit Education']")
    WebElement titleEditEducation;

    @FindBy(xpath = "//label[text()='Level']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputLevel;

    @FindBy(xpath = "//label[text()='Institute']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputInstitute;

    @FindBy(xpath = "//label[text()='Major/Specialization']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputMajor;

    @FindBy(xpath = "//label[text()='Year']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputYear;

    @FindBy(xpath = "//label[text()='GPA/Score']/ancestor::div[contains(@class,'oxd-input-group')]//input")
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
    WebElement btnCancel;

    @FindBy(xpath = "//button[@type='submit'][normalize-space()='Save']")
    WebElement btnSave;

    public String getTextTitleEditEducation(){
        return elementUtils.getTextOfElement(titleEditEducation);
    }

    public boolean displayTitleEditEducation(){
        return elementUtils.isElementDisplayed(titleEditEducation);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public boolean disableInputLevel(){
        return elementUtils.isElementDisabled(inputLevel);
    }

    public void enterInputInstitute(String textInstitute){
        elementUtils.enterInputEdit(inputInstitute, textInstitute);
    }

    public void enterInputMajor(String textMajor){
        elementUtils.enterInputEdit(inputMajor, textMajor);
    }

    public void enterInputYear(String textYear){
        elementUtils.enterInputEdit(inputYear, textYear);
    }

    public void enterInputGPA(String textGPA){
        elementUtils.enterInputEdit(inputGPA, textGPA);
    }

    public void selectStartDate(String day, String month, String year) {
        elementUtils.selectDate(startDate, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void selectEndDate(String day, String month, String year) {
        elementUtils.selectDate(endDate, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public QualificationsPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new QualificationsPage(driver);
    }

    public QualificationsPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new QualificationsPage(driver);
    }


}
