package com.assignment.keywords;

import java.util.List;

import org.openqa.selenium.By;

import com.assignment.core.BasePage;
import com.assignment.core.Config;
import com.assignment.core.DriverFactory;
import com.assignment.utilities.ReadFileUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * The Class SeleniumKeywords.
 */
public class MasterKeywords extends BasePage{

	/** The instance. */
	private static DriverFactory instance = DriverFactory.getInstance();
	/** The extent. */
	public static ExtentReports extent;

	/** The test. */
	public static ExtentTest test;
	
	/**
	 * Finds the page element.
	 *
	 * @param PageName		page name tag in ObjectRepository.xml file.
	 * @param objectName	name of element.
	 * @return By			returns By data type.
	 */
	public static By findElement(String PageName, String objectName) {
		By locator=null;
		try {
			String xmlPath = Config.locatorsFile;
			List<String> listLocator = ReadFileUtil.getXmlValue(objectName, xmlPath, PageName);
			if(!listLocator.isEmpty())
				locator = GetElement(listLocator.get(0), listLocator.get(1));
			else {
				test.log(LogStatus.FAIL, "NameOfElement or SectionName is given wrong in ObjectRepository.xml file OR PageName in Page Object Class is wrong");
			}
		} catch (Exception ex) {
			test.log(LogStatus.FAIL, "\"" + "Failed to read data from XML file\""+ ex);
			System.out.println("Failed to read data from XML file" + ex);
		}
		return locator;
	}
	
	/**
	 * Get page the element.
	 *
	 * @param locatorValue	value of locator.
	 * @param locatorType	type of locator.
	 * @return By			returns By data type.
	 * @throws Exception	on error.
	 * @see	   Exception
	 */
	public static By GetElement(String locatorValue, String locatorType) throws Exception {
		switch (locatorType.toLowerCase()) {
		case "id":
			return By.id(locatorValue);
		case "name":
			return By.name(locatorValue);
		case "classname":
		case "class":
			return By.className(locatorValue);
		case "tagname":
			return By.tagName(locatorValue);
		case "linktext":
			return By.linkText(locatorValue);
		case "partiallinktext":
			return By.partialLinkText(locatorValue);
		case "cssselector":
			return By.cssSelector(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		default:
			throw new Exception("DOM FINDER : did not find the correct dom finder type in the file for locator value: "
					+ locatorValue);
		}
	}
}
