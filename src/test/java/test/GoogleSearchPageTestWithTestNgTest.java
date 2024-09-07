package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.GoogleSearchPageObjects;

import java.util.List;

import static test.UtilsTest.waitForASec;

public class GoogleSearchPageTestWithTestNgTest {

    static WebDriver chromeDriver = null;

    @Test
    public static void googleSearchTest() {

        GoogleSearchPageObjects googleSearchPageObjects = new GoogleSearchPageObjects(chromeDriver);
        chromeDriver.get("https://www.google.com/");
        chromeDriver.manage().window().maximize();
        List<WebElement> elements = googleSearchPageObjects.findMultipleElements();
        System.out.println("Elements having name as 'q' : " + elements);
        System.out.println("No of Elements having name as 'q' : " + elements.size());
        googleSearchPageObjects.findAttribute(elements.get(0));
        googleSearchPageObjects.setTextInSearchTextBox("amazon");
        googleSearchPageObjects.clickSearchButton();
        waitForASec();
        googleSearchPageObjects.refreshPage();
        waitForASec();
        googleSearchPageObjects.goToPreviousPage();
        waitForASec();
        googleSearchPageObjects.goToNextPage();
        waitForASec();
    }

    @AfterTest
    public static void tearDownTest() {
        chromeDriver.close();
        chromeDriver.quit();
        System.out.println("Test Completed Successfully");
    }

    @BeforeTest
    public void setUpTest() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
    }
}