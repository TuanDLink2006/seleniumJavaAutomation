package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class EditLanguagePage {

    WebDriver driver;
    ElementUtils elementUtils;
    public EditLanguagePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Edit Language']")
    WebElement titleEditLanguage;

    @FindBy(xpath = "//label[text()='Language']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement dropdownLanguage;

    @FindBy(xpath = "//label[text()='Fluency']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement dropdownFluency;

    @FindBy(xpath = "//label[text()='Competency']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement dropdownCompetency;

    @FindBy(xpath = "//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']")
    WebElement comments;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    public String getTextTitleEditLanguage(){
        return elementUtils.getTextOfElement(titleEditLanguage);
    }

    public boolean displayTitleEditLanguage(){
        return elementUtils.isElementDisplayed(titleEditLanguage);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public boolean disableDropdownLanguage(){
        return elementUtils.isElementDisabled(dropdownLanguage);
    }

    public boolean disableDropdownFluency(){
        return elementUtils.isElementDisabled(dropdownFluency);
    }

    public void selectCompetencyOption(String competencyOption) {
        elementUtils.selectOptionFromDropdown(dropdownCompetency, competencyOption);
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
