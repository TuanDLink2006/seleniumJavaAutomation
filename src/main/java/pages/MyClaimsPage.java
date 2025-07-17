package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;
import java.util.List;

public class MyClaimsPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public MyClaimsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h5[normalize-space()='My Claims']")
    WebElement titleMyClaim;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement inputReferenceId;

    @FindBy(xpath = "//div[@role='listbox']")
    WebElement listBox;

    @FindBy(xpath = "//div[@role='option']")
    List<WebElement> options;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'][normalize-space()='-- Select --'])[1]")
    WebElement dropdownEventName;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'][normalize-space()='-- Select --'])[2]")
    WebElement dropdownStatus;

    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[1]")
    WebElement fromDate;

    @FindBy(xpath = "(//input[@placeholder='yyyy-dd-mm'])[2]")
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

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    WebElement btnReset;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement btnSearch;

    @FindBy(xpath = "//button[normalize-space()='Submit Claim']")
    WebElement btnSubmitClaim;

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    List<WebElement> jobRows;

    @FindBy(xpath = "//button[@type='button'][normalize-space()='View Details']")
    WebElement btnViewDetails;

    public String getTextTitleMyClaim(){
        return elementUtils.getTextOfElement(titleMyClaim);
    }

    public boolean displayTitleMyClaim(){
        return elementUtils.isElementDisplayed(titleMyClaim);
    }

    public boolean displayBtnReset(){
        return elementUtils.isElementDisplayed(btnReset);
    }

    public boolean displayBtnSearch(){
        return elementUtils.isElementDisplayed(btnSearch);
    }

    public boolean displayBtnSubmitClaim(){
        return elementUtils.isElementDisplayed(btnSubmitClaim);
    }

    public boolean displayTable(){
        return elementUtils.isElementDisplayed(table);
    }

    public int getSizeRows(){
        return jobRows.size();
    }

    public void enterInputReferenceId(String id, String expectedId) throws InterruptedException {
        elementUtils.inputIdElement(inputReferenceId, listBox, options, id, expectedId);
    }

    public void clickBtnSearch(){
        elementUtils.clickOnElement(btnSearch);
    }

    public SubmitClaimPage clickBtnViewDetails(){
        elementUtils.clickOnElement(btnViewDetails);
        return new SubmitClaimPage(driver);
    }

    public CreateClaimRequestPage clickBtnSubmitClaim(){
        elementUtils.clickOnElement(btnSubmitClaim);
        return new CreateClaimRequestPage(driver);
    }

}
