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

public class RedirectionTest {
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

    @Test
    public void redirectionTest() {
        try {
            // Perform the series of actions for redirection
            performRedirectionActions();
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

    private void performRedirectionActions() {
        try {
            // Login process
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("ログイン")));
            loginButton.click();

            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
            emailField.click();
            emailField.clear();
            emailField.sendKeys("jilsa@mailinator.com");

            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
            passwordField.click();
            passwordField.clear();
            passwordField.sendKeys("Password@123");

            WebElement loginSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Button")));
            loginSubmitButton.click();

            // Redirection actions
            WebElement collectionTab = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"コレクション\nタブ: 2/4\")")));
            collectionTab.click();

            WebElement giftTab = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"ギフト\nタブ: 3/4\")")));
            giftTab.click();

            WebElement myPageTab = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"マイページ\nタブ: 4/4\")")));
            myPageTab.click();

            WebElement accountInfoButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("アカウント情報")));
            accountInfoButton.click();

            WebElement imageView = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.ImageView")));
            imageView.click();

            WebElement searchTab = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"さがす\nタブ: 1/4\")")));
            searchTab.click();

            WebElement carbsButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("炭水化物")));
            carbsButton.click();

            WebElement proteinButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("たんぱく質")));
            proteinButton.click();

            WebElement fatButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("脂質")));
            fatButton.click();

            WebElement sevenElevenButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("セブンイレブン")));
            sevenElevenButton.click();

            WebElement familyMartButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("ファミリーマート")));
            familyMartButton.click();

            WebElement lawsonButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("ローソン")));
            lawsonButton.click();

            WebElement mcdonaldsButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("マクドナルド")));
            mcdonaldsButton.click();

            WebElement starbucksButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("スターバックス")));
            starbucksButton.click();

            WebElement generalCookingButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("一般料理")));
            generalCookingButton.click();

            WebElement ingredientsButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("食材")));
            ingredientsButton.click();

            // Clicking on ImageViews
            WebElement imageViewFirst = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(0)")));
            imageViewFirst.click();

            WebElement imageViewSecond = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(0)")));
            imageViewSecond.click();

        } catch (Exception e) {
            e.printStackTrace();
            restartApp();
        }
    }

    private void restartApp() {
        try {
            System.out.println("Restarting the app...");
            driver.activateApp("com.allright.lookmeal"); // Restart the app
            performRedirectionActions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
