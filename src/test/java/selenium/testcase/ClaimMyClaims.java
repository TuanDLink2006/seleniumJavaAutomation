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
public class ClaimMyClaims extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    ClaimPage claimPage;
    MyClaimsPage myClaimsPage;
    CreateClaimRequestPage createClaimRequestPage;
    SubmitClaimPage submitClaimPage;
    AddExpensesPopUp addExpensesPopUp;
    EditExpensesPopUp editExpensesPopUp;
    AddAttachmentPage addAttachmentPopUp;
    DeletePopup deletePopup;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        claimPage = new ClaimPage(driver);
        myClaimsPage = new MyClaimsPage(driver);
        createClaimRequestPage = new CreateClaimRequestPage(driver);
        submitClaimPage = new SubmitClaimPage(driver);
        addExpensesPopUp = new AddExpensesPopUp(driver);
        editExpensesPopUp = new EditExpensesPopUp(driver);
        addAttachmentPopUp = new AddAttachmentPage(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        claimPage = leftOption.clickClaimOption();
        myClaimsPage = claimPage.selectMyClaim();
    }

    @Test(priority = 1)
    public void checkDisplayMyClaimPage(){
        String titleMyClaim = "My Claims";
        Assert.assertTrue(myClaimsPage.displayTitleMyClaim());
        Assert.assertEquals(myClaimsPage.getTextTitleMyClaim(), titleMyClaim);
        Assert.assertTrue(myClaimsPage.displayBtnReset());
        Assert.assertTrue(myClaimsPage.displayBtnSearch());
        Assert.assertTrue(myClaimsPage.displayBtnSubmitClaim());
        Assert.assertTrue(myClaimsPage.displayTable());
        Assert.assertTrue(myClaimsPage.getSizeRows() > 0);
    }

    @Test(priority = 2)
    public void checkDisplayCreateClaimRequest(){
        String titleCreateClaimRequest = "Create Claim Request";
        createClaimRequestPage = myClaimsPage.clickBtnSubmitClaim();
        Assert.assertTrue(createClaimRequestPage.displayTitleCreateClaimRequest());
        Assert.assertEquals(createClaimRequestPage.getTextTitleCreateClaimRequest(), titleCreateClaimRequest);
        Assert.assertTrue(createClaimRequestPage.displayBtnCancel());
        Assert.assertTrue(createClaimRequestPage.displayBtnCreate());
    }

    @Test(priority = 3)
    public void checkRequiredCreateClaimRequest(){
        String messageRequired = "Required";
        createClaimRequestPage = myClaimsPage.clickBtnSubmitClaim();
        createClaimRequestPage.clickBtnCreate();
        Assert.assertEquals(createClaimRequestPage.getTextMessageRequiredEvent(), messageRequired);
        Assert.assertEquals(createClaimRequestPage.getTextMessageRequiredCurrency(), messageRequired);
    }

    @Test(priority = 4)
    public void checkExceedCharacterCreateClaimRequest(){
        String messageExceedCharacter = "Should not exceed 1000 characters";
        createClaimRequestPage = myClaimsPage.clickBtnSubmitClaim();
        createClaimRequestPage.enterRemarks(prop.getProperty("exceed1000Character"));
        Assert.assertEquals(createClaimRequestPage.getTextMessageExceed1000Characters(), messageExceedCharacter);
    }

    @Test(priority = 5)
    public void createClaimRequestWhenClickBtnCancel(){
        createClaimRequestPage = myClaimsPage.clickBtnSubmitClaim();
        myClaimsPage = createClaimRequestPage.clickBtnCancel();
    }

    @Test(priority = 6)
    public void createClaimRequestSuccessAndAddOptions(){
        createClaimRequestPage = myClaimsPage.clickBtnSubmitClaim();
        createClaimRequestPage.selectEventOptions(prop.getProperty("eventOptionAccommodation"));
        createClaimRequestPage.selectCurrencyOption(prop.getProperty("currencyAlbanianLek"));
        createClaimRequestPage.enterRemarks("textRemark");
        submitClaimPage = createClaimRequestPage.clickBtnCreate();
        Assert.assertTrue(createClaimRequestPage.getTextMessageSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 7)
    public void viewDetailClaims() throws InterruptedException {
        String messageSubmitClaim = "Submit Claim";
        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        Assert.assertTrue(submitClaimPage.displayTitleSubmitClaim());
        Assert.assertEquals(submitClaimPage.getTextSubmitClaim(), messageSubmitClaim);
        Assert.assertTrue(submitClaimPage.disableReferenceId());
        Assert.assertTrue(submitClaimPage.disableEvent());
        Assert.assertTrue(submitClaimPage.disableStatus());
        Assert.assertTrue(submitClaimPage.disableCurrency());
        Assert.assertTrue(submitClaimPage.disableRemarks());
    }

    @Test(priority = 8)
    public void viewDetailClaimCheckRequiredWhenAddExpenses() throws InterruptedException {
        String titleExpenses = "Expenses";
        String titleAddExpenses = "Add Expense";
        String messageRequired = "Required";

        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        Assert.assertTrue(submitClaimPage.displayTitleExpenses());
        Assert.assertEquals(submitClaimPage.getTextTitleExpenses(), titleExpenses);

        addExpensesPopUp = submitClaimPage.clickBtnAddExpenses();
        Assert.assertTrue(addExpensesPopUp.displayAddDocumentExpenses());
        Assert.assertTrue(addExpensesPopUp.displayTitleAddExpenses());
        Assert.assertEquals(addExpensesPopUp.getTextTitleAddExpenses(), titleAddExpenses);

        addExpensesPopUp.clickBtnSaveAddExpenses();
        Assert.assertEquals(addExpensesPopUp.getTextMessageRequiredExpenseType(), messageRequired);
        Assert.assertEquals(addExpensesPopUp.getTextMessageRequiredDate(), messageRequired);
        Assert.assertEquals(addExpensesPopUp.getTextMessageRequiredAmount(), messageRequired);
    }

    @Test(priority = 9)
    public void viewDetailClaimCheckAmountWhenAddExpenses() throws InterruptedException {
        String titleExpenses = "Expenses";
        String titleAddExpenses = "Add Expense";
        String messageShouldBe = "Should be a valid number (xxx.xx)";

        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        Assert.assertTrue(submitClaimPage.displayTitleExpenses());
        Assert.assertEquals(submitClaimPage.getTextTitleExpenses(), titleExpenses);

        addExpensesPopUp = submitClaimPage.clickBtnAddExpenses();
        Assert.assertTrue(addExpensesPopUp.displayAddDocumentExpenses());
        Assert.assertTrue(addExpensesPopUp.displayTitleAddExpenses());
        Assert.assertEquals(addExpensesPopUp.getTextTitleAddExpenses(), titleAddExpenses);

        addExpensesPopUp.enterAmount("ddd");
        Assert.assertEquals(addExpensesPopUp.getTextMessageShouldBe(), messageShouldBe);
    }

    @Test(priority = 10)
    public void viewDetailClaimCheckShouldBeLessThaNWhenAddExpenses() throws InterruptedException {
        String titleExpenses = "Expenses";
        String titleAddExpenses = "Add Expense";
        String messageShouldBeLessThan = "Should be less than 10,000,000,000";

        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        Assert.assertTrue(submitClaimPage.displayTitleExpenses());
        Assert.assertEquals(submitClaimPage.getTextTitleExpenses(), titleExpenses);

        addExpensesPopUp = submitClaimPage.clickBtnAddExpenses();
        Assert.assertTrue(addExpensesPopUp.displayAddDocumentExpenses());
        Assert.assertTrue(addExpensesPopUp.displayTitleAddExpenses());
        Assert.assertEquals(addExpensesPopUp.getTextTitleAddExpenses(), titleAddExpenses);

        addExpensesPopUp.enterAmount("11111111111111111111");
        Assert.assertEquals(addExpensesPopUp.getTextMessageShouldBeLessThan(), messageShouldBeLessThan);
    }

    @Test(priority = 11)
    public void viewDetailClaimAndAddExpensesSuccess() throws InterruptedException {
        String titleExpenses = "Expenses";
        String titleAddExpenses = "Add Expense";

        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        Assert.assertTrue(submitClaimPage.displayTitleExpenses());
        Assert.assertEquals(submitClaimPage.getTextTitleExpenses(), titleExpenses);

        addExpensesPopUp = submitClaimPage.clickBtnAddExpenses();
        Assert.assertTrue(addExpensesPopUp.displayAddDocumentExpenses());
        Assert.assertTrue(addExpensesPopUp.displayTitleAddExpenses());
        Assert.assertEquals(addExpensesPopUp.getTextTitleAddExpenses(), titleAddExpenses);

        addExpensesPopUp.selectExpensesTypeOption(prop.getProperty("expenseTypePlannedSurgery"));
        addExpensesPopUp.selectDate(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        addExpensesPopUp.enterAmount("111");
        addExpensesPopUp.clickBtnSaveAddExpenses();
        Assert.assertTrue(addExpensesPopUp.getTextAddExpenseSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 12)
    public void editExpenses() throws InterruptedException {
        String titleExpenses = "Expenses";
        String messageEditExpenses = "Edit Expense";

        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        Assert.assertTrue(submitClaimPage.displayTitleExpenses());
        Assert.assertEquals(submitClaimPage.getTextTitleExpenses(), titleExpenses);

        editExpensesPopUp = submitClaimPage.clickEditExpensesType();
        Assert.assertTrue(editExpensesPopUp.displayEditDocumentExpenses());
        Assert.assertTrue(editExpensesPopUp.displayTitleEditExpenses());
        Assert.assertEquals(editExpensesPopUp.getTextTitleEditExpenses(), messageEditExpenses);

        editExpensesPopUp.selectExpensesTypeOption(prop.getProperty("expenseTypeTransport"));
        editExpensesPopUp.selectDate(prop.getProperty("dayEdit"), prop.getProperty("monthEdit"), prop.getProperty("yearEdit"));
        editExpensesPopUp.enterAmount("2928");
        editExpensesPopUp.clickBtnSaveEditExpenses();
        Assert.assertTrue(editExpensesPopUp.getTextEditExpenseSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 13)
    public void deleteExpensesWhenClickBtnCancel() throws InterruptedException {

        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();

        deletePopup = submitClaimPage.clickDeleteExpensesType();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 14)
    public void deleteExpensesSuccess() throws InterruptedException {
        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();

        deletePopup = submitClaimPage.clickDeleteExpensesType();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(submitClaimPage.getTextDeletedSuccess().contains("Successfully Deleted"));
    }

    @Test(priority = 15)
    public void viewDetailClaimCheckRequiredWhenAddAttachments() throws InterruptedException {
        String titleAttachments = "Attachments";
        String titleAddAttachments = "Add Attachment";
        String messageAccept = "Accepts up to 1MB";
        String messageRequired = "Required";

        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        Assert.assertTrue(submitClaimPage.displayTitleAttachments());
        Assert.assertEquals(submitClaimPage.getTextTitleAttachments(), titleAttachments);

        addAttachmentPopUp = submitClaimPage.clickBtnAddAttachments();
        Assert.assertTrue(addExpensesPopUp.displayAddDocumentExpenses());
        Assert.assertEquals(addAttachmentPopUp.getTextTitleAddAttachment(), titleAddAttachments);
        Assert.assertEquals(addAttachmentPopUp.getTextAcceptTo(), messageAccept);

        addAttachmentPopUp.clickBtnSaveAddAttachment();
        Assert.assertEquals(addAttachmentPopUp.getTextMessageRequired(), messageRequired);
    }

    @Test(priority = 16)
    public void viewDetailClaimCheckAttachmentSizeExceededWhenAddAttachments() throws InterruptedException {
        String titleAttachments = "Attachments";
        String titleAddAttachments = "Add Attachment";
        String messageAccept = "Accepts up to 1MB";
        String messageAttachmentSizeExceeded = "Attachment Size Exceeded";

        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        Assert.assertTrue(submitClaimPage.displayTitleAttachments());
        Assert.assertEquals(submitClaimPage.getTextTitleAttachments(), titleAttachments);

        addAttachmentPopUp = submitClaimPage.clickBtnAddAttachments();
        Assert.assertTrue(addExpensesPopUp.displayAddDocumentExpenses());
        Assert.assertEquals(addAttachmentPopUp.getTextTitleAddAttachment(), titleAddAttachments);
        Assert.assertEquals(addAttachmentPopUp.getTextAcceptTo(), messageAccept);

        addAttachmentPopUp.selectFile(prop.getProperty("fileAttachmentSizeExceeded"));
        Assert.assertEquals(addAttachmentPopUp.getTextMessageAttachmentSizeExceeded(), messageAttachmentSizeExceeded);
    }

    @Test(priority = 17)
    public void addAttachmentsWhenClickBtnCancel() throws InterruptedException {
        String titleAttachments = "Attachments";
        String messageAccept = "Accepts up to 1MB";
        String titleAddAttachments = "Add Attachment";

        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        Assert.assertTrue(submitClaimPage.displayTitleAttachments());
        Assert.assertEquals(submitClaimPage.getTextTitleAttachments(), titleAttachments);

        addAttachmentPopUp = submitClaimPage.clickBtnAddAttachments();
        Assert.assertTrue(addExpensesPopUp.displayAddDocumentExpenses());
        Assert.assertEquals(addAttachmentPopUp.getTextTitleAddAttachment(), titleAddAttachments);
        Assert.assertEquals(addAttachmentPopUp.getTextAcceptTo(), messageAccept);

        addAttachmentPopUp.selectFile(prop.getProperty("validateFileType"));
        addAttachmentPopUp.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPopUp.clickBtnCancelAddAttachment();
    }

    @Test(priority = 18)
    public void addAttachmentsSuccess() throws InterruptedException {
        String titleAttachments = "Attachments";
        String messageAccept = "Accepts up to 1MB";
        String titleAddAttachments = "Add Attachment";

        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        Assert.assertTrue(submitClaimPage.displayTitleAttachments());
        Assert.assertEquals(submitClaimPage.getTextTitleAttachments(), titleAttachments);

        addAttachmentPopUp = submitClaimPage.clickBtnAddAttachments();
        Assert.assertTrue(addExpensesPopUp.displayAddDocumentExpenses());
        Assert.assertEquals(addAttachmentPopUp.getTextTitleAddAttachment(), titleAddAttachments);
        Assert.assertEquals(addAttachmentPopUp.getTextAcceptTo(), messageAccept);

        addAttachmentPopUp.selectFile(prop.getProperty("validateFileType"));
        addAttachmentPopUp.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPopUp.clickBtnSaveAddAttachment();
        Assert.assertTrue(addAttachmentPopUp.getTextAttachmentSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 19)
    public void deleteClickCheckboxDeleteAndChooseNoCancel() throws InterruptedException {
        String titleAttachments = "Attachments";
        String titleDelete = "Are you Sure?";
        String contentDelete = "The selected record will be permanently deleted. Are you sure you want to continue?";

        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        Assert.assertTrue(submitClaimPage.displayTitleAttachments());
        Assert.assertEquals(submitClaimPage.getTextTitleAttachments(), titleAttachments);

        submitClaimPage.clickCheckboxDelete();
        deletePopup = submitClaimPage.clickBtnDeleteSelected();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), titleDelete);
        Assert.assertEquals(deletePopup.getTextContentDelete(), contentDelete);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 20)
    public void deleteClickCheckboxDeleteSuccess() throws InterruptedException {
        String titleAttachments = "Attachments";
        String titleDelete = "Are you Sure?";
        String contentDelete = "The selected record will be permanently deleted. Are you sure you want to continue?";

        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        Assert.assertTrue(submitClaimPage.displayTitleAttachments());
        Assert.assertEquals(submitClaimPage.getTextTitleAttachments(), titleAttachments);

        submitClaimPage.clickCheckboxDelete();
        deletePopup = submitClaimPage.clickBtnDeleteSelected();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), titleDelete);
        Assert.assertEquals(deletePopup.getTextContentDelete(), contentDelete);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
        deletePopup.clickBtnYesDelete();
    }

    @Test(priority = 21)
    public void clickBtnBack() throws InterruptedException {
        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        myClaimsPage = submitClaimPage.clickBtnBack();
    }

    @Test(priority = 22)
    public void clickBtnSubmit() throws InterruptedException {
        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        submitClaimPage.clickBtnSubmit();
        Assert.assertTrue(submitClaimPage.getTextSavedSuccess().contains("Successfully Saved"));
        Assert.assertFalse(submitClaimPage.isBtnSubmitDisappeared(), "Submit button không còn hiển thị");
    }

    @Test(priority = 23)
    public void clickBtnCancel() throws InterruptedException {
        myClaimsPage.enterInputReferenceId(prop.getProperty("referenceId"), prop.getProperty("referenceId"));
        myClaimsPage.clickBtnSearch();
        submitClaimPage = myClaimsPage.clickBtnViewDetails();
        submitClaimPage.clickBtnCancel();
        Assert.assertTrue(submitClaimPage.getTextSavedSuccess().contains("Successfully Saved"));
        Assert.assertFalse(submitClaimPage.isBtnCancelDisappeared(), "Cancel button không còn hiển thị");
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
