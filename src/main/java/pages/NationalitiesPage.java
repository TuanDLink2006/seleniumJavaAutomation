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

public class NationalitiesPage {

    WebDriver driver;
    Properties prop;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public NationalitiesPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Nationalities']")
    WebElement titleNationalities;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement btnAdd;

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    List<WebElement> jobRows;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public String getTextTitleNationalities(){
        return elementUtils.getTextOfElement(titleNationalities);
    }

    public boolean displayTitleNationalities(){
        return elementUtils.isElementDisplayed(titleNationalities);
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

    public AddNationalityPage clickBtnAdd(){
        elementUtils.clickOnElement(btnAdd);
        return new AddNationalityPage(driver);
    }

    public EditNationalitiesPage clickEditNationalities(){

        String nationality = "Viet Nam";
        elementUtils.clickEditElement(jobRows, nationality);
        return new EditNationalitiesPage(driver);
    }

    public DeletePopup clickDeleteNationalities(){
        String nationality = "Nga";
        elementUtils.clickDeleteElement(jobRows, nationality);
        return new DeletePopup(driver);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }
}
