package com.assignment.testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.assignment.core.BasePage;
import com.assignment.pageobjects.HomePage;

public class TestCase extends BasePage {


	@Test
	public static void getGameDetailsTournamentCount() {
		SoftAssert softAssert = new SoftAssert();

		HomePage.clickTiles();
		softAssert.assertAll();
	}

}
