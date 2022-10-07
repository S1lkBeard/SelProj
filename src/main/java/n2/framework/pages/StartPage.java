package n2.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends BasePage {

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    public WebElement header;

    @FindBy(xpath = "//div/ul/li/a/span[contains(text(), 'Расходы')]")
    public WebElement expenses;

    @FindBy(xpath = "//a[@href='/business-trip/']/span")
    public WebElement businessTrip;

    public StartPage checkHeader() {
        wait.until(ExpectedConditions.visibilityOf(header));
        Assert.assertTrue("Заголовок не отобразился", header.isDisplayed());
        Assert.assertTrue("Текст заголовка не совпадает", "Панель быстрого запуска".equals(header.getText()));
        return this;
    }

    public BusinessTripPage clickField() {
        expenses.click();
        businessTrip.click();
        return pageManager.getBusinessTripPage();
    }
}
