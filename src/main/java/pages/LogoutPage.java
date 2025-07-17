package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class LogoutPage {

    WebDriver driver;
    ElementUtils elementUtils;
    public LogoutPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    WebElement userDropdown;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    WebElement logout;

    public void clickUserDropdown(){
        elementUtils.clickOnElement(userDropdown);
    }

    public void clickLogout(){
        elementUtils.clickOnElement(logout);
    }


}
