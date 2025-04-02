package ui.tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ui.pages.OkFeedPage;
import ui.pages.OkLoginPage;
import java.time.Duration;
import java.util.stream.Stream;


@Tag("LoginTests")
public class OkLoginTest {
    OkLoginPage loginPage;
    OkFeedPage feedPage;

    @BeforeEach
    void setup() {
        loginPage = new OkLoginPage();
        feedPage = new OkFeedPage();

        loginPage.openPage();
        loginPage.checkLoginPageLoaded();
    }

    @AfterEach
    void tearDown() {
        if (feedPage.isUserLoggedIn()) {
            feedPage.logout();
        }
    }

    @Test
    @DisplayName("Успешный логин")
    void successfulLogin() {
        loginPage.login("technopol74", "technopolisPassword");
        assertTrue(feedPage.isFeedPageLoaded(), "Лента не загрузилась после логина");
    }

    @Test
    @DisplayName("Логин с капчей")
    @Disabled("Тест ещё не реализован")
    void loginWithCaptcha() {
        assertTrue(true, "Этот тест не должен выполниться");
    }

    @ParameterizedTest
    @CsvSource({
        "wrong_user, wrong_password",
        "technopol74, wrong_password"
    })
    @DisplayName("Негативные тесты логина")
    void unsuccessfulLogin(String username, String password) {
        loginPage.login(username, password);
        assertTrue(loginPage.isErrorDisplayed(), "Ошибка при неправильном логине не отображается");
    }

    @Test
    @Tag("Security")
    @DisplayName("Логин с SQL-инъекцией")
    void sqlInjectionLogin() {
        loginPage.login("' OR '1'='1", "password");
        assertTrue(loginPage.isErrorDisplayed(), "SQL-инъекция сработала");
    }

    @Nested
    @DisplayName("Тесты UI элементов формы логина")
    class LoginFormUITests {

        @Test
        @DisplayName("Поля ввода и кнопка входа присутствуют")
        void loginFieldsAreVisible() {
            assertAll(
                () -> assertTrue(loginPage.isLoginFieldVisible(), "Поле логина отсутствует"),
                () -> assertTrue(loginPage.isPasswordFieldVisible(), "Поле пароля отсутствует"),
                () -> assertTrue(loginPage.isLoginButtonVisible(), "Кнопка входа отсутствует")
            );
        }

        @Test
        @DisplayName("Маскировка пароля")
        void passwordMasking() {
            loginPage.enterPassword("secret");
            assertTrue(loginPage.isPasswordMasked(), "Пароль не скрыт");
        }
    }

    @TestFactory
    Stream<DynamicTest> loginTimeoutTests() {
        return Stream.of(
            dynamicTest("Логин за 3 секунды", () -> {
                loginPage.openPage();
                assertTimeout(Duration.ofSeconds(3), () -> {
                    loginPage.checkLoginPageLoaded();
                    loginPage.login("technopol74", "technopolisPassword");
                });
                if (feedPage.isUserLoggedIn()) {
                    feedPage.logout();
                }
            }),
            dynamicTest("Логин за 5 секунд", () -> {
                loginPage.openPage();
                assertTimeout(Duration.ofSeconds(5), () -> {
                    loginPage.checkLoginPageLoaded();
                    loginPage.login("technopol74", "technopolisPassword");
                });
                if (feedPage.isUserLoggedIn()) {
                    feedPage.logout();
                }
            })
        );
    }
}
