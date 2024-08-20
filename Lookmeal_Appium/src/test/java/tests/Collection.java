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

public class Collection {
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
    public void loginAndCreateCollectionTest() {
        try {
            // Perform login actions
            performLogin();

            // Perform actions to create a new collection
            createNewCollection();
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

    private void performLogin() {
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
            emailField.sendKeys("jilsa@mailinator.com");

            // Locate and interact with password field
            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
            passwordField.click();
            passwordField.clear();
            passwordField.sendKeys("Password@123");

            // Locate and click login button
            WebElement finalLoginButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Button")));
            finalLoginButton.click();

        } catch (Exception e) {
            e.printStackTrace();
            restartApp();
        }
    }

    private void createNewCollection() {
        try {
            // Locate and click on the Collections tab
            WebElement collectionsTab = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"コレクション\nタブ: 2/4\")")));
            collectionsTab.click();

            // Locate and click the create button
            WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Button")));
            createButton.click();

            // Locate and interact with the collection name field
            WebElement collectionNameField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.EditText")));
            collectionNameField.click();
            collectionNameField.clear();
            collectionNameField.sendKeys("collection11");

            // Locate and click the create action button
            WebElement createActionButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("作成する")));
            createActionButton.click();

        } catch (Exception e) {
            e.printStackTrace();
            restartApp();
        }
    }

    private void restartApp() {
        try {
            System.out.println("Restarting the app...");
            driver.activateApp("com.allright.lookmeal"); // Restart the app
            loginAndCreateCollectionTest(); // Retry the test
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
