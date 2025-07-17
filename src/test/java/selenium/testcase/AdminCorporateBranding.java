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
public class AdminCorporateBranding extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    AdminPage adminPage;
    CorporateBrandingPage corporateBrandingPage;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        adminPage = new AdminPage(driver);
        corporateBrandingPage = new CorporateBrandingPage(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        leftOption.clickAdminOption();
        corporateBrandingPage = adminPage.clickCorporateBrandingPage();
    }

    @Test(priority = 1)
    public void checkDisplayCorporateBrandingDefault(){

        String titleCorporateBranding = "Corporate Branding";
        String textClientLogo = "Accepts jpg, .png, .gif, .svg up to 1MB. Recommended dimensions: 50px X 50px";
        String textClientBanner = "Accepts jpg, .png, .gif, .svg up to 1MB. Recommended dimensions: 182px X 50px";
        String textLoginBanner = "Accepts jpg, .png, .gif, .svg up to 1MB. Recommended dimensions: 340px X 65px";

        Assert.assertTrue(corporateBrandingPage.displayTitleCorporateBranding());
        Assert.assertEquals(corporateBrandingPage.getTextTitleCorporateBranding(), titleCorporateBranding);
        Assert.assertTrue(corporateBrandingPage.displayPrimaryColor());
        Assert.assertTrue(corporateBrandingPage.displayPrimaryFontColor());
        Assert.assertTrue(corporateBrandingPage.displayPrimaryGradientColor1());
        Assert.assertTrue(corporateBrandingPage.displaySecondaryColor());
        Assert.assertTrue(corporateBrandingPage.displaySecondaryFontColor());
        Assert.assertTrue(corporateBrandingPage.displayPrimaryGradientColor2());
        Assert.assertEquals(corporateBrandingPage.getTextDimensionsClientLogo(), textClientLogo);
        Assert.assertEquals(corporateBrandingPage.getTextDimensionsClientBanner(), textClientBanner);
        Assert.assertEquals(corporateBrandingPage.getTextDimensionsLoginBanner(), textLoginBanner);
        Assert.assertTrue(corporateBrandingPage.displayCheckboxSocialMediaImages());
        Assert.assertTrue(corporateBrandingPage.displayBtnPublish());
        Assert.assertTrue(corporateBrandingPage.displayBtnPreview());
        Assert.assertTrue(corporateBrandingPage.displayBtnResetToDefault());

    }

    @Test(priority = 2)
    public void checkPublishDefault(){
        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 3)
    public void checkRequiredColor(){
        String messageRequired = "Required";
        corporateBrandingPage.clickPrimaryColor();
        corporateBrandingPage.clearHexInput();
        Assert.assertEquals(corporateBrandingPage.getMessageRequiredPrimaryColor(), messageRequired);

        corporateBrandingPage.clickPrimaryFontColor();
        corporateBrandingPage.clearHexInput();
        Assert.assertEquals(corporateBrandingPage.getMessageRequiredPrimaryFontColor(), messageRequired);

        corporateBrandingPage.clickPrimaryGradientColor1();
        corporateBrandingPage.clearHexInput();
        Assert.assertEquals(corporateBrandingPage.getMessageRequiredPrimaryGradientColor1(), messageRequired);

        corporateBrandingPage.clickSecondaryColor();
        corporateBrandingPage.clearHexInput();
        Assert.assertEquals(corporateBrandingPage.getMessageRequiredSecondaryColor(), messageRequired);

        corporateBrandingPage.clickSecondaryFontColor();
        corporateBrandingPage.clearHexInput();
        Assert.assertEquals(corporateBrandingPage.getMessageRequiredSecondaryFontColor(), messageRequired);

        corporateBrandingPage.clickPrimaryGradientColor2();
        corporateBrandingPage.clearHexInput();
        Assert.assertEquals(corporateBrandingPage.getMessageRequiredPrimaryGradientColor2(), messageRequired);
    }

    @Test(priority = 4)
    public void checkPublishDragAndDropIndicatorColorCircle(){

        corporateBrandingPage.clickPrimaryColor();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(-20, -20);

        corporateBrandingPage.clickPrimaryFontColor();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(-20, -20);

        corporateBrandingPage.clickPrimaryGradientColor1();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(-20, -20);

        corporateBrandingPage.clickSecondaryColor();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(20, 20);

        corporateBrandingPage.clickSecondaryFontColor();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(-20, -20);

        corporateBrandingPage.clickPrimaryGradientColor2();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(-20, -20);

        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 5)
    public void checkPublishDragAndDropHex(){
        corporateBrandingPage.clickPrimaryColor();
        corporateBrandingPage.dragAndDropHEX(30);

        corporateBrandingPage.clickPrimaryFontColor();
        corporateBrandingPage.dragAndDropHEX(20);

        corporateBrandingPage.clickPrimaryGradientColor1();
        corporateBrandingPage.dragAndDropHEX(24);

        corporateBrandingPage.clickSecondaryColor();
        corporateBrandingPage.dragAndDropHEX(31);

        corporateBrandingPage.clickSecondaryFontColor();
        corporateBrandingPage.dragAndDropHEX(29);

        corporateBrandingPage.clickPrimaryGradientColor2();
        corporateBrandingPage.dragAndDropHEX(11);

        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 6)
        public void checkPublishEnterInputHex(){
        corporateBrandingPage.clickPrimaryColor();
        corporateBrandingPage.enterHexInput("#867b47");

        corporateBrandingPage.clickPrimaryFontColor();
        corporateBrandingPage.enterHexInput("#715353");

        corporateBrandingPage.clickPrimaryGradientColor1();
        corporateBrandingPage.enterHexInput("#7f6443");

        corporateBrandingPage.clickSecondaryColor();
        corporateBrandingPage.enterHexInput("#708c4f");

        corporateBrandingPage.clickSecondaryFontColor();
        corporateBrandingPage.enterHexInput("#845f5f");

        corporateBrandingPage.clickPrimaryGradientColor2();
        corporateBrandingPage.enterHexInput("#8c5f4a");

        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 7)
    public void checkPublishUploadFileIncorrectDimensions(){
        String messageIncorrectDimensions = "Incorrect Dimensions";
        corporateBrandingPage.uploadClientLogo(prop.getProperty("fileIncorrectDimension"));
        Assert.assertEquals(corporateBrandingPage.getTextIncorrectDimensionClientLogo(), messageIncorrectDimensions);

        corporateBrandingPage.uploadClientBanner(prop.getProperty("fileIncorrectDimension"));
        Assert.assertEquals(corporateBrandingPage.getTextIncorrectDimensionClientBanner(), messageIncorrectDimensions);

        corporateBrandingPage.uploadClientLoginBanner(prop.getProperty("fileIncorrectDimension"));
        Assert.assertEquals(corporateBrandingPage.getTextIncorrectDimensionLoginBanner(), messageIncorrectDimensions);
    }


    @Test(priority = 8)
    public void checkPublishUploadFileAttachmentSizeExceeded(){
        String messageAttachmentSizeExceeded = "Attachment Size Exceeded";

        corporateBrandingPage.uploadClientLogo(prop.getProperty("fileAttachmentSizeExceeded"));
        Assert.assertEquals(corporateBrandingPage.getTextAttachmentSizeExceededClientLogo(), messageAttachmentSizeExceeded);

        corporateBrandingPage.uploadClientBanner(prop.getProperty("fileAttachmentSizeExceeded"));
        Assert.assertEquals(corporateBrandingPage.getTextAttachmentSizeExceededClientBanner(), messageAttachmentSizeExceeded);

        corporateBrandingPage.uploadClientLoginBanner(prop.getProperty("fileAttachmentSizeExceeded"));
        Assert.assertEquals(corporateBrandingPage.getTextAttachmentSizeExceededLoginBanner(), messageAttachmentSizeExceeded);
    }

    @Test(priority = 9)
    public void checkPublishDragAndDropIndicatorColorCircleWithUploadFileAndClickOnSocialMediaImages(){

        corporateBrandingPage.clickPrimaryColor();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(-20, -20);

        corporateBrandingPage.clickPrimaryFontColor();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(-2, -20);

        corporateBrandingPage.clickPrimaryGradientColor1();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(0, -20);

        corporateBrandingPage.clickSecondaryColor();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(20, 20);

        corporateBrandingPage.clickSecondaryFontColor();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(11, -20);

        corporateBrandingPage.clickPrimaryGradientColor2();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(9, -20);

        corporateBrandingPage.uploadClientLogo(prop.getProperty("fileUpload"));
        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
        Assert.assertTrue(corporateBrandingPage.displayUploadedFile());
        corporateBrandingPage.clickBtnResetToDefault();
    }

    @Test(priority = 10)
    public void checkPublishEnterInputTextWithUploadFileAndClickOnSocialMediaImages(){
        corporateBrandingPage.clickPrimaryColor();
        corporateBrandingPage.enterHexInput("#867b47");

        corporateBrandingPage.clickPrimaryFontColor();
        corporateBrandingPage.enterHexInput("#715353");

        corporateBrandingPage.clickPrimaryGradientColor1();
        corporateBrandingPage.enterHexInput("#7f6443");

        corporateBrandingPage.clickSecondaryColor();
        corporateBrandingPage.enterHexInput("#708c4f");

        corporateBrandingPage.clickSecondaryFontColor();
        corporateBrandingPage.enterHexInput("#845f5f");

        corporateBrandingPage.clickPrimaryGradientColor2();
        corporateBrandingPage.enterHexInput("#8c5f4a");

        corporateBrandingPage.uploadClientLogo(prop.getProperty("fileUpload"));
        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
        Assert.assertTrue(corporateBrandingPage.displayUploadedFile());
        corporateBrandingPage.clickBtnResetToDefault();
    }

    @Test(priority = 10)
    public void checkPublishDragAndDropHexWithUploadFileAndClickOnSocialMediaImages(){
        corporateBrandingPage.clickPrimaryColor();
        corporateBrandingPage.dragAndDropHEX(30);

        corporateBrandingPage.clickPrimaryFontColor();
        corporateBrandingPage.dragAndDropHEX(20);

        corporateBrandingPage.clickPrimaryGradientColor1();
        corporateBrandingPage.dragAndDropHEX(24);

        corporateBrandingPage.clickSecondaryColor();
        corporateBrandingPage.dragAndDropHEX(31);

        corporateBrandingPage.clickSecondaryFontColor();
        corporateBrandingPage.dragAndDropHEX(29);

        corporateBrandingPage.clickPrimaryGradientColor2();
        corporateBrandingPage.dragAndDropHEX(11);

        corporateBrandingPage.uploadClientLogo(prop.getProperty("fileUpload"));
        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
        Assert.assertTrue(corporateBrandingPage.displayUploadedFile());
        corporateBrandingPage.clickBtnResetToDefault();
    }

    @Test(priority = 11)
    public void checkPublishDragAndDropIndicatorColorCircleWithUploadFileAndClickOffSocialMediaImages(){

        corporateBrandingPage.clickPrimaryColor();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(-20, -20);

        corporateBrandingPage.clickPrimaryFontColor();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(-2, -20);

        corporateBrandingPage.clickPrimaryGradientColor1();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(0, -20);

        corporateBrandingPage.clickSecondaryColor();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(20, 20);

        corporateBrandingPage.clickSecondaryFontColor();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(11, -20);

        corporateBrandingPage.clickPrimaryGradientColor2();
        corporateBrandingPage.dragAndDropIndicatorColorCircle(9, -20);

        corporateBrandingPage.uploadClientLogo(prop.getProperty("fileUpload"));
        corporateBrandingPage.clickOffCheckboxSocialMediaImages();

        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
        Assert.assertTrue(corporateBrandingPage.displayUploadedFile());
        corporateBrandingPage.clickBtnResetToDefault();
    }

    @Test(priority = 12)
    public void checkPublishEnterInputTextWithUploadFileAndClickOffSocialMediaImages(){
        corporateBrandingPage.clickPrimaryColor();
        corporateBrandingPage.enterHexInput("#867b47");

        corporateBrandingPage.clickPrimaryFontColor();
        corporateBrandingPage.enterHexInput("#715353");

        corporateBrandingPage.clickPrimaryGradientColor1();
        corporateBrandingPage.enterHexInput("#7f6443");

        corporateBrandingPage.clickSecondaryColor();
        corporateBrandingPage.enterHexInput("#708c4f");

        corporateBrandingPage.clickSecondaryFontColor();
        corporateBrandingPage.enterHexInput("#845f5f");

        corporateBrandingPage.clickPrimaryGradientColor2();
        corporateBrandingPage.enterHexInput("#8c5f4a");

        corporateBrandingPage.uploadClientLogo(prop.getProperty("fileUpload"));
        corporateBrandingPage.clickOffCheckboxSocialMediaImages();

        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
        Assert.assertTrue(corporateBrandingPage.displayUploadedFile());
        corporateBrandingPage.clickBtnResetToDefault();
    }

    @Test(priority = 13)
    public void checkPublishDragAndDropHexWithUploadFileAndClickOffSocialMediaImages(){
        corporateBrandingPage.clickPrimaryColor();
        corporateBrandingPage.dragAndDropHEX(30);

        corporateBrandingPage.clickPrimaryFontColor();
        corporateBrandingPage.dragAndDropHEX(20);

        corporateBrandingPage.clickPrimaryGradientColor1();
        corporateBrandingPage.dragAndDropHEX(24);

        corporateBrandingPage.clickSecondaryColor();
        corporateBrandingPage.dragAndDropHEX(31);

        corporateBrandingPage.clickSecondaryFontColor();
        corporateBrandingPage.dragAndDropHEX(29);

        corporateBrandingPage.clickPrimaryGradientColor2();
        corporateBrandingPage.dragAndDropHEX(11);

        corporateBrandingPage.uploadClientLogo(prop.getProperty("fileUpload"));
        corporateBrandingPage.clickOffCheckboxSocialMediaImages();

        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
        Assert.assertTrue(corporateBrandingPage.displayUploadedFile());
        corporateBrandingPage.clickBtnResetToDefault();
    }

    @Test(priority = 14)
    public void checkKeepCurrentAfterUploadFile(){
        corporateBrandingPage.uploadClientLogo(prop.getProperty("fileUpload"));

        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
        Assert.assertTrue(corporateBrandingPage.displayUploadedFile());

        corporateBrandingPage.clickKeepCurrent();
        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
        corporateBrandingPage.clickBtnResetToDefault();
    }

    @Test(priority = 15)
    public void checkReplaceCurrentAfterUploadFile(){
        corporateBrandingPage.uploadClientLogo(prop.getProperty("fileUpload"));

        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
        Assert.assertTrue(corporateBrandingPage.displayUploadedFile());

        corporateBrandingPage.clickReplaceCurrent();
        corporateBrandingPage.uploadClientLogo(prop.getProperty("fileReplaceCurrent"));
        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
        String textFileReplaceCurrent = "clientLogo2.jpg";
        Assert.assertEquals(corporateBrandingPage.getTextFileReplaceCurrent(), textFileReplaceCurrent);
        corporateBrandingPage.clickBtnResetToDefault();
        corporateBrandingPage.clickBtnResetToDefault();
    }

    @Test(priority = 16)
    public void checkDeleteCurrentAfterUploadFile(){
        corporateBrandingPage.uploadClientLogo(prop.getProperty("fileUpload"));

        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
        Assert.assertTrue(corporateBrandingPage.displayUploadedFile());

        corporateBrandingPage.clickDeleteCurrent();
        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
        Assert.assertTrue(corporateBrandingPage.displayUploadedFile());
    }
    @Test(priority = 17)
    public void checkButtonResetToDefault(){

        String stylePrimaryColorDefault = "background-color: rgb(29, 67, 255); opacity: 1; cursor: pointer;";
        String stylePrimaryFontColorDefault = "background-color: rgb(255, 255, 255); opacity: 1; cursor: pointer;";
        String stylePrimaryGradientColor1Default = "background-color: rgb(11, 92, 255); opacity: 1; cursor: pointer;";
        String styleSecondaryColor = "background-color: rgb(33, 56, 188); opacity: 1; cursor: pointer;";
        String styleSecondaryFontColor = "background-color: rgb(255, 255, 255); opacity: 1; cursor: pointer;";
        String stylePrimaryGradientColor2 = "background-color: rgb(23, 181, 243); opacity: 1; cursor: pointer;";

        corporateBrandingPage.clickPrimaryColor();
        corporateBrandingPage.dragAndDropHEX(30);

        corporateBrandingPage.clickPrimaryFontColor();
        corporateBrandingPage.dragAndDropHEX(20);

        corporateBrandingPage.clickPrimaryGradientColor1();
        corporateBrandingPage.dragAndDropHEX(24);

        corporateBrandingPage.clickSecondaryColor();
        corporateBrandingPage.dragAndDropHEX(31);

        corporateBrandingPage.clickSecondaryFontColor();
        corporateBrandingPage.dragAndDropHEX(29);

        corporateBrandingPage.clickPrimaryGradientColor2();
        corporateBrandingPage.dragAndDropHEX(11);

        corporateBrandingPage.uploadClientLogo(prop.getProperty("fileUpload"));
        corporateBrandingPage.clickOffCheckboxSocialMediaImages();

        corporateBrandingPage.clickBtnPublish();
        Assert.assertTrue(corporateBrandingPage.getTextPublishSuccess().contains("Successfully Saved"));
        Assert.assertTrue(corporateBrandingPage.displayUploadedFile());
        corporateBrandingPage.clickBtnResetToDefault();

        Assert.assertEquals(corporateBrandingPage.getAttributeStylePrimaryColor(), stylePrimaryColorDefault);
        Assert.assertEquals(corporateBrandingPage.getAttributeStylePrimaryFontColor(), stylePrimaryFontColorDefault);
        Assert.assertEquals(corporateBrandingPage.getAttributeStylePrimaryGradientColor1(), stylePrimaryGradientColor1Default);
        Assert.assertEquals(corporateBrandingPage.getAttributeStyleSecondaryColor(), styleSecondaryColor);
        Assert.assertEquals(corporateBrandingPage.getAttributeStyleSecondaryFontColor(), styleSecondaryFontColor);
        Assert.assertEquals(corporateBrandingPage.getAttributeStylePrimaryGradientColor2(), stylePrimaryGradientColor2);
        Assert.assertFalse(corporateBrandingPage.getTextContainClientLogo().contains("No file selected"));
        Assert.assertFalse(corporateBrandingPage.getTextContainClientBanner().contains("No file selected"));
        Assert.assertFalse(corporateBrandingPage.getTextContainLoginBanner().contains("No file selected"));
        Assert.assertTrue(corporateBrandingPage.displayCheckboxSocialMediaImages());
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
