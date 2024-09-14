package testseptember;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import orangehrm.OrangeHRMTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static orangehrm.Constants.*;
import static orangehrm.Constants.extentReportForOrangeHrm;
import static orangehrm.OrangeHRMTest.waitForNow;
import static test.UtilsTest.waitForASec;

public class DummyWebPageTest {
    static final String webPageAddress = "C:\\Users\\admin\\IdeaProjects\\Selenium-Testing\\src\\test\\resources\\myPage.html";
    static WebDriver chromeDriver = null;
    static ExtentSparkReporter spark;
    static ExtentReports extent;
    static ExtentTest testStep;
    public static String baseUrl = null;
    int milliSeconds;


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
        testStep = extent.createTest("Dummy WebPage Setup Test", "This is the Dummy WebPage Test setup method");
        testStep.log(Status.INFO, "Setup completed");
        waitForNow(milliSeconds);
    }

    @AfterTest
    public void tearDown() {

        testStep = extent.createTest("Dummy WebPage Tear Down Test");
        testStep.log(Status.INFO, "Execute the tear down method");
        waitForNow(milliSeconds);
        chromeDriver.close();
        testStep.pass("Closed the browser");
        chromeDriver.quit();
        testStep.info("Test completed");
        extent.flush();
    }

    public static void waitForNow(int waitTime) {
        try {
            Thread.sleep(waitTime);
            testStep.log(Status.INFO, "Wait for 3 seconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dummyWebPageTest() {

        chromeDriver.get(baseUrl);
        chromeDriver.manage().window().maximize();
        waitForASec();
        chromeDriver.manage().window().minimize();
        waitForASec();
        chromeDriver.manage().window().maximize();
        testStep.addScreenCaptureFromPath(Utils.captureScreenshotAndSaveInLocal("screenshotDummy.png", chromeDriver));
    }
}
