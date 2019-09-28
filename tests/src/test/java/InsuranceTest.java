import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class InsuranceTest extends WebDriverSettings {


    @Test
    public void firstTest() throws InterruptedException {
        String url = "http://www.rgs.ru";

        driver.get(url);

        String path1 = "/html/body/div[4]/div/div[4]/a";
        WebElement element = driver.findElementByXPath(path1);
        element.click();

        WebDriverWait wait2 = new WebDriverWait(driver, 10);
        wait2.until(ExpectedConditions.elementToBeClickable(By.linkText("Добровольное медицинское страхование (ДМС)")));

        String dmsPath = "/html/body/div[5]/div/div[1]/div[1]/div/a[1]";

        WebElement ele = driver.findElement(By.xpath(dmsPath));
        JavascriptExecutor executor = driver;
        executor.executeScript("arguments[0].click();", ele);

        Thread.sleep(2000);

        String title = driver.getTitle();
        Assert.assertEquals("ДМС 2019 | Рассчитать стоимость добровольного медицинского страхования и оформить ДМС в Росгосстрах", title);

        String form = "/html/body/div[8]/div/div/div/div/a[3]";
        WebElement el = driver.findElementByXPath(form);
        el.click();

        Thread.sleep(2000);

        WebElement webElement = driver.findElementByXPath("/html/body/div[9]/div/div/div/div[1]/h4/b");
        Assert.assertEquals("Заявка на добровольное медицинское страхование", webElement.getText());

        String findSurname = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[1]/input";
        String findName = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[2]/input";
        String findMiddleName = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[3]/input";
        String findRegion = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[4]/select";
        String phone = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[5]/input";
        String mail = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[6]/input";
        String comment = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[8]/textarea";
        String check = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[9]/label/input";
        String date = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[7]/input";
        String send = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[10]/div/button";

        driver.findElementByXPath(findSurname).sendKeys("Фамилия");
        driver.findElementByXPath(findName).sendKeys("Имя");
        driver.findElementByXPath(findMiddleName).sendKeys("Отчество");
        Select select = new Select(driver.findElementByXPath(findRegion));
        select.selectByVisibleText("Выберите...");
        WebElement phoneField = driver.findElementByXPath(phone);
        phoneField.click();
        phoneField.clear();
        phoneField.sendKeys("9999999999");
        WebElement email = driver.findElementByXPath(mail);
        email.sendKeys("qwertyqwerty");
        driver.findElementByXPath(comment).sendKeys("Комментарии");
        WebElement element1 = driver.findElementByXPath(check);
        element1.click();
        driver.findElementByXPath(date).sendKeys("01012020");
        driver.findElementByXPath(send).click();
        String error = "Введите адрес электронной почты";
        String errorPath = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[6]/div/label/span";
        Assert.assertEquals(error, driver.findElementByXPath(errorPath).getText());

    }

}
