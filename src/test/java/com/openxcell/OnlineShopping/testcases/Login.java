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

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

	public WebDriver driver;
	public WebDriverWait wait;
	public String productName = "ADIDAS ORIGINAL";
	public String countryName = "Singapore";

	@BeforeTest
	public void navigateToForgotPasswordForm() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Browser launched");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		System.out.println("Redirected on Login form");
	}

	@Test
	public void VerifyAndSubmitLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form/div/input[@id='userEmail']")))
				.sendKeys("denish.knight@gmail.com");
		// driver.findElement(By.xpath("userEmail")).sendKeys("bhavin.dholakiya@openxcell.com");
		System.out.println("Email address entered");
		driver.findElement(By.xpath("//form/div/input[@id='userPassword']")).sendKeys("Test@321");
		System.out.println("Password entered");
		driver.findElement(By.xpath("//form/input[@id='login']")).click();
		System.out.println("Login button clicked");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		Assert.assertTrue(true, "Login successfully!");

//		WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//		String ActualMessage = toastMessage.findElement(By.cssSelector("div div[role='alert']")).getText();
//		System.out.println("Actual message is: "+ActualMessage);
	}

	@Test(dependsOnMethods = "VerifyAndSubmitLogin")
	public void GetProducesAndAddToCart() throws InterruptedException {
		List<WebElement> products = driver.findElements(By.xpath(
				"//section[@id='products']/div[@class='container']/div[@class='row']/div[contains(@class, 'mb-3')]"));
		System.out.println("Number of available products: " + products.size());

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL"))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		System.out.println("Item added in Cart");

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		System.out.println("Cart menu clicked");
	}

	@Test(dependsOnMethods = "GetProducesAndAddToCart")
	public void VerifyCart() {
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		System.out.println("Product found in Cart");
	}

	@Test(dependsOnMethods = "VerifyCart")
	public void DoCheckout() throws InterruptedException {
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']"))))
				.click();
		System.out.println("Checkout button clicked");

		/*Select drpMonth = new Select(
		 * driver.findElement(By.xpath("//form/div/div[2]/div/select[@class='input ddl'][1]")));
		 * drpMonth.selectByVisibleText("3");		
		 * System.out.println("Month of Expiry date selected"); Select drpYear = new
		 * Select(driver.findElement(By.
		 * xpath("//form/div/div[2]/div//select[@class='input ddl'][2]")));
		 * drpYear.selectByVisibleText("26");
		 * System.out.println("Year of Expiry date selected");
		 */
				
		driver.findElement(By.xpath("//form/div/div[2]/div/input")).sendKeys("345");
		System.out.println("CVV code entered");
		driver.findElement(By.xpath("//form/div/div[3]/div/input")).sendKeys("Denish Knight");
		System.out.println("Name on card entered");

		WebElement drpCountry = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		drpCountry.sendKeys(countryName);
		System.out.println("Country name entered");
		driver.findElement(By.xpath("//span[text()=' " + countryName + "']")).click();
		System.out.println("Country selected");

		driver.findElement(By.cssSelector(".action__submit")).click();
		String ActualMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(ActualMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		Thread.sleep(2000);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
