package com.assignment.logging;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;


/**
 * The Class ExtentManager.
 */
public class ExtentManager {
    
    /** The extent. */
    private static ExtentReports extent;
    
    /**
     * Gets the single instance of ExtentManager.
     *
     * @param fileName the file name
     * @return single instance of ExtentManager
     */
    public static ExtentReports getInstance(String fileName) {
    	if (extent == null) {
    		extent = new ExtentReports(fileName, true, DisplayOrder.NEWEST_FIRST);

			//Loading the configuration file for the report
			extent.loadConfig(new File(System.getProperty("user.dir") + "//reportConfig.xml"));
			
			//Adding the system information in the reports
			extent.addSystemInfo("Selenium Version", "3.141.59").addSystemInfo("Environment", "QA");
    	}
   
    	
        return extent;
    }
    
}
