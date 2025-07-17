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

public class AddSkillQualificationsPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;

    public AddSkillQualificationsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Skill']")
    WebElement titleAddSkills;

    @FindBy(xpath = "//div[@class='oxd-select-text oxd-select-text--active']")
    WebElement dropdownSkills;

    @FindBy(xpath = "//label[text()='Years of Experience']/following::input[1]")
    WebElement inputYearsOfExperience;

    @FindBy(xpath = "//textarea[@class='oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical']")
    WebElement comments;

    @FindBy(xpath = "//span[normalize-space()='Required']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[normalize-space()='Should be a number']")
    WebElement messageShouldBeNumber;

    @FindBy(xpath = "//span[normalize-space()='Should be less than 100']")
    WebElement messageShouldBeLessThan100;

    @FindBy(xpath = "(//button[normalize-space()='Cancel'])[3]")
    WebElement btnCancelSkills;

    @FindBy(xpath = "(//button[normalize-space()='Save'])[3]")
    WebElement btnSaveSkills;

    public boolean displayTitleAddSkills(){
        return elementUtils.isElementDisplayed(titleAddSkills);
    }

    public String getTextTitleAddSkills(){
        return elementUtils.getTextOfElement(titleAddSkills);
    }

    public boolean displayBtnCancelSkills(){
        return elementUtils.isElementDisplayed(btnCancelSkills);
    }

    public boolean displayBtnSaveSkills(){
        return elementUtils.isElementDisplayed(btnSaveSkills);
    }

    public void selectSkillOption(String skillOption) {
        elementUtils.selectOptionFromDropdown(dropdownSkills, skillOption);
    }

    public void enterInputYearsOfExperience(String textYearsOfExperience){
        elementUtils.enterInputElement(inputYearsOfExperience, textYearsOfExperience);
    }

    public void enterCommentsAddSkills(String textComments){
        elementUtils.enterTextarea(comments, textComments);
    }

    public String getTextMessageRequired(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageShouldBeNumber(){
        return elementUtils.getTextOfElement(messageShouldBeNumber);
    }

    public String getTextMessageShouldBeLessThan100(){
        return elementUtils.getTextOfElement(messageShouldBeLessThan100);
    }

    public QualificationsPage clickBtnCancelSkills(){
        elementUtils.clickOnElement(btnCancelSkills);
        return new QualificationsPage(driver);
    }

    public QualificationsPage clickBtnSaveSkills(){
        elementUtils.clickOnElement(btnSaveSkills);
        return new QualificationsPage(driver);
    }
}
