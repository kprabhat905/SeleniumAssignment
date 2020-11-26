package com.assignment.keywords;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.assignment.core.BasePage;
import com.assignment.core.DriverFactory;

/**
 * The Class SeleniumKeywords.
 */
public class JavascriptKeywords extends BasePage{

	/** The instance. */
	private static DriverFactory instance = DriverFactory.getInstance();
	
	public static void scrollElementIntoView(WebElement element, boolean scrollDown){	
		JavascriptExecutor executor = (JavascriptExecutor)instance.getDriver();
		executor.executeScript("arguments[0].scrollIntoView("+scrollDown+");", element);
	}

	/**
	 * Click using js.
	 *
	 * @param element the element
	 */
	public static void clickUsingJs(WebElement element){			
		JavascriptExecutor executor = (JavascriptExecutor)instance.getDriver();
		executor.executeScript("arguments[0].click();", element);
	}


}
