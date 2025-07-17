package selenium.testcase;

import listeners.MyListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
public class AdminEmployeeStatus extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    AdminPage adminPage;
    EmploymentStatusPage employmentStatusPage;
    AddEmploymentStatusPage addEmploymentStatusPage;
    EditEmployeeStatusPage editEmployeeStatusPage;
    DeletePopup deletePopup;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        adminPage = new AdminPage(driver);
        addEmploymentStatusPage = new AddEmploymentStatusPage(driver);
        employmentStatusPage = new EmploymentStatusPage(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        leftOption.clickAdminOption();
        adminPage.clickDropdownJob();
        employmentStatusPage = adminPage.clickEmploymentStatusOption();
    }

    @Test(priority = 1)
    public void checkDisplayAdminEmployeeStatus(){
        String titleEmploymentStatus = "Employment Status";

        Assert.assertEquals(employmentStatusPage.getTextTitleEmploymentStatus(), titleEmploymentStatus);
        Assert.assertTrue(employmentStatusPage.displayTitleEmploymentStatus());
        Assert.assertTrue(employmentStatusPage.displayBtnAdd());
        Assert.assertTrue(employmentStatusPage.displayTable());
        Assert.assertTrue(employmentStatusPage.getSizeRowTable() > 0, "There should be at least one employee row");
    }

    @Test(priority = 2)
    public void checkDisplayAddEmployeeStatus(){
        String addEmployeeStatus = "Add Employment Status";
        addEmploymentStatusPage = employmentStatusPage.clickBtnAdd();
        Assert.assertEquals(addEmploymentStatusPage.getTextTitleAddEmployeeStatus(), addEmployeeStatus);
        Assert.assertTrue(addEmploymentStatusPage.displayTitleAddEmployeeStatus());
        Assert.assertTrue(addEmploymentStatusPage.displayBtnCancel());
        Assert.assertTrue(addEmploymentStatusPage.displayBtnSave());
    }

    @Test(priority = 3)
    public void checkValidateAddEmployeeStatus(){
        String messageRequired = "Required";
        addEmploymentStatusPage = employmentStatusPage.clickBtnAdd();
        addEmploymentStatusPage.clickBtnSave();
        Assert.assertEquals(addEmploymentStatusPage.getMessageRequiredEmployeeStatus(), messageRequired);
    }

    @Test(priority = 4)
    public void addEmployeeStatusCheckMessageExceedCharacter(){
        String textExceedCharacters = "Should not exceed 50 characters";
        addEmploymentStatusPage = employmentStatusPage.clickBtnAdd();
        addEmploymentStatusPage.enterInputEmployeeStatus(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addEmploymentStatusPage.getMessageExceedCharacter(), textExceedCharacters);
    }

    @Test(priority = 5)
    public void addEmployeeStatusWhenClickBtnCancel(){
        addEmploymentStatusPage = employmentStatusPage.clickBtnAdd();
        addEmploymentStatusPage.enterInputEmployeeStatus(prop.getProperty("employeeStatus"));
        employmentStatusPage = addEmploymentStatusPage.clickBtnCancel();
    }

    @Test(priority = 6)
    public void addEmployeeStatusWithRequiredFields(){
        addEmploymentStatusPage = employmentStatusPage.clickBtnAdd();
        addEmploymentStatusPage.enterInputEmployeeStatus(prop.getProperty("employeeStatus"));
        addEmploymentStatusPage.clickBtnSave();
        Assert.assertTrue(addEmploymentStatusPage.getTextAddEmployeeStatusSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 7)
    public void addEmployeeStatusAlreadyExists(){
        addEmploymentStatusPage = employmentStatusPage.clickBtnAdd();
        addEmploymentStatusPage.enterInputEmployeeStatus(prop.getProperty("employeeStatus"));
        addEmploymentStatusPage.clickBtnSave();
        String messageExist = "Already exists";
        Assert.assertEquals(addEmploymentStatusPage.getMessageExistEmployeeStatus(), messageExist);
    }

    @Test(priority = 8)
    public void checkDisplayEditEmploymentStatus(){
        String textTitleEditEmploymentStatus = "Edit Employment Status";
        editEmployeeStatusPage = employmentStatusPage.clickEditEmployeeStatus();
        Assert.assertEquals(editEmployeeStatusPage.getTextTitleEditEmploymentStatus(), textTitleEditEmploymentStatus);
        Assert.assertTrue(editEmployeeStatusPage.displayTitleEditEmploymentStatus());
        Assert.assertTrue(editEmployeeStatusPage.displayBtnCancelEditEmploymentStatus());
        Assert.assertTrue(editEmployeeStatusPage.displayBtnSaveEditEmploymentStatus());
    }

    @Test(priority = 9)
    public void editEmployeeStatusWhenClickBtnCancel(){
        editEmployeeStatusPage = employmentStatusPage.clickEditEmployeeStatus();
        employmentStatusPage = editEmployeeStatusPage.clickBtnCancel();
    }

    @Test(priority = 10)
    public void editSuccessEmployeeStatus(){
        String textTitleEditEmploymentStatus = "Edit Employment Status";
        editEmployeeStatusPage = employmentStatusPage.clickEditEmployeeStatus();
        Assert.assertEquals(editEmployeeStatusPage.getTextTitleEditEmploymentStatus(), textTitleEditEmploymentStatus);

        editEmployeeStatusPage.enterEmployeeStatus(prop.getProperty("employeeStatusEdit"));
        employmentStatusPage = editEmployeeStatusPage.clickBtnSave();
        Assert.assertTrue(editEmployeeStatusPage.getTextEditSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 11)
    public void deleteEmployeeStatusCheckDisplayDocument(){
        String titleAreYouSure = "Are you Sure?";
        String content = "The selected record will be permanently deleted. Are you sure you want to continue?";
        deletePopup = employmentStatusPage.clickDeleteEmployeeStatus();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), titleAreYouSure);
        Assert.assertEquals(deletePopup.getTextContentDelete(), content);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 12)
    public void deleteEmployeeStatusWhenClickOnNoCancel(){
        deletePopup = employmentStatusPage.clickDeleteEmployeeStatus();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 13)
    public void deleteSuccessEmployeeStatus(){
        deletePopup = employmentStatusPage.clickDeleteEmployeeStatus();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(employmentStatusPage.getTextMessageSuccess().contains("Successfully Deleted"));
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }

}
