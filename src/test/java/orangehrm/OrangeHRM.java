package orangehrm;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.Port.Info;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM {

	public static String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	WebDriver chromeDriver;
	String randomNumber;
	String emplFName;
	String empMName;
	String empLName;
	
	ExtentSparkReporter spark;
	ExtentReports extent;
	ExtentTest testStep;
	
	@BeforeTest
	public void setup() {
		
		WebDriverManager.chromedriver().setup();
		chromeDriver = new ChromeDriver();
		chromeDriver.manage().window().maximize();
		chromeDriver.get(baseUrl);
		chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		
		spark = new ExtentSparkReporter("extent-reportohrm.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		testStep = extent.createTest("Orange HRM Test");
		testStep.log(Status.INFO, "Execute the setup method");
	}

	@AfterTest
	public void tearDown() {
		testStep.log(Status.INFO, "Execute the teardown method");
		waitForNow();
		chromeDriver.close();
		testStep.pass("Closed the browser");
		chromeDriver.quit();
		testStep.info("Test completed");
		extent.flush();
	}

	@Test(priority = 1)
	public void invalidPasswordLoginTest() {

		testStep.log(Status.INFO, "Invalid Password Login Test");
		waitForNow();
		chromeDriver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		chromeDriver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("123");
		chromeDriver.findElement(By.xpath("//button[@type='submit']")).submit();
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
		Random random = new Random();
		randomNumber = String.valueOf(random.nextInt(100));
		emplFName = "John" + randomNumber;
		empMName = "K";
		empLName = "Doe" + randomNumber;
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
		chromeDriver.findElement(By.xpath("//span[normalize-space()='PIM']")).click();
		chromeDriver.findElement(By.xpath("(//input[@placeholder='Type for hints...'])[1]")).sendKeys(emplFName);
		chromeDriver.findElement(By.xpath("//button[@type='submit']")).submit();
		chromeDriver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span']"));
		String recordFoundText = chromeDriver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span']")).getText();
		Assert.assertTrue(recordFoundText.contains(" Found"), "Validating that a record is found for the searched employee first name");
	}
	
	@Test(priority = 5)
	public void deleteEmployee() {
		chromeDriver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[1]/div/div[9]/div/button[1]")).click();
		waitForNow();
		chromeDriver.findElement(By.xpath("//*[@id=\"app\"]/div[3]/div/div/div/div[3]/button[2]")).click();
		chromeDriver.findElement(By.xpath("//span[normalize-space()='PIM']")).click();
		chromeDriver.findElement(By.xpath("(//input[@placeholder='Type for hints...'])[1]")).sendKeys(emplFName);
		chromeDriver.findElement(By.xpath("//button[@type='submit']")).submit();
		chromeDriver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span']"));
		String recordFoundText = chromeDriver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span']")).getText();
		System.out.println("Emp First Name : "+emplFName);
		System.out.println("No Records Found Text : "+recordFoundText);
		Assert.assertTrue(recordFoundText.contains("No Records Found"), "Validating that a record is not found for the searched employee first name after deletion");
	}

	@Test(priority = 6)
	public void logoutTest() {
		waitForNow();
		chromeDriver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		chromeDriver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		Assert.assertEquals(chromeDriver.getTitle(), "OrangeHRM", "Validating the page title is correct after logout");
	}

	public static void waitForNow() {
		try {
			Thread.sleep(3000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
