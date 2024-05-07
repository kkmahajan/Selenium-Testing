package ExtentReports;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ExtentReportDemo {

	static WebDriver chromeDriver = null;

	public static void main(String[] args) {

		ExtentSparkReporter spark = new ExtentSparkReporter("extent-report.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);

		ExtentTest testStep = extent.createTest("Google Search Text");

		WebDriverManager.chromedriver().setup();
		chromeDriver = new ChromeDriver();

		testStep.log(Status.INFO, "Starting google search test");
		chromeDriver.get("https://www.google.com/");
		testStep.pass("Navigated to the website Google.com");

		WebElement googleTextBox = chromeDriver.findElement(By.id("APjFqb"));
		googleTextBox.sendKeys("amazon");
		testStep.pass("Entered search string amazon");

		googleTextBox.sendKeys(Keys.CONTROL, "a");
		googleTextBox.sendKeys(Keys.DELETE);
		testStep.pass("Removed the first search string amazon");

		googleTextBox.sendKeys("amazon.com");
		testStep.pass("Entered search string amazon.com");

		chromeDriver.findElement(By.name("btnK")).click();
		testStep.pass("Clicked on the search button");

		chromeDriver.close();
		chromeDriver.quit();

		testStep.pass("Closed the browser");
		testStep.info("Test completed");

		extent.flush();
	}

}
