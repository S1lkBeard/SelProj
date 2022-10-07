package tests.n2.base;

import n2.framework.managers.DriverManager;
import n2.framework.managers.InitManager;
import n2.framework.managers.PageManager;
import n2.framework.managers.PropManager;
import n2.framework.utils.PropsConsts;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseTests {

    private DriverManager driverManager = DriverManager.getInstance();
    private PropManager propManager = PropManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();

    @BeforeClass
    public static void BeforeClass() {
        InitManager.initFramework();
    }

    @Before
    public void before() {
        driverManager.getDriver().get(propManager.getProperty(PropsConsts.BASE_URL));
    }

    @AfterClass
    public static void AfterClass() {
        InitManager.quitFramework();
    }
}
