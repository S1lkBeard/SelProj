package n2.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BusinessTripPage extends BasePage {

    @FindBy(xpath = "//div[@class='btn-group']/div/a[@href='/business-trip/create/']")
    public WebElement createBusinessTrip;

    @FindBy(xpath = "//h1[@class='user-name']")
    public WebElement header;

    @FindBy(xpath = "//select[@name='crm_business_trip[businessUnit]']")
    public WebElement DivFirst;

    @FindBy(xpath = "//option[contains(text(), 'Отдел внутренней разработки')]")
    public WebElement DivSecond;

    @FindBy(xpath = "//a[@id='company-selector-show']")
    public WebElement OrgFisrt;

    @FindBy(xpath = "//div[@class='select2-container select2 input-widget']")
    public WebElement OrgSecond;

    @FindBy(xpath = "//div[contains(text(), '(Хром) Призрачная Организация Охотников')]")
    public WebElement OrgThird;

    @FindBy(xpath = "//input[@value='1']")
    public WebElement Checkbox;

    @FindBy(xpath = "//input[@name='crm_business_trip[departureCity]']")
    public WebElement CityDep;

    @FindBy(xpath = "//input[@name='crm_business_trip[arrivalCity]']")
    public WebElement CityArr;

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")
    public WebElement DateDep;

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")
    public WebElement DateArr;

    @FindBy(xpath = "//button[@data-action='{\"route\":\"crm_business_trip_index\"}']")
    public WebElement SaveAndExit;

    @FindBy(xpath = "//*[contains(text(), 'Список командируемых сотрудников не может быть пустым')]")
    public WebElement ErrorMsg;

    public BusinessTripPage clickBusinessTrip() {
        createBusinessTrip.click();
        return pageManager.getBusinessTripPage();
    }

    public BusinessTripPage checkHeader() {
        wait.until(ExpectedConditions.visibilityOf(header));
        Assert.assertTrue("Заголовок не отобразился", header.isDisplayed());
        Assert.assertTrue("Текст заголовка не совпадает", "Создать командировку".equals(header.getText()));
        return this;
    }

    public BusinessTripPage choiseDiv() {
        DivFirst.click();
        DivSecond.click();
        return pageManager.getBusinessTripPage();
    }

    public BusinessTripPage choiseOrg() {
        OrgFisrt.click();
        OrgSecond.click();
        OrgThird.click();
        return pageManager.getBusinessTripPage();
    }

    public BusinessTripPage choiseCheckbox() {
        Checkbox.click();
        return pageManager.getBusinessTripPage();
    }

    public BusinessTripPage choiseCity(String cityDep, String cityArr) {
        CityDep.clear();
        CityDep.sendKeys(cityDep);
        CityArr.clear();
        CityArr.sendKeys(cityArr);
        return pageManager.getBusinessTripPage();
    }

    public BusinessTripPage choiseDate(String dateDep, String dateArr) {
        DateDep.clear();
        DateDep.sendKeys(dateDep);
        DateArr.clear();
        DateArr.sendKeys(dateArr);
        return pageManager.getBusinessTripPage();
    }

    public BusinessTripPage clickSaveAndExit() {
        js.executeScript("arguments[0].click()", SaveAndExit);
        return pageManager.getBusinessTripPage();
    }

    public BusinessTripPage checkErrorMsg() {
        wait.until(ExpectedConditions.visibilityOf(ErrorMsg));
        Assert.assertTrue("Ошибка не отобразилась", ErrorMsg.isDisplayed());
        Assert.assertTrue("Текст ошибки не совпадает", "Список командируемых сотрудников не может быть пустым".equals(ErrorMsg.getText()));
        return this;
    }
}
