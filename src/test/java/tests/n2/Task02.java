package tests.n2;

import n2.framework.pages.AuthPage;
import n2.framework.pages.BasePage;
import n2.framework.pages.BusinessTripPage;
import n2.framework.pages.StartPage;
import org.junit.Test;
import tests.n2.base.BaseTests;

public class Task02 extends BaseTests {

    BasePage basePage = new BasePage();
    AuthPage authPage = new AuthPage();
    StartPage startPage = new StartPage();
    BusinessTripPage businessTripPage = new BusinessTripPage();


    @Test
    public void test() {
        pageManager.getAuthPage()
                .fillField("Логин", "Taraskina Valeriya")
                .fillField("Пароль", "testing")
                .clickField()
                .checkHeader()
                .clickField()
                .clickBusinessTrip()
                .checkHeader()
                .choiseDiv()
                .choiseOrg()
                .choiseCheckbox()
                .choiseCity("Россия, Москва", "Россия, Москва")
                .choiseDate("29.10.2022", "30.10.2022")
                .clickSaveAndExit()
                .checkErrorMsg();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
