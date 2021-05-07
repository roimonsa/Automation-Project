package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

import com.google.common.collect.ImmutableMap;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.BasePage;
import utils.Utils;

public class BaseTest {

	//Objects
	WebDriver driver;

	@Parameters({"baseUrl"})
	@BeforeSuite
	void setUp(String baseUrl) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		allureEnvironmentWriter(
				ImmutableMap.<String, String>builder()
				.put("Browser.Version", ((RemoteWebDriver) driver).getCapabilities().getVersion())
				.put("Browser", ((RemoteWebDriver) driver).getCapabilities().getBrowserName())
				.put("URL", baseUrl)
				.build());
		driver.quit();
		}

	@BeforeClass
	public void setup(ITestContext testContext) {
		//Setup chromedriver by WebDriverManager
		// WebDriverManager.chromedriver().setup();
		//Configure of the browser
		driver = new ChromeDriver();
		//Maximize the window 
		driver.manage().window().maximize();
		//Set the attribute of WebDriver
		testContext.setAttribute("WebDriver", this.driver);
		//Open the follow URL
		driver.get(Utils.readProperty("url"));
		//Waiting when the site opened
		BasePage bp = new BasePage(driver);
		bp.sleep(1000);
	}

	@AfterClass
	public void tearDown() {
		//Quit from the browser
		driver.quit();
	}
}
