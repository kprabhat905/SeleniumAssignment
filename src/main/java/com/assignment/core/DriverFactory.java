package com.assignment.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.assignment.utilities.ReadingPropertiesFile;

/**
 * A factory for creating Driver objects.
 */
public class DriverFactory {

	/** The Constant CHROME. */
	public static final String CHROME = "chrome";
	
	/** The Constant FIREFOX. */
	public static final String FIREFOX = "firefox";
	
	/** The Constant INTERNET_EXPLORER. */
	public static final String INTERNET_EXPLORER = "ie";
	
	/** The driver. */
	private final ThreadLocal<WebDriver> driver; 
	
	/**
	 * The Class LazyHolder.
	 */
	private static class LazyHolder {
		
		/** The Constant INSTANCE. */
		private static final DriverFactory INSTANCE = new DriverFactory();
	}

	/**
	 * Gets the single instance of DriverFactory.
	 *
	 * @return single instance of DriverFactory
	 */
	public static DriverFactory getInstance()
	{
		return LazyHolder.INSTANCE;
	}

	/**
	 * Instantiates a new driver factory.
	 */
	private DriverFactory() {
		driver = new ThreadLocal<WebDriver>(); 
	}

	/**
	 * Sets the driver.
	 *
	 * @param browser the new driver
	 * @throws FrameworkException the framework exception
	 */
	public void setDriver(String browser) throws FrameworkException 
	{	
		try {
			System.out.println("setDriver Method Called");
			DesiredCapabilities capability = null;

			System.setProperty("webdriver.chrome.driver", Config.ChromeDriverPath);
			System.setProperty("webdriver.ie.driver",  Config.IEDriverPath);
			System.setProperty("webdriver.gecko.driver", Config.FirefoxDriverPath);

			if(browser.equalsIgnoreCase(CHROME))
			{
				capability = DesiredCapabilities.chrome();
				ChromeOptions option = new ChromeOptions();
				driver.set(new ChromeDriver(option));
			}
			else if(browser.equalsIgnoreCase(FIREFOX))
			{
				setFirefoxCapabilities();
			}
			else if(browser.equalsIgnoreCase(INTERNET_EXPLORER))
			{
				setIECapabilities();
			}
			else
			{
				throw new FrameworkException("Invalid browser option chosen for local execution : "+ browser);
			}
			WebDriver currentDriver = DriverFactory.getInstance().getDriver();
			currentDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			currentDriver.manage().window().maximize();
			currentDriver.get(ReadingPropertiesFile.getProperty("url"));
			System.out.println("Application Opened");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new FrameworkException(e.getMessage());
		}
	}

	/**
	 * Sets the desired capabilities of firefox.
	 */
	private void setFirefoxCapabilities() {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setCapability("marionatte", true);
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.merge(capability);
		// Creating firefox profile
		FirefoxProfile profile = new FirefoxProfile();
		// Instructing firefox to use custom download location
		profile.setPreference("browser.download.folderList", 2);
		// Setting custom download directory
		profile.setPreference("browser.download.dir", Config.ExportFilePath);
		// Skipping Save As dialog box for types of files with their MIME
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv");
		// Creating FirefoxOptions to set profile
		firefoxOptions.setProfile(profile);  

		driver.set(new FirefoxDriver(firefoxOptions));
	}

	/**
	 * Sets the IE capabilities.
	 */
	public void setIECapabilities() {
		InternetExplorerOptions ieOptions = new InternetExplorerOptions();
		ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
		ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		driver.set(new InternetExplorerDriver(ieOptions));
	}

	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	public WebDriver getDriver() // call this method to get the driver object and launch the browser
	{
		return getInstance().driver.get();
	}

	/**
	 * Removes the driver.
	 */
	public void removeDriver() // Quits the driver and closes the browser
	{
		getDriver().quit();
		getInstance().driver.remove();
	}

}

