package test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.GoogleSearchPageObjects;

public class GoogleSearchPageTestWithTestNg {

	static WebDriver chromeDriver = null;
	
	@BeforeTest
	public void setUpTest() {
		WebDriverManager.chromedriver().setup();
		chromeDriver = new ChromeDriver();
	}
	
	@Test
	public static void googleSearchTest() {
		
		
		GoogleSearchPageObjects googleSearchPageObjects = new GoogleSearchPageObjects(chromeDriver);
		chromeDriver.get("https://www.google.com/");
		
		googleSearchPageObjects.setTextInSearchTextBox("amazon");
		googleSearchPageObjects.clickSearchButton();
		
	}
	
	@AfterTest
	public static void tearDownTest() {
		chromeDriver.close();
		chromeDriver.quit();
		System.out.println("Test Completed Successfully");
	}
	
	

}
