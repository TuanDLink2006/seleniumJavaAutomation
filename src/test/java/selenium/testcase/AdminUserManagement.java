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

public class AdminUserManagement extends Base {

    public WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    UsersAdminPage usersAdminPage;
    AddUserNamePage addUserNamePage;
    EditUserPage editUserPage;
    DeletePopup deletePopup;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        addUserNamePage = new AddUserNamePage(driver);
        editUserPage = new EditUserPage(driver);
        usersAdminPage = new UsersAdminPage(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        usersAdminPage = leftOption.clickAdminOption();
    }

    @Test(priority = 1)
    public void checkDisplaySystemUsers(){
        String titleSystemUsers = "System Users";
        Assert.assertTrue(usersAdminPage.displayTitleSystemUsers());
        Assert.assertEquals(usersAdminPage.getTextTitleSystemUsers(), titleSystemUsers);
        Assert.assertTrue(usersAdminPage.displayBtnReset());
        Assert.assertTrue(usersAdminPage.displayBtnSearch());
        Assert.assertTrue(usersAdminPage.displayTable());
    }

    @Test(priority = 2)
    public void checkDisplayAddUser(){
        String titleAddUser = "Add User";
        String textPassHint = "For a strong password, please use a hard to guess combination of text with upper and lower case characters, symbols and numbers";
        addUserNamePage = usersAdminPage.clickBtnAdd();

        Assert.assertEquals(addUserNamePage.getTextTitleAddUser(), titleAddUser);
        Assert.assertTrue(addUserNamePage.displayTitleAddUser());
        Assert.assertEquals(addUserNamePage.getTextPassHint(), textPassHint);
        Assert.assertTrue(addUserNamePage.displayBtnCancel());
        Assert.assertTrue(addUserNamePage.displayBtnSave());
    }

    @Test(priority = 3)
    public void checkValidateRequiredWhenAddUser(){

        addUserNamePage = usersAdminPage.clickBtnAdd();
        addUserNamePage.clickBtnSave();

        String messageRequired = "Required";

        Assert.assertEquals(addUserNamePage.getTextMessageRequiredUserRole(), messageRequired);
        Assert.assertEquals(addUserNamePage.getTextMessageRequiredEmployeeName(), messageRequired);
        Assert.assertEquals(addUserNamePage.getTextMessageRequiredStatus(), messageRequired);
        Assert.assertEquals(addUserNamePage.getTextMessageRequiredUserName(), messageRequired);
        Assert.assertEquals(addUserNamePage.getTextMessageRequiredPassword(), messageRequired);
    }

    @Test(priority = 4)
    public void checkValidateInvalidWhenAddUser() throws InterruptedException {

        addUserNamePage = usersAdminPage.clickBtnAdd();
        addUserNamePage.inputEmployee(prop.getProperty("inputEmployeeEdit"), prop.getProperty("inputEmployeeEdit"));;
        addUserNamePage.clickTitleAddUserName();
        String invalidEmployeeName = "Invalid";
        Assert.assertEquals(addUserNamePage.getTextMessageInvalidEmployeeName(), invalidEmployeeName);
    }

    @Test(priority = 5)
    public void checkValidateCharactersWhenAddUser(){

        addUserNamePage = usersAdminPage.clickBtnAdd();
        addUserNamePage.enterUserName(prop.getProperty("shouldBeCharacter"));
        addUserNamePage.enterPassword(prop.getProperty("shouldBeCharacter"));

        String invalidUsername = "Should be at least 5 characters";
        String invalidPassword = "Should have at least 7 characters";

        Assert.assertEquals(addUserNamePage.getTextMessageShouldAt5CharactersUserName(), invalidUsername);
        Assert.assertEquals(addUserNamePage.getTextMessageShouldAt7CharactersPassword(), invalidPassword);
    }

    @Test(priority = 6)
    public void checkValidateDoNotMarchWhenAddUser(){

        addUserNamePage = usersAdminPage.clickBtnAdd();
        addUserNamePage.enterPassword(prop.getProperty("passwordAddUser"));
        addUserNamePage.enterConfirmPass(prop.getProperty("passwordDoNotMatch"));

        String messagePasswordNotMarch = "Passwords do not match";

        Assert.assertEquals(addUserNamePage.getTextMessagePasswordNotMarch(), messagePasswordNotMarch);
    }

    @Test(priority = 7)
    public void checkValidateExceedCharactersWhenAddUser(){

        addUserNamePage = usersAdminPage.clickBtnAdd();
        addUserNamePage.enterUserName(prop.getProperty("exceedCharacter"));
        addUserNamePage.enterPassword(prop.getProperty("exceedCharacter"));

        String messageExceed40Character = "Should not exceed 40 characters";
        String messageExceed64Character = "Should not exceed 64 characters";

        Assert.assertEquals(addUserNamePage.getTextMessageShouldNotExceed40Characters(), messageExceed40Character);
        Assert.assertEquals(addUserNamePage.getTextMessageShouldNotExceed64Characters(), messageExceed64Character);
    }

    @Test(priority = 8)
    public void checkValidatePasswordContainMinimum1LowerCaseWhenAddUser(){

        addUserNamePage = usersAdminPage.clickBtnAdd();
        addUserNamePage.enterPassword(prop.getProperty("passwordContainMinimum1LowerCase"));

        String messagePasswordContainMinimum1LowerCase = "Your password must contain minimum 1 lower-case letter";

        Assert.assertEquals(addUserNamePage.getTextMessagePasswordContainMinimum1LowerCase(), messagePasswordContainMinimum1LowerCase);
    }

    @Test(priority = 9)
    public void checkValidatePasswordContainMinimum1NumberWhenAddUser(){

        addUserNamePage = usersAdminPage.clickBtnAdd();
        addUserNamePage.enterPassword(prop.getProperty("passwordContainMinimum1Number"));

        String messagePasswordContainMinimum1Number = "Your password must contain minimum 1 number";

        Assert.assertEquals(addUserNamePage.getTextMessagePasswordContainMinimum1Number(), messagePasswordContainMinimum1Number);
    }

    @Test(priority = 10)
    public void addUserWhenClickButtonCancel(){

        addUserNamePage = usersAdminPage.clickBtnAdd();
        usersAdminPage = addUserNamePage.clickBtnCancel();
    }



    @Test(priority = 11)
    public void addUserSuccess() throws InterruptedException {

        addUserNamePage = usersAdminPage.clickBtnAdd();
        addUserNamePage.selectUserRoleOption(prop.getProperty("existingUserName"));
        addUserNamePage.inputEmployee(prop.getProperty("inputEmployee"), prop.getProperty("inputEmployee"));
        addUserNamePage.selectStatusOption(prop.getProperty("status"));

        addUserNamePage.enterUserName(prop.getProperty("userNameAddUser"));
        addUserNamePage.enterPassword(prop.getProperty("passwordAddUser"));
        addUserNamePage.enterConfirmPass(prop.getProperty("confirmPasswordAddUser"));

        usersAdminPage = addUserNamePage.clickBtnSave();
        Assert.assertTrue(editUserPage.getTextEditSuccess().contains("Successfully Saved"));
    }
    
    @Test(priority = 12)
    public void addUserExistUserName() throws InterruptedException {

        addUserNamePage = usersAdminPage.clickBtnAdd();
        addUserNamePage.selectUserRoleOption(prop.getProperty("existingUserName"));
        addUserNamePage.inputEmployee(prop.getProperty("inputEmployee"), prop.getProperty("inputEmployee"));
        addUserNamePage.selectStatusOption(prop.getProperty("status"));

        addUserNamePage.enterUserName(prop.getProperty("userNameAddUser"));
        addUserNamePage.enterPassword(prop.getProperty("passwordAddUser"));
        addUserNamePage.enterConfirmPass(prop.getProperty("confirmPasswordAddUser"));

        usersAdminPage = addUserNamePage.clickBtnSave();

        String existUsername = "Already exists";
        Assert.assertEquals(addUserNamePage.getTextMessageExistUserName(), existUsername);
    }

    @Test(priority = 13)
    public void searchUserNameExist(){
        usersAdminPage.enterUserName(prop.getProperty("existingUserName"));
        usersAdminPage.clickBtnSearch();
        Assert.assertTrue(usersAdminPage.isUsernameDisplayedInResult("Admin"));
    }

    @Test(priority = 14)
    public void searchUserNameNotExist(){
        usersAdminPage.enterUserName(prop.getProperty("existingNotUserName"));
        usersAdminPage.clickBtnSearch();

        boolean isDisplayed = usersAdminPage.isUsernameDisplayedInResult("Tuanlinh1");
        Assert.assertFalse(isDisplayed, "Username 'Tuanlinh1' should be displayed in search results.");
    }

    @Test(priority = 15)
    public void searchUserNameNotExistWithFullOption() throws InterruptedException {
        usersAdminPage.enterUserName(prop.getProperty("existingUserName"));
        usersAdminPage.selectUserRole("ESS");
        usersAdminPage.inputEmployee(prop.getProperty("inputEmployee"), prop.getProperty("inputEmployee"));
        usersAdminPage.selectStatus("Enabled");
        usersAdminPage.clickBtnSearch();

        boolean isDisplayed = usersAdminPage.isUsernameDisplayedInResult("Admin");
        Assert.assertTrue(isDisplayed, "Username should be displayed in search results.");
    }

    @Test(priority = 16)
    public void editUserWhenClickCancel() throws InterruptedException {
        usersAdminPage.enterUserName(prop.getProperty("userNameAddUser"));
        usersAdminPage.clickBtnSearch();
        Assert.assertTrue(usersAdminPage.isUsernameDisplayedInResult("Tuanlinh"));
        editUserPage = usersAdminPage.clickEditUser();
        editUserPage.clickYesPassword();
        editUserPage.enterPassword(prop.getProperty("passwordAddUser"));
        editUserPage.enterConfirmPass(prop.getProperty("confirmPasswordAddUser"));
        usersAdminPage = editUserPage.clickBtnCancel();

    }

    @Test(priority = 17)
    public void editUserNoChangePassword(){
        usersAdminPage.enterUserName(prop.getProperty("userNameAddUser"));
        usersAdminPage.clickBtnSearch();
        Assert.assertTrue(usersAdminPage.isUsernameDisplayedInResult("Tuanlinh"));
        editUserPage = usersAdminPage.clickEditUser();

        usersAdminPage = editUserPage.clickBtnSave();
        Assert.assertTrue(editUserPage.getTextEditSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 18)
    public void editUserYesChangePassword() throws InterruptedException {
        usersAdminPage.enterUserName(prop.getProperty("userNameAddUser"));
        usersAdminPage.clickBtnSearch();
        Assert.assertTrue(usersAdminPage.isUsernameDisplayedInResult("Tuanlinh"));
        editUserPage = usersAdminPage.clickEditUser();

        editUserPage.selectUserRole("ESS");
        editUserPage.inputEmployee(prop.getProperty("inputEmployeeEdit"), prop.getProperty("inputEmployeeEdit"));

        editUserPage.selectStatusOption("Disabled");

        editUserPage.clickYesPassword();
        editUserPage.enterPassword(prop.getProperty("passwordAddUser"));
        editUserPage.enterConfirmPass(prop.getProperty("confirmPasswordAddUser"));
        usersAdminPage = editUserPage.clickBtnSave();

        Assert.assertTrue(editUserPage.getTextEditSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 19)
    public void deleteUserCheckTitleAndContentDelete(){
        usersAdminPage.enterUserName(prop.getProperty("userNameAddUser"));
        usersAdminPage.clickBtnSearch();
        Assert.assertTrue(usersAdminPage.isUsernameDisplayedInResult("Tuanlinh"));
        deletePopup = usersAdminPage.clickDeleteUser();

        String titleDelete = "Are you Sure?";
        String contentDelete = "The selected record will be permanently deleted. Are you sure you want to continue?";
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), titleDelete);
        Assert.assertEquals(deletePopup.getTextContentDelete(), contentDelete);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());

    }

    @Test(priority = 20)
    public void deleteUserWhenClickNoCancel(){
        usersAdminPage.enterUserName(prop.getProperty("userNameAddUser"));
        usersAdminPage.clickBtnSearch();
        Assert.assertTrue(usersAdminPage.isUsernameDisplayedInResult("Tuanlinh"));
        deletePopup = usersAdminPage.clickDeleteUser();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 21)
    public void deleteUserWhenClickYesDelete(){
        usersAdminPage.enterUserName(prop.getProperty("userNameAddUser"));
        usersAdminPage.clickBtnSearch();
        Assert.assertTrue(usersAdminPage.isUsernameDisplayedInResult("Tuanlinh"));
        deletePopup = usersAdminPage.clickDeleteUser();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(usersAdminPage.getTextMessageSuccess().contains("Successfully Deleted"));

        usersAdminPage.enterUserName(prop.getProperty("userNameAddUser"));
        usersAdminPage.clickBtnSearch();
        Assert.assertFalse(usersAdminPage.isUsernameDisplayedInResult("Tuanlinh"));
    }

    @AfterMethod
    public void tearDown() {
       closeBrowser(driver);
    }


}
