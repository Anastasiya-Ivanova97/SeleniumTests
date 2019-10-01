import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSettings {
    public ChromeDriver driver;

    @Before
    public void setUp() {
            System.setProperty("webdriver.chrome.driver", path); //здесь будет обозначен путь до драйвера
            driver = new ChromeDriver();
         }

    @After
    public void close() {
        driver.quit();
    }
}
