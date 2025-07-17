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

public class MyInfoQualifications extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    MyInfoPage myInfoPage;
    QualificationsPage qualificationsPage;
    AddWorkExperienceQualificationsPage addWorkExperienceQualificationsPage;
    EditWorkExperiencePage editWorkExperiencePage;
    AddEducationQualificationsPage addEducationQualificationsPage;
    EditEducationPage editEducationPage;
    AddSkillQualificationsPage addSkillQualificationsPage;
    AddLanguageQualificationsPage addLanguageQualificationsPage;
    EditLanguagePage editLanguagePage;
    AddLicenseQualificationsPage addLicenseQualificationsPage;
    AddAttachmentPage addAttachmentPage;
    ContactDetailPage contactDetailPage;
    DeletePopup deletePopup;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        myInfoPage = new MyInfoPage(driver);
        qualificationsPage = new QualificationsPage(driver);
        addWorkExperienceQualificationsPage = new AddWorkExperienceQualificationsPage(driver);
        editWorkExperiencePage = new EditWorkExperiencePage(driver);
        addEducationQualificationsPage = new AddEducationQualificationsPage(driver);
        editEducationPage = new EditEducationPage(driver);
        addSkillQualificationsPage = new AddSkillQualificationsPage(driver);
        addLanguageQualificationsPage = new AddLanguageQualificationsPage(driver);
        editLanguagePage = new EditLanguagePage(driver);
        addLicenseQualificationsPage = new AddLicenseQualificationsPage(driver);
        addAttachmentPage = new AddAttachmentPage(driver);
        contactDetailPage = new ContactDetailPage(driver);
        deletePopup = new DeletePopup(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        myInfoPage = leftOption.clickMyInfoOption();
        qualificationsPage = myInfoPage.clickQualifications();
    }

    @Test(priority = 1)
    public void checkDisplayQualificationsPage(){
        String textQualifications = "Qualifications";
        String textWorkExperience = "Work Experience";
        String textEducation = "Education";
        String textSkills = "Skills";
        String textLanguages = "Languages";
        String textLicense = "License";
        String textAttachments = "Attachments";

        Assert.assertTrue(qualificationsPage.displayTitleQualifications());
        Assert.assertEquals(qualificationsPage.getTextTitleQualifications(), textQualifications);

        Assert.assertTrue(qualificationsPage.displayTitleWorkExperience());
        Assert.assertEquals(qualificationsPage.getTextTitleWorkExperience(), textWorkExperience);
        Assert.assertTrue(qualificationsPage.displayBtnAddWorkExperience());
        Assert.assertTrue(qualificationsPage.displayTableWorkExperience());

        Assert.assertTrue(qualificationsPage.displayTitleEducation());
        Assert.assertEquals(qualificationsPage.getTextTitleEducation(), textEducation);
        Assert.assertTrue(qualificationsPage.displayBtnAddEducation());
        Assert.assertTrue(qualificationsPage.displayTableEducation());

        Assert.assertTrue(qualificationsPage.displayTitleSkills());
        Assert.assertEquals(qualificationsPage.getTextTitleSkills(), textSkills);
        Assert.assertTrue(qualificationsPage.displayBtnAddSkills());
        Assert.assertTrue(qualificationsPage.displayTableSkills());

        Assert.assertTrue(qualificationsPage.displayTitleLanguages());
        Assert.assertEquals(qualificationsPage.getTextTitleLanguages(), textLanguages);
        Assert.assertTrue(qualificationsPage.displayBtnAddLanguages());
        Assert.assertTrue(qualificationsPage.displayTableLanguage());

        Assert.assertTrue(qualificationsPage.displayTitleLicense());
        Assert.assertEquals(qualificationsPage.getTextTitleLicense(), textLicense);
        Assert.assertTrue(qualificationsPage.displayBtnAddLicense());
        Assert.assertTrue(qualificationsPage.displayTableLicense());

        Assert.assertTrue(qualificationsPage.displayTitleAttachments());
        Assert.assertEquals(qualificationsPage.getTextTitleAttachments(), textAttachments);
        Assert.assertTrue(qualificationsPage.displayBtnAddAttachment());
        Assert.assertTrue(qualificationsPage.displayTableAttachments());
    }

    @Test(priority = 2)
    public void checkDisplayAddWorkExperience(){
        String titleAddWorkExperience = "Add Work Experience";

        addWorkExperienceQualificationsPage = qualificationsPage.clickBtnAddWorkExperience();
        Assert.assertTrue(addWorkExperienceQualificationsPage.displayTitleAddWorkExperience());
        Assert.assertEquals(addWorkExperienceQualificationsPage.getTextTitleAddWorkExperience(), titleAddWorkExperience);
        Assert.assertTrue(addWorkExperienceQualificationsPage.displayBtnCancelAddWorkExperience());
        Assert.assertTrue(addWorkExperienceQualificationsPage.displayBtnSaveAddWorkExperience());
    }

    @Test(priority = 3)
    public void checkRequiredAddWorkExperience(){
        String messageRequired = "Required";
        addWorkExperienceQualificationsPage = qualificationsPage.clickBtnAddWorkExperience();
        addWorkExperienceQualificationsPage.clickBtnSaveAddWorkExperience();
        Assert.assertEquals(addWorkExperienceQualificationsPage.getTextMessageRequiredCompany(), messageRequired);
        Assert.assertEquals(addWorkExperienceQualificationsPage.getTextMessageRequiredJobTitle(), messageRequired);
    }

    @Test(priority = 4)
    public void checkExceed100CharactersAddWorkExperience(){
        String messageExceed100Characters = "Should not exceed 100 characters";
        addWorkExperienceQualificationsPage = qualificationsPage.clickBtnAddWorkExperience();
        addWorkExperienceQualificationsPage.enterInputCompany(prop.getProperty("exceedCharacter"));
        addWorkExperienceQualificationsPage.enterInputJobTitle(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addWorkExperienceQualificationsPage.getTextMessageExceed100CharactersCompany(), messageExceed100Characters);
        Assert.assertEquals(addWorkExperienceQualificationsPage.getTextMessageExceed100CharactersJobTitle(), messageExceed100Characters);
    }

    @Test(priority = 5)
    public void checkExceed200CharactersAddWorkExperience(){
        String messageExceed200Characters = "Should not exceed 200 characters";
        addWorkExperienceQualificationsPage = qualificationsPage.clickBtnAddWorkExperience();
        addWorkExperienceQualificationsPage.enterCommentAddWorkExperience(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addWorkExperienceQualificationsPage.getTextMessageExceed200Characters(), messageExceed200Characters);
    }

    @Test(priority = 6)
    public void checkShouldBeFormatAddWorkExperience(){
        String messageShouldBeFormat = "Should be a valid date in yyyy-dd-mm format";
        addWorkExperienceQualificationsPage = qualificationsPage.clickBtnAddWorkExperience();
        addWorkExperienceQualificationsPage.enterFromDate(prop.getProperty("dateFormat"));
        addWorkExperienceQualificationsPage.enterToDate(prop.getProperty("dateFormat"));
        Assert.assertEquals(addWorkExperienceQualificationsPage.getTextMessageShouldBeFrom(), messageShouldBeFormat);
        Assert.assertEquals(addWorkExperienceQualificationsPage.getTextMessageShouldBeTo(), messageShouldBeFormat);
    }

    @Test(priority = 7)
    public void checkToDateShouldBeAfterFromDateAddWorkExperience(){
        String messageToDate = "To date should be after from date";
        addWorkExperienceQualificationsPage = qualificationsPage.clickBtnAddWorkExperience();
        addWorkExperienceQualificationsPage.selectFromDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        addWorkExperienceQualificationsPage.selectToDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        Assert.assertEquals(addWorkExperienceQualificationsPage.getTextMessageToDate(), messageToDate);
    }

    @Test(priority = 8)
    public void addWorkExperienceWhenClickBtnCancel(){
        addWorkExperienceQualificationsPage = qualificationsPage.clickBtnAddWorkExperience();
        addWorkExperienceQualificationsPage.enterInputCompany(prop.getProperty("company"));
        addWorkExperienceQualificationsPage.enterInputJobTitle(prop.getProperty("jobTitle"));
        addWorkExperienceQualificationsPage.selectFromDate(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        addWorkExperienceQualificationsPage.selectToDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        addWorkExperienceQualificationsPage.enterCommentAddWorkExperience(prop.getProperty("textComments"));
        qualificationsPage = addWorkExperienceQualificationsPage.clickBtnCancelAddWorkExperience();
    }

    @Test(priority = 9)
    public void addWorkExperienceSuccess(){
        addWorkExperienceQualificationsPage = qualificationsPage.clickBtnAddWorkExperience();
        addWorkExperienceQualificationsPage.enterInputCompany(prop.getProperty("company"));
        addWorkExperienceQualificationsPage.enterInputJobTitle(prop.getProperty("jobTitle"));
        addWorkExperienceQualificationsPage.selectFromDate(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        addWorkExperienceQualificationsPage.selectToDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        addWorkExperienceQualificationsPage.enterCommentAddWorkExperience(prop.getProperty("textComments"));
        qualificationsPage = addWorkExperienceQualificationsPage.clickBtnSaveAddWorkExperience();
        Assert.assertTrue(qualificationsPage.getTextMessageSuccess().contains("Successfully Saved"));
        Assert.assertTrue(qualificationsPage.displayTableWorkExperience());
    }

    @Test(priority = 10)
    public void displayEditWorkExperience(){
        String titleEditExperience = "Edit Work Experience";
        editWorkExperiencePage = qualificationsPage.clickEditWorkExperience();
        Assert.assertEquals(editWorkExperiencePage.getTextTitleEditWorkExperience(), titleEditExperience);
        Assert.assertTrue(editWorkExperiencePage.displayTitleEditWorkExperience());
        Assert.assertTrue(editWorkExperiencePage.displayBtnCancel());
        Assert.assertTrue(editWorkExperiencePage.displayBtnSave());
    }

    @Test(priority = 11)
    public void editWorkExperienceWhenClickBtnCancel(){
        editWorkExperiencePage = qualificationsPage.clickEditWorkExperience();
        editWorkExperiencePage.clickBtnCancel();
    }

    @Test(priority = 12)
    public void editSuccessWorkExperience(){
        editWorkExperiencePage = qualificationsPage.clickEditWorkExperience();
        editWorkExperiencePage.enterInputCompany(prop.getProperty("companyEdit"));
        editWorkExperiencePage.enterInputJobTitle(prop.getProperty("jobTitleEdit"));
        editWorkExperiencePage.selectFromDate(prop.getProperty("dayEdit"), prop.getProperty("monthEdit"), prop.getProperty("yearEdit"));
        editWorkExperiencePage.selectToDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        editWorkExperiencePage.clickBtnSave();
        Assert.assertTrue(qualificationsPage.getTextMessageSuccess().contains("Successfully Updated"));
        Assert.assertTrue(qualificationsPage.displayTableWorkExperience());
    }

    @Test(priority = 13)
    public void displayDeleteWorkExperience(){
        String titleDelete = "Are you Sure?";
        String contentDelete = "The selected record will be permanently deleted. Are you sure you want to continue?";

        deletePopup = qualificationsPage.clickDeleteWorkExperience();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), titleDelete);
        Assert.assertEquals(deletePopup.getTextContentDelete(), contentDelete);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 14)
    public void deleteWorkExperienceWhenClickBtnNoCancel(){
        deletePopup = qualificationsPage.clickDeleteWorkExperience();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 15)
    public void deleteSuccessWorkExperience(){
        deletePopup = qualificationsPage.clickDeleteWorkExperience();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(qualificationsPage.getTextMessageSuccess().contains("Successfully Deleted"));
    }

    @Test(priority = 16)
    public void checkDisplayAddEducation(){
        String titleAddEducation = "Add Education";

        addEducationQualificationsPage = qualificationsPage.clickBtnAddEducation();
        Assert.assertTrue(addEducationQualificationsPage.displayTitleAddEducation());
        Assert.assertEquals(addEducationQualificationsPage.getTextTitleAddEducation(), titleAddEducation);
        Assert.assertTrue(addEducationQualificationsPage.displayBtnCancelAddEducation());
        Assert.assertTrue(addEducationQualificationsPage.displayBtnSaveAddEducation());
    }

    @Test(priority = 17)
    public void checkRequiredAddEducation(){
        String messageRequired = "Required";

        addEducationQualificationsPage = qualificationsPage.clickBtnAddEducation();
        addEducationQualificationsPage.clickBtnSaveAddEducation();
        Assert.assertEquals(addEducationQualificationsPage.getTextMessageRequired(), messageRequired);
    }

    @Test(priority = 18)
    public void checkMessageExceed100Characters(){
        String messageExceed100Characters = "Should not exceed 100 characters";
        addEducationQualificationsPage = qualificationsPage.clickBtnAddEducation();
        addEducationQualificationsPage.enterInputInstitute(prop.getProperty("exceedCharacter"));
        addEducationQualificationsPage.enterInputMajor(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addEducationQualificationsPage.getTextMessageExceed100CharactersInstitute(), messageExceed100Characters);
        Assert.assertEquals(addEducationQualificationsPage.getTextMessageExceed100CharactersMajor(), messageExceed100Characters);
    }

    @Test(priority = 19)
    public void checkMessageExceed4Characters(){
        String messageExceed4Characters = "Should not exceed 4 characters";
        addEducationQualificationsPage = qualificationsPage.clickBtnAddEducation();
        addEducationQualificationsPage.enterInputYear(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addEducationQualificationsPage.getTextMessageExceed4Characters(), messageExceed4Characters);
    }

    @Test(priority = 20)
    public void checkMessageExceed25Characters(){
        String messageExceed25Characters = "Should not exceed 25 characters";
        addEducationQualificationsPage = qualificationsPage.clickBtnAddEducation();
        addEducationQualificationsPage.enterInputGPA(prop.getProperty("exceedCharacter"));
        Assert.assertEquals(addEducationQualificationsPage.getTextMessageExceed25Characters(), messageExceed25Characters);
    }

    @Test(priority = 21)
    public void checkShouldBeNumberAddEducation(){
        String messageShouldBeNumber = "Should be a number";

        addEducationQualificationsPage = qualificationsPage.clickBtnAddEducation();
        addEducationQualificationsPage.enterInputYear(prop.getProperty("allowNumber"));
        Assert.assertEquals(addEducationQualificationsPage.getTextMessageShouldBeNumber(), messageShouldBeNumber);
    }

    @Test(priority = 22)
    public void checkShouldBeFormatAddEducation(){
        String messageShouldBeFormat = "Should be a valid date in yyyy-dd-mm format";

        addEducationQualificationsPage = qualificationsPage.clickBtnAddEducation();
        addEducationQualificationsPage.enterStartDate(prop.getProperty("dateFormat"));
        addEducationQualificationsPage.enterEndDate(prop.getProperty("dateFormat"));

        Assert.assertEquals(addEducationQualificationsPage.getTextMessageShouldBeFormatStartDate(), messageShouldBeFormat);
        Assert.assertEquals(addEducationQualificationsPage.getTextMessageShouldBeFormatEndDate(),messageShouldBeFormat);
    }

    @Test(priority = 23)
    public void checkEndDateShouldBeAfterStartDateAddEducation(){
        String messageEndDateShouldBeAfterStartDate = "End date should be after Start date";

        addEducationQualificationsPage = qualificationsPage.clickBtnAddEducation();
        addEducationQualificationsPage.selectStartDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        addEducationQualificationsPage.selectEndDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));

        Assert.assertEquals(addEducationQualificationsPage.getTextMessageStartDate(), messageEndDateShouldBeAfterStartDate);
    }

    @Test(priority = 24)
    public void addEducationWhenClickBtnCancel(){
        addEducationQualificationsPage = qualificationsPage.clickBtnAddEducation();

        addEducationQualificationsPage.selectLevelOption(prop.getProperty("levelMaster"));
        addEducationQualificationsPage.enterInputInstitute(prop.getProperty("institute"));
        addEducationQualificationsPage.enterInputMajor(prop.getProperty("major"));
        addEducationQualificationsPage.enterInputYear(prop.getProperty("year"));
        addEducationQualificationsPage.enterInputGPA(prop.getProperty("gpa"));
        addEducationQualificationsPage.selectStartDate(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        addEducationQualificationsPage.selectEndDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        qualificationsPage = addEducationQualificationsPage.clickBtnCancelAddEducation();
    }

    @Test(priority = 25)
    public void addEducationSuccess(){
        addEducationQualificationsPage = qualificationsPage.clickBtnAddEducation();

        addEducationQualificationsPage.selectLevelOption(prop.getProperty("levelMaster"));
        addEducationQualificationsPage.enterInputInstitute(prop.getProperty("institute"));
        addEducationQualificationsPage.enterInputMajor(prop.getProperty("major"));
        addEducationQualificationsPage.enterInputYear(prop.getProperty("year"));
        addEducationQualificationsPage.enterInputGPA(prop.getProperty("gpa"));
        addEducationQualificationsPage.selectStartDate(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        addEducationQualificationsPage.selectEndDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        qualificationsPage = addEducationQualificationsPage.clickBtnSaveAddEducation();
        Assert.assertTrue(qualificationsPage.getTextMessageSuccess().contains("Successfully Saved"));
        Assert.assertTrue(qualificationsPage.displayTableEducation());
    }

    @Test(priority = 26)
    public void displayEditEducation(){
        String titleEditEducation = "Edit Education";
        editEducationPage = qualificationsPage.clickEditEducation();
        Assert.assertEquals(editEducationPage.getTextTitleEditEducation(), titleEditEducation);
        Assert.assertTrue(editEducationPage.displayTitleEditEducation());
        Assert.assertTrue(editEducationPage.disableInputLevel());
        Assert.assertTrue(editEducationPage.displayBtnCancel());
        Assert.assertTrue(editEducationPage.displayBtnSave());
        Assert.assertTrue(qualificationsPage.getTextMessageSuccess().contains("Successfully Updated"));
        Assert.assertTrue(qualificationsPage.displayTableEducation());
    }

    @Test(priority = 27)
    public void editEducationWhenClickBtnCancel(){
        editEducationPage = qualificationsPage.clickEditEducation();
        qualificationsPage = editEducationPage.clickBtnCancel();
    }

    @Test(priority = 28)
    public void editSuccessEducation(){
        editEducationPage = qualificationsPage.clickEditEducation();
        editEducationPage.enterInputInstitute(prop.getProperty("instituteEdit"));
        editEducationPage.enterInputMajor(prop.getProperty("majorEdit"));
        editEducationPage.enterInputYear(prop.getProperty("yearEdit"));
        editEducationPage.enterInputGPA(prop.getProperty("gpa"));
        editEducationPage.selectStartDate(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        editEducationPage.selectEndDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
    }

    @Test(priority = 29)
    public void displayDeleteEducation(){
        String titleDelete = "Are you Sure?";
        String contentDelete = "The selected record will be permanently deleted. Are you sure you want to continue?";

        deletePopup = qualificationsPage.clickDeleteEducation();
        Assert.assertTrue(deletePopup.displayDocument());
        Assert.assertEquals(deletePopup.getTextTitleDelete(), titleDelete);
        Assert.assertEquals(deletePopup.getTextContentDelete(), contentDelete);
        Assert.assertTrue(deletePopup.displayBtnNoCancel());
        Assert.assertTrue(deletePopup.displayBtnYesDelete());
    }

    @Test(priority = 30)
    public void deleteEducationWhenClickBtnNoCancel(){
        deletePopup = qualificationsPage.clickDeleteEducation();
        deletePopup.clickBtnNoCancel();
    }

    @Test(priority = 31)
    public void deleteSuccessEducation(){
        deletePopup = qualificationsPage.clickDeleteEducation();
        deletePopup.clickBtnYesDelete();
        Assert.assertTrue(qualificationsPage.getTextMessageSuccess().contains("Successfully Deleted"));
    }

    @Test(priority = 32)
    public void checkDisplayAddSkill(){
        String titleAddSkill = "Add Skill";

        addSkillQualificationsPage = qualificationsPage.clickBtnAddSkills();
        Assert.assertTrue(addSkillQualificationsPage.displayTitleAddSkills());
        Assert.assertEquals(addSkillQualificationsPage.getTextTitleAddSkills(), titleAddSkill);
        Assert.assertTrue(addSkillQualificationsPage.displayBtnCancelSkills());
        Assert.assertTrue(addSkillQualificationsPage.displayBtnSaveSkills());
    }

    @Test(priority = 33)
    public void checkRequiredAddSkill(){
        String messageRequired = "Required";

        addSkillQualificationsPage = qualificationsPage.clickBtnAddSkills();
        addSkillQualificationsPage.clickBtnSaveSkills();
        Assert.assertEquals(addSkillQualificationsPage.getTextMessageRequired(), messageRequired);
    }

    @Test(priority = 34)
    public void checkShouldBeNumberAddSkill(){
        String messageShouldBeNumber = "Should be a number";

        addSkillQualificationsPage = qualificationsPage.clickBtnAddSkills();
        addSkillQualificationsPage.enterInputYearsOfExperience(prop.getProperty("allowNumber"));
        Assert.assertEquals(addSkillQualificationsPage.getTextMessageShouldBeNumber(), messageShouldBeNumber);
    }

    @Test(priority = 35)
    public void checkShouldBeLessThan100AddSkill(){
        String messageShouldBeLessThan100 = "Should be less than 100";

        addSkillQualificationsPage = qualificationsPage.clickBtnAddSkills();
        addSkillQualificationsPage.enterInputYearsOfExperience(prop.getProperty("exceedNumberCharacter"));
        Assert.assertEquals(addSkillQualificationsPage.getTextMessageShouldBeNumber(), messageShouldBeLessThan100);
    }

    @Test(priority = 36)
    public void addSkillWhenClickBtnCancel(){
        addSkillQualificationsPage = qualificationsPage.clickBtnAddSkills();

        addSkillQualificationsPage.selectSkillOption(prop.getProperty("skillJIRA"));
        addSkillQualificationsPage.enterInputYearsOfExperience(prop.getProperty("number"));
        addSkillQualificationsPage.enterCommentsAddSkills(prop.getProperty("textComments"));
        qualificationsPage = addSkillQualificationsPage.clickBtnCancelSkills();
    }

    @Test(priority = 37)
    public void addSkillsSuccess(){
        addSkillQualificationsPage = qualificationsPage.clickBtnAddSkills();

        addSkillQualificationsPage.selectSkillOption(prop.getProperty("skillJIRA"));
        addSkillQualificationsPage.enterInputYearsOfExperience(prop.getProperty("number"));
        addSkillQualificationsPage.enterCommentsAddSkills(prop.getProperty("textComments"));
        qualificationsPage = addSkillQualificationsPage.clickBtnSaveSkills();
        Assert.assertTrue(qualificationsPage.getTextMessageSuccess().contains("Successfully Saved"));
        Assert.assertTrue(qualificationsPage.displayTableSkills());
    }

    @Test(priority = 38)
    public void checkDisplayAddLanguage(){
        String titleAddLanguage = "Add Language";

        addLanguageQualificationsPage = qualificationsPage.clickBtnAddLanguages();
        Assert.assertTrue(addLanguageQualificationsPage.displayTitleAddLanguage());
        Assert.assertEquals(addLanguageQualificationsPage.getTextTitleAddLanguage(), titleAddLanguage);
        Assert.assertTrue(addLanguageQualificationsPage.displayBtnCancelLanguage());
        Assert.assertTrue(addLanguageQualificationsPage.displayBtnSaveLanguage());
    }

    @Test(priority = 39)
    public void checkRequiredAddLanguage(){
        String messageRequired = "Required";

        addLanguageQualificationsPage = qualificationsPage.clickBtnAddLanguages();
        addLanguageQualificationsPage.clickBtnSaveLanguage();

        Assert.assertEquals(addLanguageQualificationsPage.getTextMessageRequiredLanguage(), messageRequired);
        Assert.assertEquals(addLanguageQualificationsPage.getTextMessageRequiredFluency(), messageRequired);
        Assert.assertEquals(addLanguageQualificationsPage.getTextMessageRequiredCompetency(), messageRequired);
    }

    @Test(priority = 40)
    public void checkExceed100CharactersAddLanguage(){
        String messageExceed100Characters = "Should not exceed 100 characters";

        addLanguageQualificationsPage = qualificationsPage.clickBtnAddLanguages();
        addLanguageQualificationsPage.enterComments(prop.getProperty("exceedCharacter"));

        Assert.assertEquals(addLanguageQualificationsPage.getTextMessageShouldExceed100Characters(), messageExceed100Characters);

    }

    @Test(priority = 41)
    public void addLanguageWhenClickBtnCancel(){
        addLanguageQualificationsPage = qualificationsPage.clickBtnAddLanguages();
        addLanguageQualificationsPage.selectLanguageOption(prop.getProperty("language"));
        addLanguageQualificationsPage.selectFluencyOption(prop.getProperty("fluency"));
        addLanguageQualificationsPage.selectCompetencyOption(prop.getProperty("competency"));
        addLanguageQualificationsPage.enterComments(prop.getProperty("textComments"));
        qualificationsPage = addLanguageQualificationsPage.clickBtnCancelLanguage();
    }

    @Test(priority = 42)
    public void addLanguageSuccess(){
        addLanguageQualificationsPage = qualificationsPage.clickBtnAddLanguages();
        addLanguageQualificationsPage.selectLanguageOption(prop.getProperty("language"));
        addLanguageQualificationsPage.selectFluencyOption(prop.getProperty("fluency"));
        addLanguageQualificationsPage.selectCompetencyOption(prop.getProperty("competency"));
        addLanguageQualificationsPage.enterComments(prop.getProperty("textComments"));
        qualificationsPage = addLanguageQualificationsPage.clickBtnSaveLanguage();
        Assert.assertTrue(qualificationsPage.getTextMessageSuccess().contains("Successfully Saved"));
        Assert.assertTrue(qualificationsPage.displayTableLanguage());
    }

    @Test(priority = 43)
    public void displayEditLanguage(){
        String titleEditLanguage = "Edit Language";
        editLanguagePage = qualificationsPage.clickEditLanguage();
        Assert.assertEquals(editLanguagePage.getTextTitleEditLanguage(), titleEditLanguage);
        Assert.assertTrue(editLanguagePage.displayTitleEditLanguage());
        Assert.assertTrue(editLanguagePage.disableDropdownLanguage());
        Assert.assertTrue(editLanguagePage.disableDropdownFluency());
        Assert.assertTrue(editLanguagePage.displayBtnCancel());
        Assert.assertTrue(editLanguagePage.displayBtnSave());
    }

    @Test(priority = 44)
    public void editSuccessLanguage(){
        editLanguagePage = qualificationsPage.clickEditLanguage();
        editLanguagePage.selectCompetencyOption(prop.getProperty("competencyEdit"));
        editLanguagePage.clickBtnSave();
        Assert.assertTrue(qualificationsPage.getTextMessageSuccess().contains("Successfully Updated"));
        Assert.assertTrue(qualificationsPage.displayTableLanguage());
    }

    @Test(priority = 45)
    public void checkDisplayAddLicense(){
        String titleAddLicense= "Add License";

        addLicenseQualificationsPage = qualificationsPage.clickBtnAddLicense();
        Assert.assertTrue(addLicenseQualificationsPage.displayTitleAddLicense());
        Assert.assertEquals(addLicenseQualificationsPage.getTextTitleAddLicense(), titleAddLicense);
        Assert.assertTrue(addLicenseQualificationsPage.displayBtnCancelAddLicense());
        Assert.assertTrue(addLicenseQualificationsPage.displayBtnSaveAddLicense());
    }

    @Test(priority = 46)
    public void checkRequiredAddLicense(){
        String messageRequired = "Required";

        addLicenseQualificationsPage = qualificationsPage.clickBtnAddLicense();
        addLicenseQualificationsPage.clickBtnSaveAddLicense();
        Assert.assertEquals(addLicenseQualificationsPage.getTextMessageRequired(), messageRequired);
    }

    @Test(priority = 47)
    public void checkShouldBeFormatAddLicense(){
        String messageShouldBeFormat = "Should be a valid date in yyyy-dd-mm format";

        addLicenseQualificationsPage = qualificationsPage.clickBtnAddLicense();
        addLicenseQualificationsPage.enterIssuedDate(prop.getProperty("dateFormat"));
        addLicenseQualificationsPage.enterExpiryDate(prop.getProperty("dateFormat"));

        Assert.assertEquals(addLicenseQualificationsPage.getTextMessageShouldBeFormatIssuedDate(), messageShouldBeFormat);
        Assert.assertEquals(addLicenseQualificationsPage.getTextMessageShouldBeFormatExpiryDate(),messageShouldBeFormat);
    }

    @Test(priority = 48)
    public void checkExpiryDateShouldBeAfterIssuedDateAddLicense(){
        String messageExpiryDateShouldBeAfterIssuedDate = "Expiry date should be after issued date";

        addLicenseQualificationsPage = qualificationsPage.clickBtnAddLicense();
        addLicenseQualificationsPage.selectIssuedDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        addLicenseQualificationsPage.selectExpiryDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));

        Assert.assertEquals(addLicenseQualificationsPage.getTextMessageExpiryDate(), messageExpiryDateShouldBeAfterIssuedDate);
    }

    @Test(priority = 49)
    public void addLicenseWhenClickBtnCancel(){
        addLicenseQualificationsPage = qualificationsPage.clickBtnAddLicense();

        addLicenseQualificationsPage.selectLicenseTypeOption(prop.getProperty("license"));
        addLicenseQualificationsPage.enterInputLicenseNumber(prop.getProperty("number"));

        addLicenseQualificationsPage.selectIssuedDate(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        addLicenseQualificationsPage.selectExpiryDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        qualificationsPage = addLicenseQualificationsPage.clickBtnCancelAddLicense();
    }

    @Test(priority = 50)
    public void addLicenseSuccess(){
        addLicenseQualificationsPage = qualificationsPage.clickBtnAddLicense();

        addLicenseQualificationsPage.selectLicenseTypeOption(prop.getProperty("license"));
        addLicenseQualificationsPage.enterInputLicenseNumber(prop.getProperty("number"));

        addLicenseQualificationsPage.selectIssuedDate(prop.getProperty("day"), prop.getProperty("month"), prop.getProperty("year"));
        addLicenseQualificationsPage.selectExpiryDate(prop.getProperty("dayLicense"), prop.getProperty("monthLicense"), prop.getProperty("yearLicense"));
        qualificationsPage = addLicenseQualificationsPage.clickBtnSaveAddLicense();
        Assert.assertTrue(qualificationsPage.getTextMessageSuccess().contains("Successfully Saved"));
        Assert.assertTrue(qualificationsPage.displayTableEducation());
    }

    @Test(priority = 51)
    public void checkDisplayAddAttachment(){
        String titleAddAttachment = "Add Attachment";
        String textAccept = "Accepts up to 1MB";

        contactDetailPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        Assert.assertEquals(addAttachmentPage.getTextAcceptTo(), textAccept);
        Assert.assertTrue(addAttachmentPage.displayBtnCancelAttachment());
        Assert.assertTrue(addAttachmentPage.displayBtnSaveAttachment());
    }

    @Test(priority = 52)
    public void updateAddAttachmentSizeExceededFile(){
        String titleAddAttachment = "Add Attachment";
        String messageAttachmentSizeExceeded = "Attachment Size Exceeded";

        contactDetailPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        addAttachmentPage.selectFile(prop.getProperty("fileAttachmentSizeExceeded"));
        Assert.assertEquals(addAttachmentPage.getTextMessageAttachmentSizeExceeded(), messageAttachmentSizeExceeded);
    }

    @Test(priority = 53)
    public void updateAddAttachmentWhenClickBtnCancel(){
        String titleAddAttachment = "Add Attachment";

        contactDetailPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        addAttachmentPage.selectFile(prop.getProperty("fileUpload"));
        addAttachmentPage.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPage.clickBtnCancelAddAttachment();
    }

    @Test(priority = 54)
    public void updateAddAttachmentSuccess(){
        String titleAddAttachment = "Add Attachment";

        contactDetailPage.clickBtnAddAttachment();
        Assert.assertTrue(addAttachmentPage.displayTitleAddAttachment());
        Assert.assertEquals(addAttachmentPage.getTextTitleAddAttachment(), titleAddAttachment);
        addAttachmentPage.selectFile(prop.getProperty("fileUpload"));
        addAttachmentPage.enterCommentAddAttachment(prop.getProperty("textComments"));
        addAttachmentPage.clickBtnSaveAddAttachment();
        Assert.assertFalse(addAttachmentPage.getTextAttachmentSuccess().contains("Successfully Updated"));
        Assert.assertTrue(contactDetailPage.displayTable());
        Assert.assertTrue(contactDetailPage.getSizeRows() > 0);
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
