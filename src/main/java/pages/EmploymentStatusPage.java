package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class EmploymentStatusPage {

    WebDriver driver;
    Properties prop;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public EmploymentStatusPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        prop = new Properties();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Employment Status']")
    WebElement titleEmployeeStatus;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement btnAdd;

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    List<WebElement> jobRows;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public String getTextTitleEmploymentStatus(){
        return elementUtils.getTextOfElement(titleEmployeeStatus);
    }

    public boolean displayTitleEmploymentStatus(){
        return elementUtils.isElementDisplayed(titleEmployeeStatus);
    }

    public boolean displayBtnAdd(){
        return elementUtils.isElementDisplayed(btnAdd);
    }

    public boolean displayTable(){
        return elementUtils.isElementDisplayed(table);
    }

    public int getSizeRowTable(){
        return jobRows.size();
    }

    public AddEmploymentStatusPage clickBtnAdd(){
        elementUtils.clickOnElement(btnAdd);
        return new AddEmploymentStatusPage(driver);
    }

    public EditEmployeeStatusPage clickEditEmployeeStatus(){

        String employeeStatus = "part-time Manchester United";
        elementUtils.clickEditElement( jobRows, employeeStatus);
        return new EditEmployeeStatusPage(driver);
    }

    public DeletePopup clickDeleteEmployeeStatus(){
        String employeeStatus = "part-time Manchester United 2";
        elementUtils.clickDeleteElement(jobRows, employeeStatus);
        return new DeletePopup(driver);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
