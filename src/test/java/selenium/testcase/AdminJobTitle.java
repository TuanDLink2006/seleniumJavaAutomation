package selenium.testcase;

import listeners.MyListeners;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import selenium.base.Base;
import utils.CommonUtils;

import java.util.Properties;

@Listeners(MyListeners.class)
public class AdminJobTitle extends Base {

    public WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    AdminPage adminPage;
    JobTitleAdminPage jobTitleAdminPage;
    AddJobTitlePage addJobTitlePage;
    EditJobTitlePage editJobTitlePage;
    DeletePopup deletePopup;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        adminPage = new AdminPage(driver);
        addJobTitlePage = new AddJobTitlePage(driver);
        jobTitleAdminPage = new JobTitleAdminPage(driver);
        editJobTitlePage = new EditJobTitlePage(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        leftOption.clickAdminOption();
        adminPage.clickDropdownJob();
        jobTitleAdminPage = adminPage.clickJobTitleOption();
    }

    @Test(priority = 1)
    public void checkDisplayAdminJobTitle(){
        String titleJobTitles = "Job Titles";

        Assert.assertEquals(jobTitleAdminPage.getTextJobTitles(), titleJobTitles);
        Assert.assertTrue(jobTitleAdminPage.displayJobTitles());
        Assert.assertTrue(jobTitleAdminPage.displayBtnAdd());
        Assert.assertTrue(jobTitleAdminPage.displayTable());
        Assert.assertTrue(jobTitleAdminPage.getSizeRowTable() > 0, "There should be at least one job title row");
    }

    @Test(priority = 2)
    public void displayAddJobTitle(){
        String titleAddJobTitle = "Add Job Title";
        addJobTitlePage = jobTitleAdminPage.clickBtnAdd();
        Assert.assertEquals(addJobTitlePage.getTextTitleAddJobTitle(), titleAddJobTitle);
        Assert.assertTrue(addJobTitlePage.displayAddJobTitle());
        Assert.assertTrue(addJobTitlePage.displayBtnCancel());
        Assert.assertTrue(addJobTitlePage.displayBtnSave());
    }

    @Test(priority = 3)
    public void checkValidateAddJobTitle(){
        String messageRequired = "Required";
        addJobTitlePage = jobTitleAdminPage.clickBtnAdd();
        addJobTitlePage.clickBtnSave();
        Assert.assertEquals(addJobTitlePage.getMessageRequiredJobTitle(), messageRequired);
    }

    @Test(priority = 4)
    public void checkValidateExceed100Characters(){
        String messageExceedCharacters = "Should not exceed 100 characters";
        addJobTitlePage = jobTitleAdminPage.clickBtnAdd();
        addJobTitlePage.enterInputJobTitle(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addJobTitlePage.getMessageExceedCharactersJobTitle(), messageExceedCharacters);
    }

    @Test(priority = 5)
    public void checkValidateExceed400Characters(){
        String messageExceedCharacters = "Should not exceed 400 characters";
        addJobTitlePage = jobTitleAdminPage.clickBtnAdd();
        addJobTitlePage.enterJobDescription(prop.getProperty("exceedCharacter"));
        addJobTitlePage.enterAddNote(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addJobTitlePage.getMessageExceedCharactersJobDescription(), messageExceedCharacters);
        Assert.assertEquals(addJobTitlePage.getMessageExceedCharactersNote(), messageExceedCharacters);
    }

    @Test(priority = 6)
    public void checkFileTypeNotAllowedAddJobTitle(){
        String messageFileType = "File type not allowed";
        addJobTitlePage = jobTitleAdminPage.clickBtnAdd();
        addJobTitlePage.uploadFileJobSpecification(prop.getProperty("fileAttachmentSizeExceeded"));
        Assert.assertEquals(addJobTitlePage.getMessageFileType(), messageFileType);
    }

    @Test(priority = 7)
    public void addJobTitleWithRequiredFieldsWhenClickOnBtnCancel(){
        addJobTitlePage = jobTitleAdminPage.clickBtnAdd();
        addJobTitlePage.enterInputJobTitle(prop.getProperty("jobTitle"));
        addJobTitlePage.enterJobDescription(prop.getProperty("textComments"));
        addJobTitlePage.uploadFileJobSpecification(prop.getProperty("fileUpload"));
        jobTitleAdminPage = addJobTitlePage.clickBtnCancel();
    }

    @Test(priority = 8)
    public void addSuccessJobTitleWithRequiredFields(){
        addJobTitlePage = jobTitleAdminPage.clickBtnAdd();
        addJobTitlePage.enterInputJobTitle(prop.getProperty("jobTitle"));
        addJobTitlePage.enterJobDescription(prop.getProperty("textComments"));
        addJobTitlePage.uploadFileJobSpecification(prop.getProperty("fileUpload"));
        addJobTitlePage.clickBtnSave();
        Assert.assertTrue(addJobTitlePage.getTextAddTitleSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 9)
    public void addJobTitleAlreadyExists(){
        addJobTitlePage = jobTitleAdminPage.clickBtnAdd();
        addJobTitlePage.enterInputJobTitle(prop.getProperty("jobTitle"));
        addJobTitlePage.clickBtnSave();
        String messageExist = "Already exists";
        Assert.assertEquals(addJobTitlePage.getMessageExistJobTitle(), messageExist);
    }

    @Test(priority = 10)
    public void displayEditJobTitle(){
        String textTitle = "Edit Job Title";
        editJobTitlePage = jobTitleAdminPage.clickEditJobTitle();
        Assert.assertEquals(editJobTitlePage.getTextTitleEditJobTitle(), textTitle);
        Assert.assertTrue(editJobTitlePage.displayTitleEditJobTitle());
        Assert.assertTrue(editJobTitlePage.displayBtnCancel());
        Assert.assertTrue(editJobTitlePage.displayBtnSave());
    }

    @Test(priority = 11)
    public void editJobTitleWhenClickBtnCancel(){
        editJobTitlePage = jobTitleAdminPage.clickEditJobTitle();
        jobTitleAdminPage = editJobTitlePage.clickBtnCancel();
    }

    @Test(priority = 12)
    public void editJobTitleWhenClickKeepCurrent(){
        String textTitle = "Edit Job Title";
        editJobTitlePage = jobTitleAdminPage.clickEditJobTitle();
        Assert.assertEquals(editJobTitlePage.getTextTitleEditJobTitle(), textTitle);
        Assert.assertTrue(editJobTitlePage.displayFilePreview());
        editJobTitlePage.clickOnRadioButtonKeepCurrent();
        jobTitleAdminPage = editJobTitlePage.clickBtnSave();
        Assert.assertTrue(editJobTitlePage.getTextEditSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 13)
    public void editJobTitleWhenClickReplaceCurrent(){
        String textTitle = "Edit Job Title";
        editJobTitlePage = jobTitleAdminPage.clickEditJobTitle();
        Assert.assertEquals(editJobTitlePage.getTextTitleEditJobTitle(), textTitle);
        Assert.assertTrue(editJobTitlePage.displayFilePreview());
        editJobTitlePage.clickOnRadioReplaceCurrent();
        editJobTitlePage.uploadLoadJobSpecification(prop.getProperty("fileDataRecord"));
        jobTitleAdminPage = editJobTitlePage.clickBtnSave();
        Assert.assertTrue(editJobTitlePage.getTextEditSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 14)
    public void editJobTitleWhenClickDeleteCurrent(){
        String textTitle = "Edit Job Title";
        editJobTitlePage = jobTitleAdminPage.clickEditJobTitle();
        Assert.assertEquals(editJobTitlePage.getTextTitleEditJobTitle(), textTitle);
        Assert.assertTrue(editJobTitlePage.displayFilePreview());
        editJobTitlePage.clickOnRadioButtonDeleteCurrent();
        jobTitleAdminPage = editJobTitlePage.clickBtnSave();
        Assert.assertTrue(editJobTitlePage.getTextEditSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 15)
    public void editSuccessJobTitle(){
        editJobTitlePage = jobTitleAdminPage.clickEditJobTitle();
        editJobTitlePage.enterJobTitle(prop.getProperty("jobTitle2"));
        editJobTitlePage.uploadLoadJobSpecification(prop.getProperty("fileDataRecord"));
        jobTitleAdminPage = editJobTitlePage.clickBtnSave();
        Assert.assertTrue(editJobTitlePage.getTextEditSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 16)
    public void deleteJobTitleCheckDisplayDocument(){
        String titleAreYouSure = "Are you Sure?";
        String content = "The selected record will be permanently deleted. Are you sure you want to continue?";
        deletePopup = jobTitleAdminPage.clickDeleteJobTitle();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), titleAreYouSure);
        Assert.assertEquals(deletePopup.getTextContentDelete(), content);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 17)
    public void deleteJobTitleWhenClickOnNoCancel(){
        deletePopup = jobTitleAdminPage.clickDeleteJobTitle();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 18)
    public void deleteSuccessJobTitle(){
        deletePopup = jobTitleAdminPage.clickDeleteJobTitle();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(jobTitleAdminPage.getTextMessageSuccess().contains("Successfully Deleted"));
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }

}
