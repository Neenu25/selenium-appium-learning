package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class invalidLogin {

    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        try {
            UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("Lookmeal_User")
                .setAutomationName("UiAutomator2")
                .setAppPackage("com.allright.lookmeal")
                .setAppActivity("com.allright.lookmeal.MainActivity")
                .setNewCommandTimeout(Duration.ofMinutes(10))
                .autoGrantPermissions();

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Increased wait time
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginTest() {
        try {
            performAppActions();
            String expectedToastText = "メールアドレスかパスワードに誤りがあります";
            String actualToastText = fetchToastMessage();
            System.out.println("Expected Toast Message: " + expectedToastText);
            System.out.println("Actual Toast Message: " + actualToastText);

            if (expectedToastText.equals(actualToastText)) {
                System.out.println("Toast message displayed as expected: " + actualToastText);
            } else {
                System.out.println("Toast message not as expected. Expected: " + expectedToastText + ", but found: " + actualToastText);
            }
        } catch (Exception e) {
            e.printStackTrace();
            restartApp();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void performAppActions() {
        try {
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("ログイン")));
            loginButton.click();

            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
            emailField.click();
            emailField.clear();
            emailField.sendKeys("murshid@mailinator.com");

            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
            passwordField.click();
            passwordField.clear();
            passwordField.sendKeys("Password");

            WebElement finalLoginButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Button")));
            finalLoginButton.click();

            Thread.sleep(5000); // Adjust wait time as necessary
        } catch (Exception e) {
            e.printStackTrace();
            restartApp();
        }
    }

    private String fetchToastMessage() {
        try {
            // Using UiAutomator to locate the toast message
            WebElement toastElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Toast\")")
            ));
            String toastMessage = toastElement.getAttribute("text");
            System.out.println("Fetched Toast Message: " + toastMessage);
            return toastMessage != null ? toastMessage : "";
        } catch (Exception e) {
            System.out.println("Toast message not found or error occurred.");
            e.printStackTrace();
            return "";
        }
    }

    private void restartApp() {
        try {
            System.out.println("Restarting the app...");
            driver.activateApp("com.allright.lookmeal");
            performAppActions();
        } catch (Exception e) {
            System.out.println("Error restarting the app.");
            e.printStackTrace();
        }
    }
}
