package pages;

import org.bouncycastle.jcajce.provider.asymmetric.X509;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class DataImportPage {
    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public DataImportPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-main-title']")
    WebElement titleDataImport;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-sub-title']")
    WebElement textTitleNote;

    @FindBy(xpath = "//p[normalize-space()='Column order should not be changed']")
    WebElement textColumnOrder;

    @FindBy(xpath = "//p[normalize-space()='First Name and Last Name are compulsory']")
    WebElement textFirstName;

    @FindBy(xpath = "//p[normalize-space()='All date fields should be in YYYY-MM-DD format']")
    WebElement textAllDate;

    @FindBy(xpath = "//p[normalize-space()='If gender is specified, value should be either Male or Female']")
    WebElement textIfGender;

    @FindBy(xpath = "//p[normalize-space()='Each import file should be configured for 100 records or less']")
    WebElement textEachImport;

    @FindBy(xpath = "//p[normalize-space()='Multiple import files may be required']")
    WebElement textMultipleImport;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-input-hint']")
    WebElement textAcceptUpTo;

    @FindBy(xpath = "//input[@type='file']")
    WebElement selectFile;

    @FindBy(xpath = "//button[normalize-space()='Upload']")
    WebElement btnUpload;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageRequired;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageFileType;

    @FindBy(xpath = "//div[@role='document']")
    WebElement document;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--card-title']")
    WebElement titleDocument;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--card-body']")
    WebElement textBodyDocument;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--card-body orangehrm-success-message']")
    WebElement textMessageBodyDocument;

    @FindBy(xpath = "//button[normalize-space()='Ok']")
    WebElement btnOk;

    public boolean displayTitleDataImport(){
        return elementUtils.isElementDisplayed(titleDataImport);
    }

    public boolean displayBtnUpload(){
        return elementUtils.isElementDisplayed(btnUpload);
    }

    public String getTextTitleNote(){
        return elementUtils.getTextOfElement(textTitleNote);
    }

    public String getTextColumnOrder(){
        return elementUtils.getTextOfElement(textColumnOrder);
    }

    public String getTextFirstName(){
        return elementUtils.getTextOfElement(textFirstName);
    }

    public String getTextAllDate(){
        return elementUtils.getTextOfElement(textAllDate);
    }

    public String getTextIfGender(){
        return elementUtils.getTextOfElement(textIfGender);
    }

    public String getTextEachImport(){
        return elementUtils.getTextOfElement(textEachImport);
    }

    public String getTextMultipleImport(){
        return elementUtils.getTextOfElement(textMultipleImport);
    }

    public String getTextAcceptUpTo(){
        return elementUtils.getTextOfElement(textAcceptUpTo);
    }

    public void enterSelectFile(String fileUpload){
        elementUtils.selectElementFile(selectFile, fileUpload);
    }

    public void clickBtnUpload(){
        elementUtils.clickOnElement(btnUpload);
    }

    public String getMessageRequired(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public String getMessageFileType(){
        return elementUtils.getTextOfElement(messageFileType);
    }

    public boolean displayDocument(){
        return elementUtils.isElementDisplayed(document);
    }

    public String getTextTitleDocument(){
        return elementUtils.getTextOfElement(titleDocument);
    }

    public String getTextBodyDocument(){
        return elementUtils.getTextOfElement(textBodyDocument);
    }

    public String getTextMessageBodyDocument(){
        return elementUtils.getTextOfElement(textMessageBodyDocument);
    }

    public void clickBtnOk(){
        elementUtils.clickOnElement(btnOk);
    }

}
