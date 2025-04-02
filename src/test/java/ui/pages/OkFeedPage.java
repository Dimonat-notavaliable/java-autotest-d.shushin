package ui.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class OkFeedPage {
    String feedXpath = "//div[contains(@class, 'feed-list')]";
    String userSettingsXpath = "//button[@aria-label='Настройки профиля']";
    String logoutButton = "//a[contains(text(), 'Выйти')]";
    String logoutInput = "//input[@value='Выйти']";

    public boolean isUserLoggedIn() {
        return $x(userSettingsXpath).exists();
    }

    public boolean isFeedPageLoaded() {
        return $x(feedXpath).shouldBe(Condition.visible).isDisplayed();
    }

    public void logout() {
        $x(userSettingsXpath).click();
        $x(logoutButton).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if ($x(logoutInput).exists()) {
            $x(logoutInput).click();
        }
        $x(userSettingsXpath).shouldNotBe(Condition.exist);
    }
}
