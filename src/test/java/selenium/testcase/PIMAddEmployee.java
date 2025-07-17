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

public class PIMAddEmployee extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    PIMPage pimPage;
    AddEmployeePage addEmployeePage;
    MyInfoPage myInfoEmployeePage;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        addEmployeePage = new AddEmployeePage(driver);
        myInfoEmployeePage = new MyInfoPage(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        pimPage = leftOption.clickPIMOption();
        addEmployeePage = pimPage.clickAddEmployee();
    }

    @Test(priority = 1)
    public void checkDisplayAddEmployee(){
        String messageAccept = "Accepts jpg, .png, .gif up to 1MB. Recommended dimensions: 200px X 200px";
        Assert.assertTrue(addEmployeePage.displayTitleAddEmployee());
        Assert.assertEquals(addEmployeePage.getTextMessageAccept(), messageAccept);
        Assert.assertTrue(addEmployeePage.displayBtnCancel());
        Assert.assertTrue(addEmployeePage.displayBtnSave());
    }

    @Test(priority = 2)
    public void checkValidateWhenCreateLoginDetailOff(){
        String messageRequired = "Required";
        addEmployeePage.clickBtnSave();
        Assert.assertEquals(addEmployeePage.getMessageRequiredFirstName(), messageRequired);
        Assert.assertEquals(addEmployeePage.getMessageRequiredLastName(), messageRequired);
    }

    @Test(priority = 3)
    public void checkValidateWhenCreateLoginDetailOn(){
        String messageRequired = "Required";
        String messageDoNotMatch = "Passwords do not match";
        addEmployeePage.clickCheckBoxCreateLoginDetail();
        addEmployeePage.clickBtnSave();
        Assert.assertEquals(addEmployeePage.getMessageRequiredFirstName(), messageRequired);
        Assert.assertEquals(addEmployeePage.getMessageRequiredLastName(), messageRequired);
        Assert.assertEquals(addEmployeePage.getMessageRequiredUserName(), messageRequired);
        Assert.assertEquals(addEmployeePage.getMessageRequiredPassword(), messageRequired);
        Assert.assertEquals(addEmployeePage.getMessageDoNotMatchPassword(), messageDoNotMatch);
    }

    @Test(priority = 4)
    public void checkValidateAddImageFileTypeNotAllowed(){
        String messageFileType = "File type not allowed";
        addEmployeePage.enterImageEmployee(prop.getProperty("validateFileType"));
        Assert.assertEquals(addEmployeePage.getMessageFileType(), messageFileType);
    }

    @Test(priority = 5)
    public void checkValidateFileAttachmentSizeExceeded(){
        String messageAttachmentSizeExceeded = "Attachment Size Exceeded";
        addEmployeePage.enterImageEmployee(prop.getProperty("fileAttachmentSizeExceeded"));
        Assert.assertEquals(addEmployeePage.getMessageAttachment(), messageAttachmentSizeExceeded);
    }

    @Test(priority = 6)
    public void addSuccessEmployee(){

        addEmployeePage.enterImageEmployee(prop.getProperty("fileUpload"));
        addEmployeePage.enterFirstName(prop.getProperty("firstName"));
        addEmployeePage.enterLastName(prop.getProperty("lastName"));
        addEmployeePage.employeeId();
        myInfoEmployeePage = addEmployeePage.clickBtnSave();
        Assert.assertTrue(addEmployeePage.getTextAddEmployeeSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 7)
    public void addSuccessEmployeeWhenClickCreateLoginDetails(){

        addEmployeePage.enterImageEmployee(prop.getProperty("fileUpload"));
        addEmployeePage.enterFirstName(prop.getProperty("firstName"));
        addEmployeePage.enterLastName(prop.getProperty("lastName"));
        addEmployeePage.employeeId();
        addEmployeePage.clickCheckBoxCreateLoginDetail();
        addEmployeePage.enterInputUsername(prop.getProperty("userNameAddUser"));
        addEmployeePage.clickEnable();
        addEmployeePage.enterPassword(prop.getProperty("passwordAddUser"));
        addEmployeePage.enterConfirmPass(prop.getProperty("confirmPasswordAddUser"));
        myInfoEmployeePage = addEmployeePage.clickBtnSave();
        Assert.assertTrue(addEmployeePage.getTextAddEmployeeSuccess().contains("Successfully Saved"));
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
