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
public class AdminNationalities extends Base {

    public WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    AdminPage adminPage;
    NationalitiesPage nationalitiesPage;
    AddNationalityPage addNationalityPage;
    EditNationalitiesPage editNationalitiesPage;
    DeletePopup deletePopup;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        adminPage = new AdminPage(driver);
        addNationalityPage = new AddNationalityPage(driver);
        editNationalitiesPage = new EditNationalitiesPage(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        leftOption.clickAdminOption();
        nationalitiesPage = adminPage.clickNationalitiesPage();
    }

    @Test(priority = 1)
    public void checkDisplayAdminNationalities(){
        String titleNationalities = "Nationalities";
        Assert.assertEquals(nationalitiesPage.getTextTitleNationalities(), titleNationalities);
        Assert.assertTrue(nationalitiesPage.displayTitleNationalities());
        Assert.assertTrue(nationalitiesPage.displayBtnAdd());
        Assert.assertTrue(nationalitiesPage.displayTable());
        Assert.assertTrue(nationalitiesPage.getSizeRowTable() > 0, "There should be at least one nationalities row");
    }

    @Test(priority = 2)
    public void checkDisplayAddNationalities(){
        String titleAddNationality = "Add Nationality";
        addNationalityPage = nationalitiesPage.clickBtnAdd();
        Assert.assertEquals(addNationalityPage.getTextTitleAddNationality(), titleAddNationality);
        Assert.assertTrue(addNationalityPage.displayTitleAddNationality());
        Assert.assertTrue(addNationalityPage.displayBtnCancel());
        Assert.assertTrue(addNationalityPage.displayBtnSave());
    }

    @Test(priority = 3)
    public void checkValidateAddNationalities(){
        String messageRequired = "Required";
        addNationalityPage = nationalitiesPage.clickBtnAdd();
        addNationalityPage.clickBtnSave();
        Assert.assertEquals(addNationalityPage.getMessageRequired(), messageRequired);
    }

    @Test(priority = 4)
    public void checkValidateExceedCharactersAddNationalities(){
        String messageExceedCharacters = "Should not exceed 100 characters";
        addNationalityPage = nationalitiesPage.clickBtnAdd();
        addNationalityPage.enterInputName(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addNationalityPage.getMessageExceedCharacters(), messageExceedCharacters);
    }

    @Test(priority = 5)
    public void addNationalitiesWhenClickBtnCancel(){
        addNationalityPage = nationalitiesPage.clickBtnAdd();
        addNationalityPage.enterInputName(prop.getProperty("nationalities"));
        nationalitiesPage = addNationalityPage.clickBtnCancel();
    }

    @Test(priority = 6)
    public void addNationalitiesWithRequiredFields(){
        addNationalityPage = nationalitiesPage.clickBtnAdd();
        addNationalityPage.enterInputName(prop.getProperty("nationalities"));
        addNationalityPage.clickBtnSave();
        Assert.assertFalse(addNationalityPage.getTextTitleAddNationality().contains("Successfully Saved"));
    }

    @Test(priority = 7)
    public void addNationalitiesAlreadyExists(){
        addNationalityPage = nationalitiesPage.clickBtnAdd();
        addNationalityPage.enterInputName(prop.getProperty("nationalities"));
        addNationalityPage.clickBtnSave();
        String messageExist = "Already exists";
        Assert.assertEquals(addNationalityPage.getMessageExist(), messageExist);
    }

    @Test(priority = 8)
    public void displayEditNationalities(){
        String titleEditNationality = "Edit Nationality";
        editNationalitiesPage = nationalitiesPage.clickEditNationalities();
        Assert.assertEquals(editNationalitiesPage.getTextTitleEditNationality(), titleEditNationality);
        Assert.assertTrue(editNationalitiesPage.displayTitleEditNationality());
        Assert.assertTrue(editNationalitiesPage.displayBtnCancel());
        Assert.assertTrue(editNationalitiesPage.displayBtnSave());
    }

    @Test(priority = 9)
    public void editNationalitiesWhenClickBtnCancel(){
        editNationalitiesPage = nationalitiesPage.clickEditNationalities();
        nationalitiesPage = editNationalitiesPage.clickBtnCancel();
    }

    @Test(priority = 10)
    public void editSuccessNationalities(){
        editNationalitiesPage = nationalitiesPage.clickEditNationalities();
        editNationalitiesPage.enterNationalities(prop.getProperty("nationalitiesEdit"));
        nationalitiesPage = editNationalitiesPage.clickBtnSave();
        Assert.assertTrue(editNationalitiesPage.getTextEditSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 11)
    public void deleteNationalitiesCheckDisplayDocument(){
        String titleAreYouSure = "Are you Sure?";
        String textContentDocument = "The selected record will be permanently deleted. Are you sure you want to continue?";
        deletePopup = nationalitiesPage.clickDeleteNationalities();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), titleAreYouSure);
        Assert.assertEquals(deletePopup.getTextContentDelete(), textContentDocument);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 12)
    public void deleteNationalitiesWhenClickBtnNoCancel(){
        nationalitiesPage.clickDeleteNationalities();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 13)
    public void deleteSuccessNationalities(){
        nationalitiesPage.clickDeleteNationalities();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(nationalitiesPage.getTextMessageSuccess().contains("Successfully Deleted"));
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
