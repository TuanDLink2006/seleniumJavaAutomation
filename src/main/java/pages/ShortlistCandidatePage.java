package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class ShortlistCandidatePage {
    WebDriver driver;
    ElementUtils elementUtils;
    public ShortlistCandidatePage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Shortlist Candidate']")
    WebElement titleShortlistCandidate;

    @FindBy(xpath = "//label[text()='Candidate']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputCandidate;

    @FindBy(xpath = "//label[text()='Vacancy']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputVacancy;

    @FindBy(xpath = "//label[text()='Hiring Manager']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputHiringManager;

    @FindBy(xpath = "//label[text()='Current Status']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputCurrentStatus;

    @FindBy(xpath = "//textarea[@placeholder='Type here']")
    WebElement notes;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    public String getTextTitleShortlistCandidate(){
        return elementUtils.getTextOfElement(titleShortlistCandidate);
    }

    public boolean displayTitleShortlistCandidate(){
        return elementUtils.isElementDisplayed(titleShortlistCandidate);
    }

    public boolean disableInputCandidate(){
        return elementUtils.isElementDisabled(inputCandidate);
    }

    public boolean disableInputVacancy(){
        return elementUtils.isElementDisabled(inputVacancy);
    }

    public boolean disableInputHiringManager(){
        return elementUtils.isElementDisabled(inputHiringManager);
    }

    public boolean disableInputCurrentStatus(){
        return elementUtils.isElementDisabled(inputCurrentStatus);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public void enterNotes(String textNote){
        elementUtils.enterTextarea(notes, textNote);
    }

    public ViewCandidatesPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new ViewCandidatesPage(driver);
    }

    public ViewCandidatesPage clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
        return new ViewCandidatesPage(driver);
    }
}
