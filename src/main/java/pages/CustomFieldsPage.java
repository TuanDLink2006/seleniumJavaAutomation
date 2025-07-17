package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

import java.util.List;
import java.util.Properties;

public class CustomFieldsPage {

    WebDriver driver;
    Properties prop;
    ElementUtils elementUtils;
    public CustomFieldsPage(WebDriver driver){
        this.driver = driver;
        prop = new Properties();
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Custom Fields']")
    WebElement titleCustomFields;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement btnAdd;

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    List<WebElement> jobRows;

    @FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
    WebElement yesDelete;

    public String getTextTitleCustomFields(){
        return elementUtils.getTextOfElement(titleCustomFields);
    }

    public boolean displayTitleCustomFields(){
        return elementUtils.isElementDisplayed(titleCustomFields);
    }

    public boolean displayBtnAdd(){
        return elementUtils.isElementDisplayed(btnAdd);
    }

    public boolean displayTable(){
        return elementUtils.isElementDisplayed(table);
    }

    public int getSizeRowTable(){
        return jobRows.size();
    }

    public AddCustomFieldPage clickBtnAdd(){
        elementUtils.clickOnElement(btnAdd);
        return new AddCustomFieldPage(driver);
    }

    public EditCustomFieldPage clickEditCustomField() {
        String customFieldName ="Tuan";
        elementUtils.clickEditElement(jobRows, customFieldName);
        return new EditCustomFieldPage(driver);
    }

    public void clickDeleteCustomField() {
        String customFieldNameEdit ="linh";
        elementUtils.clickDeleteElement(jobRows, customFieldNameEdit);
    }

}
