package ui.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ui.pages.SauceInventoryPage;
import ui.pages.SauceLoginPage;
import ui.pages.SauceProductPage;

public class ProductPageTest {
    SauceLoginPage loginPage;
    SauceInventoryPage inventoryPage;
    SauceProductPage productPage;

    @BeforeEach
    void setup() {
        loginPage = new SauceLoginPage();
        inventoryPage = new SauceInventoryPage();
        productPage = new SauceProductPage();

        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.checkInventoryPageLoaded();
    }

    @Test
    void openProductPage() {
        String specificName = inventoryPage.getFirstProductName();
        inventoryPage.openProductPage(specificName);

        productPage.checkProductPageLoaded();
        productPage.checkProductDetails();
    }

    @Test
    void openSpecificProductPage() {
        String specificName = inventoryPage.getFirstProductName();
        inventoryPage.openProductPage(specificName);

        String loadedName = productPage.getProductName();
        assertEquals(specificName, loadedName);
    }
}
