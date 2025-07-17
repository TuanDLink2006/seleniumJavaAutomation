package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class RecruitmentPage {

    WebDriver driver;
    ElementUtils elementUtils;
    public RecruitmentPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Candidates']")
    WebElement candidates;

    public CandidatesPage clickCandidates(){
        elementUtils.clickOnElement(candidates);
        return new CandidatesPage(driver);
    }

}
