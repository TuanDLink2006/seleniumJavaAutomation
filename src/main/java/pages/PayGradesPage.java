package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.ElementUtils;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class PayGradesPage {

    WebDriver driver;
    Properties prop;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public PayGradesPage(WebDriver driver){
        this.driver = driver;
        prop = new Properties();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Pay Grades']")
    WebElement titlePayGrades;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement btnAdd;

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    List<WebElement> jobRows;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public String getTextTitlePayGrades(){
        return elementUtils.getTextOfElement(titlePayGrades);
    }

    public boolean displayTitlePayGrades(){
        return elementUtils.isElementDisplayed(titlePayGrades);
    }

    public boolean displayBtnAdd(){
        return elementUtils.isElementDisplayed(btnAdd);
    }

    public boolean displayTable(){
        return elementUtils.isElementDisplayed(table);
    }

    public int getSizeRows(){
        return jobRows.size();
    }

    public AddPayGradesPage clickBtnAdd(){
        elementUtils.clickOnElement(btnAdd);
        return new AddPayGradesPage(driver);
    }

    public EditPayGradesPage clickEditPayGrades(){

        String payGrades = "Grade 2007";
        elementUtils.clickEditElement(jobRows, payGrades);
        return new EditPayGradesPage(driver);
    }

    public DeletePopup clickDeletePayGrades(){
        String payGrades = "Grade 2001";
        elementUtils.clickDeleteElement(jobRows, payGrades);
        return new DeletePopup(driver);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
