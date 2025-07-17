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

public class MyInfoDependents extends Base {
    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    MyInfoPage myInfoPage;
    DependentsPage dependentsPage;
    AddAttachmentPage addAttachmentPage;
    DeletePopup deletePopup;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        myInfoPage = new MyInfoPage(driver);
        dependentsPage = new DependentsPage(driver);
        addAttachmentPage = new AddAttachmentPage(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        myInfoPage = leftOption.clickMyInfoOption();
        dependentsPage = myInfoPage.clickDependents();
    }

    @Test(priority = 1)
    public void checkDisplayDependentsPage(){
        String textAssignedDependents = "Assigned Dependents";
        String textAttachments = "Attachments";

        Assert.assertTrue(dependentsPage.displayTitleAssignedDependents());
        Assert.assertEquals(dependentsPage.getTextTitleAssignedDependents(), textAssignedDependents);
        Assert.assertTrue(dependentsPage.displayBtnAddAssignedDependents());

        Assert.assertTrue(dependentsPage.displayTitleAttachments());
        Assert.assertEquals(dependentsPage.getTextTitleAttachments(), textAttachments);
        Assert.assertTrue(dependentsPage.displayBtnAddAttachments());
    }

    @Test(priority = 2)
    public void checkDisplayAddDependent(){
        String textAddDependent = "Add Dependent";

        dependentsPage.clickBtnAddAssignedDependents();
        Assert.assertTrue(dependentsPage.displayTitleAddDependent());
        Assert.assertEquals(dependentsPage.getTextTitleAddDependent(), textAddDependent);
        Assert.assertTrue(dependentsPage.displayBtnCancelAddDependent());
        Assert.assertTrue(dependentsPage.displayBtnSaveAddDependent());
    }

    @Test(priority = 3)
    public void checkRequiredAddDependent(){
        String messageRequired = "Required";
        dependentsPage.clickBtnAddAssignedDependents();
        dependentsPage.clickBtnSaveDependent();
        Assert.assertEquals(dependentsPage.getMessageRequiredName(), messageRequired);
        Assert.assertEquals(dependentsPage.getMessageRequiredRelationship(), messageRequired);
    }

    @Test(priority = 4)
    public void checkRequiredWhenChooseRelationshipOtherOption(){
        String messageRequired = "Required";
        dependentsPage.clickBtnAddAssignedDependents();

        dependentsPage.selectRelationshipOption(prop.getProperty("relationshipOther"));
        dependentsPage.clickBtnSaveDependent();
        Assert.assertEquals(dependentsPage.getMessageRequiredName(), messageRequired);
        Assert.assertEquals(dependentsPage.getMessageRequiredPleaseSpecify(), messageRequired);
    }

    @Test(priority = 5)
    public void checkMessageExceed100Characters(){
        String messageExceed100Characters = "Should not exceed 100 characters";
        dependentsPage.clickBtnAddAssignedDependents();
        dependentsPage.enterInputName(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(dependentsPage.getMessageExceed100Characters(), messageExceed100Characters);
    }

    @Test(priority = 6)
    public void checkMessageValidDateFormat(){
        String messageValidDateFormat = "Should be a valid date in yyyy-mm-dd format";
        dependentsPage.clickBtnAddAssignedDependents();
        dependentsPage.enterDateOfBirth(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(dependentsPage.getMessageValidDateFormat(), messageValidDateFormat);
    }

    @Test(priority = 7)
    public void addDependentWhenClickBtnCancel(){
        dependentsPage.clickBtnAddAssignedDependents();

        dependentsPage.enterInputName(prop.getProperty("name"));
        dependentsPage.selectRelationshipOption(prop.getProperty("relationshipOther"));
        dependentsPage.enterInputPleaseSpecify(prop.getProperty("state"));
        dependentsPage.selectDateOfBirth(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        dependentsPage.clickBtnCancelDependent();
    }

    @Test(priority = 8)
    public void addDependentSuccess(){
        dependentsPage.clickBtnAddAssignedDependents();

        dependentsPage.enterInputName(prop.getProperty("name"));
        dependentsPage.selectRelationshipOption(prop.getProperty("relationshipChild"));
        dependentsPage.selectDateOfBirth(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        dependentsPage.clickBtnSaveDependent();
        Assert.assertTrue(dependentsPage.getTextMessageSuccess().contains("Successfully Saved"));
        Assert.assertTrue(dependentsPage.displayTableDependent());
        Assert.assertTrue(dependentsPage.getSizeRowDependents() > 0);
    }

    @Test(priority = 9)
    public void displayEditDependent(){
        String titleEditDependent = "Edit Dependent";
        dependentsPage.clickEditDependent();
        Assert.assertEquals(dependentsPage.getTextTitleEditDependent(), titleEditDependent);
        Assert.assertTrue(dependentsPage.displayTitleEditDependent());
    }

    @Test(priority = 10)
    public void editDependentWhenClickBtnCancel(){
        dependentsPage.clickEditDependent();

        dependentsPage.selectRelationshipOption(prop.getProperty("relationshipOther"));
        dependentsPage.enterInputPleaseSpecify("Please Specify");
        dependentsPage.selectDateOfBirth(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        dependentsPage.clickBtnCancelDependent();
    }

    @Test(priority = 11)
    public void editDependentSuccess(){
        dependentsPage.clickEditDependent();

        dependentsPage.selectRelationshipOption(prop.getProperty("relationshipOther"));
        dependentsPage.enterInputPleaseSpecify("Please Specify");
        dependentsPage.selectDateOfBirth(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        dependentsPage.clickBtnSaveDependent();
        Assert.assertTrue(dependentsPage.getTextMessageSuccess().contains("Successfully Updated"));
        Assert.assertTrue(dependentsPage.displayTableDependent());
        Assert.assertTrue(dependentsPage.getSizeRowDependents() > 0);
    }

    @Test(priority = 12)
    public void displayDelete(){
        String textTitle = "Are you Sure?";
        String textBody = "The selected record will be permanently deleted. Are you sure you want to continue?";
        deletePopup = dependentsPage.clickDeleteDependent();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), textTitle);
        Assert.assertEquals(deletePopup.getTextContentDelete(), textBody);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 13)
    public void deleteDependentWhenClickBtnCancel(){
        deletePopup = dependentsPage.clickDeleteDependent();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 14)
    public void deleteSuccessDependent(){
        deletePopup = dependentsPage.clickDeleteDependent();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(dependentsPage.getTextMessageSuccess().contains("Successfully Deleted"));
    }

    @Test(priority = 15)
    public void checkDisplayAddAttachment(){
        String titleAddAttachment = "Add Attachment";
        String textAccept = "Accepts up to 1MB";

        dependentsPage.clickBtnAddAttachment();
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

        dependentsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        addAttachmentPage.selectFile(prop.getProperty("fileAttachmentSizeExceeded"));
        Assert.assertEquals(addAttachmentPage.getTextMessageAttachmentSizeExceeded(), messageAttachmentSizeExceeded);
    }

    @Test(priority = 17)
    public void updateAddAttachmentWhenClickBtnCancel(){
        String titleAddAttachment = "Add Attachment";

        dependentsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        addAttachmentPage.selectFile(prop.getProperty("fileUpload"));
        addAttachmentPage.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPage.clickBtnCancelAddAttachment();
    }

    @Test(priority = 18)
    public void updateAddAttachmentSuccess(){
        String titleAddAttachment = "Add Attachment";

        dependentsPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        addAttachmentPage.selectFile(prop.getProperty("fileUpload"));
        addAttachmentPage.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPage.clickBtnSaveAddAttachment();
        Assert.assertFalse(addAttachmentPage.getTextAttachmentSuccess().contains("Successfully Updated"));
        Assert.assertTrue(dependentsPage.displayTableAttachment());
        Assert.assertTrue(dependentsPage.getSizeRowsAttachment() > 0);
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
