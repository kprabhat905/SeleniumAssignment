package com.assignment.core;

import com.assignment.utilities.ReadingPropertiesFile;

/**
 * The Class Config.
 */
public class Config {
	
	/** The relative chrome driver path. */
	private static String relativeChromeDriverPath = "\\Resources\\drivers\\chromedriver.exe";
	
	/** The relative firefox driver path. */
	private static String relativeFirefoxDriverPath = "\\Resources\\drivers\\geckodriver.exe";
	
	/** The relative IE driver path. */
	private static String relativeIEDriverPath = "\\Resources\\drivers\\IEDriverServer.exe";

	/** The IE driver path. */
	public static String IEDriverPath;
	
	/** The Chrome driver path. */
	public static String ChromeDriverPath;
	
	/** The Firefox driver path. */
	public static String FirefoxDriverPath;
	
	/** The Screen shots path. */
	public static String ScreenShotsPath;
	
	/** The Zip path. */
	public static String ZipPath;
	
	/** The Test reports. */
	public static String TestReports;
	
	/** The Export file path. */
	public static String ExportFilePath;
	
	/** The Extent reports path. */
	public static String ExtentReportsPath;

	public static String locatorsFile;
	
	/**
	 * Inits the constants.
	 */
	public static void initConstants() {
	
		IEDriverPath = System.getProperty("user.dir") + relativeIEDriverPath;
		ChromeDriverPath = System.getProperty("user.dir") + relativeChromeDriverPath;
		FirefoxDriverPath = System.getProperty("user.dir") + relativeFirefoxDriverPath;
		ScreenShotsPath = System.getProperty("user.dir") + ReadingPropertiesFile.getProperty("ScreenShotsPath");
		ZipPath = System.getProperty("user.dir")+ ReadingPropertiesFile.getProperty("ZipPath");
		TestReports = System.getProperty("user.dir")+ ReadingPropertiesFile.getProperty("TestReports");
		ExportFilePath = System.getProperty("user.dir")+ ReadingPropertiesFile.getProperty("ExportFilePath");
		ExtentReportsPath = System.getProperty("user.dir") + ReadingPropertiesFile.getProperty("ExtentReportsPath");
		locatorsFile = System.getProperty("user.dir") + ReadingPropertiesFile.getProperty("ORXmlFile");
	}
}
