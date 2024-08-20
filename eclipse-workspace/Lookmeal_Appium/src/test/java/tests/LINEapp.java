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

	public class LINEapp {
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
	    public void redirectToLineTest() {
	        try {
	            // Perform login and redirect to LINE app
	            performLoginAndRedirectToLine();
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

	    private void performLoginAndRedirectToLine() {
	        try {
	            // Locate and click login button
	            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("ログイン")));
	            loginButton.click();

	            // Locate and interact with email field
	            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")));
	            emailField.click();
	            emailField.clear();
	            emailField.sendKeys("joyna@mailinator.com");

	            // Locate and interact with password field
	            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")));
	            passwordField.click();
	            passwordField.clear();
	            passwordField.sendKeys("Password@123");

	            // Locate and click login button
	            WebElement finalLoginButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.Button")));
	            finalLoginButton.click();

	            // Navigate to My Page tab
	            WebElement myPageTab = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiSelector().description(\"マイページ\nタブ: 4/4\")")));
	            myPageTab.click();

	            // Locate and click "Register here" button
	            WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("登録はこちらから")));
	            registerButton.click();

	            // Assuming the LINE app is to be launched after clicking the button.
	            // Alternatively, if you need to interact with LINE app after redirection, you might need to handle it differently.
	            System.out.println("Redirected to LINE app.");

	        } catch (Exception e) {
	            e.printStackTrace();
	            restartApp();
	        }
	    }

	    private void restartApp() {
	        try {
	            System.out.println("Restarting the app...");
	            driver.activateApp("com.allright.lookmeal"); // Restart the app
	            performLoginAndRedirectToLine(); // Retry the test
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

