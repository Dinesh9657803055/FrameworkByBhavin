package com.openxcell.OnlineShopping.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openxcell.OnlineShopping.FileReader.FileReader;
import com.openxcell.OnlineShopping.PageObjects.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public String browserName = getProperty("browser");
	public static Properties prop = new Properties();
	public static InputStream input = null;
	public static String value = "";
	
	public LandingPage landingPage;
	public FileReader fileReader = new FileReader();
		
	public WebDriver initializeDriver() throws IOException {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();	
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--start-maximized");
		    options.addArguments("--always-authorize-plugins");
			options.addArguments("disable-infobars"); 
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
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
	
	@BeforeMethod(alwaysRun = true)
	//@BeforeTest (alwaysRun = true)
	public void launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
	}
	
	@AfterMethod (alwaysRun = true)
	public void tearDown() {
		System.out.println("Driver going to close.");
		driver.quit();
	}
	
	public String getProperty(String key)
    {
        try {
            prop = new Properties();
            String filePath = (System.getProperty("user.dir")+"\\src\\main\\java\\com\\openxcell\\OnlineShopping\\Resources\\GlobalData.properties");
            File file = new File(filePath);
            if (file.exists()){
                prop.load(new FileInputStream(file));
                value = prop.getProperty(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
	
	
}
