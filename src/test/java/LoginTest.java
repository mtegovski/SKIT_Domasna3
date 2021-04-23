import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = getDriver();
    }


    @Test
    public void shouldOpen() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }

    @Test
    public void shouldLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("mihailtegovski@hotmail.com", "Test_123");
        assertTrue(new ProductPage(driver).isLoaded());

    }

    @Test
    public void canNotLoginWithInvalidUserName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("fake_user", "wrong_password");
        String errorMessage = loginPage.getErrorMessageForInvalidUser();
        assertEquals(errorMessage,"Incorrect username or password.");

    }

    @Test
    public void canNotLoginWithEmptyUserName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("", "wrong_password");
        String errorMessage = loginPage.getErrorMessageForEmptyUser();
        assertEquals(errorMessage, "Please enter your Spotify username or email address.");

    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Miha/IdeaProjects/181016_Domasna3/src/main/resources/chromedriver.exe");
        return new ChromeDriver();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

}