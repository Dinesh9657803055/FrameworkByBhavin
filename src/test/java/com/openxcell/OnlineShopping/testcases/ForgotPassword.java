package com.openxcell.OnlineShopping.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ForgotPassword {

	WebDriver driver; 
	
	@BeforeTest
	public void navigateToForgotPasswordForm() {
		driver = new FirefoxDriver();
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Browser launched");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector(".forgot-password-link")).click();
		System.out.println("Forgot Password link clicked");
	}
	
	@Test(priority = 1)
	public void SubtmitForm() {		
		driver.findElement(By.xpath("//input[@placeholder='Enter your email address']")).sendKeys("bhavin.dholakiya@openxcell.com");
		System.out.println("Email address entered");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("QAwsedrf@23");
		System.out.println("New Password entered");
		driver.findElement(By.cssSelector("#confirmPassword")).sendKeys("QAwsedrf@23");
		System.out.println("Confirm Passwod entered");
		//driver.findElement(By.cssSelector("button[type='submit']")).click();
		//System.out.println("Submit button clicked");
	}
	
	@Test(priority = 2)
	public void VerifyLoginAndRegisterLink() {
		driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
		System.out.println("Login link clicked");
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		System.out.println("Register link clicked");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
