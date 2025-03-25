package ui.pages;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

public class SauceInventoryPage {
    public void checkInventoryPageLoaded() {
        $x("//div[@id='inventory_container']").shouldBe(visible);
    }

    public void openBurgerMenu() {
        $x("//button[@id='react-burger-menu-btn']").click();
    }

    public void logout() {
        openBurgerMenu();
        $x("//a[@id='logout_sidebar_link']").click();
    }

    public String getFirstProductName() {
        return $x("//div[@class='inventory_item_name ']").getText();
    }

    public void openProductPage(String productName) {
        $x("//div[@class='inventory_item_name ' and text()='" + productName + "']").click();
    }
}
