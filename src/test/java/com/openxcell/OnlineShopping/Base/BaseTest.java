package com.openxcell.OnlineShopping.Base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.openxcell.OnlineShopping.FileReader.FileReader;
import com.openxcell.OnlineShopping.PageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public WebDriverWait wait;

	public static Properties prop = new Properties();
	public static InputStream input = null;
	public static String value = "";

	public LandingPage landingPage;
	public FileReader fileReader = new FileReader();
	// public String browserName = fileReader.getProperty("browser");

	public WebDriver initializeDriver(String browserName) throws IOException {
		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--start-maximized");
			options.addArguments("--always-authorize-plugins");
			options.addArguments("disable-infobars");
			//added 47 comment
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--start-maximized");
			options.addArguments("--always-authorize-plugins");
			options.addArguments("disable-infobars");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return driver;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "//report//" + testCaseName + ".png";
		File destinationFile = new File(filePath);
		FileUtils.copyFile(source, destinationFile);
		return filePath;
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	// @BeforeTest (alwaysRun = true)
	public void launchApplication(String browser) throws IOException {
		driver = initializeDriver(browser);
		landingPage = new LandingPage(driver);
		landingPage.goTo();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		System.out.println("Driver going to close.");
		driver.quit();
	}
}
