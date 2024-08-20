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

	public class Lottery {
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
	    public void participateInGiftTest() {
	        try {
	            // Perform login
	            performLogin();

	            // Participate in the gift lottery
	            participateInGiftLottery();
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

	            // Locate and interact with email field
	            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
	            emailField.click();
	            emailField.clear();
	            emailField.sendKeys("arun@mailinator.com");

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

	    private void participateInGiftLottery() {
	        try {
	            // Navigate to the Gifts tab
	            WebElement giftsTab = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"ギフト\nタブ: 3/4\")")));
	            giftsTab.click();

	            // Locate and click participate in lottery button
	            WebElement participateButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("抽選に参加する")));
	            participateButton.click();

	            // Locate and click participate button
	            WebElement finalParticipateButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("参加する")));
	            finalParticipateButton.click();

	            // Locate and click final confirm button
	            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Button")));
	            confirmButton.click();

	        } catch (Exception e) {
	            e.printStackTrace();
	            restartApp();
	        }
	    }

	    private void restartApp() {
	        try {
	            System.out.println("Restarting the app...");
	            driver.activateApp("com.allright.lookmeal"); // Restart the app
	            participateInGiftTest(); // Retry the test
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
