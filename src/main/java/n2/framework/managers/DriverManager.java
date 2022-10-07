package n2.framework.managers;

import n2.framework.utils.PropsConsts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    private WebDriver driver;

    private WebDriverWait wait;

    private PropManager propManager = PropManager.getInstance();

    private static DriverManager INSTANCE;

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private void initDriver() {
        if (propManager.getProperty(PropsConsts.TYPE_BROWSER).equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", propManager.getProperty(PropsConsts.PATH_CHROME_DRIVER_WINDOWS));
            driver = new ChromeDriver();

        } else if (propManager.getProperty(PropsConsts.TYPE_BROWSER).equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", propManager.getProperty(PropsConsts.PATH_GECKO_DRIVER_WINDOWS));
            driver = new FirefoxDriver();
        }
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
