package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class ClaimPage {

    WebDriver driver;
    ElementUtils elementUtils;
    public ClaimPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='oxd-topbar-body-nav-tab-item']")
    WebElement dropdownConfiguration;

    @FindBy(xpath = "//a[normalize-space()='Events']")
    WebElement eventsOption;

    @FindBy(xpath = "//a[normalize-space()='Expense Types']")
    WebElement expenseTypesOption;

    @FindBy(xpath = "//a[normalize-space()='My Claims']")
    WebElement myClaims;

    public void clickDropdownConfiguration(){
        elementUtils.clickOnElement(dropdownConfiguration);
    }

    public EventsPage selectEventsOption(){
        clickDropdownConfiguration();
        elementUtils.clickOnElement(eventsOption);
        return new EventsPage(driver);
    }

    public ExpenseTypesPage selectExpenseTypeOption(){
        clickDropdownConfiguration();
        elementUtils.clickOnElement(expenseTypesOption);
        return new ExpenseTypesPage(driver);
    }

    public MyClaimsPage selectMyClaim(){
        elementUtils.clickOnElement(myClaims);
        return new MyClaimsPage(driver);
    }
}
