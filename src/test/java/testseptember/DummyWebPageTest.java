package testseptember;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static orangehrm.Constants.*;

public class DummyWebPageTest {

    public static String baseUrl = null;
    static WebDriver chromeDriver = null;
    static ExtentSparkReporter spark;
    static ExtentReports extent;
    static ExtentTest testStep;
    int milliSeconds;

    public static void waitForNow(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void setup() {

        final Logger LOGGER = Logger.getLogger(DummyWebPageTest.class.getName());

        Properties prop = new Properties();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(PROP_FILE_PATH);
            prop.load(fileInputStream);
            baseUrl = prop.getProperty(dummyWebPageUrl);
        } catch (FileNotFoundException fnfe) {
            LOGGER.log(Level.SEVERE, "Pr̥operties file not found on the given location", fnfe);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        milliSeconds = Integer.parseInt(prop.getProperty(waitTime));
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();

        spark = new ExtentSparkReporter(extentReportForDummyWebPage);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        testStep = extent.createTest("Dummy WebPage Setup Test", "This is the Description for Dummy WebPage Setup Test");
        testStep.log(Status.INFO, "Setup completed");
        waitForNow(milliSeconds);
    }

    @AfterTest
    public void tearDown() {

        testStep = extent.createTest("Dummy WebPage Tear Down Test", "This is the Description for Dummy WebPage Tear Down Test");
        testStep.log(Status.INFO, "Execute the tear down method");
        waitForNow(milliSeconds);
        chromeDriver.close();
        testStep.info("Closed the browser");
        chromeDriver.quit();
        testStep.info("Test completed");
        extent.flush();
    }

    @Test
    public void dummyWebPageTest() {

        chromeDriver.get(baseUrl);
        chromeDriver.manage().window().maximize();
        String base64 = Utils.captureScreenshotBase64(chromeDriver);
        testStep.log(Status.INFO, "Screenshot Test 1 - Base 64").addScreenCaptureFromBase64String(base64);
        testStep.log(Status.INFO, "Screenshot Test 1.1 - Base 64").addScreenCaptureFromBase64String(base64, "Dummy App Image");
        testStep.log(Status.INFO, "Screenshot Test 2 - From Path").addScreenCaptureFromPath(Utils.captureScreenshotAndSaveInLocal("screenshotDum1.png", chromeDriver), "Dummy App Image");
        testStep.log(Status.INFO, "Screenshot Test 2.1 - From Path").addScreenCaptureFromPath(Utils.captureScreenshotAndSaveInLocal("screenshotDum2.png", chromeDriver));
    }
}
