package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class CorporateBrandingPage {

    WebDriver driver;
    WebDriverWait wait;
    ElementUtils elementUtils;
    JavascriptExecutor js;
    public CorporateBrandingPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        elementUtils = new ElementUtils(driver);
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h6[normalize-space()='Corporate Branding']")
    WebElement titleCorporateBranding;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-color-input-preview']")
    WebElement primaryColor;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters --offset-row-2']//div[@class='oxd-color-input-preview']")
    WebElement primaryFontColor;

    @FindBy(xpath = "//div[@type='color']//div[@class='oxd-color-input-preview']")
    WebElement primaryGradientColor1;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters --offset-column-3']//div[@class='oxd-color-input-preview']")
    WebElement secondaryColor;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters --offset-row-2 --offset-column-3']//div[@class='oxd-color-input-preview']")
    WebElement secondaryFontColor;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters --offset-row-3 --offset-column-3']//div[@class='oxd-color-input-preview']")
    WebElement primaryGradientColor2;

    @FindBy(css = ".oxd-color-picker-indicator")
    WebElement indicatorColorCircle;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageRequired;

    @FindBy(xpath = "//input[@type='range']")
    WebElement hex;

    @FindBy(xpath = "//div[@role='alert']//input[@class='oxd-input oxd-input--active']")
    WebElement hexInput;

    @FindBy(xpath = "(//input[@type='file'])[1]")
    WebElement clientLogo;

    @FindBy(xpath = "//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div[1]//div[1]//p[1]")
    WebElement dimensionsClientLogo;

    @FindBy(xpath = "(//input[@type='file'])[2]")
    WebElement clientBanner;

    @FindBy(xpath = "//div[@class='oxd-form-row']//div[2]//div[1]//p[1]")
    WebElement dimensionsClientBanner;

    @FindBy(xpath = "(//input[@type='file'])[3]")
    WebElement loginBanner;

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters --offset-row-2']//p[@class='oxd-text oxd-text--p orangehrm-input-hint']")
    WebElement dimensionsLoginBanner;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageIncorrectDimensions;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    WebElement messageAttachmentSizeExceeded;

    @FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
    WebElement checkboxSocialMediaImages;

    @FindBy(xpath = "//button[normalize-space()='Publish']")
    WebElement btnPublish;

    @FindBy(xpath = "//button[normalize-space()='Preview']")
    WebElement btnPreview;

    @FindBy(xpath = "//button[normalize-space()='Reset to Default']")
    WebElement btnResetToDefault;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    WebElement messageSuccess;

    @FindBy(xpath = "//div[@class='orangehrm-file-preview']")
    WebElement uploadedFile;

    @FindBy(xpath = "//label[normalize-space()='Keep Current']")
    WebElement keepCurrent;

    @FindBy(xpath = "//label[normalize-space()='Delete Current']")
    WebElement deleteCurrent;

    @FindBy(xpath = "//label[normalize-space()='Replace Current']")
    WebElement replaceCurrent;

    @FindBy(xpath = "//p[@title='clientLogo2.jpg']")
    WebElement textFileReplaceCurrent;

    public boolean displayTitleCorporateBranding(){
        return elementUtils.isElementDisplayed(titleCorporateBranding);
    }

    public String getTextTitleCorporateBranding(){
        return elementUtils.getTextOfElement(titleCorporateBranding);
    }

    public boolean displayPrimaryColor(){
        return elementUtils.isElementDisplayed(primaryColor);
    }

    public String getAttributeStylePrimaryColor(){
        return primaryColor.getAttribute("style");
    }

    public boolean displayPrimaryFontColor(){
        return elementUtils.isElementDisplayed(primaryFontColor);
    }

    public String getAttributeStylePrimaryFontColor(){
        return primaryFontColor.getAttribute("style");
    }

    public boolean displayPrimaryGradientColor1(){
        return elementUtils.isElementDisplayed(primaryGradientColor1);
    }

    public String getAttributeStylePrimaryGradientColor1(){
        return primaryGradientColor1.getAttribute("style");
    }

    public boolean displaySecondaryColor(){
        return elementUtils.isElementDisplayed(secondaryColor);
    }

    public String getAttributeStyleSecondaryColor(){
        return secondaryColor.getAttribute("style");
    }

    public boolean displaySecondaryFontColor(){
        return elementUtils.isElementDisplayed(secondaryFontColor);
    }

    public String getAttributeStyleSecondaryFontColor(){
        return secondaryFontColor.getAttribute("style");
    }

    public boolean displayPrimaryGradientColor2(){
        return elementUtils.isElementDisplayed(primaryGradientColor2);
    }

    public String getAttributeStylePrimaryGradientColor2(){
        return primaryGradientColor2.getAttribute("style");
    }

    public boolean displayCheckboxSocialMediaImages(){
        return elementUtils.isElementDisplayed(checkboxSocialMediaImages);
    }

    public boolean displayBtnPublish(){
        return elementUtils.isElementDisplayed(btnPublish);
    }

    public boolean displayBtnPreview(){
        return elementUtils.isElementDisplayed(btnPreview);
    }

    public boolean displayBtnResetToDefault(){
        return elementUtils.isElementDisplayed(btnResetToDefault);
    }

    public void clickPrimaryColor(){
        elementUtils.clickOnElement(primaryColor);
    }

    public String getMessageRequiredPrimaryColor(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public void clickPrimaryFontColor(){
        elementUtils.clickOnElement(primaryFontColor);
    }

    public String getMessageRequiredPrimaryFontColor(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public void clickPrimaryGradientColor1(){
        wait.until(ExpectedConditions.visibilityOf(primaryGradientColor1));
        elementUtils.clickOnElement(primaryGradientColor1);
    }

    public String getMessageRequiredPrimaryGradientColor1(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public void clickSecondaryColor(){
        elementUtils.clickOnElement(secondaryColor);
    }

    public String getMessageRequiredSecondaryColor(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public void clickSecondaryFontColor(){
        elementUtils.clickOnElement(secondaryFontColor);
    }

    public String getMessageRequiredSecondaryFontColor(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public void clickPrimaryGradientColor2(){
        elementUtils.clickOnElement(primaryGradientColor2);
    }

    public String getMessageRequiredPrimaryGradientColor2(){
        return elementUtils.getTextOfElement(messageRequired);
    }

    public void dragAndDropIndicatorColorCircle(int x, int y){
        Actions actions = new Actions(driver);
        actions.clickAndHold(indicatorColorCircle).moveByOffset(x, y).release().perform();

        elementUtils.clickOnElement(titleCorporateBranding);
    }

    public void dragAndDropHEX(int x){
        Actions actions = new Actions(driver);
        actions.clickAndHold(hex).moveByOffset(x, 0).release().perform();
        elementUtils.clickOnElement(titleCorporateBranding);
    }

    public void clearHexInput() {
        try {
            js.executeScript("arguments[0].value = '';", hexInput);
            js.executeScript("arguments[0].dispatchEvent(new Event('input'));", hexInput);
            elementUtils.clickOnElement(titleCorporateBranding);
        } catch (Exception e) {
            System.out.println("Failed to clear HEX input: " + e.getMessage());
        }
    }

    public void enterHexInput(String textHex){
        js.executeScript("arguments[0].value = '';", hexInput);
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", hexInput);
        elementUtils.enterInputElement(hexInput, textHex);
        elementUtils.clickOnElement(titleCorporateBranding);
    }

    public void uploadClientLogo(String filePath) {
        elementUtils.selectElementFile(clientLogo, filePath);
    }

    public String getTextContainClientLogo(){
        return elementUtils.getTextOfElement(clientLogo);
    }

    public String getTextDimensionsClientLogo(){
        return elementUtils.getTextOfElement(dimensionsClientLogo);
    }

    public String getTextIncorrectDimensionClientLogo(){
        return elementUtils.getTextOfElement(messageIncorrectDimensions);
    }

    public String getTextAttachmentSizeExceededClientLogo(){
        return elementUtils.getTextOfElement(messageAttachmentSizeExceeded);
    }

    public void uploadClientBanner(String filePath){
        elementUtils.selectElementFile(clientBanner, filePath);
    }

    public String getTextContainClientBanner(){
        return elementUtils.getTextOfElement(clientBanner);
    }

    public String getTextDimensionsClientBanner(){
        return elementUtils.getTextOfElement(dimensionsClientBanner);
    }

    public String getTextIncorrectDimensionClientBanner(){
        return elementUtils.getTextOfElement(messageIncorrectDimensions);
    }

    public String getTextAttachmentSizeExceededClientBanner(){
        return elementUtils.getTextOfElement(messageAttachmentSizeExceeded);
    }

    public void uploadClientLoginBanner(String filePath){
        elementUtils.selectElementFile(loginBanner, filePath);
    }

    public String getTextContainLoginBanner(){
        return elementUtils.getTextOfElement(loginBanner);
    }

    public String getTextDimensionsLoginBanner(){
        return elementUtils.getTextOfElement(dimensionsLoginBanner);
    }

    public String getTextIncorrectDimensionLoginBanner(){
        return elementUtils.getTextOfElement(messageIncorrectDimensions);
    }

    public String getTextAttachmentSizeExceededLoginBanner(){
        return elementUtils.getTextOfElement(messageAttachmentSizeExceeded);
    }

    public void clickOffCheckboxSocialMediaImages(){
        elementUtils.clickOffCheckbox(checkboxSocialMediaImages);
    }

    public void clickBtnPublish(){
        elementUtils.clickOnElement(btnPublish);
    }

    public void clickBtnPreview(){
        elementUtils.clickOnElement(btnPreview);
    }

    public void clickBtnResetToDefault(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        elementUtils.clickOnElement(btnResetToDefault);
    }

    public String getTextPublishSuccess(){
        WebElement successToast = wait.until(ExpectedConditions.visibilityOf(messageSuccess));
        return elementUtils.getTextOfElement(successToast);
    }

    public boolean displayUploadedFile(){
        return elementUtils.isElementDisplayed(uploadedFile);
    }

    public void clickKeepCurrent(){
        elementUtils.clickRadioButton(keepCurrent);
    }

    public void clickDeleteCurrent(){
        elementUtils.clickRadioButton(deleteCurrent);
    }

    public void clickReplaceCurrent(){
        elementUtils.clickRadioButton(replaceCurrent);
    }

    public String getTextFileReplaceCurrent(){
        return elementUtils.getTextOfElement(textFileReplaceCurrent);
    }
}
