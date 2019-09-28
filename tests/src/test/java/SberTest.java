import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class SberTest extends WebDriverSettings{

    @Test
    public void sbertest() throws InterruptedException {
        String url = "http://www.sberbank.ru/ru/person";

        driver.get(url);
        String findGeoposition = "/html/body/div[1]/div[1]/div/div/div[4]/header/div/div/div/div[2]/div[3]/div/div/a/div/span";
        WebElement loc = driver.findElementByXPath(findGeoposition);
        loc.click();
        String point = "/html/body/div[7]/div/div/div/div/div/div/div/div[3]/div[2]/a[17]";
        driver.findElementByXPath(point).click();
        String location = "Нижегородская область";
        String newPath = "/html/body/div[1]/div[1]/div/div/div[4]/header/div/div/div/div[2]/div[3]/div/div/a/div/span";
        loc = driver.findElementByXPath(newPath);
        Assert.assertEquals(location,loc.getText());

        String footerPath = "/html/body/div[1]/div[4]/div/div/div/div[3]/footer";
        WebElement element = driver.findElement(By.xpath(footerPath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);

        String soc = "/html/body/div[1]/div[4]/div/div/div/div[3]/footer/div/div[2]/div[3]/ul";
        WebElement social = driver.findElementByXPath(soc);
        System.out.println(social.isDisplayed());
    }
}
