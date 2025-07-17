package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class EditAttachmentPage {

    WebDriver driver;
    ElementUtils elementUtils;

    public EditAttachmentPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Edit Attachment']")
    WebElement titleEditAttachment;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p']")
    WebElement fileCurrent;

    @FindBy(xpath = "//input[@type='file']")
    WebElement uploadFile;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

    public String getTextTitleEditAttachment(){
        return elementUtils.getTextOfElement(titleEditAttachment);
    }

    public boolean displayTitleEditAttachment(){
        return elementUtils.isElementDisplayed(titleEditAttachment);
    }

    public boolean displayFileCurrent(){
        return elementUtils.isElementDisplayed(fileCurrent);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public void uploadFile(String filePath){
        elementUtils.selectElementFile(uploadFile, filePath);
    }

    public void clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
    }

    public void clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
    }
}
