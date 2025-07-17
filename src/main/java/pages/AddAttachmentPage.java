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

public class AddAttachmentPage{

    WebDriver driver;
    ElementUtils elementUtils;
    WebDriverWait wait;
    public AddAttachmentPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@role='document']")
    WebElement documentAddAttachment;

    @FindBy(xpath = "//h6[normalize-space()='Add Attachment']")
    WebElement titleAddAttachment;

    @FindBy(xpath = "//input[@type='file']")
    WebElement inputSelectFile;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-input-hint']")
    WebElement messageAccept;

    @FindBy(xpath = "//span[normalize-space()='Should not exceed 200 characters']")
    WebElement messageExceed200Characters;

    @FindBy(xpath = "//textarea[@placeholder='Type comment here']")
    WebElement commentAddAttachment;

    @FindBy(xpath = "//h6[text()='Add Attachment']/ancestor::div[contains(@class,'modal')]//button[normalize-space()='Cancel']")
    WebElement btnCancelAddAttachment;

    @FindBy(xpath = "//h6[text()='Add Attachment']/ancestor::div[contains(@class,'modal')]//button[normalize-space()='Save']")
    WebElement btnSaveAddAttachment;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageAttachmentSizeExceeded;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayDocumentAddAttachment(){
        return elementUtils.isElementDisplayed(documentAddAttachment);
    }

    public boolean displayTitleAddAttachment(){
        return elementUtils.isElementDisplayed(titleAddAttachment);
    }

    public String getTextTitleAddAttachment(){
        return elementUtils.getTextOfElement(titleAddAttachment);
    }

    public boolean displayBtnCancelAttachment(){
        return elementUtils.isElementDisplayed(btnCancelAddAttachment);
    }

    public boolean displayBtnSaveAttachment(){
        return elementUtils.isElementDisplayed(btnSaveAddAttachment);
    }

    public void selectFile(String filePath){
        elementUtils.selectElementFile(inputSelectFile, filePath);
    }

    public String getTextAcceptTo(){
        return elementUtils.getTextOfElement(messageAccept);
    }

    public void enterCommentAddAttachment(String textComment){
        elementUtils.enterInputElement(commentAddAttachment, textComment);
    }

    public void clickBtnCancelAddAttachment(){
        elementUtils.clickOnElement(btnCancelAddAttachment);
    }

    public void clickBtnSaveAddAttachment(){
        elementUtils.clickOnElement(btnSaveAddAttachment);
    }

    public String getTextMessageRequired(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getTextMessageAttachmentSizeExceeded(){
        return elementUtils.getTextOfElement(messageAttachmentSizeExceeded);
    }

    public String getTextMessageExceed200Characters(){
        return elementUtils.getTextOfElement(messageExceed200Characters);
    }

    public String getTextAttachmentSuccess(){
        wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(messageSuccess);
    }



}
