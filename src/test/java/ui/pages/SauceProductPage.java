package ui.pages;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

public class SauceProductPage {
    public void checkProductPageLoaded() {
        $x("//div[@class='inventory_details_container']").shouldBe(visible);
    }

    public void checkProductDetails() {
        $x("//div[@data-test='inventory-item-name']").shouldBe(visible);
        $x("//div[@data-test='inventory-item-desc']").shouldBe(visible);
        $x("//div[@data-test='inventory-item-price']").shouldBe(visible);
        $x("//button[@data-test='add-to-cart']").shouldBe(visible);
    }

    public String getProductName() {
        return $x("//div[@data-test='inventory-item-name']").getText();
    }
}
