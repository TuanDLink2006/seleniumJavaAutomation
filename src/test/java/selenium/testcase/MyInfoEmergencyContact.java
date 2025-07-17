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

import javax.swing.text.html.HTMLDocument;
import java.util.Properties;
@Listeners(MyListeners.class)
public class MyInfoEmergencyContact extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    MyInfoPage myInfoPage;
    EmergencyContactsPage emergencyContactsPage;
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
        emergencyContactsPage = new EmergencyContactsPage(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        myInfoPage = leftOption.clickMyInfoOption();
        emergencyContactsPage = myInfoPage.clickEmergencyContact();

    }

    @Test(priority = 1)
    public void checkDisplayEmergencyContactPage(){
        String textAssignedEmergencyContacts = "Assigned Emergency Contacts";
        String textAttachments = "Attachments";

        Assert.assertTrue(emergencyContactsPage.displayTitleAssignedEmergencyContacts());
        Assert.assertEquals(emergencyContactsPage.getTextTitleAssignedEmergencyContacts(), textAssignedEmergencyContacts);
        Assert.assertTrue(emergencyContactsPage.displayBtnAddAssignedEmergencyContacts());

        Assert.assertTrue(emergencyContactsPage.displayTitleAttachments());
        Assert.assertEquals(emergencyContactsPage.getTextTitleAttachments(), textAttachments);
        Assert.assertTrue(emergencyContactsPage.displayBtnAddAttachments());
    }

    @Test(priority = 2)
    public void checkDisplayAddEmergencyContact(){
        String textSaveEmergencyContact = "Save Emergency Contact";

        emergencyContactsPage.clickBtnAddAssignedEmergencyContacts();
        Assert.assertTrue(emergencyContactsPage.displayTitleSaveEmergencyContact());
        Assert.assertEquals(emergencyContactsPage.getTextTitleSaveEmergencyContact(), textSaveEmergencyContact);
        Assert.assertTrue(emergencyContactsPage.displayBtnCancelSaveEmergencyContact());
        Assert.assertTrue(emergencyContactsPage.displayBtnSaveEmergencyContact());
    }

    @Test(priority = 3)
    public void checkRequiredSaveEmergencyContact(){
        String messageRequired = "Required";
        String messageNumberRequired = "At least one phone number is required";
        emergencyContactsPage.clickBtnAddAssignedEmergencyContacts();
        emergencyContactsPage.clickBtnSaveEmergencyContact();
        Assert.assertEquals(emergencyContactsPage.getTextMessageRequiredName(), messageRequired);
        Assert.assertEquals(emergencyContactsPage.getTextMessageRequiredRelationship(), messageRequired);
        Assert.assertEquals(emergencyContactsPage.getTextMessageNumberRequiredHomeTelephone(), messageNumberRequired);
    }

    @Test(priority = 4)
    public void checkAllowNumberSaveEmergencyContact(){
        String messageAllowNumber = "Allows numbers and only + - / ( )";
        emergencyContactsPage.clickBtnAddAssignedEmergencyContacts();

        emergencyContactsPage.enterInputHomeTelephone(prop.getProperty("allowNumber"));
        emergencyContactsPage.enterInputMobile(prop.getProperty("allowNumber"));
        emergencyContactsPage.enterWorkTelephone(prop.getProperty("allowNumber"));

        Assert.assertEquals(emergencyContactsPage.getTextMessageAllowNumberHomeTelephone(), messageAllowNumber);
        Assert.assertEquals(emergencyContactsPage.getTextMessageAllowNumberMobile(), messageAllowNumber);
        Assert.assertEquals(emergencyContactsPage.getTextMessageAllowNumberWorkTelephone(), messageAllowNumber);
    }

    @Test(priority = 5)
    public void checkExceed100CharactersSaveEmergencyContact(){
        String messageExceed100Characters = "Should not exceed 100 characters";
        emergencyContactsPage.clickBtnAddAssignedEmergencyContacts();

        emergencyContactsPage.enterInputName(prop.getProperty("exceedCharacter"));
        emergencyContactsPage.enterInputRelationship(prop.getProperty("exceedCharacter"));

        Assert.assertEquals(emergencyContactsPage.getTextMessageShould100CharactersName(), messageExceed100Characters);
        Assert.assertEquals(emergencyContactsPage.getTextMessageShould100CharactersRelationship(), messageExceed100Characters);
    }

    @Test(priority = 6)
    public void checkExceed30CharactersSaveEmergencyContact(){
        String messageExceed30Characters = "Should not exceed 30 characters";
        emergencyContactsPage.clickBtnAddAssignedEmergencyContacts();

        emergencyContactsPage.enterInputHomeTelephone(prop.getProperty("exceedNumberCharacter"));
        emergencyContactsPage.enterInputMobile(prop.getProperty("exceedNumberCharacter"));
        emergencyContactsPage.enterWorkTelephone(prop.getProperty("exceedNumberCharacter"));

        Assert.assertEquals(emergencyContactsPage.getTextMessageShould30CharactersHomeTelephone(), messageExceed30Characters);
        Assert.assertEquals(emergencyContactsPage.getTextMessageShould30CharactersMobile(), messageExceed30Characters);
        Assert.assertEquals(emergencyContactsPage.getTextMessageShould30CharactersWorkTelephone(), messageExceed30Characters);
    }

    @Test(priority = 7)
    public void addSaveEmergencyContactWhenClickBtnCancel(){
        emergencyContactsPage.clickBtnAddAssignedEmergencyContacts();

        emergencyContactsPage.enterInputName(prop.getProperty("name"));
        emergencyContactsPage.enterInputRelationship(prop.getProperty("relationshipChild"));
        emergencyContactsPage.enterInputHomeTelephone(prop.getProperty("mobile"));
        emergencyContactsPage.enterInputMobile(prop.getProperty("mobile"));
        emergencyContactsPage.enterWorkTelephone(prop.getProperty("mobile"));
        emergencyContactsPage.clickBtnCancelSaveEmergencyContact();
    }

    @Test(priority = 8)
    public void addSaveEmergencyContactSuccess(){
        emergencyContactsPage.clickBtnAddAssignedEmergencyContacts();

        emergencyContactsPage.enterInputName(prop.getProperty("name"));
        emergencyContactsPage.enterInputRelationship(prop.getProperty("relationshipChild"));
        emergencyContactsPage.enterInputHomeTelephone(prop.getProperty("mobile"));
        emergencyContactsPage.enterInputMobile(prop.getProperty("mobile"));
        emergencyContactsPage.enterWorkTelephone(prop.getProperty("mobile"));
        emergencyContactsPage.clickBtnSaveEmergencyContact();
        Assert.assertTrue(emergencyContactsPage.getTextMessageSuccess().contains("Successfully Saved"));
        Assert.assertTrue(emergencyContactsPage.displayTableEmergencyContact());
        Assert.assertTrue(emergencyContactsPage.getSizeRowEmergencyContacts() > 0);
    }

    @Test(priority = 9)
    public void displayEditEmergencyContact(){
        String titleEditEmergencyContact = "Edit Emergency Contact";
        emergencyContactsPage.clickEditEmergencyContacts();
        Assert.assertEquals(emergencyContactsPage.getTextTitleEditEmergencyContact(), titleEditEmergencyContact);
        Assert.assertTrue(emergencyContactsPage.displayTitleEditEmergencyContact());
        Assert.assertTrue(emergencyContactsPage.displayBtnCancelSaveEmergencyContact());
        Assert.assertTrue(emergencyContactsPage.displayBtnCancelSaveEmergencyContact());
    }

    @Test(priority = 10)
    public void editEmergencyContactWhenClickBtnCancel(){
        emergencyContactsPage.clickEditEmergencyContacts();
        emergencyContactsPage.clickBtnCancelSaveEmergencyContact();
    }

    @Test(priority = 11)
    public void editEmergencyContactSuccess(){
        emergencyContactsPage.clickEditEmergencyContacts();

        emergencyContactsPage.enterInputNameEdit(prop.getProperty("nameEdit"));

        emergencyContactsPage.enterInputRelationshipEdit(prop.getProperty("relationshipOther"));

        emergencyContactsPage.enterInputHomeTelephoneEdit(prop.getProperty("work"));

        emergencyContactsPage.enterInputMobileEdit(prop.getProperty("mobile"));

        emergencyContactsPage.enterWorkTelephoneEdit(prop.getProperty("work"));

        emergencyContactsPage.clickBtnSaveEmergencyContact();
        Assert.assertTrue(emergencyContactsPage.getTextMessageSuccess().contains("Successfully Updated"));
        Assert.assertTrue(emergencyContactsPage.displayTableEmergencyContact());
        Assert.assertTrue(emergencyContactsPage.getSizeRowEmergencyContacts() > 0);
    }

    @Test(priority = 12)
    public void displayDeleteEmergencyContact(){
        String textTitleDelete = "Are you Sure?";
        String textContentDelete = "The selected record will be permanently deleted. Are you sure you want to continue?";
        deletePopup = emergencyContactsPage.clickDeleteEmergencyContacts();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), textTitleDelete);
        Assert.assertEquals(deletePopup.getTextContentDelete(), textContentDelete);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 13)
    public void deleteEmergencyContactWhenClickBtnCancel(){
        deletePopup = emergencyContactsPage.clickDeleteEmergencyContacts();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 14)
    public void deleteSuccessEmergencyContact(){
        deletePopup= emergencyContactsPage.clickDeleteEmergencyContacts();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(emergencyContactsPage.getTextMessageSuccess().contains("Successfully Deleted"));
    }

    @Test(priority = 15)
    public void checkDisplayAddAttachment(){
        String titleAddAttachment = "Add Attachment";
        String textAccept = "Accepts up to 1MB";

        emergencyContactsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        Assert.assertEquals(addAttachmentPage.getTextAcceptTo(), textAccept);
        Assert.assertTrue(addAttachmentPage.displayBtnCancelAttachment());
        Assert.assertTrue(addAttachmentPage.displayBtnSaveAttachment());
    }

    @Test(priority = 16)
    public void updateAddAttachmentSizeExceededFile(){
        String titleAddAttachment = "Add Attachment";
        String messageAttachmentSizeExceeded = "Attachment Size Exceeded";

        emergencyContactsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        addAttachmentPage.selectFile(prop.getProperty("fileAttachmentSizeExceeded"));
        Assert.assertEquals(addAttachmentPage.getTextMessageAttachmentSizeExceeded(), messageAttachmentSizeExceeded);
    }

    @Test(priority = 17)
    public void updateAddAttachmentWhenClickBtnCancel(){
        String titleAddAttachment = "Add Attachment";

        emergencyContactsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        addAttachmentPage.selectFile(prop.getProperty("fileUpload"));
        addAttachmentPage.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPage.clickBtnCancelAddAttachment();
    }

    @Test(priority = 18)
    public void updateAddAttachmentSuccess(){
        String titleAddAttachment = "Add Attachment";

        emergencyContactsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        addAttachmentPage.selectFile(prop.getProperty("fileUpload"));
        addAttachmentPage.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPage.clickBtnSaveAddAttachment();
        Assert.assertTrue(addAttachmentPage.getTextAttachmentSuccess().contains("Successfully Saved"));
        Assert.assertTrue(emergencyContactsPage.displayTableAttachment());
        Assert.assertTrue(emergencyContactsPage.getSizeRowsAttachment() > 0);
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
