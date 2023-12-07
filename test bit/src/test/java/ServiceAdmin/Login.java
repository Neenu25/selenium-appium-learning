package ServiceAdmin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login {
	
	WebDriver driver;
	
	
//	@AfterMethod
//	public void teardown() {
//		
//		driver.quit();
//	}
	
	@BeforeMethod
	public void setup() {
		
		String browserName = "chrome";
		
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
	
	
	@Test(priority=1)
	 public void verifyLoginWithValidInputs() {
		
		driver.findElement(By.id("login-email")).sendKeys("serviceadmin@gosmart.com");	
		driver.findElement(By.name("password")).sendKeys("Gosmart1");		
//		driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/div[2]/div[2]/span")).click();   //password toggle button clicking  
//		driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/button")).click();
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7000));
		
		 WebElement companyIcon = driver.findElement(By.xpath("//div[@class='navbar-header']"));

	       
	       if(companyIcon.isDisplayed()) {
	    	   System.out.println("Successfully logged in and Company icon is  displayed");
	       }
	       else
	       {
	    	   System.out.println("Login is unsuccessful.");
	       }
		
	}
	
	@Test(priority=2)
	
	public void verifyLoginwithvalidUsernameInvalidPassword() {
		
		driver.findElement(By.id("login-email")).sendKeys("serviceadmin@gosmart.com");	
		driver.findElement(By.name("password")).sendKeys("Gosmart");	
		driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/div[2]/div[2]/span")).click();   //password toggle button clicking  
		//driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/button")).click();
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		
		String errorText = "メールアドレスかパスワードが違います";
		
		String toastText2 = driver.findElement(By.xpath("//*[@class='Toastify__toast-body']")).getText();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		
		if(toastText2.equals(errorText)) {
		
			System.out.println("Login failed and toast message is "+toastText2);
		
		}
		
		else 
		{
			System.out.println("Login success and toast message is "+toastText2);
		}
		
		
	}

	
	@Test(priority=3)
	public void verifyLoginwithInvalidUsernameValidPassword() {
	
		driver.findElement(By.id("login-email")).sendKeys("serviceadmin@gosma.com");	
		driver.findElement(By.name("password")).sendKeys("Gosmart1");	
		driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/div[2]/div[2]/span")).click();   //password toggle button clicking  
		driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/button")).click();
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		
		
String invalidLoginToast3 = "メールアドレスかパスワードが違います";
		
		String toastText3 = driver.findElement(By.name("toastify-body")).getText();
		
		if(toastText3.contains(invalidLoginToast3)) {
		
			System.out.println("Login failed and toast message is "+toastText3);
		
		}
		
		else 
		{
			System.out.println("Login success and toast message is "+toastText3);
		}
		
		
		}
	
@Test(priority=4)
	public void verifyLoginwithInvalidUsernameInvalidPassword() {
	
	
	driver.findElement(By.id("login-email")).sendKeys("serviceadmin@gosma.com");	
	driver.findElement(By.name("password")).sendKeys("Gosmar");	
	driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/div[2]/div[2]/span")).click();   //password toggle button clicking  
	driver.findElement(By.id("remember-me")).click();
	driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/button")).click();

	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
	
	
	
	String invalidLoginToast4 = "メールアドレスかパスワードが違います";
	
	String toastText4 = driver.findElement(By.name("toastify-body")).getText();
	
	if(toastText4.contains(invalidLoginToast4)) {
	
		System.out.println("Login failed and toast message is "+toastText4);
	
	}
	
	else 
	{
		System.out.println("Login successand toast message is "+toastText4);
	}
	
	
}

@Test(priority=5)

public void verifyLoginwithNoInputs()  {

	driver.findElement(By.id("login-email")).sendKeys("");	
	driver.findElement(By.name("password")).sendKeys("");	
	driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/div[2]/div[2]/span")).click();   //password toggle button clicking  
	driver.findElement(By.id("remember-me")).click();
	driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/button")).click();

	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
	
	
	
	String invalidusername = "メールアドレスを入力してください";
	String invalidPassword = "パスワードを入力してください";
	
	String invalidusernameText = driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/div[1]/div")).getText();
	String invalidPasswordText = driver.findElement(By.xpath("/html/body/div/div[1]/div/div/div/div/div/div/div/form/div[2]/div[3]")).getText();
	
	if(invalidusernameText.contains(invalidusername)) {
	
		System.out.println("Invalid username : "+invalidusernameText);
	
	}
	
	else 
	{
		System.out.println("Valid username : "+invalidusernameText);
	}
	
	if(invalidPasswordText.contains(invalidPassword)) {
		
		System.out.println("Invalid Password : "+invalidPasswordText);
	
	}
	
	else 
	{
		System.out.println("Valid Password : "+invalidPasswordText);
	}

}

	
}
