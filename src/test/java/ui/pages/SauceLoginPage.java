package ui.pages;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;

public class SauceLoginPage {

    public void openPage() {
        open("https://www.saucedemo.com/");
    }

    public void enterUsername(String username) {
        String usernameInput = "//input[@id='user-name']";
        $x(usernameInput).setValue(username);
    }

    public void enterPassword(String password) {
        String passwordInput = "//input[@id='password']";
        $x(passwordInput).setValue(password);
    }

    public void clickLogin() {
        String loginButton = "//input[@id='login-button']";
        $x(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public void checkUnsuccessfulLogin() {
        $x("//h3[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]").shouldBe(visible);
    }

    public void checkLoginPageLoaded() {
        $x("//input[@id='user-name']").shouldBe(visible);
    }
}
