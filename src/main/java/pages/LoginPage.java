package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement userName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement password;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement login;

    @FindBy(xpath = "//div[@role='alert']")
    WebElement messageAlert;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageUsername;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messagePassword;

    public void enterUserName(String textUsername){
        elementUtils.enterInputElement(userName, textUsername);
    }

    public void enterPassword(String textPassword){
        elementUtils.enterInputElement(password, textPassword);
    }

    public void clickLogin(){
        elementUtils.clickOnElement(login);
    }

    public String getTextMessageAlert(){
        return elementUtils.getTextOfElement(messageAlert);
    }

    public String getTextMessageUsername(){
        return elementUtils.getTextOfElement(messageUsername);
    }

    public String getTextMessagePassword(){
        return elementUtils.getTextOfElement(messagePassword);
    }
}
