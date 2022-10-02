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
        //Авторизация
        baseMenu = driver.findElement(By.xpath("//*[@id=\"prependedInput\"]"));
        fillInputField(baseMenu, "Taraskina Valeriya");
        baseMenu = driver.findElement(By.xpath("//*[@id=\"prependedInput2\"]"));
        fillInputField(baseMenu, "testing");
        baseMenu = driver.findElement(By.xpath("//*[@id=\"_submit\"]"));
        baseMenu.click();

        //Проверка наличия заголовка
        baseMenu = driver.findElement(By.xpath("//h1[@class='oro-subtitle']"));
        Assert.assertTrue("Заголовок не отобразился", baseMenu.isDisplayed());
        Assert.assertTrue("Текст заголовка не совпадает", "Панель быстрого запуска".equals(baseMenu.getText()));

        //Выбор "Командировки" в меню "Расходы"
        baseMenu = driver.findElement(By.xpath("//div/ul/li/a/span[contains(text(), 'Расходы')]"));
        baseMenu.click();
        baseMenu = driver.findElement(By.xpath("//a/span[contains(text(), 'Командировки')]"));
        baseMenu.click();

        //Создание командировки
        baseMenu = driver.findElement(By.xpath(
                "//div[@class='btn-group']/div/a[@href='/business-trip/create/']"));
        baseMenu.click();

        //Проверка наличия заголовка
        baseMenu = driver.findElement(By.xpath("//h1[@class='user-name']"));
        Assert.assertTrue("Заголовок не отобразился", baseMenu.isDisplayed());
        Assert.assertTrue("Текст заголовка не совпадает", "Создать командировку".equals(baseMenu.getText()));

        //Заполнение полей, выбор подразделения
        baseMenu = driver.findElement(By.xpath("//select[@name='crm_business_trip[businessUnit]']"));
        baseMenu.click();
        secondMenu = driver.findElement(By.xpath(
                "//option[contains(text(), 'Отдел внутренней разработки')]"));
        secondMenu.click();

        //Выбор организации
        baseMenu = driver.findElement(By.xpath("//a[@id='company-selector-show']"));
        baseMenu.click();
        baseMenu = driver.findElement(By.xpath("//div[@class='select2-container select2 input-widget']"));
        baseMenu.click();
        baseMenu = driver.findElement(By.xpath(
                "//div[contains(text(), '(Хром) Призрачная Организация Охотников')]"));
        baseMenu.click();

        //Выбор чекбокса
        baseMenu = driver.findElement(By.xpath("//input[@value='1']"));
        baseMenu.click();

        //Выбор городов выбытия и прибытия
        baseMenu = driver.findElement(By.xpath("//input[@name='crm_business_trip[departureCity]']"));
        baseMenu.clear();
        baseMenu.sendKeys("Россия, Москва");
        baseMenu = driver.findElement(By.xpath("//input[@name='crm_business_trip[arrivalCity]']"));
        baseMenu.clear();
        baseMenu.sendKeys("Россия, Москва");

        //Выбор дат
        baseMenu = driver.findElement(By.xpath(
                "//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]"));
        baseMenu.sendKeys("29.10.2022");
        baseMenu = driver.findElement(By.xpath(
                "//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]"));
        baseMenu.sendKeys("30.10.2022");
        
        //Сохранение и закрытие
        baseMenu = driver.findElement(By.xpath(
                "//button[@data-action='{\"route\":\"crm_business_trip_index\"}']"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", baseMenu);

        //Проверка появления сообщения
        baseMenu = driver.findElement(By.xpath(
                "//*[contains(text(), 'Список командируемых сотрудников не может быть пустым')]"));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
                "//*[contains(text(), 'Список командируемых сотрудников не может быть пустым')]"))));
        Assert.assertTrue("Заголовок не отобразился", baseMenu.isDisplayed());
        Assert.assertTrue("Текст заголовка не совпадает",
                "Список командируемых сотрудников не может быть пустым".equals(baseMenu.getText()));

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
        Assert.assertTrue("Поле не было заполнено", checkField);
    }
}
