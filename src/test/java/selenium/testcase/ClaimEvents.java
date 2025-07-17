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

public class ClaimEvents extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    ClaimPage claimPage;
    EventsPage eventsPage;
    EditEventPage editEventPage;
    AddEventClaimPage addEventClaimPage;
    DeletePopup deletePopup;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        claimPage = new ClaimPage(driver);
        eventsPage = new EventsPage(driver);
        editEventPage = new EditEventPage(driver);
        addEventClaimPage = new AddEventClaimPage(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        claimPage = leftOption.clickClaimOption();
        eventsPage = claimPage.selectEventsOption();
    }

    @Test(priority = 1)
    public void checkDisplayEventsPage(){
        String textEvent = "Events";
        Assert.assertEquals(eventsPage.getTextTitleEvents(), textEvent);
        Assert.assertTrue(eventsPage.displayTitleEvents());
        Assert.assertTrue(eventsPage.displayBtnReset());
        Assert.assertTrue(eventsPage.displayBtnSearch());
        Assert.assertTrue(eventsPage.displayBtnAdd());
        Assert.assertTrue(eventsPage.displayTable());
        Assert.assertTrue(eventsPage.getSizeRows() > 0);
    }

    @Test(priority = 2)
    public void checkDisplayAddEvent(){
        String textAddEvent = "Add Event";
        addEventClaimPage = eventsPage.clickBtnAdd();
        Assert.assertEquals(addEventClaimPage.getTextTitleAddEvent(), textAddEvent);
        Assert.assertTrue(addEventClaimPage.displayTitleAddEvent());
        Assert.assertTrue(addEventClaimPage.displayBtnCancel());
        Assert.assertTrue(addEventClaimPage.displayBtnSave());
    }

    @Test(priority = 3)
    public void checkValidateAddEvent(){
        String messageRequired = "Required";
        addEventClaimPage = eventsPage.clickBtnAdd();
        addEventClaimPage.clickBtnSave();
        Assert.assertEquals(addEventClaimPage.getTextMessageRequired(), messageRequired);
    }

    @Test(priority = 4)
    public void checkMessageErrorAddEvent(){
        addEventClaimPage = eventsPage.clickBtnAdd();
        addEventClaimPage.enterInputEventName(prop.getProperty("exceed1000Character"));
        Assert.assertTrue(addEventClaimPage.getTextMessageError().contains("Invalid Parameter"));
    }

    @Test(priority = 5)
    public void checkValidateExceedCharactersAddEvent(){
        String messageExceed100Characters = "Should not exceed 100 characters";
        String messageExceed1000Characters = "Should not exceed 1000 characters";
        addEventClaimPage = eventsPage.clickBtnAdd();
        addEventClaimPage.enterInputEventName(prop.getProperty("exceedCharacter"));
        addEventClaimPage.enterDescription(prop.getProperty("exceed1000Character"));
        Assert.assertEquals(addEventClaimPage.getTextMessageExceed100Characters(), messageExceed100Characters);
        Assert.assertEquals(addEventClaimPage.getTextMessageExceed1000Characters(), messageExceed1000Characters);
    }

    @Test(priority = 6)
    public void addEventWhenClickBtnCancel(){
        addEventClaimPage = eventsPage.clickBtnAdd();
        eventsPage = addEventClaimPage.clickBtnCancel();
    }

    @Test(priority = 7)
    public void addEventSuccess(){
        addEventClaimPage = eventsPage.clickBtnAdd();
        addEventClaimPage.enterInputEventName(prop.getProperty("textEvent"));
        eventsPage = addEventClaimPage.clickBtnSave();
        Assert.assertTrue(addEventClaimPage.getTextAddEventSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 8)
    public void addEventExist() {
        String messageExist = "Already exists";
        addEventClaimPage = eventsPage.clickBtnAdd();
        addEventClaimPage.enterInputEventName(prop.getProperty("textEvent"));
        Assert.assertEquals(addEventClaimPage.getTextMessageExist(), messageExist);
    }

    @Test(priority = 9)
    public void checkSearchNoRecord() throws InterruptedException {
        eventsPage.enterInputEventName(prop.getProperty("textEvent"), prop.getProperty("textEvent"));
        eventsPage.selectStatusOption(prop.getProperty("statusInactive"));
        eventsPage.clickBtnSearch();
        Assert.assertFalse(eventsPage.getTextMessageNoRecord().contains("No Records Found"));
    }

    @Test(priority = 10)
    public void checkSearchRecord() throws InterruptedException {
        eventsPage.enterInputEventName(prop.getProperty("textEvent"), prop.getProperty("textEvent"));
        eventsPage.selectStatusOption(prop.getProperty("statusActive"));
        eventsPage.clickBtnSearch();
        Assert.assertTrue(eventsPage.getSizeRows() > 0);
    }

    @Test(priority = 11)
    public void checkDisplayEditEventWhenSearch() throws InterruptedException {
        String titleEdit = "Edit Event";
        eventsPage.enterInputEventName(prop.getProperty("textEvent"), prop.getProperty("textEvent"));
        eventsPage.selectStatusOption(prop.getProperty("statusActive"));
        eventsPage.clickBtnSearch();
        Assert.assertTrue(eventsPage.getSizeRows() > 0);
        editEventPage = eventsPage.clickEditEventWhenSearch();
        Assert.assertEquals(editEventPage.getTextTitleEditEvent(), titleEdit);
        Assert.assertTrue(editEventPage.displayTitleEditEvent());
        Assert.assertFalse(editEventPage.disableInputEventName());
        Assert.assertFalse(editEventPage.selectedCheckboxActive());
        Assert.assertTrue(editEventPage.displayBtnCancel());
        Assert.assertTrue(editEventPage.displayBtnSave());
    }

    @Test(priority = 12)
    public void checkEditEventWhenClickBtnCancel() throws InterruptedException {
        eventsPage.enterInputEventName(prop.getProperty("textEvent"), prop.getProperty("textEvent"));
        eventsPage.selectStatusOption(prop.getProperty("statusActive"));
        eventsPage.clickBtnSearch();
        Assert.assertTrue(eventsPage.getSizeRows() > 0);
        editEventPage = eventsPage.clickEditEventWhenSearch();
        editEventPage.clickOffCheckboxActive();
        eventsPage = editEventPage.clickBtnCancel();
    }

    @Test(priority = 13)
    public void checkEditEventSuccessWhenSearch() throws InterruptedException {
        eventsPage.enterInputEventName(prop.getProperty("textEvent"), prop.getProperty("textEvent"));
        eventsPage.selectStatusOption(prop.getProperty("statusActive"));
        eventsPage.clickBtnSearch();
        Assert.assertTrue(eventsPage.getSizeRows() > 0);
        editEventPage = eventsPage.clickEditEventWhenSearch();
        editEventPage.clickOffCheckboxActive();
        eventsPage = editEventPage.clickBtnSave();
        Assert.assertTrue(editEventPage.getTextEditEventSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 14)
    public void checkEditEventSuccessWhenNoSearch(){
        editEventPage = eventsPage.clickEditEvent();
        editEventPage.clickOnCheckboxActive();
        eventsPage = editEventPage.clickBtnSave();
        Assert.assertTrue(editEventPage.getTextEditEventSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 15)
    public void checkDisplayDeleteEvent(){
        String textTitleDelete = "Are you Sure?";
        String textContentDelete = "The selected record will be permanently deleted. Are you sure you want to continue?";
        deletePopup = eventsPage.clickDeleteEvent();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), textTitleDelete);
        Assert.assertEquals(deletePopup.getTextContentDelete(), textContentDelete);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 16)
    public void checkDeleteEventClickBtnNoCancel(){
        deletePopup = eventsPage.clickDeleteEvent();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 17)
    public void checkDeleteEventClickBtnYesDelete(){
        deletePopup = eventsPage.clickDeleteEvent();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(eventsPage.getTextDeleteSuccess().contains("Successfully Deleted"));
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
