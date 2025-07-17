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

public class MyInfoContactDetails extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    MyInfoPage myInfoPage;
    ContactDetailPage contactDetailPage;
    AddAttachmentPage addAttachmentPage;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        myInfoPage = new MyInfoPage(driver);
        addAttachmentPage = new AddAttachmentPage(driver);
        contactDetailPage = new ContactDetailPage(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        myInfoPage = leftOption.clickMyInfoOption();
        contactDetailPage = myInfoPage.clickContactDetailPage();
    }

    /*@Test(priority = 1)
    public void checkDisplayContactDetailsPage(){
        String textContactDetails = "Contact Details";
        String textAddress = "Address";
        String textTelephone = "Telephone";
        String textEmail = "Email";
        String textAddAttachment = "Attachments";

        Assert.assertTrue(contactDetailPage.displayTitleContactDetails());
        Assert.assertEquals(contactDetailPage.getTextTitleContactDetails(), textContactDetails);

        Assert.assertTrue(contactDetailPage.displayTitleAddress());
        Assert.assertEquals(contactDetailPage.getTextTitleAddress(), textAddress);

        Assert.assertTrue(contactDetailPage.displayTitleTelephone());
        Assert.assertEquals(contactDetailPage.getTextTitleTelephone(), textTelephone);

        Assert.assertTrue(contactDetailPage.displayTitleEmail());
        Assert.assertEquals(contactDetailPage.getTextTitleEmail(), textEmail);

        Assert.assertTrue(contactDetailPage.displayTitleAttachment());
        Assert.assertEquals(contactDetailPage.getTextTitleAttachment(), textAddAttachment);
    }
*/
    @Test(priority = 2)
    public void checkMessageRequiredContactDetail(){

        String messageAllow = "Allows numbers and only + - / ( )";
        String messageFormatEmail = "Expected format: admin@example.com";

        contactDetailPage.enterInputHome(prop.getProperty("allowNumber"));

        contactDetailPage.enterInputMobile(prop.getProperty("allowNumber"));

        contactDetailPage.enterInputWork(prop.getProperty("allowNumber"));

        contactDetailPage.enterInputWorkEmail(prop.getProperty("formatEmail"));

        contactDetailPage.enterInputOtherEmail(prop.getProperty("formatEmail"));

        Assert.assertEquals(contactDetailPage.getTextMessageAllowNumberHome(), messageAllow);
        Assert.assertEquals(contactDetailPage.getTextMessageAllowNumberMobile(), messageAllow);
        Assert.assertEquals(contactDetailPage.getTextMessageAllowNumberWork(), messageAllow);
        Assert.assertEquals(contactDetailPage.getTextMessageExpectedFormatWorkEmail(), messageFormatEmail);
        Assert.assertEquals(contactDetailPage.getTextMessageExpectedFormatOtherEmail(), messageFormatEmail);
    }

    @Test(priority = 3)
    public void checkEmailSame(){
        String messageEmailSame = "Work Email and Other Email cannot be the same";

        contactDetailPage.enterInputWorkEmail(prop.getProperty("email"));

        contactDetailPage.enterInputOtherEmail(prop.getProperty("email"));

        Assert.assertEquals(contactDetailPage.getTextMessageEmailSame(), messageEmailSame);
    }

    @Test(priority = 4)
    public void checkExistEmail(){
        String messageExistEmail = "Already exists";

        contactDetailPage.enterInputWorkEmail(prop.getProperty("existEmail"));

        contactDetailPage.enterInputOtherEmail(prop.getProperty("existEmail"));

        Assert.assertEquals(contactDetailPage.getTextMessageEmailExist(), messageExistEmail);
    }

    @Test(priority = 5)
    public void checkMessageExceed70Characters(){
        String messageExceedCharacters = "Should not exceed 70 characters";

        contactDetailPage.enterInputStreet1(prop.getProperty("exceedCharacter"));
        contactDetailPage.enterInputStreet2(prop.getProperty("exceedCharacter"));
        contactDetailPage.enterInputCity(prop.getProperty("exceedCharacter"));
        contactDetailPage.enterInputState(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(contactDetailPage.getTextMessageExceed70CharactersInputStreet1(), messageExceedCharacters);
        Assert.assertEquals(contactDetailPage.getTextMessageExceed70CharactersInputStreet2(), messageExceedCharacters);
        Assert.assertEquals(contactDetailPage.getTextMessageExceed70CharactersInputCity(), messageExceedCharacters);
        Assert.assertEquals(contactDetailPage.getTextMessageExceed70CharactersInputState(), messageExceedCharacters);
    }

    @Test(priority = 6)
    public void checkMessageExceed10Characters(){
        String messageExceedCharacters = "Should not exceed 10 characters";

        contactDetailPage.enterInputZip(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(contactDetailPage.getTextMessageExceed10Characters(), messageExceedCharacters);
    }

    @Test(priority = 7)
    public void checkMessageExceed25Characters(){
        String messageExceedCharacters = "Should not exceed 25 characters";

        contactDetailPage.enterInputHome(prop.getProperty("exceedCharacter"));
        contactDetailPage.enterInputMobile(prop.getProperty("exceedCharacter"));
        contactDetailPage.enterInputWork(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(contactDetailPage.getTextMessageExceed25CharactersHome(), messageExceedCharacters);
        Assert.assertEquals(contactDetailPage.getTextMessageExceed25CharactersMobile(), messageExceedCharacters);
        Assert.assertEquals(contactDetailPage.getTextMessageExceed25CharactersWork(), messageExceedCharacters);
    }

    @Test(priority = 8)
    public void checkMessageExceed50Characters(){
        String messageExceedCharacters = "Should not exceed 50 characters";

        contactDetailPage.enterInputWorkEmail(prop.getProperty("exceedCharacter"));
        contactDetailPage.enterInputOtherEmail(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(contactDetailPage.getTextMessageExceed50CharactersWorkEmail(), messageExceedCharacters);
        Assert.assertEquals(contactDetailPage.getTextMessageExceed50CharactersOtherEmail(), messageExceedCharacters);
    }

    @Test(priority = 9)
    public void updateContactDetailsSuccess(){
        contactDetailPage.clearInputStreet1();
        contactDetailPage.enterInputStreet1(prop.getProperty("streetOne"));

        contactDetailPage.clearInputStreet2();
        contactDetailPage.enterInputStreet2(prop.getProperty("streetTwo"));

        contactDetailPage.clearInputCity();
        contactDetailPage.enterInputCity(prop.getProperty("city"));

        contactDetailPage.clearInputState();
        contactDetailPage.enterInputState(prop.getProperty("state"));

        contactDetailPage.clearInputZip();
        contactDetailPage.enterInputZip(prop.getProperty("zip"));

        contactDetailPage.selectCountryOption(prop.getProperty("country"));

        contactDetailPage.clearInputHome();
        contactDetailPage.enterInputHome(prop.getProperty("home"));

        contactDetailPage.clearInputMobile();
        contactDetailPage.enterInputMobile(prop.getProperty("mobile"));

        contactDetailPage.clearInputWork();
        contactDetailPage.enterInputWork(prop.getProperty("work"));

        contactDetailPage.enterInputWorkEmail("123@gmail.com");
        contactDetailPage.enterInputOtherEmail("345@gmail.com");
        contactDetailPage.clickBtnSaveContactDetail();
        Assert.assertTrue(contactDetailPage.getTextMessageSuccess().contains("Successfully Updated"));
    }


    @Test(priority = 10)
    public void checkDisplayAddAttachment(){
        String titleAddAttachment = "Add Attachment";
        String textAccept = "Accepts up to 1MB";

        contactDetailPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        Assert.assertEquals(addAttachmentPage.getTextAcceptTo(), textAccept);
        Assert.assertTrue(addAttachmentPage.displayBtnCancelAttachment());
        Assert.assertTrue(addAttachmentPage.displayBtnSaveAttachment());
    }

    @Test(priority = 11)
    public void updateAddAttachmentSizeExceededFile(){
        String messageAttachmentSizeExceeded = "Attachment Size Exceeded";

        contactDetailPage.clickBtnAddAttachment();
        addAttachmentPage.selectFile(prop.getProperty("fileAttachmentSizeExceeded"));
        Assert.assertEquals(addAttachmentPage.getTextMessageAttachmentSizeExceeded(), messageAttachmentSizeExceeded);
    }

    @Test(priority = 12)
    public void updateAddAttachmentWhenClickBtnCancel(){
        contactDetailPage.clickBtnAddAttachment();
        addAttachmentPage.selectFile(prop.getProperty("fileUpload"));
        addAttachmentPage.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPage.clickBtnCancelAddAttachment();
    }

    @Test(priority = 13)
    public void updateAddAttachmentSuccess(){

        contactDetailPage.clickBtnAddAttachment();
        addAttachmentPage.selectFile(prop.getProperty("fileUpload"));
        addAttachmentPage.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPage.clickBtnSaveAddAttachment();
        Assert.assertTrue(addAttachmentPage.getTextAttachmentSuccess().contains("Successfully Updated"));
        Assert.assertFalse(contactDetailPage.displayTable());
        Assert.assertTrue(contactDetailPage.getSizeRows() > 0);
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
