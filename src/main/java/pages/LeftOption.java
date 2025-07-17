package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.ElementUtils;

import java.time.Duration;

public class LeftOption {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public LeftOption(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[normalize-space()='Admin']")
    WebElement admin;

    @FindBy(xpath = "//span[normalize-space()='Leave']")
    WebElement leave;

    @FindBy(xpath = "//span[normalize-space()='PIM']")
    WebElement pim;

    @FindBy(xpath = "//span[normalize-space()='Recruitment']")
    WebElement recruitment;

    @FindBy(xpath = "//span[normalize-space()='Claim']")
    WebElement claim;

    @FindBy(xpath = "//span[normalize-space()='My Info']")
    WebElement myInfo;

    public UsersAdminPage clickAdminOption(){
        elementUtils.clickOnElement(admin);
        return new UsersAdminPage(driver);
    }

    public LeavePage clickLeaveOption(){
        elementUtils.clickOnElement(leave);
        return new LeavePage(driver);
    }

    public PIMPage clickPIMOption(){
        elementUtils.clickOnElement(pim);
        return new PIMPage(driver);
    }

    public RecruitmentPage clickRecruitmentOption(){
        elementUtils.clickOnElement(recruitment);
        return new RecruitmentPage(driver);
    }

    public ClaimPage clickClaimOption(){
        elementUtils.clickOnElement(claim);
        return new ClaimPage(driver);
    }

    public MyInfoPage clickMyInfoOption(){
        elementUtils.clickOnElement(myInfo);
        return new MyInfoPage(driver);
    }


}
