package tests;

import org.testng.annotations.BeforeMethod;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Registration {
    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
	@Test
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
            
            // Interact with the app elements
            performAppActions();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private void performAppActions() {
        try {
            // Add wait until elements are visible
            WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("新規登録")));
            signUpButton.click();

            // Debugging: Print page source after clicking sign-up
            System.out.println("Page Source after clicking sign-up: " + driver.getPageSource());

            // Locate email field
            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
            emailField.click();
            emailField.clear();
            emailField.sendKeys("justin1@mailinator.com");

            // Locate password field
            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
            passwordField.click();
            passwordField.clear();
            passwordField.sendKeys("Password@123");

            // Locate name field
            WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(2)")));
            nameField.click();
            nameField.clear();
            nameField.sendKeys("Justin1");

            // Select DOB from dropdown
            WebElement dobDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(1)")));
            dobDropdown.click();
            selectDOB("1998年", "9月");

            // Select Gender from dropdown
            WebElement genderDropdown = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(2)")));
            genderDropdown.click();
            selectGender("回答しない");

            WebElement finalButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("新規登録")));
            finalButton.click();

        } catch (Exception e) {
            e.printStackTrace();
            restartApp();
        }
    }

    private void selectDOB(String year, String month) {
        // Scroll to year and select
        scrollToText(year);
        WebElement yearElement = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + year + "\")")));
        yearElement.click();

        // Scroll to month and select
        scrollToText(month);
        WebElement monthElement = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + month + "\")")));
        monthElement.click();

        WebElement doneButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("完了")));
        doneButton.click();
    }

    private void selectGender(String gender) {
        // Scroll to gender and select
        scrollToText(gender);
        WebElement genderOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + gender + "\")")));
        genderOption.click();

        WebElement doneButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("完了")));
        doneButton.click();
    }

    private void scrollToText(String text) {
        // Use UiScrollable to scroll to the text
        try {
            String scrollableUiSelector = "new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                + ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))";
            driver.findElement(AppiumBy.androidUIAutomator(scrollableUiSelector));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void restartApp() {
        try {
            System.out.println("Restarting the app...");
            driver.activateApp("com.allright.lookmeal"); // Restart the app
            performAppActions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
