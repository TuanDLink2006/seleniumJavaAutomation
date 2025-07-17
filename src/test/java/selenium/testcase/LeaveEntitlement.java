package selenium.testcase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import selenium.base.Base;
import utils.CommonUtils;
import utils.ElementUtils;

import java.util.Properties;

public class LeaveEntitlement extends Base {

    public WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    LeavePage leavePage;
    AddLeaveEntitlementPage addLeaveEntitlementPage;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        leavePage = new LeavePage(driver);
        addLeaveEntitlementPage = new AddLeaveEntitlementPage(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        leavePage = leftOption.clickLeaveOption();
        leavePage.clickDropdownEntitlements();
        addLeaveEntitlementPage = leavePage.clickAddLeaveEntitlement();
    }

    @Test(priority = 1)
    public void checkDisplayAddEntitlement(){
        String titleAddEntitlement = "Add Leave Entitlement";
        Assert.assertEquals(addLeaveEntitlementPage.getTextTitleAddEntitlement(), titleAddEntitlement);
        Assert.assertTrue(addLeaveEntitlementPage.displayTitleEntitlement());
        Assert.assertFalse(addLeaveEntitlementPage.isSelectedIndividualEmployee());
        Assert.assertTrue(addLeaveEntitlementPage.displayBtnCancel());
        Assert.assertTrue(addLeaveEntitlementPage.displayBtnSave());
    }

    @Test(priority = 2)
    public void checkValidateAddEntitlement(){
        String messageRequired = "Required";
        addLeaveEntitlementPage.clickBtnSave();
        Assert.assertEquals(addLeaveEntitlementPage.getTextMessageRequiredEmployee(), messageRequired);
        Assert.assertEquals(addLeaveEntitlementPage.getTextMessageRequiredLeaveType(), messageRequired);
        Assert.assertEquals(addLeaveEntitlementPage.getTextMessageRequiredEntitlement(), messageRequired);
    }

    @Test(priority = 3)
    public void checkMessageValidAddEntitlement() throws InterruptedException {
        String messageInvalid = "Invalid";
        addLeaveEntitlementPage.inputEmployee("ddd", "ddd");
        addLeaveEntitlementPage.clickTitle();
        Assert.assertEquals(addLeaveEntitlementPage.getTextMessageInvalid(), messageInvalid);
    }

    @Test(priority = 4)
    public void checkMessageShouldBeNumberAddEntitlement(){
        String messageShouldBeNumber = "Should be a number with upto 2 decimal places";
        addLeaveEntitlementPage.enterEntitlement("ddd");
        Assert.assertEquals(addLeaveEntitlementPage.getTextMessageShouldBeNumber(), messageShouldBeNumber);
    }

    @Test(priority = 5)
    public void checkMessageShouldBeLessThanAddEntitlement(){
        String messageShouldBeLessThan = "Should be less than 10000";
        addLeaveEntitlementPage.enterEntitlement("111111");
        Assert.assertEquals(addLeaveEntitlementPage.getTextMessageShouldBeLessThan(), messageShouldBeLessThan);
    }

    @Test(priority = 6)
    public void addLeaveEntitlementWhenClickOnIndividualEmployeeAndCancelUpdatingEntitlement() throws InterruptedException {
        addLeaveEntitlementPage.inputEmployee(prop.getProperty("employeeNameLeave"), prop.getProperty("expectedFullName"));
        addLeaveEntitlementPage.selectLeaveType(prop.getProperty("leaveType"));
        addLeaveEntitlementPage.selectLeavePeriod(prop.getProperty("leavePeriod"));
        addLeaveEntitlementPage.enterEntitlement("1111");
        addLeaveEntitlementPage.clickBtnSave();
        Assert.assertTrue(addLeaveEntitlementPage.displayUpdatingEntitlement());
        addLeaveEntitlementPage = addLeaveEntitlementPage.clickBtnCancelUpdatingEntitlement();
    }

    @Test(priority = 7)
    public void addLeaveEntitlementWhenClickOnIndividualEmployeeAndConfirmUpdatingEntitlement() throws InterruptedException {
        addLeaveEntitlementPage.inputEmployee(prop.getProperty("employeeNameLeave"), prop.getProperty("expectedFullName"));
        addLeaveEntitlementPage.selectLeaveType(prop.getProperty("leaveType"));
        addLeaveEntitlementPage.selectLeavePeriod(prop.getProperty("leavePeriod"));
        addLeaveEntitlementPage.enterEntitlement("1111");
        addLeaveEntitlementPage.clickBtnSave();
        Assert.assertTrue(addLeaveEntitlementPage.displayUpdatingEntitlement());
        addLeaveEntitlementPage.clickBtnConfirm();
        Assert.assertTrue(addLeaveEntitlementPage.getTextMessageSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 8)
    public void addLeaveEntitlementWhenClickOnMultipleEmployeesAndCancelUpdatingEntitlement() {
        addLeaveEntitlementPage.clickMultipleEmployees();
        addLeaveEntitlementPage.selectLocation(prop.getProperty("location"));
        addLeaveEntitlementPage.selectSubUnit(prop.getProperty("subUnit"));
        addLeaveEntitlementPage.selectLeaveType(prop.getProperty("leaveType"));
        addLeaveEntitlementPage.selectLeavePeriod(prop.getProperty("leavePeriod"));
        addLeaveEntitlementPage.enterEntitlement("1111");
        addLeaveEntitlementPage.clickBtnSave();
        Assert.assertTrue(addLeaveEntitlementPage.displayUpdatingEntitlement());
        addLeaveEntitlementPage = addLeaveEntitlementPage.clickBtnCancelUpdatingEntitlement();
    }

    @Test(priority = 9)
    public void addLeaveEntitlementWhenClickOnMultipleEmployeesAndConfirmUpdatingEntitlement(){
        addLeaveEntitlementPage.clickMultipleEmployees();
        addLeaveEntitlementPage.selectLocation(prop.getProperty("location"));
        addLeaveEntitlementPage.selectSubUnit(prop.getProperty("subUnit"));
        addLeaveEntitlementPage.selectLeaveType(prop.getProperty("leaveType"));
        addLeaveEntitlementPage.selectLeavePeriod(prop.getProperty("leavePeriod"));
        addLeaveEntitlementPage.enterEntitlement("1111");
        addLeaveEntitlementPage.clickBtnSave();
        Assert.assertTrue(addLeaveEntitlementPage.displayUpdatingEntitlement());
        addLeaveEntitlementPage.clickBtnConfirm();
        Assert.assertFalse(addLeaveEntitlementPage.getTextMessageSuccess().contains("Successfully Saved"));
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }

}
