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

public class CandidatesPage {

    WebDriver driver;
    ElementUtils elementUtils;
    WebDriverWait wait;
    public CandidatesPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h5[normalize-space()='Candidates']")
    WebElement titleCandidates;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement inputCandidateName;

    @FindBy(xpath = "//div[@role='listbox']")
    WebElement listBox;

    By option = By.xpath("//div[@role='option']");

    @FindBy(xpath = "//span[normalize-space()='Invalid']")
    WebElement messageInvalid;

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    WebElement btnReset;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    WebElement btnSearch;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement btnAdd;

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//div[@role='row']")
    List<WebElement> rows;

    @FindBy(xpath = "//i[@class='oxd-icon bi-eye-fill']")
    WebElement btnView;

    @FindBy(xpath = "//i[@class='oxd-icon bi-trash']")
    WebElement btnDelete;

    @FindBy(xpath = "//i[@class='oxd-icon bi-download']")
    WebElement btnDownload;

    @FindBy(xpath = "//div[@role='document']")
    WebElement document;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public String getTextTitleCandidates(){
        return elementUtils.getTextOfElement(titleCandidates);
    }

    public boolean displayTitleCandidates(){
        return elementUtils.isElementDisplayed(titleCandidates);
    }

    public boolean displayBtnReset(){
        return elementUtils.isElementDisplayed(btnReset);
    }

    public boolean displayBtnSearch(){
        return elementUtils.isElementDisplayed(btnSearch);
    }

    public boolean displayTable(){
        return elementUtils.isElementDisplayed(table);
    }

    public String getTextMessageInvalid(){
        return elementUtils.getTextOfElement(messageInvalid);
    }

    public int getSizeRows(){
        return rows.size();
    }

    public void enterCandidateName(String textCandidateName){
        elementUtils.enterInputElement(inputCandidateName, textCandidateName);
    }

    public void selectCandidateNameOption(String textName, String expectedTextName) throws InterruptedException {
        elementUtils.inputEmployeeElement(inputCandidateName, listBox, option, textName, expectedTextName );
    }

    public void clickBtnReset(){
        elementUtils.clickOnElement(btnReset);
    }

    public void clickBtnSearch(){
        elementUtils.clickOnElement(btnSearch);
    }

    public AddCandidatePage clickBtnAdd(){
        elementUtils.clickOnElement(btnAdd);
        return new AddCandidatePage(driver);
    }

    public ViewCandidatesPage clickBtnView(){
        elementUtils.clickOnElement(btnView);
        return new ViewCandidatesPage(driver);
    }

    public DeletePopup clickBtnDelete(){
        elementUtils.clickOnElement(btnDelete);
        return new DeletePopup(driver);
    }

    public boolean displayDelete(){
        return elementUtils.isElementDisplayed(document);
    }

    public void clickDownloadFile(String expectedName, String downloadFilePath){
        elementUtils.downloadFile(btnDownload, expectedName, downloadFilePath, 2);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
