package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class ElementUtils {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions actions;

    public ElementUtils(WebDriver driver){
        this.driver = driver;
        js = (JavascriptExecutor)driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    public boolean isElementDisplayed(WebElement element){
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementEnabled(WebElement element){
        try {
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisabled(WebElement element){
        try {
            return !element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementSelected(WebElement element){
        try {
            return element.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnElement(WebElement element){
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
        js.executeScript("arguments[0].click();", webElement);

        try {
            Thread.sleep(1000); // 1 giây là đủ để toast xuất hiện
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectElementFile(WebElement element, String filePath){
        element.sendKeys(filePath);
    }

    public String getTextOfElement(WebElement element) {
        try {
            return element.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found when getting text: " + e.getMessage());
            return "";
        } catch (Exception e) {
            System.out.println("Error getting text from element: " + e.getMessage());
            return "";
        }
    }

    public void inputIdElement(WebElement element, WebElement elementListBox, List<WebElement> optionElements, String textName, String expectedFullName) throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
        element.sendKeys(textName);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(elementListBox));

        for (WebElement fullName : optionElements) {
            String text = fullName.getText().trim();
            if (text.equalsIgnoreCase(expectedFullName.trim())) {
                js.executeScript("arguments[0].scrollIntoView(true);", fullName);
                fullName.click();
                break;
            }
        }
    }

    public void inputEmployeeElement(WebElement element, WebElement elementListBox, By optionLocator, String textName, String expectedFullName) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        element.clear();
        element.sendKeys(textName);

        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(elementListBox));

        List<WebElement> optionElements = driver.findElements(optionLocator);
        for (WebElement fullName : optionElements) {
            String text = fullName.getText().trim();
            if (text.equalsIgnoreCase(expectedFullName.trim())) {
                js.executeScript("arguments[0].scrollIntoView(true);", fullName);
                wait.until(ExpectedConditions.elementToBeClickable(fullName)).click();
                break;
            }
        }
    }

    public void selectOptionFromDropdown(WebElement dropdownElement, String optionText) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownElement);
        wait.until(ExpectedConditions.elementToBeClickable(dropdownElement)).click();

        By optionsLocator = By.xpath("//div[@role='option']//span");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(optionsLocator));

        List<WebElement> options = driver.findElements(optionsLocator);
        for (WebElement option : options) {
            String text = option.getText().trim();
            if (text.equalsIgnoreCase(optionText.trim())) {
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", option);
                option.click();
                break;
            }
        }
    }

    public void selectDate(WebElement dateInput,
                           WebElement yearDropdown, List<WebElement> yearOptions,
                           WebElement monthDropdown, List<WebElement> monthOptions,
                           List<WebElement> dayOptions,
                           String day, String month, String year) {

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", dateInput);
        dateInput.click();

        // Chọn năm
        wait.until(ExpectedConditions.elementToBeClickable(yearDropdown));
        js.executeScript("arguments[0].scrollIntoView(true);", yearDropdown);
        js.executeScript("arguments[0].click();", yearDropdown);

        for (WebElement y : yearOptions) {
            if (y.getText().trim().equals(year)) {
                js.executeScript("arguments[0].scrollIntoView(true);", y);
                js.executeScript("arguments[0].click();", y);
                break;
            }
        }

        // Chọn tháng
        wait.until(ExpectedConditions.elementToBeClickable(monthDropdown));
        js.executeScript("arguments[0].click();", monthDropdown);

        for (WebElement m : monthOptions) {
            if (m.getText().trim().equals(month)) {
                js.executeScript("arguments[0].click();", m);
                break;
            }
        }

        // Chọn ngày
        for (WebElement d : dayOptions) {
            if (d.getText().trim().equals(day)) {
                wait.until(ExpectedConditions.elementToBeClickable(d));
                js.executeScript("arguments[0].click();", d);
                break;
            }
        }
    }

    public void clickOffCheckbox(WebElement element){
        if (element.isSelected()){
            element.click();
        }
    }

    public void clickOnCheckbox(WebElement element){
        if (!element.isSelected()){
            element.click();
        }
    }

    public void clickRadioButton(WebElement element){
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        if (!element.isSelected()){
            element.click();
        }
    }

    public void waitForLoaderToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
    }

    public void clearInput(WebElement inputElement) {
        actions.moveToElement(inputElement)
                .click()
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .pause(Duration.ofMillis(300))
                .perform();
    }

    public void enterTextarea(WebElement element, String text){
        element.sendKeys(text);
    }

    public void enterInputElement(WebElement inputElement, String textElement) {

        js.executeScript("arguments[0].scrollIntoView(true);", inputElement);
        js.executeScript("arguments[0].focus();", inputElement);
        inputElement.sendKeys(textElement);
    }

    public void enterInputAdmin(WebElement inputElement, String textEmployeeStatus) {

        js.executeScript("arguments[0].scrollIntoView(true);", inputElement);
        js.executeScript("arguments[0].focus();", inputElement);
        inputElement.click();

        actions.moveToElement(inputElement)
                .click()
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .pause(Duration.ofMillis(200))
                .perform();

        actions.sendKeys(textEmployeeStatus).perform();

        if (!inputElement.getAttribute("value").equals(textEmployeeStatus)) {
            js.executeScript(
                    "arguments[0].value = arguments[1];" +
                            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
                            "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
                    inputElement, textEmployeeStatus
            );
        }
    }

    public void enterInputEdit(WebElement inputElement, String text) {
        actions.moveToElement(inputElement)
                .click()
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .pause(Duration.ofMillis(300))
                .sendKeys(text)
                .pause(Duration.ofMillis(300))
                .sendKeys(Keys.TAB)
                .perform();
    }

    public void enterInputNameForceJS(WebElement inputElement, String text) {

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", inputElement);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript(
                "const input = arguments[0];" +
                        "input.focus();" +
                        "input.value = arguments[1];" +
                        "input.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "input.dispatchEvent(new Event('change', { bubbles: true }));" +
                        "input.dispatchEvent(new Event('blur', { bubbles: true }));",
                inputElement, text
        );
        String currentValue = inputElement.getAttribute("value");
        System.out.println("Giá trị sau khi set: " + currentValue);

        if (!currentValue.equals(text)) {
            js.executeScript(
                    "const input = arguments[0];" +
                            "input.value = arguments[1];" +
                            "input.dispatchEvent(new Event('input', { bubbles: true }));",
                    inputElement, text
            );
        }
    }

    public void clickEditElement(List<WebElement> rows, String textElement){
        for (WebElement row : rows) {
            String text = row.getText();
            if (text != null && text.contains(textElement)) {
                WebElement btnEditInRow = row.findElement(By.xpath(".//i[contains(@class,'bi-pencil-fill')]"));
                btnEditInRow.click();
                break;
            }
        }
    }

    public void clickDeleteElement(List<WebElement> rows, String textElement){
        for (WebElement row : rows) {
            String text = row.getText();
            if (text != null && text.contains(textElement)) {
                WebElement btnDeleteInRow = row.findElement(By.xpath(".//i[@class='oxd-icon bi-trash']"));
                btnDeleteInRow.click();
                break;
            }
        }
    }

    public boolean downloadFile(WebElement downloadElement, String expectedName, String downloadPath, int timeoutSec) {
        try {
            downloadElement.click();

            // Bắt đầu chờ file xuất hiện
            File folder = new File(downloadPath);
            long endTime = System.currentTimeMillis() + timeoutSec * 1000;

            while (System.currentTimeMillis() < endTime) {
                File[] files = folder.listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.getName().contains(expectedName)
                                && !file.getName().endsWith(".crdownload")
                                && file.length() > 0) {
                            System.out.println("File tải thành công: " + file.getName());
                            return true;
                        }
                    }
                }

                Thread.sleep(1000); // Chờ 1 giây rồi kiểm tra lại
            }

            System.out.println("Hết thời gian chờ. Không thấy file hợp lệ.");
            return false;

        } catch (Exception e) {
            System.out.println("Lỗi khi tải file: " + e.getMessage());
            return false;
        }
    }


}
