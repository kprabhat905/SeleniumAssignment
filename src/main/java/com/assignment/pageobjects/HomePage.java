package com.assignment.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.core.BasePage;
import com.assignment.core.DriverFactory;
import com.assignment.keywords.JavascriptKeywords;
import com.assignment.keywords.MasterKeywords;
import com.assignment.keywords.SeleniumKeywords;
import com.relevantcodes.extentreports.LogStatus;

/**
 * The Class HomePage.
 */
public class HomePage extends BasePage {
	private static DriverFactory instance = DriverFactory.getInstance();

	private static String pageName = "HomePage";

	
	public static void clickTiles() {
		// TODO Auto-generated method stub

		System.out.println("# - Page URL - Page Status - Tournament Count");
		By gameTiles = MasterKeywords.findElement(pageName, "GameTiles");

		List<WebElement> tiles = instance.getDriver().findElements(gameTiles);

		int size = tiles.size();
		for (int j = 0; j < size; j++) {

			WebElement tilesElement = instance.getDriver().findElements(gameTiles).get(j);

			By gameTilesLink = MasterKeywords.findElement(pageName, "GameTilesLink");
			WebDriverWait wait = new WebDriverWait(instance.getDriver(), 10);
			wait.until(ExpectedConditions.elementToBeClickable(gameTilesLink));

			String url = instance.getDriver().findElements(gameTilesLink).get(j).getAttribute("href");
			int respCode = 200;
			respCode = SeleniumKeywords.getResponseCode(url);

			if(j<5) {
				By AvailableHeading = MasterKeywords.findElement(pageName, "AvailableHeading");
				JavascriptKeywords.scrollElementIntoView(instance.getDriver().findElement(AvailableHeading), true);
			}
			else {
				Actions a=new Actions(instance.getDriver());
				a.moveToElement(tilesElement).perform();
			}

			JavascriptKeywords.clickUsingJs(instance.getDriver().findElements(gameTilesLink).get(j));
			String gameTileDetailsPage = instance.getDriver().findElement(MasterKeywords.findElement(pageName, "Header")).getText();

			String TournamentsCount =instance.getDriver().findElement(MasterKeywords.findElement(pageName, "TournamentsCount")).getText();
			System.out.println(j+". "+gameTileDetailsPage+" - "+url+" - "+respCode+" - "+TournamentsCount);
			test.log(LogStatus.INFO, "\"" + j+". "+gameTileDetailsPage+" - "+url+" - "+respCode+" - "+TournamentsCount+"\"" );
			instance.getDriver().navigate().back();
		}

	}

	

}

