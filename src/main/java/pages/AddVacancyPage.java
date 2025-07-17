package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddVacancyPage {

    WebDriver driver;
    public AddVacancyPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Add Vacancy']")
    WebElement titleAddVacancy;

    @FindBy(xpath = "//label[text()='Vacancy Name']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement inputVacancyName;

    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
    WebElement dropdownJobTitle;

    @FindBy(xpath = "//textarea[@placeholder='Type description here']")
    WebElement description;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement hiringManager;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div[2]/div/div/div/div[2]/input")
    WebElement numberOfPositions;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div/label/span")
    WebElement checkboxActive;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/div/div/label/span")
    WebElement checkboxPublishIn;

    @FindBy(xpath = "//a[@href='https://opensource-demo.orangehrmlive.com/web/index.php/recruitmentApply/jobs.rss']")
    WebElement rssFeedURL;

    @FindBy(xpath = "//a[@href='https://opensource-demo.orangehrmlive.com/web/index.php/recruitmentApply/jobs.html']")
    WebElement webPageUrl;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    WebElement btnCancel;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement btnSave;

}
