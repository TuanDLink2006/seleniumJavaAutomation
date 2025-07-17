package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class AdminPage {

    WebDriver driver;
    ElementUtils elementUtils;
    public AdminPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[normalize-space()='Job']")
    WebElement dropdownJob;

    @FindBy(xpath = "//a[normalize-space()='Job Titles']")
    WebElement jobTitlesOption;

    @FindBy(xpath = "//a[normalize-space()='Pay Grades']")
    WebElement payGradesOption;

    @FindBy(xpath = "//a[normalize-space()='Employment Status']")
    WebElement employmentStatusOption;

    @FindBy(xpath = "//a[normalize-space()='Nationalities']")
    WebElement nationalitiesOption;

    @FindBy(xpath = "//a[normalize-space()='Corporate Branding']")
    WebElement corporateBranding;

    public void clickDropdownJob(){
        elementUtils.clickOnElement(dropdownJob);
    }

    public JobTitleAdminPage clickJobTitleOption(){
        elementUtils.clickOnElement(jobTitlesOption);
        return new JobTitleAdminPage(driver);
    }

    public PayGradesPage clickPayGradesOption(){
        elementUtils.clickOnElement(payGradesOption);
        return new PayGradesPage(driver);
    }

    public EmploymentStatusPage clickEmploymentStatusOption(){
        elementUtils.clickOnElement(employmentStatusOption);
        return new EmploymentStatusPage(driver);
    }

    public NationalitiesPage clickNationalitiesPage(){
        elementUtils.clickOnElement(nationalitiesOption);
        return new NationalitiesPage(driver);
    }

    public CorporateBrandingPage clickCorporateBrandingPage(){
        elementUtils.clickOnElement(corporateBranding);
        return new CorporateBrandingPage(driver);
    }
}
