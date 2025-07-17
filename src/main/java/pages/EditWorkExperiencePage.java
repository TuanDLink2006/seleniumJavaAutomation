package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

import java.util.List;

public class EditWorkExperiencePage {

    WebDriver driver;
    ElementUtils elementUtils;
    public EditWorkExperiencePage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Edit Work Experience']")
    WebElement titleEditWorkExperience;

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
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    public String getTextTitleEditWorkExperience(){
        return elementUtils.getTextOfElement(titleEditWorkExperience);
    }

    public boolean displayTitleEditWorkExperience(){
        return elementUtils.isElementDisplayed(titleEditWorkExperience);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public void enterInputCompany(String textCompany){
        elementUtils.enterInputEdit(inputCompany, textCompany);
    }

    public void enterInputJobTitle(String textJobTitle){
        elementUtils.enterInputEdit(inputJobTitle, textJobTitle);
    }

    public void selectFromDate(String day, String month, String year) {
        elementUtils.selectDate(fromDate, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void selectToDate(String day, String month, String year) {
        elementUtils.selectDate(toDate, yearDropdown, years, monthDropdown, months, days, day, month, year);
    }

    public void enterCommentAddWorkExperience(String textComments){
        elementUtils.enterInputEdit(commentAddWorkExperience, textComments);
    }

    public void clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
    }

    public void clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
    }

}
