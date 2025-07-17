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
public class ClaimExpenseTypes extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    ClaimPage claimPage;
    ExpenseTypesPage expenseTypesPage;
    AddExpenseTypePage addExpenseTypePage;
    EditExpenseTypePage editExpenseTypePage;
    DeletePopup deletePopup;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        claimPage = new ClaimPage(driver);
        expenseTypesPage = new ExpenseTypesPage(driver);
        addExpenseTypePage = new AddExpenseTypePage(driver);
        editExpenseTypePage = new EditExpenseTypePage(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        claimPage = leftOption.clickClaimOption();
        expenseTypesPage = claimPage.selectExpenseTypeOption();
    }

    @Test(priority = 1)
    public void checkDisplayExpenseTypesPage(){
        String textExpenseTypes = "Expense Types";
        Assert.assertEquals(expenseTypesPage.getTextTitleExpenseTypes(), textExpenseTypes);
        Assert.assertTrue(expenseTypesPage.displayTitleExpenseTypes());
        Assert.assertTrue(expenseTypesPage.displayBtnReset());
        Assert.assertTrue(expenseTypesPage.displayBtnSearch());
        Assert.assertTrue(expenseTypesPage.displayBtnAdd());
        Assert.assertTrue(expenseTypesPage.displayTable());
        Assert.assertTrue(expenseTypesPage.getSizeRows() > 0);
    }

    @Test(priority = 2)
    public void checkDisplayAddExpenseType(){
        String textTitleAddExpenseType = "Add Expense Type";
        addExpenseTypePage = expenseTypesPage.clickBtnAdd();
        Assert.assertEquals(addExpenseTypePage.getTextTitleAddExpenseType(), textTitleAddExpenseType);
        Assert.assertTrue(addExpenseTypePage.displayTitleAddExpenseType());
        Assert.assertTrue(addExpenseTypePage.displayBtnCancel());
        Assert.assertTrue(addExpenseTypePage.displayBtnSave());
    }

    @Test(priority = 3)
    public void checkValidateAddExpense(){
        String messageRequired = "Required";
        addExpenseTypePage = expenseTypesPage.clickBtnAdd();
        addExpenseTypePage.clickBtnSave();
        Assert.assertEquals(addExpenseTypePage.getTextMessageRequired(), messageRequired);
    }

    @Test(priority = 4)
    public void checkMessageErrorAddExpenseSuccess(){
        addExpenseTypePage = expenseTypesPage.clickBtnAdd();
        addExpenseTypePage.enterInputExpenseName(prop.getProperty("exceed1000Character"));
        Assert.assertTrue(addExpenseTypePage.getTextMessageError().contains("Invalid Parameter"));
    }

    @Test(priority = 5)
    public void checkValidateExceedCharactersAddExpenseSuccess(){
        String messageExceed100Characters = "Should not exceed 100 characters";
        String messageExceed1000Characters = "Should not exceed 1000 characters";
        addExpenseTypePage = expenseTypesPage.clickBtnAdd();
        addExpenseTypePage.enterInputExpenseName(prop.getProperty("exceedCharacter"));
        addExpenseTypePage.enterDescription(prop.getProperty("exceed1000Character"));
        Assert.assertEquals(addExpenseTypePage.getTextMessageExceed100Characters(), messageExceed100Characters);
        Assert.assertEquals(addExpenseTypePage.getTextMessageExceed1000Characters(), messageExceed1000Characters);
    }

    @Test(priority = 6)
    public void addExpenseSuccessWhenClickBtnCancel(){
        addExpenseTypePage = expenseTypesPage.clickBtnAdd();
        expenseTypesPage = addExpenseTypePage.clickBtnCancel();
    }

    @Test(priority = 7)
    public void addExpenseSuccess(){
        addExpenseTypePage = expenseTypesPage.clickBtnAdd();
        addExpenseTypePage.enterInputExpenseName(prop.getProperty("expenseType"));
        expenseTypesPage = addExpenseTypePage.clickBtnSave();
        Assert.assertTrue(addExpenseTypePage.getTextAddExpenseSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 8)
    public void addExpenseExist(){
        String messageExist = "Already exists";
        addExpenseTypePage = expenseTypesPage.clickBtnAdd();
        addExpenseTypePage.enterInputExpenseName(prop.getProperty("expenseType"));
        Assert.assertEquals(addExpenseTypePage.getTextMessageExist(), messageExist);
    }

    @Test(priority = 9)
    public void checkSearchNoRecord() throws InterruptedException {
        expenseTypesPage.enterInputEventName(prop.getProperty("expenseType"), prop.getProperty("expenseType"));
        expenseTypesPage.selectStatusOption(prop.getProperty("statusInactive"));
        expenseTypesPage.clickBtnSearch();
        Assert.assertFalse(expenseTypesPage.getTextMessageNoRecord().contains("No Records Found"));
    }

    @Test(priority = 10)
    public void checkSearchRecord() throws InterruptedException {
        expenseTypesPage.enterInputEventName(prop.getProperty("expenseType"), prop.getProperty("expenseType"));
        expenseTypesPage.selectStatusOption(prop.getProperty("statusActive"));
        expenseTypesPage.clickBtnSearch();
        Assert.assertTrue(expenseTypesPage.getSizeRows() > 0);
    }

    @Test(priority = 11)
    public void checkDisplayEditExpenseTypeWhenSearch() throws InterruptedException {
        expenseTypesPage.enterInputEventName(prop.getProperty("expenseType"), prop.getProperty("expenseType"));
        expenseTypesPage.selectStatusOption(prop.getProperty("statusActive"));
        expenseTypesPage.clickBtnSearch();
        Assert.assertTrue(expenseTypesPage.getSizeRows() > 0);
        editExpenseTypePage = expenseTypesPage.clickEditExpenseWhenSearch();
        Assert.assertTrue(editExpenseTypePage.displayTitleEditExpenseType());
        Assert.assertFalse(editExpenseTypePage.isDisableInputExpenseTypeName());
        Assert.assertFalse(editExpenseTypePage.isSelectedCheckboxActive());
        Assert.assertTrue(editExpenseTypePage.displayBtnCancel());
        Assert.assertTrue(editExpenseTypePage.displayBtnSave());
    }

    @Test(priority = 12)
    public void checkEditWhenClickBtnCancel() throws InterruptedException {
        expenseTypesPage.enterInputEventName(prop.getProperty("expenseType"), prop.getProperty("expenseType"));
        expenseTypesPage.selectStatusOption(prop.getProperty("statusActive"));
        expenseTypesPage.clickBtnSearch();
        Assert.assertTrue(expenseTypesPage.getSizeRows() > 0);
        editExpenseTypePage = expenseTypesPage.clickEditExpenseWhenSearch();
        editExpenseTypePage.clickOffCheckboxActive();
        expenseTypesPage = editExpenseTypePage.clickBtnCancel();
    }

    @Test(priority = 13)
    public void checkEditExpenseSuccessWhenSearch() throws InterruptedException {
        expenseTypesPage.enterInputEventName(prop.getProperty("expenseType"), prop.getProperty("expenseType"));
        expenseTypesPage.selectStatusOption(prop.getProperty("statusActive"));
        expenseTypesPage.clickBtnSearch();
        Assert.assertTrue(expenseTypesPage.getSizeRows() > 0);
        editExpenseTypePage = expenseTypesPage.clickEditExpenseWhenSearch();
        editExpenseTypePage.clickOffCheckboxActive();
        expenseTypesPage = editExpenseTypePage.clickBtnSave();
        Assert.assertTrue(editExpenseTypePage.getTextEditEventSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 14)
    public void checkEditExpenseSuccessWhenNoSearch(){
        editExpenseTypePage = expenseTypesPage.clickEditExpense();
        editExpenseTypePage.clickOnCheckboxActive();
        expenseTypesPage = editExpenseTypePage.clickBtnSave();
        Assert.assertTrue(editExpenseTypePage.getTextEditEventSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 15)
    public void checkDisplayDeleteExpense(){
        String textTitleDelete = "Are you Sure?";
        String textContentDelete = "The selected record will be permanently deleted. Are you sure you want to continue?";
        deletePopup = expenseTypesPage.clickDeleteExpense();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), textTitleDelete);
        Assert.assertEquals(deletePopup.getTextContentDelete(), textContentDelete);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 16)
    public void checkDeleteExpenseClickBtnNoCancel(){
        deletePopup = expenseTypesPage.clickDeleteExpense();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 17)
    public void checkDeleteExpenseClickBtnYesDelete(){
        deletePopup = expenseTypesPage.clickDeleteExpense();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(expenseTypesPage.getTextDeleteSuccess().contains("Successfully Deleted"));
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
