package n2.framework.managers;

import java.util.concurrent.TimeUnit;

import static n2.framework.utils.PropsConsts.IMPLICITLY_WAIT;
import static n2.framework.utils.PropsConsts.PAGE_LOAD_TIMEOUT;

public class InitManager {

    private static final PropManager props = PropManager.getInstance();

    private static final DriverManager driverManager = DriverManager.getInstance();

    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().
                pageLoadTimeout(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts().
                implicitlyWait(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }
}
