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

public class AdminPayGrades extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    AdminPage adminPage;
    PayGradesPage payGradesPage;
    AddPayGradesPage addPayGradesPage;
    EditPayGradesPage editPayGrades;
    AddCurrencyPage addCurrencyPage;
    EditCurrencyPage editCurrencyPage;
    DeletePopup deletePopup;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        adminPage = new AdminPage(driver);
        addPayGradesPage = new AddPayGradesPage(driver);
        editPayGrades = new EditPayGradesPage(driver);
        addPayGradesPage = new AddPayGradesPage(driver);
        editCurrencyPage = new EditCurrencyPage(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        leftOption.clickAdminOption();
        adminPage.clickDropdownJob();
        payGradesPage = adminPage.clickPayGradesOption();
    }

    @Test(priority = 1)
    public void checkDisplayPayGrades(){
        String titlePayGrades = "Pay Grades";
        Assert.assertEquals(payGradesPage.getTextTitlePayGrades(), titlePayGrades);
        Assert.assertTrue(payGradesPage.displayTitlePayGrades());
        Assert.assertTrue(payGradesPage.displayBtnAdd());
        Assert.assertTrue(payGradesPage.displayTable());
        Assert.assertTrue(payGradesPage.getSizeRows() > 0, "There should be at least one job title row");
    }

    @Test(priority = 2)
    public void checkDisplayAddPayGrades(){
        String titleAddPayGrade = "Add Pay Grade";
        addPayGradesPage = payGradesPage.clickBtnAdd();
        Assert.assertEquals(addPayGradesPage.getTextTitleAddPayGrades(), titleAddPayGrade);
        Assert.assertTrue(addPayGradesPage.displayTitleAddPayGrades());
        Assert.assertTrue(addPayGradesPage.displayBtnCancel());
        Assert.assertTrue(addPayGradesPage.displayBtnSave());
    }

    @Test(priority = 3)
    public void checkValidateAddPayGrades(){
        addPayGradesPage = payGradesPage.clickBtnAdd();
        addPayGradesPage.clickBtnSave();
        String messageRequired = "Required";
        Assert.assertEquals(addPayGradesPage.getMessageRequiredName(), messageRequired);
    }

    @Test(priority = 4)
    public void checkValidateExceedCharacterAddPayGrades(){
        String messageExceedCharacter = "Should not exceed 50 characters";
        addPayGradesPage = payGradesPage.clickBtnAdd();
        addPayGradesPage.enterInputName(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addPayGradesPage.getMessageExceedCharacters(), messageExceedCharacter);
    }

    @Test(priority = 5)
    public void addPayGradesWhenClickBtnCancel(){
        addPayGradesPage = payGradesPage.clickBtnAdd();
        addPayGradesPage.enterInputName(prop.getProperty("payGrade"));
        payGradesPage = addPayGradesPage.clickBtnCancel();
    }

    @Test(priority = 6)
    public void addPayGradesSuccess(){
        addPayGradesPage = payGradesPage.clickBtnAdd();
        addPayGradesPage.enterInputName(prop.getProperty("payGrade"));
        editPayGrades = addPayGradesPage.clickBtnSave();
        Assert.assertFalse(addPayGradesPage.getTextTitleAddPayGrades().contains("Successfully Saved"));
    }

    @Test(priority = 7)
    public void checkPayGradesExist(){
        addPayGradesPage = payGradesPage.clickBtnAdd();
        addPayGradesPage.enterInputName(prop.getProperty("payGrade"));
        addPayGradesPage.clickBtnSave();
        String messageExist = "Already exists";
        Assert.assertEquals(addPayGradesPage.getMessageExistPayGrades(), messageExist);
    }

    @Test(priority = 8)
    public void displayEditPayGrades(){
        editPayGrades = payGradesPage.clickEditPayGrades();
        String titleEditPayGrades = "Edit Pay Grade";
        Assert.assertEquals(editPayGrades.getTitleEditPayGrade(), titleEditPayGrades);
        Assert.assertTrue(editPayGrades.displayTitleEditPayGrade());
        Assert.assertTrue(editPayGrades.displayBtnCancel());
        Assert.assertTrue(editPayGrades.displayBtnSave());
    }

    @Test(priority = 9)
    public void editPayGradesWhenClickBtnCancel(){
        editPayGrades = payGradesPage.clickEditPayGrades();
        String titleEditPayGrades = "Edit Pay Grade";
        Assert.assertEquals(editPayGrades.getTitleEditPayGrade(), titleEditPayGrades);
        payGradesPage = editPayGrades.clickBtnCancel();
    }

    @Test(priority = 10)
    public void displayEditPayGradesCheckCurrencies(){
        String textTitleCurrencies = "Currencies";
        String textNoRecordsFound = "No Records Found";
        editPayGrades = payGradesPage.clickEditPayGrades();
        Assert.assertEquals(editPayGrades.getTextTitleCurrencies(), textTitleCurrencies);
        Assert.assertTrue(editPayGrades.displayTitleCurrencies());
        Assert.assertTrue(editPayGrades.displayBtnAddCurrencies());
        Assert.assertTrue(editPayGrades.displayTable());
        if (editPayGrades.getSizeRows() == 0){
            Assert.assertEquals(editPayGrades.getTextNoRecordsFound(), textNoRecordsFound);
        }else {
            Assert.assertTrue(editPayGrades.getSizeRows() > 0);
        }
    }

    @Test(priority = 11)
    public void displayAddCurrentWhenEditPayGrades(){
        String textAddCurrency = "Add Currency";
        editPayGrades = payGradesPage.clickEditPayGrades();
        addCurrencyPage = editPayGrades.clickBtnAddCurrencies();

        Assert.assertEquals(addCurrencyPage.getTextTitleAddCurrency(), textAddCurrency);
        Assert.assertTrue(addCurrencyPage.displayTitleAddCurrency());
        Assert.assertTrue(addCurrencyPage.displayBtnCancel());
        Assert.assertTrue(addCurrencyPage.displayBtnSave());
    }

    @Test(priority = 12)
    public void checkRequiredAddCurrentWhenEditPayGrades(){
        String textRequired = "Required";
        editPayGrades = payGradesPage.clickEditPayGrades();
        addCurrencyPage = editPayGrades.clickBtnAddCurrencies();
        addCurrencyPage.clickBtnSave();
        Assert.assertEquals(addCurrencyPage.getTextMessageRequired(), textRequired);
    }

    @Test(priority = 13)
    public void checkShouldBeNumberAddCurrentWhenEditPayGrades(){
        String textShouldBeNumber = "Should be a valid number (xxx.xx)";
        editPayGrades = payGradesPage.clickEditPayGrades();
        addCurrencyPage = editPayGrades.clickBtnAddCurrencies();
        addCurrencyPage.enterInputMinimumSalary("ddd");
        addCurrencyPage.enterInputMaximumSalary("ddd");
        Assert.assertEquals(addCurrencyPage.getTextMessageShouldBeNumberMinimumSalary(), textShouldBeNumber);
        Assert.assertEquals(addCurrencyPage.getTextMessageShouldBeNumberMaximumSalary(), textShouldBeNumber);
    }

    @Test(priority = 14)
    public void checkMinimumSalaryEqualsMaximumSalaryAddCurrentWhenEditPayGrades(){
        String textShouldBeHigherThan = "Should be higher than Minimum Salary";
        editPayGrades = payGradesPage.clickEditPayGrades();
        addCurrencyPage = editPayGrades.clickBtnAddCurrencies();
        addCurrencyPage.enterInputMinimumSalary(String.valueOf(2));
        addCurrencyPage.enterInputMaximumSalary(String.valueOf(2));
        Assert.assertEquals(addCurrencyPage.getTextMessageHigherThan(), textShouldBeHigherThan);
    }

    @Test(priority = 15)
    public void checkMinimumSalaryBiggerMaximumSalaryAddCurrentWhenEditPayGrades(){
        String textShouldBeHigherThan = "Should be higher than Minimum Salary";
        editPayGrades = payGradesPage.clickEditPayGrades();
        addCurrencyPage = editPayGrades.clickBtnAddCurrencies();
        addCurrencyPage.enterInputMinimumSalary(String.valueOf(2));
        addCurrencyPage.enterInputMaximumSalary(String.valueOf(1));
        Assert.assertEquals(addCurrencyPage.getTextMessageHigherThan(), textShouldBeHigherThan);
    }

    @Test(priority = 16)
    public void checkMinimumSalaryAndMaximumSalaryBiggerOneBillionAddCurrentWhenEditPayGrades(){
        String textShouldBeLessThan = "Should be less than 1,000,000,000";
        editPayGrades = payGradesPage.clickEditPayGrades();
        addCurrencyPage = editPayGrades.clickBtnAddCurrencies();
        addCurrencyPage.enterInputMinimumSalary(String.valueOf(1_500_000_000L));
        addCurrencyPage.enterInputMaximumSalary(String.valueOf(1_600_000_000L));
        Assert.assertEquals(addCurrencyPage.getTextMessageShouldBeLessThanMinimumSalary(), textShouldBeLessThan);
        Assert.assertEquals(addCurrencyPage.getTextMessageShouldBeLessThanMaximumSalary(), textShouldBeLessThan);
    }

    @Test(priority = 17)
    public void clickBtnCancelAddCurrentWhenEditPayGrades(){
        editPayGrades = payGradesPage.clickEditPayGrades();
        addCurrencyPage = editPayGrades.clickBtnAddCurrencies();
        editPayGrades = addCurrencyPage.clickBtnCancel();
    }

    @Test(priority = 18)
    public void addCurrentSuccessWhenEditPayGrades(){
        editPayGrades = payGradesPage.clickEditPayGrades();
        addCurrencyPage = editPayGrades.clickBtnAddCurrencies();
        Assert.assertTrue(addCurrencyPage.displayTitleAddCurrency());
        addCurrencyPage.selectOptionCurrency(prop.getProperty("optionCurrent"));
        addCurrencyPage.enterInputMinimumSalary(String.valueOf(20000));
        addCurrencyPage.enterInputMaximumSalary(String.valueOf(30000));
        editPayGrades = addCurrencyPage.clickBtnSave();
        Assert.assertTrue(editPayGrades.getTextMessageSuccess().contains("Successfully Saved"));
        Assert.assertTrue(editPayGrades.getSizeRows() > 0);
    }

    @Test(priority = 19)
    public void displayEditCurrentWhenEditPayGrades(){
        String titleEditCurrency = "Edit Currency";
        editPayGrades = payGradesPage.clickEditPayGrades();
        editCurrencyPage = editPayGrades.clickEditCurrent();
        Assert.assertEquals(editCurrencyPage.getTextTitleEditCurrent(), titleEditCurrency);
        Assert.assertTrue(editCurrencyPage.displayTitleEditCurrent());
        Assert.assertFalse(editCurrencyPage.disableInputCurrency());
        Assert.assertTrue(editCurrencyPage.displayBtnCancel());
        Assert.assertTrue(editCurrencyPage.displayBtnSave());
    }

    @Test(priority = 20)
    public void clickBtnCancelEditCurrentWhenEditPayGrades(){
        editPayGrades = payGradesPage.clickEditPayGrades();
        editCurrencyPage = editPayGrades.clickEditCurrent();
        editPayGrades = editCurrencyPage.clickBtnCancel();
    }

    @Test(priority = 21)
    public void editCurrentSuccessWhenEditPayGrades(){
        editPayGrades = payGradesPage.clickEditPayGrades();
        editCurrencyPage = editPayGrades.clickEditCurrent();
        Assert.assertFalse(editCurrencyPage.disableInputCurrency());
        editCurrencyPage.enterMinimumSalary(1111);
        editCurrencyPage.enterMaximumSalary(2222);
        editPayGrades = editCurrencyPage.clickBtnSave();
        Assert.assertTrue(editPayGrades.getTextMessageSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 22)
    public void displayDeleteCurrentWhenEditPayGrades(){
        String textAreYouSure = "Are you Sure?";
        String textContentDelete = "The selected record will be permanently deleted. Are you sure you want to continue?";
        editPayGrades = payGradesPage.clickEditPayGrades();
        deletePopup =  editPayGrades.clickDeleteCurrent();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), textAreYouSure);
        Assert.assertEquals(deletePopup.getTextContentDelete(), textContentDelete);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 23)
    public void clickOnNoCancelDeleteCurrentWhenEditPayGrades(){
        editPayGrades = payGradesPage.clickEditPayGrades();
        deletePopup = editPayGrades.clickDeleteCurrent();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 24)
    public void deleteCurrentSuccessWhenEditPayGrades(){
        String textNoRecordsFound = "No Records Found";
        editPayGrades = payGradesPage.clickEditPayGrades();
        deletePopup = editPayGrades.clickDeleteCurrent();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(editPayGrades.getTextMessageSuccess().contains("Successfully Deleted"));
        if (editPayGrades.getSizeRows() == 0){
            Assert.assertEquals(editPayGrades.getTextNoRecordsFound(), textNoRecordsFound);
        }else {
            Assert.assertTrue(editPayGrades.getSizeRows() > 0);
        }
    }

    @Test(priority = 25)
    public void editSuccessPayGrades(){
        editPayGrades = payGradesPage.clickEditPayGrades();
        String titleEditPayGrades = "Edit Pay Grade";
        Assert.assertEquals(editPayGrades.getTitleEditPayGrade(), titleEditPayGrades);

        editPayGrades.enterInputName(prop.getProperty("payGradeEdit"));
        editPayGrades.clickBtnSave();
        Assert.assertTrue(editPayGrades.getTextMessageSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 26)
    public void deletePayGradesCheckDisplayDocument(){
        String titleAreYouSure = "Are you Sure?";
        String contentDocument = "The selected record will be permanently deleted. Are you sure you want to continue?";
        deletePopup = payGradesPage.clickDeletePayGrades();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), titleAreYouSure);
        Assert.assertEquals(deletePopup.getTextContentDelete(), contentDocument);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 27)
    public void deletePayGradesWhenClickBtnNoCancel(){
        deletePopup = payGradesPage.clickDeletePayGrades();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 28)
    public void deleteSuccessPayGrades(){
        deletePopup = payGradesPage.clickDeletePayGrades();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(payGradesPage.getTextMessageSuccess().contains("Successfully Deleted"));
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
