import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class InsuranceTest extends WebDriverSettings {


    @Test
    public void firstTest() {
        String url = "http://www.rgs.ru";

        driver.get(url);
        String path1 = "[href=\"/products/private_person/health/index.wbp\"]";
        WebElement element = driver.findElementByCssSelector(path1);
        element.click();

        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("Добровольное медицинское страхование (ДМС)")));

        String dmsPath = "[href=\"/products/private_person/health/dms/generalinfo/index.wbp\"]";

        WebElement ele = driver.findElement(By.cssSelector(dmsPath));
        JavascriptExecutor executor = driver;
        executor.executeScript("arguments[0].click();", ele);

        WebDriverWait wait2 = new WebDriverWait(driver,10);


        String title = driver.getTitle();
        Assert.assertEquals("ДМС 2019 | Рассчитать стоимость добровольного медицинского страхования и оформить ДМС в Росгосстрах", title);

        String form = "/html/body/div[8]/div/div/div/div/a[3]";
        WebElement el = driver.findElementByXPath(form);
        el.click();

        WebDriverWait wait3 = new WebDriverWait(driver,10);

        WebElement webElement = driver.findElementByXPath("/html/body/div[9]/div/div/div/div[1]/h4/b");
        Assert.assertEquals("Заявка на добровольное медицинское страхование", webElement.getText());

        String findSurname = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[1]/input";
        String findName = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[2]/input";
        String findMiddleName = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[3]/input";
        String findRegion = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[4]/select";
        String phone = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[5]/input";
        String mailField = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[6]/input";
        String comment = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[8]/textarea";
        String check = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[9]/label/input";
        String date = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[7]/input";
        String send = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[10]/div/button";

        String surname = "Фамилия";
        String name = "Имя";
        String middleName = "Отчество";
        String region = "Выберите...";
        String number = "9999999999";
        String mail = "qwertyqwerty";
        String com = "Комментарии";
        String fillDate = "01012020";



        WebElement surnameField = driver.findElementByXPath(findSurname);
        surnameField.sendKeys(surname);

        WebElement nameField = driver.findElementByXPath(findName);
        nameField.sendKeys(name);

        WebElement middleNameField = driver.findElementByXPath(findMiddleName);
        middleNameField.sendKeys(middleName);

        Select select = new Select(driver.findElementByXPath(findRegion));
        select.selectByVisibleText(region);

        WebElement phoneField = driver.findElementByXPath(phone);
        phoneField.click();
        phoneField.clear();
        phoneField.sendKeys(number);

        WebElement email = driver.findElementByXPath(mailField);
        email.sendKeys(mail);

        WebElement coms = driver.findElementByXPath(comment);
        coms.sendKeys(com);

        WebElement checkbox = driver.findElementByXPath(check);
        checkbox.click();

        WebElement dateField = driver.findElementByXPath(date);
        dateField.sendKeys(fillDate);

        WebElement apply = driver.findElementByXPath(send);
        apply.click();

        Assert.assertEquals(surname,surnameField.getText());
        Assert.assertEquals(name,nameField.getText());
        Assert.assertEquals(middleName,middleNameField.getText());
        Assert.assertEquals(mail,email.getText());
        Assert.assertEquals(com, coms.getText());


        String error = "Введите адрес электронной почты";
        String errorPath = "/html/body/div[9]/div/div/div/div[2]/div[2]/form/div[2]/div[6]/div/label/span";
        Assert.assertEquals(error, driver.findElementByXPath(errorPath).getText());

    }

}
