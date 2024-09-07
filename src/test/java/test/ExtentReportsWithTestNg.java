package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.GoogleSearchPageObjects;

public class ExtentReportsWithTestNg {

    static WebDriver chromeDriver = null;
    static ExtentReports extent;
    ExtentSparkReporter htmlReporter;

    @Test
    public static void googleSearchTestPass() {

        GoogleSearchPageObjects googleSearchPageObjects = new GoogleSearchPageObjects(chromeDriver);
        ExtentTest test = extent.createTest("Google Search Test Pass");

        chromeDriver.get("https://www.google.com/");
        test.pass("In Test1 Navigated to Google.com");

        googleSearchPageObjects.setTextInSearchTextBox("amazon");
        test.pass("In Test1 Entered text in search text box");

        googleSearchPageObjects.clickSearchButton();
        test.pass("In Test1 Clicked on the search button");
    }

    @Test
    public static void googleSearchTestFail() {

        GoogleSearchPageObjects googleSearchPageObjects = new GoogleSearchPageObjects(chromeDriver);
        ExtentTest test = extent.createTest("Google Search Test Fail");

        chromeDriver.get("https://www.google.com/");
        test.fail("In Test2 Navigated to Google.com");

        googleSearchPageObjects.setTextInSearchTextBox("amazon");
        test.pass("In Test2 Entered text in search text box");

        googleSearchPageObjects.clickSearchButton();
        test.pass("In Test2 Clicked on the search button");
    }

    @AfterSuite
    public static void tearDownTest() {
        extent.flush();
    }

    @BeforeSuite
    public void setUpTest() {

        htmlReporter = new ExtentSparkReporter("extent-report1.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeTest
    public void beforeTest() {

        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
    }

    @AfterTest
    public void afterTest() {
        chromeDriver.close();
        chromeDriver.quit();
    }
}
