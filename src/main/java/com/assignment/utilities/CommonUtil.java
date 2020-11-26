package com.assignment.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;

import com.assignment.core.Config;

/**
 * This class contains various methods which are getting used in test method creation.
 */
public class CommonUtil {
	
	/** The reading properties file. */
	static ReadingPropertiesFile readingPropertiesFile = new ReadingPropertiesFile();
	
	/** The file number. */
	private static int fileNumber = 1;
	
	/** The method name. */
	private static String methodName;
	
	/**
	 * Checks if folder exist at the specified path.
	 *
	 * @param filePath  path of the file.
	 */
	public static void isFolderExistAtPath(String filePath) {
		File folder = new File(filePath);
		if (folder.exists() && folder.isDirectory()) {
			System.out.println(folder.getName()+ " Path: "+ folder.getAbsolutePath());
		} else {
			System.out.println(folder.getName()+" folder doesn't exist at the path: " + folder.getAbsolutePath());
			System.out.println("Creating Folder");
			folder.mkdir();
			System.out.println(folder.getName()+ " folder created at path: "+folder.getAbsolutePath());
		}

	}
	
	/**
	 * Sets the screenshot relative path.
	 */
	public static void setScreenshotRelativePath() {
		FileReader fr = null;
		FileWriter fw = null;
		System.out.println(" : sshotSetRelativePath Method Called");
		try {
			File f = new File(Config.ExtentReportsPath);
			ArrayList<String> lines = new ArrayList<String>();
			String line;
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.contains("<img")) {
					line = line.replace(Config.ScreenShotsPath, "./");
				}
				lines.add(line + "\n");
			}
			fr.close();
			br.close();
			fw = new FileWriter(f);
			BufferedWriter out = new BufferedWriter(fw);
			for (String s : lines)
				out.write(s);
			out.flush();
			fw.close();
			out.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Generate unique name.
	 *
	 * @return the string
	 */
	public static String generateUniqueName() {
		System.out.println(" : GenerateUniqueNameMethod Method Called");
		SimpleDateFormat sdfDate = new SimpleDateFormat("ddMMyyyy_HHmmss_z");
		Date now = new Date();
		String uniqueText = sdfDate.format(now);
		return (uniqueText);
	}
	
	/**
	 * Zip folder.
	 *
	 * @param screenShotsPath the screen shots path
	 * @param zipPath the zip path
	 * @throws Exception the exception
	 */
	public static void zipFolder(final Path screenShotsPath, Path zipPath) throws Exception {

		final ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()));
		try {
			Files.walkFileTree(screenShotsPath, new SimpleFileVisitor<Path>() {
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					zos.putNextEntry(new ZipEntry(screenShotsPath.relativize(file).toString()));
					Files.copy(file, zos);
					zos.closeEntry();
					return FileVisitResult.CONTINUE;
				}
			});
		}catch(IOException e) {
			System.out.println("Error ocurred while closing the file: " + e.getMessage());
		}finally {
			zos.close();
		}

	}

	/**
	 * Capture screenshot.
	 *
	 * @param eDriver the e driver
	 * @param screenshotName the screenshot name
	 * @param result the result
	 * @return the string
	 */
	public static String captureScreenshot(WebDriver eDriver, String screenshotName, ITestResult result) {
		System.out.println(" : CaptureScreeshot Method Called");
		try {
			String str = result.getInstanceName().toString();
			str = str.substring(17).substring(str.substring(17).indexOf(".")+1);
			WebDriver augmentedDriver = new Augmenter().augment(eDriver);
	        File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
	        System.out.println("Screenshot taken.");
	        String dest = Config.ScreenShotsPath + str +"_"+result.getName()+".png";
			File destination = new File(dest);
			if (!result.getName().equals(methodName)) {
				methodName = result.getName();
				fileNumber = 1;
			}
			if (destination.exists()) {
				dest = Config.ScreenShotsPath + screenshotName + "_" + result.getInstanceName() + "_" + result.getName()
						+ "(" + fileNumber++ + ")" + ".png";
				destination = new File(dest);
			}
			FileUtils.copyFile(source, destination);
			return dest;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return ex.getMessage();
		}
	}

}
