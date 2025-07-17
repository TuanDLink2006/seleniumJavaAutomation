package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class MyInfoPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public MyInfoPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Personal Details']")
    WebElement personalDetails;

    @FindBy(xpath = "//a[normalize-space()='Contact Details']")
    WebElement contactDetails;

    @FindBy(xpath = "//a[normalize-space()='Emergency Contacts']")
    WebElement emergencyContact;

    @FindBy(xpath = "//a[normalize-space()='Dependents']")
    WebElement dependents;

    @FindBy(xpath = "//a[normalize-space()='Immigration']")
    WebElement immigration;

    @FindBy(xpath = "//a[normalize-space()='Qualifications']")
    WebElement qualifications;

    public PersonalDetailsPage clickPersonalDetails(){
        elementUtils.clickOnElement(personalDetails);
        return new PersonalDetailsPage(driver);
    }

    public ContactDetailPage clickContactDetailPage(){
        elementUtils.clickOnElement(contactDetails);
        return new ContactDetailPage(driver);
    }

    public EmergencyContactsPage clickEmergencyContact(){
        elementUtils.clickOnElement(emergencyContact);
        return new EmergencyContactsPage(driver);
    }

    public DependentsPage clickDependents(){
        elementUtils.clickOnElement(dependents);
        return new DependentsPage(driver);
    }

    public ImmigrationRecordsPage clickImmigration(){
        elementUtils.clickOnElement(immigration);
        return new ImmigrationRecordsPage(driver);
    }

    public QualificationsPage clickQualifications(){
        elementUtils.clickOnElement(qualifications);
        return new QualificationsPage(driver);
    }

}
