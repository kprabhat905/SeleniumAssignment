package com.assignment.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * The Class ReadingPropertiesFile.
 */
public class ReadingPropertiesFile {

	/** The prop. */
	public static Properties prop = null;
	
	/** The file. */
	public static File file = null;
	
	/** The fis. */
	public static FileInputStream fis = null;

	/**
	 * Instantiates a new reading properties file.
	 */
	// Static block to initialize class variables
	public ReadingPropertiesFile() {
		{
			file = new File(System.getProperty("user.dir") + "\\Resources\\config.properties");
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			prop = new Properties();
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets the property.
	 *
	 * @param key the key
	 * @return the property
	 */
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}