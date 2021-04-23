import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String page = "https://accounts.spotify.com/en/login/?continue=https:%2F%2Fwww.spotify.com%2Fapi%2Fgrowth%2Fl2l-redirect%3Flocale%3Dus&_locale=en-US";
        driver.get(page);
    }

    public boolean isLoaded() throws InterruptedException {
        Thread.sleep(5000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-username"))).isDisplayed();

    }

    public void login(String user, String password) throws InterruptedException {
        driver.findElement(By.id("login-username")).clear();
        driver.findElement(By.id("login-username")).sendKeys(user);
        driver.findElement(By.id("login-password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
    }

    public String getErrorMessageForInvalidUser() {
        WebElement errorPage = driver.findElement(By.cssSelector("span.ng-binding.ng-scope"));
        //ng-binding ng-scope - class
        return errorPage.getText();
    }
    public String getErrorMessageForEmptyUser() {
        WebElement errorPage = driver.findElement(By.cssSelector("label.control-label-validation.ng-binding.ng-scope"));
        //control-label-validation ng-binding ng-scope - class
        return errorPage.getText();
    }
}