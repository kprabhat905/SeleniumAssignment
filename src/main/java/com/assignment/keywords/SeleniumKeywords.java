package com.assignment.keywords;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.core.BasePage;
import com.assignment.core.DriverFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * The Class SeleniumKeywords.
 */
public class SeleniumKeywords extends BasePage{

	/** The instance. */
	private static DriverFactory instance = DriverFactory.getInstance();

	/** The wait. */
	private static WebDriverWait wait ;	

	/** The extent. */
	public static ExtentReports extent;

	/** The test. */
	public static ExtentTest test;

	/**
	 * Gets the page title.
	 *
	 * @return the page title
	 */
	public static String getPageTitle() {
		return instance.getDriver().getTitle();

	}

	/**
	 * Wait for element visibility.
	 *
	 * @param element the element
	 * @param secs the secs
	 * @return true, if successful
	 */
	public static boolean waitForElementVisibility(By element , int secs) {
		boolean result = false;
		try
		{
			WebDriverWait wait = new WebDriverWait(instance.getDriver(), secs);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			System.out.println("Presence of element visibilty verified for" + "\"" + instance.getDriver().findElement(element).getText() + "\"");
			result = true;
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "\"" + "Element not found: " + "\"" + instance.getDriver().findElement(element).getText() + "\"");
			System.out.println("No Such Element Found" + e);

		} catch (TimeoutException e) {
			test.log(LogStatus.FAIL, "\"" + "TimeOut. " + "\"" + instance.getDriver().findElement(element).getText() + "\"" + " not found. " + "\"");
			System.out.println("Timeout. Element not found." + e);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "\"" + "Element not found: " + "\"" + instance.getDriver().findElement(element).getText() + "\"");
			System.out.println("No Such Element Found" + e);
			try {

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return result;
	}

	/**
	 * Move to element.
	 *
	 * @param element the element
	 */
	public static void moveToElement(By element)  {
		try{
			Actions a=new Actions(instance.getDriver());
			WebDriverWait wait = new WebDriverWait(instance.getDriver(), 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			a.moveToElement(instance.getDriver().findElement(element)).perform();
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "\"" + "Element not found: " + "\"" + instance.getDriver().findElement(element).getText() + "\"");
			System.out.println("No Such Element Found" + e);

		} catch (TimeoutException e) {
			test.log(LogStatus.FAIL, "\"" + "TimeOut. " + "\"" + instance.getDriver().findElement(element).getText() + "\"" + " not found. " + "\"");
			System.out.println("Timeout. Element not found." + e);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "\"" + "Element not found: " + "\"" + instance.getDriver().findElement(element).getText() + "\"");
			System.out.println("No Such Element Found" + e);
			try {

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Gets the response code.
	 *
	 * @param urlString the url string
	 * @return the response code
	 */
	public static int getResponseCode(String urlString) {
		URL url = null;
		int code = 0;
		try {
			url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			code = connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return code;

	}

	/**
	 * Click.
	 *
	 * @param element the element
	 */
	public static void click(By element) {

		try{
			wait = new WebDriverWait(instance.getDriver(), 5);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			instance.getDriver().findElement(element).click();
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "\"" + "Element not found: " + "\"" + instance.getDriver().findElement(element).getText() + "\"");
			System.out.println("No Such Element Found" + e);

		} catch (TimeoutException e) {
			test.log(LogStatus.FAIL, "\"" + "TimeOut. " + "\"" + instance.getDriver().findElement(element).getText() + "\"" + " not found. " + "\"");
			System.out.println("Timeout. Element not found." + e);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "\"" + "Element not found: " + "\"" + instance.getDriver().findElement(element).getText() + "\"");
			System.out.println("No Such Element Found" + e);
			try {

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	/**
	 * Gets the text.
	 *
	 * @param element the element
	 * @return the text
	 */
	public static String getText(By element) {
		String text = null;
		try {
			SeleniumKeywords.moveToElement(element);
			WebDriverWait wait = new WebDriverWait(instance.getDriver(), 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			text = instance.getDriver().findElement(element).getText().trim();
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "\"" + "Element not found: " + "\"" + instance.getDriver().findElement(element).getText() + "\"");
			System.out.println("No Such Element Found" + e);

		} catch (TimeoutException e) {
			test.log(LogStatus.FAIL, "\"" + "TimeOut. " + "\"" + instance.getDriver().findElement(element).getText() + "\"" + " not found. " + "\"");
			System.out.println("Timeout. Element not found." + e);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "\"" + "Element not found: " + "\"" + instance.getDriver().findElement(element).getText() + "\"");
			System.out.println("No Such Element Found" + e);
			try {

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return text;
	}

	/**
	 * Enter value to textbox.
	 *
	 * @param element the element
	 * @param value the value
	 */
	public static void enterValueToTextbox(By element, String value)  {
		try{
			wait = new WebDriverWait(instance.getDriver(), 5);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			SeleniumKeywords.moveToElement(element);
			instance.getDriver().findElement(element).clear();
			instance.getDriver().findElement(element).sendKeys(value);
			System.out.println("Textbox value: " + "\"" + value + "\" is entered.");
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "\"" + "Element not found: " + "\"" + instance.getDriver().findElement(element).getText() + "\"");
			System.out.println("No Such Element Found" + e);

		} catch (TimeoutException e) {
			test.log(LogStatus.FAIL, "\"" + "TimeOut. " + "\"" + instance.getDriver().findElement(element).getText() + "\"" + " not found. " + "\"");
			System.out.println("Timeout. Element not found." + e);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "\"" + "Element not found: " + "\"" + instance.getDriver().findElement(element).getText() + "\"");
			System.out.println("No Such Element Found" + e);
			try {

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Select dropdown value.
	 *
	 * @param dropDownbutton the drop downbutton
	 * @param value the value
	 */
	public static void selectDropdownValue(By dropDownbutton, String value) {
		try{
			wait = new WebDriverWait(instance.getDriver(), 5);
			wait.until(ExpectedConditions.presenceOfElementLocated(dropDownbutton));
			SeleniumKeywords.moveToElement(dropDownbutton);
			Select dropdown = new Select(instance.getDriver().findElement(dropDownbutton));
			dropdown.selectByVisibleText(value);
			System.out.println("Dropdown value: " + "\"" + value + "\" is selected.");
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "\"" + "Element not found: " + "\"" + instance.getDriver().findElement(dropDownbutton).getText() + "\"");
			System.out.println("No Such Element Found" + e);

		} catch (TimeoutException e) {
			test.log(LogStatus.FAIL, "\"" + "TimeOut. " + "\"" + instance.getDriver().findElement(dropDownbutton).getText() + "\"" + " not found. " + "\"");
			System.out.println("Timeout. Element not found." + e);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "\"" + "Element not found: " + "\"" + instance.getDriver().findElement(dropDownbutton).getText() + "\"");
			System.out.println("No Such Element Found" + e);
			try {

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
