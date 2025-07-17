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

public class JobTitleAdminPage {

    WebDriver driver;
    Properties prop;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public JobTitleAdminPage(WebDriver driver){
        this.driver = driver;
        prop = new Properties();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Job Titles']")
    WebElement jobTitles;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement btnAdd;

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    List<WebElement> jobRows;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public String getTextJobTitles(){
        return elementUtils.getTextOfElement(jobTitles);
    }

    public boolean displayJobTitles(){
        return elementUtils.isElementDisplayed(jobTitles);
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

    public AddJobTitlePage clickBtnAdd(){
        elementUtils.clickOnElement(btnAdd);
        return new AddJobTitlePage(driver);
    }

    public EditJobTitlePage clickEditJobTitle(){

        String jobTitle = "Management Manchester United";
        elementUtils.clickEditElement(jobRows, jobTitle);
        return new EditJobTitlePage(driver);
    }

    public DeletePopup clickDeleteJobTitle(){
        String jobTitle = "President Manchester Unit";
        elementUtils.clickDeleteElement(jobRows, jobTitle);
        return new DeletePopup(driver);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }
}
