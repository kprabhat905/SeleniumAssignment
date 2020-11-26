package com.assignment.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.assignment.logging.ExtentManager;
import com.assignment.utilities.CommonUtil;
import com.assignment.utilities.ReadingPropertiesFile;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * The Class BasePage.
 */
public class BasePage {

	/** The reading properties file. */
	static ReadingPropertiesFile readingPropertiesFile = new ReadingPropertiesFile();
	
	/** The extent. */
	public static ExtentReports extent;
	
	/** The test. */
	public static ExtentTest test;
	
	/** The browser value. */
	public static String BROWSER_VALUE;

	/**
	 * Instantiates a new base page.
	 */
	public BasePage() {
		Config.initConstants();
	}

	/**
	 * Initialize browser.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@BeforeSuite(alwaysRun = true)
	public static void initializeBrowser() throws IOException {
		System.out.println("-----------------EXECUTION START----------------------");
		System.out.println("BasePage - BeforeSuite called");

		CommonUtil.isFolderExistAtPath(Config.TestReports);
		CommonUtil.isFolderExistAtPath(Config.ScreenShotsPath);
		CommonUtil.isFolderExistAtPath(Config.ZipPath);
		CommonUtil.isFolderExistAtPath(Config.ExportFilePath);
		FileUtils.cleanDirectory(new File(Config.ScreenShotsPath));
	}

	/**
	 * Before method.
	 *
	 * @param method the method
	 * @param browserName the browser name
	 * @throws FrameworkException the framework exception
	 */
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser"})
	protected void BeforeMethod(Method method, String browserName) throws FrameworkException {
		System.out.println("BasePage - BeforeMethod called");
		BROWSER_VALUE= browserName;
		extent = ExtentManager.getInstance(Config.ScreenShotsPath + "\\ExtentReports.html");
		test = extent.startTest(method.getName());

		DriverFactory.getInstance().setDriver(browserName);

	}

	/**
	 * After method.
	 *
	 * @param result the result
	 */
	@AfterMethod
	public void afterMethod(ITestResult result) {
		System.out.println("BasePage - AfterMethod called");
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(LogStatus.PASS, "Test case got passed");
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL, result.getThrowable());
			CommonUtil.captureScreenshot(DriverFactory.getInstance().getDriver(),
					"FAIL", result);
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			test.log(LogStatus.SKIP, result.getThrowable());
		}

		quitBrowser();
	}

	/**
	 * Quit browser.
	 */
	public void quitBrowser() {
		extent.flush();
		try {
			if(BROWSER_VALUE.equalsIgnoreCase(DriverFactory.FIREFOX) ){
				Runtime.getRuntime().exec("Taskkill /IM firefox.exe /F");
			}else {
				DriverFactory.getInstance().removeDriver();
			}
		}catch(IOException e) {
			System.out.println("Error ocurred while closing this browser." + e);
		}
	}

	/**
	 * After suite.
	 *
	 * @throws Exception the exception
	 */
	@AfterSuite(alwaysRun = true)
	protected void afterSuite() throws Exception {
		System.out.println("BasePage - AfterSuite called");
		CommonUtil.setScreenshotRelativePath();
		String zipFilePath = Config.ZipPath + CommonUtil.generateUniqueName() + ".zip";
		CommonUtil.zipFolder(Paths.get(Config.ScreenShotsPath), Paths.get(zipFilePath));

		System.out.println("---------------EXECUTION COMPLETED--------------------");
	}

}
