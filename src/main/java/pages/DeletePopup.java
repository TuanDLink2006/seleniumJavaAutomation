package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class DeletePopup {
    WebDriver driver;
    ElementUtils elementUtils;

    public DeletePopup(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@role='document']")
    WebElement document;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--card-title']")
    WebElement titleDelete;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--card-body']")
    WebElement contentDelete;

    @FindBy(xpath = "//button[normalize-space()='No, Cancel']")
    WebElement btnNoCancel;

    @FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
    WebElement btnYesDelete;

    public boolean displayDocument(){
        return elementUtils.isElementDisplayed(document);
    }

    public String getTextTitleDelete(){
        return elementUtils.getTextOfElement(titleDelete);
    }

    public String getTextContentDelete(){
        return elementUtils.getTextOfElement(contentDelete);
    }

    public boolean displayBtnNoCancel(){
        return elementUtils.isElementDisplayed(btnNoCancel);
    }

    public boolean displayBtnYesDelete(){
        return elementUtils.isElementDisplayed(btnYesDelete);
    }

    public void clickBtnNoCancel(){
        elementUtils.clickOnElement(btnNoCancel);
    }

    public void clickBtnYesDelete(){
        elementUtils.clickOnElement(btnYesDelete);
    }
}
