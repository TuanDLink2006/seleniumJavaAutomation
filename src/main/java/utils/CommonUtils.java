package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.Properties;

public class CommonUtils {

    public static String generateBrandNewEmail() {
        return new Date().toString().replaceAll("\\s", "").replaceAll("\\:", "") + "@email.com";
    }

    public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) {
        BufferedImage acutualBImg = null;
        BufferedImage expectedBImg = null;
        try {
            acutualBImg = ImageIO.read(new File(System.getProperty("user.dir")+actualImagePath));
            expectedBImg = ImageIO.read(new File(System.getProperty("user.dir")+expectedImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageDiffer imgDiffer = new ImageDiffer();
        ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, acutualBImg);

        return imgDifference.hasDiff();

    }

    public static Properties loadProperties() {

        Properties prop = new Properties();
        try {
            FileReader fr = new FileReader(
                    System.getProperty("user.dir") + "\\src\\test\\resources\\projectData.properties");
            prop.load(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;

    }

    public static void setProperties(String propertyKey, String propertyValue, Properties prop) {
        prop.setProperty(propertyKey, propertyValue);
        FileWriter fr = null;
        try {
            fr = new FileWriter(System.getProperty("user.dir") + "\\src\\test\\resources\\projectData.properties");
            prop.store(fr, "");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static WebDriver takeScreenshot(WebDriver driver, String pathToBCopied) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcScreenshot, new File(System.getProperty("user.dir") + pathToBCopied));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public static String takeScreenshotAndReturnPath(WebDriver driver, String pathToBCopied) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
        String destScreenshotPath = System.getProperty("user.dir") + pathToBCopied;
        try {
            FileUtils.copyFile(srcScreenshot, new File(destScreenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destScreenshotPath;
    }

    public static ExtentReports getExtentReport() {

        ExtentReports extentReport = new ExtentReports();

        File extentReportFile = new File(System.getProperty("user.dir") + "\\reports\\TNExtentReport.html");

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        ExtentSparkReporterConfig sparkConfig = sparkReporter.config();
        sparkConfig.setReportName("Tutorials Ninja Test Automation Results");
        sparkConfig.setDocumentTitle("TNER Results");

        extentReport.attachReporter(sparkReporter);
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReport.setSystemInfo("Username", System.getProperty("user.name"));
        extentReport.setSystemInfo("Selenium WebDriver Version", "4.24.0");

        return extentReport;

    }

    public void readWriteDataExcel() throws IOException {
        File file = new File("D:\\Selenium\\OrangeHRMSelenium\\file\\importData.csv");

        FileInputStream  fileInputStream = new FileInputStream(file);

        HSSFWorkbook wb = new HSSFWorkbook(fileInputStream);

        HSSFSheet sheet = wb.getSheet("hdhhd");

        int rowCount = sheet.getFirstRowNum() - sheet.getLastRowNum();

        for (int i = 0; i < rowCount; i++ ){
            int cellCount = sheet.getRow(1).getLastCellNum();
            for (int j = 0; j < cellCount; j++){
                String cell = sheet.getRow(1).getCell(2).getStringCellValue();
                System.out.println(cell + ",");
            }

        }
    }
}
