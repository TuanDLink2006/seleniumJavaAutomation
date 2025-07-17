package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class ConfirmationRequiredPopup {
    WebDriver driver;
    ElementUtils elementUtils;
    public ConfirmationRequiredPopup(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--card-title']")
    WebElement titleDeleteCurrent;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--card-body']")
    WebElement contentDeleteCurrent;

    @FindBy(xpath = "//button[normalize-space()='No, Cancel']")
    WebElement btnNoCancel;

    @FindBy(xpath = "//button[normalize-space()='Yes, Confirm']")
    WebElement btnYesConfirm;

    public String getTextTitleDeleteCurrent(){
        return elementUtils.getTextOfElement(titleDeleteCurrent);
    }

    public String getTextContentDeleteCurrent(){
        return elementUtils.getTextOfElement(contentDeleteCurrent);
    }

    public boolean displayBtnNoCancel(){
        return elementUtils.isElementDisplayed(btnNoCancel);
    }

    public boolean displayBtnYesConfirm(){
        return elementUtils.isElementDisplayed(btnYesConfirm);
    }

    public ViewCandidatesPage clickBtnNoCancel(){
        elementUtils.clickOnElement(btnNoCancel);
        return new ViewCandidatesPage(driver);
    }

    public ViewCandidatesPage clickBtnYesConfirm(){
        elementUtils.clickOnElement(btnYesConfirm);
        return new ViewCandidatesPage(driver);
    }

}
