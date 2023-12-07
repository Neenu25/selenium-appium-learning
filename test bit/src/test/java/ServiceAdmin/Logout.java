package ServiceAdmin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Logout {
	
	WebDriver driver;
	
//	@AfterMethod
//	public void teardown() {
//		
//		driver.quit();
//	}
	
	@BeforeMethod
	public void setup() {
		
		String browserName = "firefox";
		
		if(browserName.equals("chrome"))
			
		{
			driver = new ChromeDriver();
			
		}
		else if(browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
			
		}
		
		else if(browserName.equals("safari"))
		{
			driver = new SafariDriver();
		}
		
		
		

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
		driver.get("https://qa.serviceadmin.go-smart.app/login");
		
	}
	
	
	
	@Test
	 public void verifyLoginWithValidInputsandLogout() {
		
		driver.findElement(By.id("login-email")).sendKeys("serviceadmin@gosmart.com");	
		driver.findElement(By.name("password")).sendKeys("Gosmart1");	
		driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/div[2]/div[2]/span")).click();   //password toggle button clicking  
		driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/button")).click();
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
		
		
		driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/div[3]/ul/li[9]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div/div[3]/button[2]")).click();
		
		
	}

}
