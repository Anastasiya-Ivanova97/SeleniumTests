import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class InsuranceTest extends WebDriverSettings {
    private PathsInsurance pathsInsurance = new PathsInsurance();
    InsuranceForm insuranceForm = new InsuranceForm();

    @Test
    public void firstTest() {
        String url = "http://www.rgs.ru";

        driver.get(url);

        WebElement element = driver.findElementByCssSelector(pathsInsurance.getPath1());
        element.click();

        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("Добровольное медицинское страхование (ДМС)")));


        WebElement ele = driver.findElement(By.cssSelector(pathsInsurance.getDmsPath()));
        JavascriptExecutor executor = driver;
        executor.executeScript("arguments[0].click();", ele);

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);


        String title = driver.getTitle();
        Assert.assertEquals("ДМС 2019 | Рассчитать стоимость добровольного медицинского страхования и оформить ДМС в Росгосстрах", title);

        WebElement el = driver.findElementByXPath(pathsInsurance.getForm());
        el.click();

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        WebElement formTitle = driver.findElementByXPath(pathsInsurance.getTitleForm());
        Assert.assertTrue(formTitle.isDisplayed());

        WebElement surnameField = driver.findElementByName(pathsInsurance.getFindSurname());
        surnameField.sendKeys(insuranceForm.getSurname());

        WebElement nameField = driver.findElementByName(pathsInsurance.getFindName());
        nameField.sendKeys(insuranceForm.getName());

        WebElement middleNameField = driver.findElementByName(pathsInsurance.getFindMiddleName());
        middleNameField.sendKeys(insuranceForm.getMiddleName());

        Select select = new Select(driver.findElementByName(pathsInsurance.getFindRegion()));
        select.selectByVisibleText(insuranceForm.getRegion());

        WebElement phoneField = driver.findElementByXPath(pathsInsurance.getPhone());
        phoneField.click();
        phoneField.clear();
        phoneField.sendKeys(insuranceForm.getNumber());

        WebElement email = driver.findElementByXPath(pathsInsurance.getMailField());
        email.sendKeys(insuranceForm.getMail());

        WebElement coms = driver.findElementByXPath(pathsInsurance.getComment());
        coms.sendKeys(insuranceForm.getCom());

        WebElement checkbox = driver.findElementByXPath(pathsInsurance.getCheck());
        checkbox.click();

        WebElement dateField = driver.findElementByXPath(pathsInsurance.getDate());
        dateField.sendKeys(insuranceForm.getFillDate());

        WebElement apply = driver.findElementById(pathsInsurance.getSend());
        apply.click();

        Assert.assertEquals(insuranceForm.getSurname(),surnameField.getText());
        Assert.assertEquals(insuranceForm.getName(),nameField.getText());
        Assert.assertEquals(insuranceForm.getMiddleName(),middleNameField.getText());
        Assert.assertEquals(insuranceForm.getMail(),email.getText());
        Assert.assertEquals(insuranceForm.getCom(), coms.getText());

        System.out.println(driver.findElementByXPath(pathsInsurance.getErrorPath()).isDisplayed() ? "Ошибка отображается" : "Ошибка не отображается");

    }

}
