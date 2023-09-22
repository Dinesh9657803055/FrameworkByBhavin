package com.openxcell.OnlineShopping.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class UserSignup {

	public static void main(String[] args) throws InterruptedException {
		// doSignup();
		PrintStringInReverse();
	}

	public static void doSignup() throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		/*
		 * WebDriverManager.firefoxdriver().setup(); WebDriver driver = new
		 * FirefoxDriver();
		 */
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector(".text-reset")).click();
		driver.findElement(By.cssSelector("#firstName")).sendKeys("Denish");
		driver.findElement(By.cssSelector("#lastName")).sendKeys("Knight");
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("denish.knight@gmail.com");
		driver.findElement(By.cssSelector("#userMobile")).sendKeys("8596748536");

		Select drpOccupation = new Select(driver.findElement(By.xpath("(//div[@class='form-group col-md-6'] //select[@class='custom-select ng-untouched ng-pristine ng-valid'])")));
		drpOccupation.selectByVisibleText("Engineer");

		driver.findElement(By.cssSelector("input[value='Male']")).click();
		driver.findElement(By.id("userPassword")).sendKeys("Test@321");
		driver.findElement(By.id("confirmPassword")).sendKeys("Test@321");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//input[@id='login']")).click();
		Thread.sleep(3000);
		String ActualMessage = driver.findElement(By.cssSelector(".headcolor")).getText();
		String ExpectedMessage = "Account Created Successfully";
		Assert.assertEquals(ActualMessage, ExpectedMessage);
		driver.quit();
	}

	public static void PrintStringInReverse(){
		String s = "Java Programming";
		int len = s.length();
		String rev = "";

		for(int i = len-1; i>= 0 ; i--){
			rev = rev + s.charAt(i);
		}
		System.out.println("Revers of "+s+ " is "+rev);
	}
}