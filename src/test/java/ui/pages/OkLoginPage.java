package ui.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class OkLoginPage {
    String emailXpath = "//input[@id='field_email']";
    String passwordXpath = "//input[@id='field_password']";
    String loginButton = "//input[@type='submit']";
    String errorMessageXpath = "//div[contains(@class, 'login_error')]";

    public void openPage() {
        open("https://ok.ru");
    }

    public boolean isLoginFieldVisible() {
        return $x(emailXpath).shouldBe(visible).isDisplayed();
    }

    public boolean isPasswordFieldVisible() {
        return $x(passwordXpath).shouldBe(visible).isDisplayed();
    }

    public boolean isLoginButtonVisible() {
        return $x(loginButton).shouldBe(visible).isDisplayed();
    }

    public boolean isPasswordMasked() {
        return $x(passwordXpath).shouldBe(visible).getAttribute("type").equals("password");
    }

    public void enterEmail(String email) {
        $x(emailXpath).shouldBe(visible).setValue(email);
    }

    public void enterPassword(String password) {
        $x(passwordXpath).shouldBe(visible).setValue(password);
    }

    public void clickLogin() {
        $x(loginButton).shouldBe(visible).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public boolean isErrorDisplayed() {
        return $x(errorMessageXpath).shouldBe(visible).isDisplayed();
    }

    public void checkLoginPageLoaded() {
        $x(emailXpath).shouldBe(visible);
    }
}
