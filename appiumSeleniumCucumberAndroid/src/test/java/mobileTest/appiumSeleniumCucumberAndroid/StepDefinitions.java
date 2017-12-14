package mobileTest.appiumSeleniumCucumberAndroid;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import io.appium.java_client.remote.MobileCapabilityType;

public class StepDefinitions {

	File app = new File("/home/ninad/eclipse-workspace/aa_apk/app-qa.apk");

	DesiredCapabilities dc = new DesiredCapabilities();
	public static WebDriver driver = null;

	@Before("@setup")
	public void setUp() {

		dc.setCapability("BROWSER_NAME", "Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
		dc.setCapability("deviceName", "nexus5");

		dc.setCapability("app", app.getAbsolutePath());
		dc.setCapability("appPackage", "com.aa.android.qa");
		dc.setCapability("appActivity", "com.aa.android.view.SplashActivity");

		try {
			driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@After("@destroy")
	public void tearDown() throws Throwable {
		driver.close();
	}

	@Given("^I log into the app$")
	public void logIn() throws Throwable {
		driver.findElement(By.id("com.aa.android.qa:id/username")).sendKeys("5X78V88");
		driver.findElement(By.id("com.aa.android.qa:id/lastname")).sendKeys("Points");
		driver.findElement(By.id("com.aa.android.qa:id/password")).sendKeys("testing");
		driver.findElement(By.id("com.aa.android.qa:id/login_button")).click();
	}

}
