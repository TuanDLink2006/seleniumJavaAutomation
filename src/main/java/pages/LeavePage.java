package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class LeavePage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public LeavePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[normalize-space()='Entitlements']")
    WebElement dropdownEntitlements;

    @FindBy(xpath = "//a[normalize-space()='Add Entitlements']")
    WebElement addEntitlements;

    public void clickDropdownEntitlements(){
        elementUtils.clickOnElement(dropdownEntitlements);
    }

    public AddLeaveEntitlementPage clickAddLeaveEntitlement(){
        elementUtils.clickOnElement(addEntitlements);
        return new AddLeaveEntitlementPage(driver);
    }

}
