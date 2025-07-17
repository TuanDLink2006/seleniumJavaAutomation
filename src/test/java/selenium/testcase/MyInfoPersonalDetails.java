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

public class MyInfoPersonalDetails extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    MyInfoPage myInfoPage;
    PersonalDetailsPage personalDetailsPage;
    AddAttachmentPage addAttachmentPage;
    EditAttachmentPage editAttachmentPage;
    DeletePopup deletePopup;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        myInfoPage = new MyInfoPage(driver);
        personalDetailsPage = new PersonalDetailsPage(driver);
        addAttachmentPage = new AddAttachmentPage(driver);
        editAttachmentPage = new EditAttachmentPage(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        myInfoPage = leftOption.clickMyInfoOption();
        personalDetailsPage = myInfoPage.clickPersonalDetails();
    }

    /*@Test(priority = 1)
    public void checkDisplayPersonalDetailsPage(){
        String textPersonalDetail = "Personal Details";
        String textCustomFields = "Custom Fields";
        String textAttachments = "Attachments";

        Assert.assertTrue(personalDetailsPage.displayTitlePersonalDetail());
        Assert.assertEquals(personalDetailsPage.getTextTitlePersonalDetail(), textPersonalDetail);
        Assert.assertTrue(personalDetailsPage.displayTitleCustomFields());
        Assert.assertEquals(personalDetailsPage.getTextTitleCustomFields(), textCustomFields);
        Assert.assertTrue(personalDetailsPage.displayTitleAttachments());
        Assert.assertEquals(personalDetailsPage.getTextTitleAttachments(), textAttachments);
        Assert.assertTrue(personalDetailsPage.displayBtnAddAttachment());
    }

    @Test(priority = 2)
    public void checkValidatePersonalDetails(){
        String messageRequired = "Required";
        personalDetailsPage.clearInputFirstName();
        personalDetailsPage.clearInputLastName();
        Assert.assertEquals(personalDetailsPage.getTextMessageRequiredFirstName(), messageRequired);
        Assert.assertEquals(personalDetailsPage.getTextMessageRequiredLastName(), messageRequired);
    }

    @Test(priority = 3)
    public void checkValidateExceed30CharactersPersonalDetails(){
        String messageExceed30Characters = "Should not exceed 30 characters";
        personalDetailsPage.enterInputFirstName(prop.getProperty("exceedCharacter"));
        personalDetailsPage.enterInputMiddleName(prop.getProperty("exceedCharacter"));
        personalDetailsPage.enterInputLastName(prop.getProperty("exceedCharacter"));
        personalDetailsPage.enterInputOtherId(prop.getProperty("exceedCharacter"));
        personalDetailsPage.enterInputDriveLicense(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(personalDetailsPage.getTextMessageExceed30CharactersFirstName(), messageExceed30Characters);
        Assert.assertEquals(personalDetailsPage.getTextMessageExceed30CharactersMiddleName(), messageExceed30Characters);
        Assert.assertEquals(personalDetailsPage.getTextMessageExceed30CharactersLastName(), messageExceed30Characters);
        Assert.assertEquals(personalDetailsPage.getTextMessageExceed30CharactersOtherId(), messageExceed30Characters);
        Assert.assertEquals(personalDetailsPage.getTextMessageExceed30CharactersDriverLicenseNumber(), messageExceed30Characters);
    }

    @Test(priority = 4)
    public void checkValidateExceed10CharactersPersonalDetails(){
        String messageExceed10Characters = "Should not exceed 10 characters";
        personalDetailsPage.enterInputEmployeeId(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(personalDetailsPage.getTextMessageExceed10Characters(), messageExceed10Characters);
    }

    @Test(priority = 5)
    public void checkValidateFormatPersonalDetails(){
        String messageValidateFormat = "Should be a valid date in yyyy-dd-mm format";
        personalDetailsPage.enterLicenseExpiryDate(prop.getProperty("exceedCharacter"));
        personalDetailsPage.enterDateOfBirth(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(personalDetailsPage.getTextMessageShouldBeFormatLicenseExpiryDate(), messageValidateFormat);
        Assert.assertEquals(personalDetailsPage.getTextMessageShouldBeFormatDateOfBirth(), messageValidateFormat);
    }

     */

    @Test(priority = 6)
    public void updatePersonalDetailSuccess(){

        personalDetailsPage.enterInputFirstName(prop.getProperty("firstName"));

        personalDetailsPage.enterInputMiddleName(prop.getProperty("middleName"));

        personalDetailsPage.enterInputLastName(prop.getProperty("lastName"));

        personalDetailsPage.enterInputEmployeeId(prop.getProperty("employeeId"));

        personalDetailsPage.enterInputOtherId(prop.getProperty("otherId"));

        personalDetailsPage.enterInputDriveLicense(prop.getProperty("driveLicense"));

        personalDetailsPage.selectLicenseExpiryDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));

        personalDetailsPage.selectNationalityOptions(prop.getProperty("nationalityVietnamese"));

        personalDetailsPage.selectMaritalStatusOptions(prop.getProperty("maritalMarried"));

        personalDetailsPage.selectDateOfBirth(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));

        personalDetailsPage.clickRadioButtonMale();

        personalDetailsPage.clickBtnSavePersonalDetails();
        Assert.assertTrue(personalDetailsPage.getTextMessageSuccess().contains("Successfully Updated") );
    }

    @Test(priority = 7)
    public void checkValidateExceed250CharactersCustomFields(){
        String messageExceed250Characters = "Should not exceed 250 characters";
        personalDetailsPage.enterInputTestField(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(personalDetailsPage.getTextMessageExceed250Characters(), messageExceed250Characters);
    }

    @Test(priority = 8)
    public void updatedCustomFields(){
        personalDetailsPage.selectBloodTypeOption(prop.getProperty("bloodType"));
        personalDetailsPage.enterInputTestField(prop.getProperty("testField"));
        personalDetailsPage.clickBtnSaveCustomFields();
        Assert.assertTrue(personalDetailsPage.getTextMessageSuccess().contains("Successfully Saved") );
    }

    @Test(priority = 9)
    public void checkRequiredAddAttachment() {
        String titleAddAttachment = "Add Attachment";
        String accept = "Accepts up to 1MB";
        String messageRequired = "Required";

        personalDetailsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        Assert.assertEquals(addAttachmentPage.getTextAcceptTo(), accept);

        addAttachmentPage.clickBtnSaveAddAttachment();
        Assert.assertEquals(addAttachmentPage.getTextMessageRequired(), messageRequired);
    }

    @Test(priority = 10)
    public void checkExceed200CharactersAddAttachment() {
        String messageExceed200Characters = "Should not exceed 200 characters";

        addAttachmentPage = personalDetailsPage.clickBtnAddAttachment();
        addAttachmentPage.enterCommentAddAttachment(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addAttachmentPage.getTextMessageExceed200Characters(), messageExceed200Characters);
    }

    @Test(priority = 11)
    public void checkAttachmentSizeExceededAddAttachment() {
        String titleAddAttachment = "Add Attachment";
        String accept = "Accepts up to 1MB";
        String messageAttachmentSizeExceeded = "Attachment Size Exceeded";

        personalDetailsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        Assert.assertEquals(addAttachmentPage.getTextAcceptTo(), accept);

        addAttachmentPage.selectFile(prop.getProperty("fileAttachmentSizeExceeded"));
        Assert.assertEquals(addAttachmentPage.getTextMessageAttachmentSizeExceeded(), messageAttachmentSizeExceeded);
    }

    @Test(priority = 12)
    public void AddAttachmentWhenClickBtnCancel() {
        String titleAddAttachment = "Add Attachment";
        String accept = "Accepts up to 1MB";

        personalDetailsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        Assert.assertEquals(addAttachmentPage.getTextAcceptTo(), accept);

        addAttachmentPage.selectFile(prop.getProperty("fileUpload"));
        addAttachmentPage.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPage.clickBtnCancelAddAttachment();
    }

    @Test(priority = 13)
    public void AddAttachmentSuccess() {
        String titleAddAttachment = "Add Attachment";
        String accept = "Accepts up to 1MB";

        addAttachmentPage = personalDetailsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        Assert.assertEquals(addAttachmentPage.getTextAcceptTo(), accept);

        addAttachmentPage.selectFile(prop.getProperty("fileUpload"));
        addAttachmentPage.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPage.clickBtnSaveAddAttachment();
        Assert.assertTrue(personalDetailsPage.getTextMessageSuccess().contains("Successfully Saved"));
        Assert.assertTrue(personalDetailsPage.displayTable());
        Assert.assertTrue(personalDetailsPage.getSizeRows() > 0);
    }

    @Test(priority = 14)
    public void displayEditAttachment(){
        String textTitleEditAttachment = "Edit Attachment";
        editAttachmentPage = personalDetailsPage.clickEditAttachment();
        Assert.assertEquals(editAttachmentPage.getTextTitleEditAttachment(), textTitleEditAttachment);
        Assert.assertTrue(editAttachmentPage.displayTitleEditAttachment());
        Assert.assertTrue(editAttachmentPage.displayFileCurrent());
        Assert.assertTrue(editAttachmentPage.displayBtnCancel());
        Assert.assertTrue(editAttachmentPage.displayBtnSave());
    }

    @Test(priority = 15)
    public void editAttachmentWhenClickBtnCancel(){
        editAttachmentPage = personalDetailsPage.clickEditAttachment();
        editAttachmentPage.clickBtnCancel();
    }

    @Test(priority = 16)
    public void editSuccessAttachment(){
        editAttachmentPage = personalDetailsPage.clickEditAttachment();
        editAttachmentPage.uploadFile(prop.getProperty("fileDataRecord"));
        editAttachmentPage.clickBtnSave();
        Assert.assertTrue(personalDetailsPage.getTextMessageSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 17)
    public void displayDeleteAttachment(){
        String titleDelete = "Are you Sure?";
        String contentDelete = "The selected record will be permanently deleted. Are you sure you want to continue?";

        deletePopup = personalDetailsPage.clickDeleteAttachment();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), titleDelete);
        Assert.assertEquals(deletePopup.getTextContentDelete(), contentDelete);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 18)
    public void deleteAttachmentWhenClickBtnNoCancel(){
        deletePopup= personalDetailsPage.clickDeleteAttachment();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 19)
    public void deleteSuccessAttachment(){
        deletePopup= personalDetailsPage.clickDeleteAttachment();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(personalDetailsPage.getTextMessageSuccess().contains("Successfully Deleted"));
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
