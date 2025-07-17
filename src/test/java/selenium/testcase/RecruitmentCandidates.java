package selenium.testcase;

import listeners.MyListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import selenium.base.Base;
import utils.CommonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Listeners(MyListeners.class)
public class RecruitmentCandidates extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    RecruitmentPage recruitmentPage;
    CandidatesPage candidatesPage;
    AddCandidatePage addCandidatePage;
    ViewCandidatesPage viewCandidatesPage;
    RejectCandidatePage rejectCandidatePage;
    ShortlistCandidatePage shortlistCandidatePage;
    ConfirmationRequiredPopup confirmationRequiredPopup;
    DeletePopup deletePopup;

    @BeforeMethod
    public void setUp(){
        String downloadPath = "D:\\Selenium\\OrangeHRMSelenium\\download";
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("safebrowsing.enabled", true);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        recruitmentPage = new RecruitmentPage(driver);
        candidatesPage = new CandidatesPage(driver);
        addCandidatePage = new AddCandidatePage(driver);
        viewCandidatesPage = new ViewCandidatesPage(driver);
        rejectCandidatePage = new RejectCandidatePage(driver);
        shortlistCandidatePage = new ShortlistCandidatePage(driver);
        confirmationRequiredPopup = new ConfirmationRequiredPopup(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        recruitmentPage = leftOption.clickRecruitmentOption();
        candidatesPage = recruitmentPage.clickCandidates();
    }

    @Test(priority = 1)
    public void checkDisplayCandidate(){
        String titleCandidates = "Candidates";
        Assert.assertEquals(candidatesPage.getTextTitleCandidates(), titleCandidates);
        Assert.assertTrue(candidatesPage.displayTitleCandidates());
        Assert.assertTrue(candidatesPage.displayBtnReset());
        Assert.assertTrue(candidatesPage.displayBtnSearch());
    }

    @Test(priority = 2)
    public void checkDisplayAddCandidate(){
        String titleAddCandidate = "Add Candidate";
        addCandidatePage = candidatesPage.clickBtnAdd();
        Assert.assertEquals(addCandidatePage.getTextTitleAddCandidate(), titleAddCandidate);
        Assert.assertTrue(addCandidatePage.displayTitleAddCandidate());
        Assert.assertTrue(addCandidatePage.displayBtnCancel());
        Assert.assertTrue(addCandidatePage.displayBtnSave());
    }

    @Test(priority = 3)
    public void checkValidateAddCandidate(){

        String messageRequired = "Required";
        addCandidatePage = candidatesPage.clickBtnAdd();
        addCandidatePage.clickBtnSave();
        Assert.assertEquals(addCandidatePage.getMessageRequiredFirstName(), messageRequired);
        Assert.assertEquals(addCandidatePage.getMessageRequiredLastName(), messageRequired);
        Assert.assertEquals(addCandidatePage.getMessageRequiredEmail(), messageRequired);
    }

    @Test(priority = 4)
    public void checkMessageAllowsNumbersAddCandidate(){
        String messageAllowNumber = "Allows numbers and only + - / ( )";
        addCandidatePage = candidatesPage.clickBtnAdd();
        addCandidatePage.enterInputContactNumber(prop.getProperty("number"));
        Assert.assertEquals(addCandidatePage.getTextMessageAllowsNumbers(), messageAllowNumber);
    }

    @Test(priority = 5)
    public void checkShouldExceed30XCharactersAddCandidate(){
        String messageExceed30Characters = "Should not exceed 30 characters";
        addCandidatePage = candidatesPage.clickBtnAdd();
        addCandidatePage.enterInputFirstName(prop.getProperty("exceedCharacter"));
        addCandidatePage.enterInputMiddleName(prop.getProperty("exceedCharacter"));
        addCandidatePage.enterInputLastName(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addCandidatePage.getTextMessageShouldExceed30XCharactersFirstName(), messageExceed30Characters);
        Assert.assertEquals(addCandidatePage.getTextMessageShouldExceed30XCharactersMiddleName(), messageExceed30Characters);
        Assert.assertEquals(addCandidatePage.getTextMessageShouldExceed30XCharactersLastName(), messageExceed30Characters);
    }

    @Test(priority = 6)
    public void checkShouldExceed50XCharactersAddCandidate(){
        String messageExceed50Characters = "Should not exceed 50 characters";
        addCandidatePage = candidatesPage.clickBtnAdd();
        addCandidatePage.enterInputEmail(prop.getProperty("exceedCharacterEmail"));
        Assert.assertEquals(addCandidatePage.getTextMessageShouldExceed50XCharacters(), messageExceed50Characters);
    }

    @Test(priority = 7)
    public void checkShouldExceed25XCharactersAddCandidate(){
        String messageExceed25Characters = "Should not exceed 25 characters";
        addCandidatePage = candidatesPage.clickBtnAdd();
        addCandidatePage.enterInputContactNumber(prop.getProperty("exceedNumberCharacter"));
        Assert.assertEquals(addCandidatePage.getTextMessageShouldExceed25XCharacters(), messageExceed25Characters);
    }

    @Test(priority = 8)
    public void checkShouldExceed250XCharactersAddCandidate(){
        String messageExceed250Characters = "Should not exceed 250 characters";
        addCandidatePage = candidatesPage.clickBtnAdd();
        addCandidatePage.enterInputKeywords(prop.getProperty("exceedCharacter"));
        addCandidatePage.enterNotes(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addCandidatePage.getTextMessageShouldExceed250XCharactersKeywords(), messageExceed250Characters);
        Assert.assertEquals(addCandidatePage.getTextMessageShouldExceed250XCharactersNotes(), messageExceed250Characters);
    }

    @Test(priority = 9)
    public void checkFormatDateAddCandidate(){
        String messageDateFormat = "Should be a valid date in yyyy-dd-mm format";
        addCandidatePage = candidatesPage.clickBtnAdd();
        addCandidatePage.enterInputDateOfApplication(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addCandidatePage.getTextMessageFormatDatePicker(), messageDateFormat);
    }

    @Test(priority = 10)
    public void checkCurrentDateAddCandidate(){
        String messageCurrentDate = "Should be the current date or a previous date";
        addCandidatePage = candidatesPage.clickBtnAdd();
        addCandidatePage.selectDateOfApplication(prop.getProperty("dayCurrent"), prop.getProperty("monthCurrent"), prop.getProperty("yearCurrent"));
        Assert.assertEquals(addCandidatePage.getTextMessageCurrentDatePicker(), messageCurrentDate);
    }

    @Test(priority = 11)
    public void checkValidateEmailAddCandidate(){
        String messageFormatEmail = "Expected format: admin@example.com";
        addCandidatePage = candidatesPage.clickBtnAdd();
        addCandidatePage.enterInputEmail(prop.getProperty("emailNotFormat"));
        Assert.assertEquals(addCandidatePage.getMessageExpectedFormatEmail(), messageFormatEmail);
    }

    @Test(priority = 12)
    public void checkValidateFileTypeNotAllowed(){
        String messageFileType = "File type not allowed";
        addCandidatePage = candidatesPage.clickBtnAdd();
        addCandidatePage.uploadFileResume(prop.getProperty("fileTypeResume"));
        Assert.assertEquals(addCandidatePage.getMessageFileType(), messageFileType);
    }

    @Test(priority = 13)
    public void checkValidateAttachmentSizeExceeded(){
        String messageFileAttachment = "Attachment Size Exceeded";
        addCandidatePage = candidatesPage.clickBtnAdd();
        addCandidatePage.uploadFileResume(prop.getProperty("fileAttachmentSizeExceeded"));
        Assert.assertEquals(addCandidatePage.getMessageAttachment(), messageFileAttachment);
    }

    @Test(priority = 14)
    public void addCandidateWhenClickBtnCancel(){
        addCandidatePage = candidatesPage.clickBtnAdd();
        candidatesPage = addCandidatePage.clickBtnCancel();
    }

    @Test(priority = 15)
    public void addSuccessCandidate(){
        addCandidatePage = candidatesPage.clickBtnAdd();
        addCandidatePage.enterInputFirstName(prop.getProperty("firstName"));
        addCandidatePage.enterInputMiddleName(prop.getProperty("middleName"));
        addCandidatePage.enterInputLastName(prop.getProperty("lastName"));
        addCandidatePage.selectOptionVacancy(prop.getProperty("vacancy"));
        addCandidatePage.enterInputEmail(prop.getProperty("email"));
        addCandidatePage.enterInputContactNumber(prop.getProperty("mobile"));
        addCandidatePage.uploadFileResume(prop.getProperty("validateFileType"));
        addCandidatePage.clickOnCheckboxConsent();
        viewCandidatesPage = addCandidatePage.clickBtnSave();
        Assert.assertTrue(candidatesPage.getTextMessageSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 16)
    public void searchCandidateInvalid() {
        String messageInvalid = "Invalid";
        candidatesPage.enterCandidateName(prop.getProperty("exceedCharacter"));
        candidatesPage.clickBtnSearch();
        Assert.assertEquals(candidatesPage.getTextMessageInvalid(), messageInvalid);
    }

    @Test(priority = 17)
    public void searchCandidate() throws InterruptedException {
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("candidateName"));
        candidatesPage.clickBtnSearch();
        Assert.assertTrue(candidatesPage.getSizeRows() > 0);
    }

    @Test(priority = 18)
    public void displayViewCandidate() throws InterruptedException {
        String titleApplicationStage = "Application Stage";
        String titleCandidateProfile = "Candidate Profile";
        String titleCandidateHistory = "Candidate History";
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("expectedCandidateName"));
        candidatesPage.clickBtnSearch();
        viewCandidatesPage = candidatesPage.clickBtnView();

        Assert.assertEquals(viewCandidatesPage.getTextTitleApplicationStage(), titleApplicationStage);
        Assert.assertTrue(viewCandidatesPage.displayTitleApplicationStage());

        Assert.assertEquals(viewCandidatesPage.getTextTitleCandidateProfile(), titleCandidateProfile);
        Assert.assertTrue(viewCandidatesPage.displayTitleCandidateProfile());

        Assert.assertEquals(viewCandidatesPage.getTextTitleCandidateHistory(), titleCandidateHistory);
        Assert.assertTrue(viewCandidatesPage.displayTitleCandidateHistory());

        Assert.assertTrue(viewCandidatesPage.disableFirstName());
        Assert.assertTrue(viewCandidatesPage.disableMiddleName());
        Assert.assertTrue(viewCandidatesPage.disableLastName());
        Assert.assertTrue(viewCandidatesPage.disableJobVacancy());
        Assert.assertTrue(viewCandidatesPage.disableEmail());
        Assert.assertTrue(viewCandidatesPage.disableContactNumber());
        Assert.assertTrue(viewCandidatesPage.disableFileResume());
        Assert.assertTrue(viewCandidatesPage.disableKeywords());
        Assert.assertTrue(viewCandidatesPage.disableDateOfApplication());
        Assert.assertTrue(viewCandidatesPage.disableNotes());
        Assert.assertTrue(viewCandidatesPage.disableConsentToKeepData());
        Assert.assertTrue(viewCandidatesPage.displayTable());
        Assert.assertTrue(viewCandidatesPage.getSizeRows() > 0);
    }

    @Test(priority = 19)
    public void checkDisplayShortlistCandidate() throws InterruptedException {
        String titleShortlistCandidate = "Shortlist Candidate";
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("expectedCandidateName"));
        candidatesPage.clickBtnSearch();
        viewCandidatesPage = candidatesPage.clickBtnView();
        shortlistCandidatePage = viewCandidatesPage.clickBtnShortlist();
        Assert.assertEquals(shortlistCandidatePage.getTextTitleShortlistCandidate(), titleShortlistCandidate);
        Assert.assertTrue(shortlistCandidatePage.displayTitleShortlistCandidate());
        Assert.assertTrue(shortlistCandidatePage.disableInputCandidate());
        Assert.assertTrue(shortlistCandidatePage.disableInputVacancy());
        Assert.assertTrue(shortlistCandidatePage.disableInputHiringManager());
        Assert.assertTrue(shortlistCandidatePage.disableInputCurrentStatus());
        Assert.assertTrue(shortlistCandidatePage.displayBtnCancel());
        Assert.assertTrue(shortlistCandidatePage.displayBtnSave());
    }

    @Test(priority = 20)
    public void shortlistCandidateWhenClickBtnCancel() throws InterruptedException {
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("expectedCandidateName"));
        candidatesPage.clickBtnSearch();
        viewCandidatesPage = candidatesPage.clickBtnView();
        shortlistCandidatePage = viewCandidatesPage.clickBtnShortlist();
        viewCandidatesPage = shortlistCandidatePage.clickBtnCancel();
    }

    @Test(priority = 21)
    public void shortlistCandidateSuccess() throws InterruptedException {
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("expectedCandidateName"));
        candidatesPage.clickBtnSearch();
        viewCandidatesPage = candidatesPage.clickBtnView();
        shortlistCandidatePage = viewCandidatesPage.clickBtnShortlist();
        shortlistCandidatePage.enterNotes(prop.getProperty("textComments"));
        viewCandidatesPage = shortlistCandidatePage.clickBtnSave();
        Assert.assertTrue(viewCandidatesPage.getTextMessageSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 22)
    public void checkDisplayRejectCandidate() throws InterruptedException {
        String titleRejectCandidate = "Reject Candidate";
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("candidateName"));
        candidatesPage.clickBtnSearch();
        viewCandidatesPage = candidatesPage.clickBtnView();
        rejectCandidatePage = viewCandidatesPage.clickBtnReject();
        Assert.assertEquals(rejectCandidatePage.getTextTitleRejectCandidate(), titleRejectCandidate);
        Assert.assertTrue(rejectCandidatePage.displayTitleRejectCandidate());
        Assert.assertTrue(rejectCandidatePage.disableInputCandidate());
        Assert.assertTrue(rejectCandidatePage.disableInputVacancy());
        Assert.assertTrue(rejectCandidatePage.disableInputHiringManager());
        Assert.assertTrue(rejectCandidatePage.disableInputCurrentStatus());
        Assert.assertTrue(rejectCandidatePage.displayBtnCancel());
        Assert.assertTrue(rejectCandidatePage.displayBtnSave());
    }

    @Test(priority = 23)
    public void rejectCandidateWhenClickBtnCancel() throws InterruptedException {
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("candidateName"));
        candidatesPage.clickBtnSearch();
        viewCandidatesPage = candidatesPage.clickBtnView();
        rejectCandidatePage = viewCandidatesPage.clickBtnReject();
        viewCandidatesPage = rejectCandidatePage.clickBtnCancel();
    }

    @Test(priority = 24)
    public void rejectCandidateSuccess() throws InterruptedException {
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("expectedCandidateName"));
        candidatesPage.clickBtnSearch();
        viewCandidatesPage = candidatesPage.clickBtnView();
        rejectCandidatePage = viewCandidatesPage.clickBtnReject();
        rejectCandidatePage.enterNotes(prop.getProperty("textComments"));
        viewCandidatesPage = rejectCandidatePage.clickBtnSave();
        Assert.assertTrue(viewCandidatesPage.getTextMessageSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 25)
    public void editCandidateProfile() throws InterruptedException {
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("expectedCandidateName"));
        candidatesPage.clickBtnSearch();
        viewCandidatesPage = candidatesPage.clickBtnView();
        viewCandidatesPage.clickOnCheckBoxEdit();
        Assert.assertTrue(viewCandidatesPage.enableFirstName());
        Assert.assertTrue(viewCandidatesPage.enableMiddleName());
        Assert.assertTrue(viewCandidatesPage.enableLastName());
        Assert.assertTrue(viewCandidatesPage.enableJobVacancy());
        Assert.assertTrue(viewCandidatesPage.enableEmail());
        Assert.assertTrue(viewCandidatesPage.enableContactNumber());
        Assert.assertTrue(viewCandidatesPage.enableFileResume());
        Assert.assertTrue(viewCandidatesPage.enableKeywords());
        Assert.assertTrue(viewCandidatesPage.enableDateOfApplication());
        Assert.assertTrue(viewCandidatesPage.enableNotes());
        Assert.assertTrue(viewCandidatesPage.enableConsentToKeepData());
    }

    @Test(priority = 26)
    public void checkDisplayConfirmationRequiredEditCandidateProfile() throws InterruptedException {
        String titleConfirmationRequired = "Confirmation Required";
        String contentConfirmationRequired = "This action will remove previous vacancy. Are you sure you want to continue?";
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("expectedCandidateName"));
        candidatesPage.clickBtnSearch();
        viewCandidatesPage = candidatesPage.clickBtnView();
        viewCandidatesPage.clickOnCheckBoxEdit();
        confirmationRequiredPopup = viewCandidatesPage.clickBtnSave();
        Assert.assertTrue(viewCandidatesPage.displayDocument());
        Assert.assertEquals(confirmationRequiredPopup.getTextTitleDeleteCurrent(), titleConfirmationRequired);
        Assert.assertEquals(confirmationRequiredPopup.getTextContentDeleteCurrent(), contentConfirmationRequired);
        Assert.assertTrue(confirmationRequiredPopup.displayBtnNoCancel());
        Assert.assertTrue(confirmationRequiredPopup.displayBtnYesConfirm());
    }

    @Test(priority = 27)
    public void editCandidateProfileWhenClickBtnNoCancel() throws InterruptedException {
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("expectedCandidateName"));
        candidatesPage.clickBtnSearch();
        viewCandidatesPage = candidatesPage.clickBtnView();
        viewCandidatesPage.clickOnCheckBoxEdit();
        confirmationRequiredPopup = viewCandidatesPage.clickBtnSave();
        viewCandidatesPage = confirmationRequiredPopup.clickBtnNoCancel();
    }

    @Test(priority = 28)
    public void editCandidateProfileWhenKeepCurrent() throws InterruptedException {
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("expectedCandidateName"));
        candidatesPage.clickBtnSearch();
        viewCandidatesPage = candidatesPage.clickBtnView();
        viewCandidatesPage.clickOnCheckBoxEdit();
        viewCandidatesPage.clickOnKeepCurrentFile();
        confirmationRequiredPopup = viewCandidatesPage.clickBtnSave();
        viewCandidatesPage = confirmationRequiredPopup.clickBtnYesConfirm();
        Assert.assertTrue(viewCandidatesPage.getTextMessageSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 29)
    public void editCandidateProfileWhenDeleteCurrent() throws InterruptedException {
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("expectedCandidateName"));
        candidatesPage.clickBtnSearch();
        viewCandidatesPage = candidatesPage.clickBtnView();
        viewCandidatesPage.clickOnCheckBoxEdit();
        viewCandidatesPage.clickOnDeleteCurrentFile();
        confirmationRequiredPopup = viewCandidatesPage.clickBtnSave();
        viewCandidatesPage = confirmationRequiredPopup.clickBtnYesConfirm();
        Assert.assertTrue(viewCandidatesPage.getTextMessageSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 30)
    public void editCandidateProfileWhenReplaceCurrentFile() throws InterruptedException {
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("expectedCandidateName"));
        candidatesPage.clickBtnSearch();
        viewCandidatesPage = candidatesPage.clickBtnView();
        viewCandidatesPage.clickOnCheckBoxEdit();
        viewCandidatesPage.enterFirstName(prop.getProperty("firstName"));
        viewCandidatesPage.clearMiddleName();
        viewCandidatesPage.enterLastName(prop.getProperty("lastName"));
        viewCandidatesPage.selectJobVacancyOption(prop.getProperty("vacancyEdit"));
        viewCandidatesPage.enterEmail(prop.getProperty("existEmail"));
        viewCandidatesPage.enterContactNumber(prop.getProperty("driveLicense"));
        viewCandidatesPage.uploadFileResume(prop.getProperty("fileResume"));
        confirmationRequiredPopup = viewCandidatesPage.clickBtnSave();
        viewCandidatesPage = confirmationRequiredPopup.clickBtnYesConfirm();
        Assert.assertTrue(viewCandidatesPage.getTextMessageSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 34)
    public void downloadCandidateSuccess() throws InterruptedException {
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("candidateNameEdit"));
        candidatesPage.clickBtnSearch();
        candidatesPage.clickDownloadFile("kiem thu pm", prop.getProperty("download"));
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(viewCandidatesPage.getTextMessageSuccess().contains("Successfully Deleted"));
    }

    @Test(priority = 32)
    public void displayDeleteCandidate() throws InterruptedException {
        String titleDelete = "Are you Sure?";
        String contentDelete = "The selected record will be permanently deleted. Are you sure you want to continue?";
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("candidateNameEdit"));
        candidatesPage.clickBtnSearch();
        deletePopup = candidatesPage.clickBtnDelete();
        Assert.assertTrue(candidatesPage.displayDelete());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), titleDelete);
        Assert.assertEquals(deletePopup.getTextContentDelete(), contentDelete);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 33)
    public void deleteCandidateWhenClickBtnNoCancel() throws InterruptedException {
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("candidateNameEdit"));
        candidatesPage.clickBtnSearch();
        deletePopup = candidatesPage.clickBtnDelete();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 34)
    public void deleteCandidateSuccess() throws InterruptedException {
        candidatesPage.selectCandidateNameOption(prop.getProperty("candidateName"), prop.getProperty("candidateNameEdit"));
        candidatesPage.clickBtnSearch();
        deletePopup = candidatesPage.clickBtnDelete();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(viewCandidatesPage.getTextMessageSuccess().contains("Successfully Deleted"));
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
