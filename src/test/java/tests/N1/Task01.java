package tests.N1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Task01 {

    WebDriver driver;
    WebDriverWait wait;
    WebElement baseMenu;
    WebElement secondMenu;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://training.appline.ru/user/login");
        wait = new WebDriverWait(driver, 2, 1000);
    }

    @Test
    public void test() {
        //�����������
        baseMenu = driver.findElement(By.xpath("//*[@id=\"prependedInput\"]"));
        fillInputField(baseMenu, "Taraskina Valeriya");
        baseMenu = driver.findElement(By.xpath("//*[@id=\"prependedInput2\"]"));
        fillInputField(baseMenu, "testing");
        baseMenu = driver.findElement(By.xpath("//*[@id=\"_submit\"]"));
        baseMenu.click();

        //�������� ������� ���������
        baseMenu = driver.findElement(By.xpath("//h1[@class='oro-subtitle']"));
        Assert.assertTrue("��������� �� �����������", baseMenu.isDisplayed());
        Assert.assertTrue("����� ��������� �� ���������", "������ �������� �������".equals(baseMenu.getText()));

        //����� "������������" � ���� "�������"
        baseMenu = driver.findElement(By.xpath("//div/ul/li/a/span[contains(text(), '�������')]"));
        baseMenu.click();
        baseMenu = driver.findElement(By.xpath("//a/span[contains(text(), '������������')]"));
        baseMenu.click();

        //�������� ������������
        baseMenu = driver.findElement(By.xpath(
                "//div[@class='btn-group']/div/a[@href='/business-trip/create/']"));
        baseMenu.click();

        //�������� ������� ���������
        baseMenu = driver.findElement(By.xpath("//h1[@class='user-name']"));
        Assert.assertTrue("��������� �� �����������", baseMenu.isDisplayed());
        Assert.assertTrue("����� ��������� �� ���������", "������� ������������".equals(baseMenu.getText()));

        //���������� �����, ����� �������������
        baseMenu = driver.findElement(By.xpath("//select[@name='crm_business_trip[businessUnit]']"));
        baseMenu.click();
        secondMenu = driver.findElement(By.xpath(
                "//option[contains(text(), '����� ���������� ����������')]"));
        secondMenu.click();

        //����� �����������
        baseMenu = driver.findElement(By.xpath("//a[@id='company-selector-show']"));
        baseMenu.click();
        baseMenu = driver.findElement(By.xpath("//div[@class='select2-container select2 input-widget']"));
        baseMenu.click();
        baseMenu = driver.findElement(By.xpath(
                "//div[contains(text(), '(����) ���������� ����������� ���������')]"));
        baseMenu.click();

        //����� ��������
        baseMenu = driver.findElement(By.xpath("//input[@value='1']"));
        baseMenu.click();

        //����� ������� ������� � ��������
        baseMenu = driver.findElement(By.xpath("//input[@name='crm_business_trip[departureCity]']"));
        baseMenu.clear();
        baseMenu.sendKeys("������, ������");
        baseMenu = driver.findElement(By.xpath("//input[@name='crm_business_trip[arrivalCity]']"));
        baseMenu.clear();
        baseMenu.sendKeys("������, ������");

        //����� ���
        baseMenu = driver.findElement(By.xpath(
                "//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]"));
        baseMenu.sendKeys("29.10.2022");
        baseMenu = driver.findElement(By.xpath(
                "//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]"));
        baseMenu.sendKeys("30.10.2022");
        
        //���������� � ��������
        baseMenu = driver.findElement(By.xpath(
                "//button[@data-action='{\"route\":\"crm_business_trip_index\"}']"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", baseMenu);

        //�������� ��������� ���������
        baseMenu = driver.findElement(By.xpath(
                "//*[contains(text(), '������ ������������� ����������� �� ����� ���� ������')]"));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
                "//*[contains(text(), '������ ������������� ����������� �� ����� ���� ������')]"))));
        Assert.assertTrue("��������� �� �����������", baseMenu.isDisplayed());
        Assert.assertTrue("����� ��������� �� ���������",
                "������ ������������� ����������� �� ����� ���� ������".equals(baseMenu.getText()));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void after() {
        driver.quit();
    }

    private void fillInputField(WebElement element, String value) {
        element.click();
        element.sendKeys(value);
        boolean checkField = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assert.assertTrue("���� �� ���� ���������", checkField);
    }
}
