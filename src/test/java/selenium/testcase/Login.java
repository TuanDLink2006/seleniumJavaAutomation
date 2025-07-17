package selenium.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LogoutPage;
import selenium.base.Base;
import utils.CommonUtils;

import java.util.Properties;

public class Login extends Base {
    public WebDriver driver;
    LoginPage loginPage;
    LogoutPage logoutPage;
    Properties prop;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);
    }

    @Test(priority = 1)
    public void loginSuccess(){
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
    }

    @Test(priority = 2)
    public void loginNotExistUsername(){
        loginPage.enterUserName(prop.getProperty("existingNotUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();
        String messageAlert = "Invalid credentials";
        Assert.assertEquals(loginPage.getTextMessageAlert(), messageAlert);
    }

    @Test(priority = 3)
    public void loginNotExistPassword(){
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingNotPassword"));
        loginPage.clickLogin();
        String messageAlert = "Invalid credentials";
        Assert.assertEquals(loginPage.getTextMessageAlert(), messageAlert);
    }

    @Test(priority = 4)
    public void loginValidatedUsername(){
        loginPage.enterUserName("");
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();

        String requiredUserName = "Required";
        Assert.assertEquals(loginPage.getTextMessageUsername(), requiredUserName);
    }

    @Test(priority = 5)
    public void loginValidatedPassword(){
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword("");
        loginPage.clickLogin();

        String requiredPassword = "Required";
        Assert.assertEquals(loginPage.getTextMessagePassword(), requiredPassword);
    }

    @Test(priority = 6)
    public void loginValidateUsernameAndPassword(){
        loginPage.enterUserName("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        String requiredUserName = "Required";
        String requiredPassword = "Required";
        Assert.assertEquals(loginPage.getTextMessageUsername(), requiredUserName);
        Assert.assertEquals(loginPage.getTextMessagePassword(), requiredPassword);
    }

    @Test(priority = 7)
    public void logoutAfterWhenLoginSuccess(){
        loginPage.enterUserName(prop.getProperty("existingUserName"));
        loginPage.enterPassword(prop.getProperty("existingPassword"));
        loginPage.clickLogin();

        logoutPage.clickUserDropdown();
        logoutPage.clickLogout();
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }
}
