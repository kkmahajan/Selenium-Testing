package test;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.GoogleSearchPage;

public class GoogleSearchTest_One {

	static WebDriver chromeDriver = null;
	public static void main(String[] args) {
		googleSearch();
	}
	
	public static void googleSearch() {
		WebDriverManager.chromedriver().setup();
		chromeDriver = new ChromeDriver();
		
		//Go to Google.com
		chromeDriver.get("https://www.google.com/");
		
		GoogleSearchPage.searchTextBox(chromeDriver).sendKeys("amazon");
		waitMethod();
		GoogleSearchPage.searchTextBox(chromeDriver).sendKeys(Keys.CONTROL, "a");
		waitMethod();
		GoogleSearchPage.searchTextBox(chromeDriver).sendKeys(Keys.DELETE);
		waitMethod();
		GoogleSearchPage.searchTextBox(chromeDriver).sendKeys("amazon.com");
		// Click on the Search Button
		GoogleSearchPage.searchButton(chromeDriver).click();
		waitMethod();
		System.out.println("Test Comple̥ted Successfully");
		chromeDriver.close();
		chromeDriver.quit();
	}
	
	public static void waitMethod() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}