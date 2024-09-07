package testseptember;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static test.UtilsTest.waitForASec;

public class DummyWebPageTest {
    static final String webPageAddress = "C:\\Users\\admin\\IdeaProjects\\Selenium-Testing\\src\\test\\resources\\myPage.html";
    static WebDriver chromeDriver = null;

    @Test
    public void dummyWebPageTest() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.get(webPageAddress);
        chromeDriver.manage().window().maximize();
        waitForASec();
        chromeDriver.manage().window().minimize();
        waitForASec();
        chromeDriver.manage().window().maximize();
        waitForASec();
        chromeDriver.close();
        chromeDriver.quit();
    }
}
