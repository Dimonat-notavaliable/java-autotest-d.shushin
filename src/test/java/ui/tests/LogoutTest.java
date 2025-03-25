package ui.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.pages.SauceInventoryPage;
import ui.pages.SauceLoginPage;

public class LogoutTest {
    SauceLoginPage loginPage;
    SauceInventoryPage inventoryPage;

    @BeforeEach
    void setup() {
        loginPage = new SauceLoginPage();
        inventoryPage = new SauceInventoryPage();

        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    void successfulLogout() {
        inventoryPage.checkInventoryPageLoaded();
        inventoryPage.logout();

        loginPage.checkLoginPageLoaded();
    }
}
