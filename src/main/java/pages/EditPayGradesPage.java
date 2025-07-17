package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;
import java.util.List;

public class EditPayGradesPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    public EditPayGradesPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Edit Pay Grade']")
    WebElement titleEditPayGrade;

    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active']")
    WebElement inputName;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Cancel'])[1]")
    WebElement btnCancel;

    @FindBy(xpath = "(//button[@type='submit'][normalize-space()='Save'])[1]")
    WebElement btnSave;

    @FindBy(xpath = "//h6[normalize-space()='Currencies']")
    WebElement titleCurrencies;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement btnAddCurrencies;

    @FindBy(xpath = "//div[@role='table']")
    WebElement table;

    @FindBy(xpath = "//div[@role='row']")
    List<WebElement> rows;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span']")
    WebElement noRecordsFound;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    public String getTitleEditPayGrade(){
        return elementUtils.getTextOfElement(titleEditPayGrade);
    }

    public boolean displayTitleEditPayGrade(){
        return elementUtils.isElementDisplayed(titleEditPayGrade);
    }

    public boolean displayBtnCancel(){
        return elementUtils.isElementDisplayed(btnCancel);
    }

    public boolean displayBtnSave(){
        return elementUtils.isElementDisplayed(btnSave);
    }

    public void enterInputName(String textName){
        elementUtils.enterInputAdmin(inputName, textName);
    }

    public PayGradesPage clickBtnCancel(){
        elementUtils.clickOnElement(btnCancel);
        return new PayGradesPage(driver);
    }

    public void clickBtnSave(){
        elementUtils.clickOnElement(btnSave);
    }

    public String getTextTitleCurrencies(){
        return elementUtils.getTextOfElement(titleCurrencies);
    }

    public boolean displayTitleCurrencies(){
        return elementUtils.isElementDisplayed(titleCurrencies);
    }

    public boolean displayBtnAddCurrencies(){
        return elementUtils.isElementDisplayed(btnAddCurrencies);
    }

    public boolean displayTable(){
        return elementUtils.isElementDisplayed(table);
    }

    public int getSizeRows(){
        return rows.size();
    }

    public String getTextNoRecordsFound(){
        return elementUtils.getTextOfElement(noRecordsFound);
    }

    public AddCurrencyPage clickBtnAddCurrencies(){
        elementUtils.clickOnElement(btnAddCurrencies);
        return new AddCurrencyPage(driver);
    }

    public EditCurrencyPage clickEditCurrent(){
        String textCurrent = "Bhutan Ngultrum";
        elementUtils.clickEditElement(rows, textCurrent);
        return new EditCurrencyPage(driver);
    }

    public DeletePopup clickDeleteCurrent(){
        String textCurrent = "Bhutan Ngultrum";
        elementUtils.clickDeleteElement(rows, textCurrent);
        return new DeletePopup(driver);
    }

    public String getTextMessageSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

}
