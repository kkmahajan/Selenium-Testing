package orangehrm;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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
import static util.Utils.generateRandomText;

public class OrangeHRMTest {

    public static String baseUrl = null;
    static WebDriver chromeDriver;
    static ExtentSparkReporter spark;
    static ExtentReports extent;
    static ExtentTest testStep;
    String emplFName;
    String empMName;
    String empLName;
    int milliSeconds;

    /**
     * This method will wait for 3 seconds
     *
     * @param waitTime as integer
     */
    public static void waitForNow(int waitTime) {
        try {
            Thread.sleep(waitTime);
            testStep.log(Status.INFO, "Wait for 3 seconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void setup() {

        final Logger LOGGER = Logger.getLogger(OrangeHRMTest.class.getName());

        Properties prop = new Properties();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(PROP_FILE_PATH);
            prop.load(fileInputStream);
            baseUrl = prop.getProperty(orangeHrmBaseUrl);
        } catch (FileNotFoundException fnfe) {
            LOGGER.log(Level.SEVERE, "Pr̥operties file not found on the given location", fnfe);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        milliSeconds = Integer.parseInt(prop.getProperty(waitTime));
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.get(baseUrl);
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

        spark = new ExtentSparkReporter(extentReportForOrangeHrm);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        testStep = extent.createTest("Orange HRM Setup Test", "This is the Orange HRM test setup method");
        testStep.log(Status.INFO, "Setup completed");
        waitForNow(milliSeconds);
        testStep.addScreenCaptureFromPath(Utils.captureScreenshotAndSaveInLocal("screenshotStart.png", chromeDriver));
    }

    @AfterTest
    public void tearDown() {

        testStep = extent.createTest("Orange HRM Tear Down Test");
        testStep.log(Status.INFO, "Execute the tear down method");
        waitForNow(milliSeconds);
        chromeDriver.close();
        testStep.pass("Closed the browser");
        chromeDriver.quit();
        testStep.info("Test completed");
        extent.flush();
    }

    @Test(priority = 1)
    public void invalidPasswordLoginTest() {

        testStep = extent.createTest("Orange HRM - Login with invalid password");
        waitForNow(milliSeconds);
        testStep.log(Status.INFO, "Enter username");
        chromeDriver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
        testStep.log(Status.INFO, "Enter invalid password");
        chromeDriver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("123");
        testStep.log(Status.INFO, "Clicked on submit");
        chromeDriver.findElement(By.xpath("//button[@type='submit']")).submit();
        waitForNow(milliSeconds);
//        testStep.addScreenCaptureFromBase64String(util.Utils.captureScreenshotBase64(chromeDriver));
//        testStep.addScreenCaptureFromPath(util.Utils.captureScreenshotAndSaveInLocal("screenshot1.png", chromeDriver));
//        testStep.addScreenCaptureFromBase64String(util.Utils.captureScreenshotBase64(chromeDriver), "Login with invalid credentials");
        testStep.addScreenCaptureFromPath(Utils.captureScreenshotAndSaveInLocal("screenshot2.png", chromeDriver), "Login with invalid credentials");
        String expectedMessage = "Invalid credentials";
        String actualMessage = chromeDriver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Validating that the invalid credentials message is displayed after invlaid credentials entered");
        testStep.pass("Invalid Password Login Test Passed");
    }

    @Test(priority = 2)
    public void loginTest() {

        testStep.log(Status.INFO, "Valid Password Login Test");
        chromeDriver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
        chromeDriver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        chromeDriver.findElement(By.xpath("//button[@type='submit']")).submit();
        Assert.assertEquals(chromeDriver.getTitle(), "OrangeHRM", "Validating the page title is correct after login");
        testStep.pass("Valid Password Login Test Passed");
    }

    @Test(priority = 3)
    public void addEmployeeWithoutLoginDetailsTest() {

        testStep.log(Status.INFO, "Add Employee without login details test");
        emplFName = generateRandomText(8);
        empMName = "K";
        empLName = generateRandomText(5);
        chromeDriver.findElement(By.xpath("//span[normalize-space()='PIM']")).click();
        chromeDriver.findElement(By.xpath("//a[normalize-space()='Add Employee']")).click();
        chromeDriver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(emplFName);
        chromeDriver.findElement(By.xpath("//input[@placeholder='Middle Name']")).sendKeys(empMName);
        chromeDriver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(empLName);
        chromeDriver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
        String expectedElement = chromeDriver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();
        Assert.assertEquals(expectedElement, "Personal Details", "Validating that the personal details page is visible after adding employee");
        testStep.pass("Add Employee without login details test passed");
    }

    @Test(priority = 4)
    public void searchEmployeeUsingFName() {
        testStep.log(Status.INFO, "Search Employee using first name");
        chromeDriver.findElement(By.xpath("//span[normalize-space()='PIM']")).click();
        chromeDriver.findElement(By.xpath("(//input[@placeholder='Type for hints...'])[1]")).sendKeys(emplFName);
        chromeDriver.findElement(By.xpath("//button[@type='submit']")).submit();
        chromeDriver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span']"));
        String recordFoundText = chromeDriver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span']")).getText();
        Assert.assertTrue(recordFoundText.contains(" Found"), "Validating that a record is found for the searched employee first name");
        testStep.pass("Search Employee using first name passed");
    }

    @Test(priority = 5)
    public void deleteEmployee() {
        testStep.log(Status.INFO, "Delete Employee");
        String xpPim = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']";
        String xpEmpList = "//li[@class='oxd-topbar-body-nav-tab --visited']";
        String xpEmpNmFld = "(//input[@placeholder='Type for hints...'])[1]";
        String xpSrchBtn = "//button[@type='submit']";
        String xpDeleteBtn = "//i[@class='oxd-icon bi-trash']";
        String xpCnfrmDel = "//button[normalize-space()='Yes, Delete']";
        String xpNoRecFound = "//span[normalize-space()='No Records Found']";
        String empFullName = emplFName + " " + empLName;

        chromeDriver.findElement(By.xpath(xpPim)).click();
        chromeDriver.findElement(By.xpath(xpEmpList)).click();
        chromeDriver.findElement(By.xpath(xpEmpNmFld)).sendKeys(empFullName);
        chromeDriver.findElement(By.xpath(xpSrchBtn)).click();
        chromeDriver.findElement(By.xpath(xpDeleteBtn)).click();
        chromeDriver.findElement(By.xpath(xpCnfrmDel)).click();
        chromeDriver.findElement(By.xpath(xpEmpNmFld)).clear();
        chromeDriver.findElement(By.xpath(xpEmpNmFld)).sendKeys(empFullName);
        String text = chromeDriver.findElement(By.xpath(xpNoRecFound)).getText();
        Assert.assertEquals(text, "No Records Found", "Validating that no record is found for the searched employee first name");
        testStep.pass("Delete Employee passed");
    }

    @Test(priority = 6)
    public void logoutTest() {
        waitForNow(milliSeconds);
        chromeDriver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
        chromeDriver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
        Assert.assertEquals(chromeDriver.getTitle(), "OrangeHRM", "Validating the page title is correct after logout");
    }

    //TODO: Add code for adding screenshots to Extent Reports
}
