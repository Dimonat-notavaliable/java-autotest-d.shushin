package ui.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.pages.SauceInventoryPage;
import ui.pages.SauceLoginPage;


public class LoginTest {
    SauceLoginPage loginPage;
    SauceInventoryPage inventoryPage;

    @BeforeEach
    void setup() {
        loginPage = new SauceLoginPage();
        inventoryPage = new SauceInventoryPage();

        loginPage.openPage();  // Открываем страницу перед каждым тестом
    }

    @Test
    void successfulLogin() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.checkInventoryPageLoaded();
    }

    @Test
    void unsuccessfulLogin() {
        loginPage.login("wrong_user", "wrong_password");
        loginPage.checkUnsuccessfulLogin();
    }
}
