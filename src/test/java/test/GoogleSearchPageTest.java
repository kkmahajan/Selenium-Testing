package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.GoogleSearchPageObjects;

public class GoogleSearchPageTest {

    static WebDriver chromeDriver = null;

    public static void main(String[] args) {

        googleSearchTest();
    }

    public static void googleSearchTest() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();

        GoogleSearchPageObjects googleSearchPageObjects = new GoogleSearchPageObjects(chromeDriver);
        chromeDriver.get("https://www.google.com/");

        googleSearchPageObjects.setTextInSearchTextBox("amazon");
        googleSearchPageObjects.clickSearchButton();
        chromeDriver.close();
        chromeDriver.quit();
    }
}