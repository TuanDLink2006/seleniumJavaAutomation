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

public class QualificationsPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public QualificationsPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Qualifications']")
    WebElement titleQualifications;

    @FindBy(xpath = "//h6[normalize-space()='Work Experience']")
    WebElement titleWorkExperience;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Add'])[1]")
    WebElement btnAddWorkExperience;

    @FindBy(xpath = "//h6[normalize-space()='Work Experience']/following::table[1]")
    WebElement tableWorkExperience;

    @FindBy(xpath = "//h6[normalize-space()='Work Experience']/following::table[1]//tbody/tr")
    List<WebElement> rowsWorkExperience;

    @FindBy(xpath = "//h6[normalize-space()='Education']")
    WebElement titleEducation;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Add'])[2]")
    WebElement btnAddEducation;

    @FindBy(xpath = "//h6[normalize-space()='Education']/following::table[1]")
    WebElement tableEducation;

    @FindBy(xpath = "//h6[normalize-space()='Education']/following::table[1]//tbody/tr")
    List<WebElement> rowsEducation;

    @FindBy(xpath = "//h6[normalize-space()='Skills']")
    WebElement titleSkills;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Add'])[3]")
    WebElement btnAddSkills;

    @FindBy(xpath = "//h6[normalize-space()='Skills']/following::table[1]")
    WebElement tableSkills;

    @FindBy(xpath = "//h6[normalize-space()='Skills']/following::table[1]//tbody/tr")
    List<WebElement> rowsSkills;

    @FindBy(xpath = "//h6[normalize-space()='Languages']")
    WebElement titleLanguages;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Add'])[4]")
    WebElement btnAddLanguages;

    @FindBy(xpath = "//h6[normalize-space()='Languages']/following::table[1]")
    WebElement tableLanguage;

    @FindBy(xpath = "//h6[normalize-space()='Languages']/following::table[1]//tbody/tr")
    List<WebElement> rowsLanguages;

    @FindBy(xpath = "//h6[normalize-space()='License']")
    WebElement titleLicense;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Add'])[5]")
    WebElement btnAddLicense;

    @FindBy(xpath = "//h6[normalize-space()='License']/following::table[1]")
    WebElement tableLicense;

    @FindBy(xpath = "//h6[normalize-space()='License']/following::table[1]//tbody/tr")
    List<WebElement> rowsLicense;

    @FindBy(xpath = "//h6[normalize-space()='Attachments']")
    WebElement titleAttachments;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Add'])[6]")
    WebElement btnAddAttachment;

    @FindBy(xpath = "//h6[normalize-space()='Attachments']/following::table[1]")
    WebElement tableAttachments;

    @FindBy(xpath = "//h6[normalize-space()='Attachments']/following::table[1]//tbody/tr")
    List<WebElement> rowsAttachments;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public boolean displayTitleQualifications(){
        return  elementUtils.isElementDisplayed(titleQualifications);
    }

    public String getTextTitleQualifications(){
        return elementUtils.getTextOfElement(titleQualifications);
    }

    public boolean displayTitleWorkExperience(){
        return elementUtils.isElementDisplayed(titleWorkExperience);
    }

    public String getTextTitleWorkExperience(){
        return elementUtils.getTextOfElement(titleWorkExperience);
    }

    public boolean displayBtnAddWorkExperience(){
        return elementUtils.isElementDisplayed(btnAddWorkExperience);
    }

    public AddWorkExperienceQualificationsPage clickBtnAddWorkExperience(){
        elementUtils.clickOnElement(btnAddWorkExperience);
        return new AddWorkExperienceQualificationsPage(driver);
    }

    public boolean displayTableWorkExperience(){
        return elementUtils.isElementDisplayed(tableWorkExperience);
    }

    public EditWorkExperiencePage clickEditWorkExperience(){
        String textWorkExperience = "FPT";
        elementUtils.clickEditElement(rowsWorkExperience, textWorkExperience);
        return new EditWorkExperiencePage(driver);
    }

    public DeletePopup clickDeleteWorkExperience(){
        String textWorkExperience = "viettel";
        elementUtils.clickDeleteElement(rowsWorkExperience, textWorkExperience);
        return new DeletePopup(driver);
    }

    public boolean displayTitleEducation(){
        return elementUtils.isElementDisplayed(titleEducation);
    }

    public String getTextTitleEducation(){
        return elementUtils.getTextOfElement(titleEducation);
    }

    public boolean displayBtnAddEducation(){
        return elementUtils.isElementDisplayed(btnAddEducation);
    }

    public AddEducationQualificationsPage clickBtnAddEducation(){
        elementUtils.clickOnElement(btnAddEducation);
        return new AddEducationQualificationsPage(driver);
    }

    public boolean displayTableEducation(){
        return elementUtils.isElementDisplayed(tableEducation);
    }

    public EditEducationPage clickEditEducation(){
        String educationLevel = "Master's Degree";
        elementUtils.clickEditElement(rowsEducation, educationLevel);
        return new EditEducationPage(driver);
    }

    public DeletePopup clickDeleteEducation(){
        String educationLevel = "Master's Degree";
        elementUtils.clickDeleteElement(rowsEducation, educationLevel);
        return new DeletePopup(driver);
    }

    public boolean displayTitleSkills(){
        return elementUtils.isElementDisplayed(titleSkills);
    }

    public String getTextTitleSkills(){
        return elementUtils.getTextOfElement(titleSkills);
    }

    public boolean displayBtnAddSkills(){
        return elementUtils.isElementDisplayed(btnAddSkills);
    }

    public AddSkillQualificationsPage clickBtnAddSkills(){
        elementUtils.clickOnElement(btnAddSkills);
        return new AddSkillQualificationsPage(driver);
    }

    public boolean displayTableSkills(){
        return elementUtils.isElementDisplayed(tableSkills);
    }

    public boolean displayTitleLanguages(){
        return elementUtils.isElementDisplayed(titleLanguages);
    }

    public String getTextTitleLanguages(){
        return elementUtils.getTextOfElement(titleLanguages);
    }

    public boolean displayBtnAddLanguages(){
        return elementUtils.isElementDisplayed(btnAddLanguages);
    }

    public AddLanguageQualificationsPage clickBtnAddLanguages(){
        elementUtils.clickOnElement(btnAddLanguages);
        return new AddLanguageQualificationsPage(driver);
    }

    public boolean displayTableLanguage(){
        return elementUtils.isElementDisplayed(tableLanguage);
    }

    public EditLanguagePage clickEditLanguage(){
        String textLanguage = "English";
        elementUtils.clickEditElement(rowsLanguages, textLanguage);
        return new EditLanguagePage(driver);
    }

    public boolean displayTitleLicense(){
        return elementUtils.isElementDisplayed(titleLicense);
    }

    public String getTextTitleLicense(){
        return elementUtils.getTextOfElement(titleLicense);
    }

    public boolean displayBtnAddLicense(){
        return elementUtils.isElementDisplayed(btnAddLicense);
    }

    public AddLicenseQualificationsPage clickBtnAddLicense(){
        elementUtils.clickOnElement(btnAddLicense);
        return new AddLicenseQualificationsPage(driver);
    }

    public boolean displayTableLicense(){
        return elementUtils.isElementDisplayed(tableLicense);
    }

    public boolean displayTitleAttachments(){
        return elementUtils.isElementDisplayed(titleAttachments);
    }

    public String getTextTitleAttachments(){
        return elementUtils.getTextOfElement(titleAttachments);
    }

    public boolean displayBtnAddAttachment(){
        return elementUtils.isElementDisplayed(btnAddAttachment);
    }

    public AddAttachmentPage clickBtnAddAttachment(){
        elementUtils.clickOnElement(btnAddAttachment);
        return new AddAttachmentPage(driver);
    }

    public boolean displayTableAttachments(){
        return elementUtils.isElementDisplayed(tableAttachments);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
