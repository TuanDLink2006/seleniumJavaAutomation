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

public class PIMCustomerField extends Base {

    WebDriver driver;
    Properties prop;
    LoginPage loginPage;
    LeftOption leftOption;
    PIMPage pimPage;
    CustomFieldsPage customFieldsPage;
    AddCustomFieldPage addCustomFieldPage;
    EditCustomFieldPage editCustomFieldPage;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        leftOption = new LeftOption(driver);
        editCustomFieldPage = new EditCustomFieldPage(driver);
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        pimPage = leftOption.clickPIMOption();
        customFieldsPage = pimPage.selectCustomFields();
    }

   @Test(priority = 1)
    public void checkDisplayCustomField(){
        Assert.assertTrue(customFieldsPage.displayTitleCustomFields());
        Assert.assertTrue(customFieldsPage.displayBtnAdd());
        Assert.assertTrue(customFieldsPage.displayTable());
        Assert.assertTrue(customFieldsPage.getSizeRowTable() > 0, "There should be at least one custom row");
    }

    @Test(priority = 2)
    public void addCustomFieldCheckValidateRequired(){
        String messageRequired = "Required";

        addCustomFieldPage = customFieldsPage.clickBtnAdd();
        addCustomFieldPage.clickBtnSave();
        Assert.assertEquals(addCustomFieldPage.getTextMessageRequiredFieldName(), messageRequired);
        Assert.assertEquals(addCustomFieldPage.getTextMessageRequiredScreen(), messageRequired);
        Assert.assertEquals(addCustomFieldPage.getTextMessageRequiredType(), messageRequired);
    }

    @Test(priority = 3)
    public void addCustomFieldClickBtnCancel(){
        addCustomFieldPage = customFieldsPage.clickBtnAdd();
        addCustomFieldPage.enterInputFieldName(prop.getProperty("customFieldName"));
        addCustomFieldPage.selectOptionScreen(prop.getProperty("screenEmergencyContactsOption"));
        addCustomFieldPage.selectOptionType(prop.getProperty("typeTextOrNumberOption"));
        addCustomFieldPage.clickBtnCancel();
    }

    @Test(priority = 4)
    public void addCustomFieldClickBtnSave(){
        addCustomFieldPage = customFieldsPage.clickBtnAdd();
        addCustomFieldPage.enterInputFieldName(prop.getProperty("customFieldName"));
        addCustomFieldPage.selectOptionScreen(prop.getProperty("screenEmergencyContactsOption"));
        addCustomFieldPage.selectOptionType(prop.getProperty("typeTextOrNumberOption"));
        addCustomFieldPage.clickBtnSave();
        Assert.assertTrue(addCustomFieldPage.getTextMessageSuccess().contains("Successfully Saved"));
    }

    @Test(priority = 5)
    public void addCustomFieldExist(){
        String messageExist = "Already exists";
        addCustomFieldPage = customFieldsPage.clickBtnAdd();
        addCustomFieldPage.enterInputFieldName(prop.getProperty("customFieldName"));
        addCustomFieldPage.clickBtnSave();
        Assert.assertEquals(addCustomFieldPage.getTextMessageExist(), messageExist);
    }
    
    @Test(priority = 6)
    public void editCustomField() {
        editCustomFieldPage = customFieldsPage.clickEditCustomField();
        editCustomFieldPage.enterFieldName(prop.getProperty("customFieldNameEdit"));
        editCustomFieldPage.selectOptionScreen(prop.getProperty("screenContactDetailOption"));
        editCustomFieldPage.selectOptionType(prop.getProperty("typeDropDown"));
        editCustomFieldPage.enterSelectOption("1");
        editCustomFieldPage.clickBtnSave();
        Assert.assertTrue(editCustomFieldPage.getMessageSuccess().contains("Successfully Updated"));
    }

    @Test(priority = 7)
    public void deleteCustomField(){
        customFieldsPage.clickDeleteCustomField();
        Assert.assertTrue(editCustomFieldPage.getMessageSuccess().contains("Successfully Deleted"));
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
