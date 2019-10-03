import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SberTest extends WebDriverSettings{
    private SberPaths sberPaths = new SberPaths();
    @Test
    public void sbertest() {
        String url = "http://www.sberbank.ru/ru/person";

        driver.get(url);

        WebElement loc = driver.findElementByXPath(sberPaths.getFindGeoposition());
        loc.click();

        driver.findElementByXPath(sberPaths.getPoint()).click();

        loc = driver.findElementByXPath(sberPaths.getNewPath());
        Assert.assertTrue(loc.isDisplayed());

        WebElement element = driver.findElement(By.xpath(sberPaths.getFooterPath()));
        driver.executeScript("arguments[0].scrollIntoView(true);", element);

        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        WebElement social = driver.findElementByXPath(sberPaths.getSoc());
        Assert.assertTrue(social.isDisplayed());
    }
}
