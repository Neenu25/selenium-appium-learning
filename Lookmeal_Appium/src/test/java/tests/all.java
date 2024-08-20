package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class all {
    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
	@BeforeClass
    public void setUp() {
        try {
            // Set up the UiAutomator2Options
            UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("Lookmeal_User")
                .setAutomationName("UiAutomator2")
                .setAppPackage("com.allright.lookmeal")
                .setAppActivity("com.allright.lookmeal.MainActivity")
                .setNewCommandTimeout(Duration.ofMinutes(10))
                .autoGrantPermissions(); // Automatically grant all app permissions

            // Initialize the AndroidDriver
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test(priority=1)
    public void validLoginTest() {
        try {
            performAppActions("joyna@mailinator.com", "Password@123");
        } catch (Exception e) {
            e.printStackTrace();
            restartApp();
        }
    }

    @Test(priority=2)
    public void validEmailInvalidPasswordTest() {
        try {
            performAppActions("joyna@mailinator.com", "Abcd");
        } catch (Exception e) {
            e.printStackTrace();
            restartApp();
        }
    }

    @Test(priority=3)
    public void invalidEmaiValidPasswordTest() {
        try {
            performAppActions("joynamailinator.co", "Password@123");
        } catch (Exception e) {
            e.printStackTrace();
            restartApp();
        }
    }

    @Test(priority=4)
    public void invalidEmailInvalidPasswordTest() {
        try {
            performAppActions("joynamailinator.co", "Passwo123");
        } catch (Exception e) {
            e.printStackTrace();
            restartApp();
        }
    }

    @Test(priority=5)
    public void BlankEmailAndPasswordTest() {
        try {
            performAppActions("", "");
        } catch (Exception e) {
            e.printStackTrace();
            restartApp();
        }
    }

    @AfterMethod
	@AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void performAppActions(String email, String password) {
        try {
            // Locate and click login button
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("ログイン")));
            loginButton.click();

            // Debugging: Print page source after clicking login
            System.out.println("Page Source after clicking login: " + driver.getPageSource());

            // Locate and interact with email field
            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
            emailField.click();
            emailField.clear();
            emailField.sendKeys(email);

            // Locate and interact with password field
            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
            passwordField.click();
            passwordField.clear();
            passwordField.sendKeys(password);

            // Locate and click login button
            WebElement finalLoginButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Button")));
            finalLoginButton.click();

            // Add assertions or verifications for login failure here
            // Example: Check for an error message
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.allright.lookmeal:id/error_message")));
            assert errorMessage.isDisplayed() : "Error message not displayed for invalid login";

        } catch (Exception e) {
            e.printStackTrace();
            restartApp();
        }
    }

    private void restartApp() {
        try {
            System.out.println("Restarting the app...");
            driver.activateApp("com.allright.lookmeal"); // Restart the app
            performAppActions("", ""); // Perform actions with empty email and password to reset the state
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
