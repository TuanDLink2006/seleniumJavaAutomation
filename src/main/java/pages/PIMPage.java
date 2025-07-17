package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class PIMPage {

    WebDriver driver;
    ElementUtils elementUtils;
    public PIMPage(WebDriver driver){
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='oxd-topbar-body-nav-tab-item']")
    WebElement dropdownConfiguration;

    @FindBy(xpath = "//a[normalize-space()='Custom Fields']")
    WebElement customFields;

    @FindBy(xpath = "//a[normalize-space()='Data Import']")
    WebElement dataImport;

    @FindBy(xpath = "//a[normalize-space()='Add Employee']")
    WebElement addEmployee;

    public void clickDropdownConfiguration(){
        elementUtils.clickOnElement(dropdownConfiguration);
    }

    public CustomFieldsPage selectCustomFields(){
        clickDropdownConfiguration();
        elementUtils.clickOnElement(customFields);
        return new CustomFieldsPage(driver);
    }

    public DataImportPage selectDataImport(){
        clickDropdownConfiguration();
        elementUtils.clickOnElement(dataImport);
        return new DataImportPage(driver);
    }

    public AddEmployeePage clickAddEmployee(){
        elementUtils.clickOnElement(addEmployee);
        return new AddEmployeePage(driver);
    }
}
