import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class WebDriverSettings {
    public ChromeDriver driver;

    @Before
    public void setUp() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String path = br.readLine();
            System.setProperty("webdriver.chrome.driver", path);
            driver = new ChromeDriver();
        } catch (FileNotFoundException f) {
            System.out.println("Введите еще раз путь до файла");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
        driver.quit();
    }
}
