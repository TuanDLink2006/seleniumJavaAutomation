package selenium.base;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import utils.CommonUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Base {

    WebDriver driver;
    Properties prop;

    public WebDriver openBrowserAndApplication() {
        prop = CommonUtils.loadProperties();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(prop.getProperty("loginPageURL"));
        return driver;

    }

    public WebDriver navigateToRegisterPage(WebDriver driver,String URL) {
        driver.navigate().to(URL);
        return driver;
    }

    public WebDriver navigateBack(WebDriver driver) {
        driver.navigate().back();
        return driver;
    }

    public WebDriver enterDetailsIntoLoginPageFields(WebDriver driver) {

        prop = CommonUtils.loadProperties();
        Actions actions = new Actions(driver);
        actions.sendKeys(prop.getProperty("existingUserName")).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("existingPassword"))
                .pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER).pause(Duration.ofSeconds(2)).build().perform();

        return driver;

    }

    public String getHTMLCodeOfThePage() {
        return driver.getPageSource();
    }

    public String getPageURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public void closeBrowser(WebDriver driver) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public WebDriver refreshPage(WebDriver driver) {
        driver.navigate().refresh();
        return driver;
    }

}
