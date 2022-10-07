package n2.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"prependedInput\"]")
    public WebElement login;

    @FindBy(xpath = "//*[@id=\"prependedInput2\"]")
    public WebElement password;

    @FindBy(xpath = "//*[@id=\"_submit\"]")
    public WebElement click;

    public AuthPage fillField(String nameField, String value) {
        WebElement element = null;
        switch (nameField) {
            case "Логин":
                fillInputField(login, value);
                element = login;
                break;
            case "Пароль":
                fillInputField(password, value);
                element = password;
                break;
        }
        return this;
    }

    public StartPage clickField() {
        click.click();
        return pageManager.getStartPage();
    }
}
