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

public class PIMDataImport extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    PIMPage pimPage;
    DataImportPage dataImportPage;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        dataImportPage = new DataImportPage(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        pimPage = leftOption.clickPIMOption();
        dataImportPage = pimPage.selectDataImport();
    }

    @Test(priority = 1)
    public void checkDisplayDataImport(){
        String textColumnOrder = "Column order should not be changed";
        String textFirstName = "First Name and Last Name are compulsory";
        String textAllDate = "All date fields should be in YYYY-MM-DD format";
        String textIfGender = "If gender is specified, value should be either Male or Female";
        String textEachImport = "Each import file should be configured for 100 records or less";
        String textMultipleImport = "Multiple import files may be required";

        Assert.assertTrue(dataImportPage.displayTitleDataImport());
        Assert.assertEquals(dataImportPage.getTextColumnOrder(), textColumnOrder);
        Assert.assertEquals(dataImportPage.getTextFirstName(), textFirstName);
        Assert.assertEquals(dataImportPage.getTextAllDate(), textAllDate);
        Assert.assertEquals(dataImportPage.getTextIfGender(), textIfGender);
        Assert.assertEquals(dataImportPage.getTextEachImport(), textEachImport);
        Assert.assertEquals(dataImportPage.getTextMultipleImport(), textMultipleImport);
        Assert.assertTrue(dataImportPage.displayBtnUpload());
    }

    @Test(priority = 2)
    public void checkValidateRequired(){
        String textAcceptUpTo= "Accepts up to 1MB";
        String messageRequired = "Required";
        dataImportPage.clickBtnUpload();
        Assert.assertEquals(dataImportPage.getMessageRequired(), messageRequired);
        Assert.assertEquals(dataImportPage.getTextAcceptUpTo(), textAcceptUpTo);
    }

    @Test(priority = 3)
    public void checkValidateFileType(){
        dataImportPage.enterSelectFile(prop.getProperty("validateFileType"));
        String textFileType = "File type not allowed";
        Assert.assertEquals(dataImportPage.getMessageFileType(), textFileType);
    }

    @Test(priority = 4)
    public void checkDataNoRecord(){
        String textTitleDocument = "Import Details";
        String textBodyDocument = "No Records Imported";
        dataImportPage.enterSelectFile(prop.getProperty("validateFileNoRecord"));
        dataImportPage.clickBtnUpload();
        Assert.assertTrue(dataImportPage.displayDocument());
        Assert.assertEquals(dataImportPage.getTextTitleDocument(), textTitleDocument);
        Assert.assertEquals(dataImportPage.getTextBodyDocument(), textBodyDocument);
        dataImportPage.clickBtnOk();
    }

    @Test(priority = 5)
    public void uploadFileSuccess(){
        String textTitleDocument = "Import Details";
        dataImportPage.enterSelectFile(prop.getProperty("fileDataRecord"));
        dataImportPage.clickBtnUpload();
        Assert.assertTrue(dataImportPage.displayDocument());
        Assert.assertEquals(dataImportPage.getTextTitleDocument(), textTitleDocument);
        Assert.assertTrue(dataImportPage.getTextMessageBodyDocument().contains("2 Records Successfully Imported"));
        dataImportPage.clickBtnOk();
    }

    @Test(priority = 6)
    public void uploadFileExistRecord(){
        String textTitleDocument = "Import Details";
        dataImportPage.enterSelectFile(prop.getProperty("fileDataRecord"));
        dataImportPage.clickBtnUpload();
        Assert.assertTrue(dataImportPage.displayDocument());
        Assert.assertEquals(dataImportPage.getTextTitleDocument(), textTitleDocument);
        Assert.assertFalse(dataImportPage.getTextBodyDocument().contains("2 Records Failed to Import"));
        dataImportPage.clickBtnOk();
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
