package selenium.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import selenium.base.Base;
import utils.CommonUtils;

import java.util.Properties;

public class MyInfoImmigrationRecords extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    MyInfoPage myInfoPage;
    ImmigrationRecordsPage immigrationRecordsPage;
    AddAttachmentPage addAttachmentPage;
    DeletePopup deletePopup;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        myInfoPage = new MyInfoPage(driver);
        addAttachmentPage = new AddAttachmentPage(driver);
        immigrationRecordsPage = new ImmigrationRecordsPage(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        myInfoPage = leftOption.clickMyInfoOption();
        immigrationRecordsPage= myInfoPage.clickImmigration();
    }

    @Test(priority = 1)
    public void checkDisplayImmigrationPage(){
        String textAssignedImmigration = "Assigned Immigration Records";
        String textAttachments = "Attachments";

        Assert.assertTrue(immigrationRecordsPage.displayTitleAssignedImmigrationRecords());
        Assert.assertEquals(immigrationRecordsPage.getTextTitleAssignedImmigrationRecords(), textAssignedImmigration);
        Assert.assertTrue(immigrationRecordsPage.displayBtnAddAssignedImmigrationRecords());

        Assert.assertTrue(immigrationRecordsPage.displayTitleAttachments());
        Assert.assertEquals(immigrationRecordsPage.getTextTitleAttachments(), textAttachments);
        Assert.assertTrue(immigrationRecordsPage.displayBtnAddAttachments());
    }

    @Test(priority = 2)
    public void checkDisplayAddImmigration(){
        String textAddDependent = "Add Immigration";

        immigrationRecordsPage.clickBtnAddAssignedImmigrationRecords();
        Assert.assertTrue(immigrationRecordsPage.displayTitleAddImmigration());
        Assert.assertEquals(immigrationRecordsPage.getTextTitleAddImmigration(), textAddDependent);
        Assert.assertTrue(immigrationRecordsPage.displayBtnCancelImmigration());
        Assert.assertTrue(immigrationRecordsPage.displayBtnSaveImmigration());
    }

    @Test(priority = 3)
    public void checkRequiredAddImmigration(){
        String messageRequired = "Required";
        immigrationRecordsPage.clickBtnAddAssignedImmigrationRecords();
        immigrationRecordsPage.clickBtnSaveImmigration();
        Assert.assertEquals(immigrationRecordsPage.getTextMessageRequiredNumber(), messageRequired);
    }

    @Test(priority = 4)
    public void checkFormatDateAddImmigration(){
        String messageFormatDate = "Should be a valid date in yyyy-dd-mm format";
        immigrationRecordsPage.clickBtnAddAssignedImmigrationRecords();

        immigrationRecordsPage.enterIssuedDate(prop.getProperty("dateFormat"));
        immigrationRecordsPage.enterExpiryDate(prop.getProperty("dateFormat"));
        immigrationRecordsPage.enterEligibleReviewDate(prop.getProperty("dateFormat"));
        Assert.assertEquals(immigrationRecordsPage.getTextMessageFormatDateIssuedDate(), messageFormatDate);
        Assert.assertEquals(immigrationRecordsPage.getTextMessageFormatDateExpiryDate(), messageFormatDate);
        Assert.assertEquals(immigrationRecordsPage.getTextMessageFormatDateEligibleReviewDate(), messageFormatDate);
    }

    @Test(priority = 5)
    public void checkExceed30CharactersAddImmigration(){
        String messageExceed30Characters = "Should not exceed 30 characters";
        immigrationRecordsPage.clickBtnAddAssignedImmigrationRecords();

        immigrationRecordsPage.enterInputNumber(prop.getProperty("exceedCharacter"));
        immigrationRecordsPage.enterEligibleStatus(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(immigrationRecordsPage.getTextMessageExceed30CharactersNumber(), messageExceed30Characters);
        Assert.assertEquals(immigrationRecordsPage.getTextMessageExceed30CharactersEligibleStatus(), messageExceed30Characters);
    }

    @Test(priority = 6)
    public void checkExceed250CharactersAddImmigration(){
        String messageExceed250Characters = "Should not exceed 250 characters";
        immigrationRecordsPage.clickBtnAddAssignedImmigrationRecords();

        immigrationRecordsPage.enterComments(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(immigrationRecordsPage.getTextMessageExceed250Characters(), messageExceed250Characters);
    }

    @Test(priority = 7)
    public void checkExpiryDateShouldAddImmigration(){

        String messageExpiryDateShould = "Expiry date should be after issued date";
        immigrationRecordsPage.clickBtnAddAssignedImmigrationRecords();

        immigrationRecordsPage.selectIssuedDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        immigrationRecordsPage.selectExpiryDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        Assert.assertEquals(immigrationRecordsPage.getTextMessageExpiryDateShould(), messageExpiryDateShould);
    }

    @Test(priority = 8)
    public void addImmigrationWhenClickBtnCancel(){
        immigrationRecordsPage.clickBtnAddAssignedImmigrationRecords();
        immigrationRecordsPage.clickBtnCancelImmigration();
    }

    @Test(priority = 9)
    public void addImmigrationSuccess(){
        immigrationRecordsPage.clickBtnAddAssignedImmigrationRecords();

        immigrationRecordsPage.clickRadioButtonVisa();
        immigrationRecordsPage.enterInputNumber(prop.getProperty("number"));
        immigrationRecordsPage.selectIssuedDate(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        immigrationRecordsPage.selectExpiryDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        immigrationRecordsPage.enterEligibleStatus(prop.getProperty("statusPending"));
        immigrationRecordsPage.selectIssuedByOption(prop.getProperty("country"));
        immigrationRecordsPage.selectEligibleReviewDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        immigrationRecordsPage.enterComments(prop.getProperty("textComments"));
        immigrationRecordsPage.clickBtnSaveImmigration();
        Assert.assertTrue(immigrationRecordsPage.getTextMessageSuccess().contains("Successfully Saved"));
        Assert.assertTrue(immigrationRecordsPage.displayTableImmigration());
        Assert.assertTrue(immigrationRecordsPage.getSizeRowsImmigration() > 0);
    }

    @Test(priority = 10)
    public void checkDisplayEditImmigration(){
        String titleEditImmigration = "Edit Immigration";
        immigrationRecordsPage.clickEditImmigration();
        Assert.assertEquals(immigrationRecordsPage.getTextTitleEditImmigration(), titleEditImmigration);
        Assert.assertTrue(immigrationRecordsPage.displayTitleEditImmigration());
        Assert.assertTrue(immigrationRecordsPage.displayBtnCancelImmigration());
        Assert.assertTrue(immigrationRecordsPage.displayBtnSaveImmigration());
    }

    @Test(priority = 11)
    public void editImmigrationWhenClickBtnCancel(){
        immigrationRecordsPage.clickEditImmigration();
        immigrationRecordsPage.clickBtnCancelImmigration();
    }

    @Test(priority = 12)
    public void editSuccessImmigration(){
        immigrationRecordsPage.clickEditImmigration();
        immigrationRecordsPage.clickRadioButtonPassport();
        immigrationRecordsPage.enterInputNumberEdit(prop.getProperty("numberEdit"));
        immigrationRecordsPage.selectIssuedDate(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        immigrationRecordsPage.selectExpiryDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        immigrationRecordsPage.enterEligibleStatusEdit(prop.getProperty("statusActive"));
        immigrationRecordsPage.selectIssuedByOption(prop.getProperty("country"));
        immigrationRecordsPage.selectEligibleReviewDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        immigrationRecordsPage.enterComments(prop.getProperty("textComments"));
        immigrationRecordsPage.clickBtnSaveImmigration();
        Assert.assertTrue(immigrationRecordsPage.getTextMessageSuccess().contains("Successfully Updated"));
        Assert.assertTrue(immigrationRecordsPage.displayTableImmigration());
        Assert.assertTrue(immigrationRecordsPage.getSizeRowsImmigration() > 0);
    }

    @Test(priority = 13)
    public void displayDeleteImmigration(){
        String titleDelete = "Are you Sure?";
        String contentDelete = "The selected record will be permanently deleted. Are you sure you want to continue?";
        deletePopup = immigrationRecordsPage.clickDeleteImmigration();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), titleDelete);
        Assert.assertEquals(deletePopup.getTextContentDelete(), contentDelete);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 14)
    public void deleteImmigrationWhenClickBtnNoCancel(){
        deletePopup= immigrationRecordsPage.clickDeleteImmigration();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 15)
    public void deleteSuccessImmigration(){
        deletePopup= immigrationRecordsPage.clickDeleteImmigration();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(immigrationRecordsPage.getTextMessageSuccess().contains("Successfully Deleted"));
    }

    @Test(priority = 16)
    public void checkDisplayAddAttachment(){
        String titleAddAttachment = "Add Attachment";
        String textAccept = "Accepts up to 1MB";

        immigrationRecordsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        Assert.assertEquals(addAttachmentPage.getTextAcceptTo(), textAccept);
        Assert.assertTrue(addAttachmentPage.displayBtnCancelAttachment());
        Assert.assertTrue(addAttachmentPage.displayBtnSaveAttachment());
    }

    @Test(priority = 17)
    public void updateAddAttachmentSizeExceededFile(){
        String titleAddAttachment = "Add Attachment";
        String messageAttachmentSizeExceeded = "Attachment Size Exceeded";

        immigrationRecordsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        addAttachmentPage.selectFile(prop.getProperty("fileAttachmentSizeExceeded"));
        Assert.assertEquals(addAttachmentPage.getTextMessageAttachmentSizeExceeded(), messageAttachmentSizeExceeded);
    }

    @Test(priority = 18)
    public void updateAddAttachmentWhenClickBtnCancel(){
        String titleAddAttachment = "Add Attachment";

        immigrationRecordsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        addAttachmentPage.selectFile(prop.getProperty("fileUpload"));
        addAttachmentPage.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPage.clickBtnCancelAddAttachment();
    }

    @Test(priority = 19)
    public void addAttachmentSuccess(){
        String titleAddAttachment = "Add Attachment";

        immigrationRecordsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        addAttachmentPage.selectFile(prop.getProperty("fileUpload"));
        addAttachmentPage.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPage.clickBtnSaveAddAttachment();
        Assert.assertFalse(addAttachmentPage.getTextAttachmentSuccess().contains("Successfully Updated"));
        Assert.assertTrue(immigrationRecordsPage.displayTableAttachment());
        Assert.assertTrue(immigrationRecordsPage.getSizeRowsAttachment() > 0);
    }

    @AfterMethod
    public void tearDown() {
       closeBrowser(driver);
    }
}
