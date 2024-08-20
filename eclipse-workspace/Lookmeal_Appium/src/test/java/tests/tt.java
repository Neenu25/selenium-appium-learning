package tests;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
public class tt {
    private AndroidDriver driver;
    private WebDriverWait wait;
    @BeforeMethod
    public void setUp() {
        try {
            File f = new File("src");
            File fs = new File(f, "hitouch169.apk");
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setDeviceName("Hitouchuser");
            options.setAutomationName("UiAutomator2");
            options.setApp(fs.getAbsolutePath());
            options.autoGrantPermissions();
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void sampleTest() {
        try {
            // Execute your test steps...
            // Capture the logcat logs
            String logOutput = driver.manage().logs().get("logcat").getAll().toString();
            String expectedText = "正しい電話番号を入力してください。";
            if (logOutput.contains(expectedText)) {
                System.out.println("Pass: The text is visible in logcat.");
            } else {
                System.out.println("Fail: The text is not visible in logcat.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Page Source for debugging: " + driver.getPageSource());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}











