package com.assignment.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

/**
 * The Class DataProviders.
 */
public class DataProviders {
	/** The use of the comma character (,) typically separates each field of text.
	 */
	private static String separator = ",";
	
	/**
	 * Get the data set required to test all the test cases.
	 *
	 * @param method the method
	 * @return Object[][], return data from the specified file.
	 */
	@DataProvider(name="TestData")
	public static Object[][] getTestData(Method method){
		String [][] dataSet = ReadFileUtil.CSVDataProvider(method.getName(), separator , System.getProperty("user.dir") + new ReadingPropertiesFile().getProperty("CSVFile"));
		return dataSet;
	}
}
