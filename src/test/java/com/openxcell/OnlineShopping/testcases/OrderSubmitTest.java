package com.openxcell.OnlineShopping.testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.openxcell.OnlineShopping.PageObjects.ForgotPasswordPage;
import com.openxcell.OnlineShopping.PageObjects.LandingPage;
import com.openxcell.OnlineShopping.PageObjects.MyCartPage;
import com.openxcell.OnlineShopping.PageObjects.ProductCataloguePage;
import com.openxcell.OnlineShopping.PageObjects.SignupPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrderSubmitTest {

	public WebDriver driver;
	public WebDriverWait wait;
	public String productName = "ADIDAS ORIGINAL";
	public String countryName = "Singapore";
	
	public LandingPage landingPage;
	public SignupPage signupPage;
	public ForgotPasswordPage forgotPasswordPage;
	public ProductCataloguePage productCatalogue;
	public MyCartPage myCartPage;

	public void objectInitilization() {
		landingPage = new LandingPage(driver);
		signupPage = new SignupPage(driver);
		forgotPasswordPage = new ForgotPasswordPage(driver);
	}
	
	@BeforeTest
	public void navigateToLandingPage() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();		
		System.out.println("Browser launched");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		objectInitilization();
	}

	@Test
	public void VerifyAndSubmitLogin() {
		landingPage.goTo();		
		wait.until(ExpectedConditions.elementToBeClickable(landingPage.txtEmail));
		productCatalogue = landingPage.loginApplication("denish.knight@gmail.com", "Test@321");
		WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));		
		Assert.assertTrue(toastMessage.isDisplayed(), "Login successfully!");
	}

	@Test(dependsOnMethods = "VerifyAndSubmitLogin")
	public void GetProducesAndAddToCart() throws InterruptedException {
		productCatalogue.getProductList();
		productCatalogue.getProductByName(productName);
		
		productCatalogue.clickAddToCartButton(productName);
		System.out.println("Item added in Cart");

		productCatalogue.waitForLoadingToDisappear();
		productCatalogue.waitForToastMessageToAppear();			
		
	 	myCartPage = landingPage.GoToCart();	
	}

	@Test(dependsOnMethods = "GetProducesAndAddToCart")
	public void VerifyCart() {
		Boolean match = myCartPage.VerifyProuctToDisplay(productName);
		Assert.assertTrue(match);
	}

	@Test(dependsOnMethods = "VerifyCart")
	public void DoCheckout() throws InterruptedException {
		
		myCartPage.doCheckout("544", "Denish Knight", countryName);

		/*Select drpMonth = new Select(
		 * driver.findElement(By.xpath("//form/div/div[2]/div/select[@class='input ddl'][1]")));
		 * drpMonth.selectByVisibleText("3");		
		 * System.out.println("Month of Expiry date selected"); Select drpYear = new
		 * Select(driver.findElement(By.xpath("//form/div/div[2]/div//select[@class='input ddl'][2]")));
		 * drpYear.selectByVisibleText("26");
		 * System.out.println("Year of Expiry date selected");
		 */
				
		String ActualMessage = myCartPage.getThankYouMessage();
		Assert.assertTrue(ActualMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
