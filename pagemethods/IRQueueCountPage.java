package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.IRQueueCountLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class IRQueueCountPage extends IRQueueCountLocators {
	
	public IRQueueCountPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void selectQueueType(String queueType) {
		//Output to the system and report
		System.out.println("Selecting the Queue Type -> " + queueType);
		reportLogger.log(LogStatus.INFO,  "Selecting the Queue Type -> " + queueType);
		
		//Locate the drop-down list
		Select queueTypeSelector = new Select(queueTypeDropDown);
		
		//Select the desired queue type from the drop-down list
		if (queueType.equalsIgnoreCase("rmi") || queueType.equalsIgnoreCase("rmi rejected interface records")) {
			queueTypeSelector.selectByVisibleText("RMI Rejected Interface Records");
		} else if (queueType.equalsIgnoreCase("rma") || queueType.equalsIgnoreCase("rma system queue")) {
			queueTypeSelector.selectByVisibleText("RMA System Queue");
			Select statusSelector = new Select(statusDropDown);
			statusSelector.selectByVisibleText("All");
		} else if (queueType.equalsIgnoreCase("iar") || queueType.equalsIgnoreCase("iar reconciliation")) {
			queueTypeSelector.selectByVisibleText("IAR");
		} else {
			queueTypeSelector.selectByVisibleText("!~ The entry (" + queueType + ") is not a valid input");
		}
	}
	
	public void clickSearchButton() {
		//Output to the system and report
		System.out.println("Clicking the 'Search Button' for the specified Queue Type");
		reportLogger.log(LogStatus.INFO,  "Clicking the 'Search Button' for the specified Queue Type");
		
		//Click the 'Search' button to check if the search capabilities work
		searchButton.click();
	}
	
	public int getSearchCount() {
		//Initialize Variable(s)
		int searchCount = 0;
		
		//Check the 'Search Count' label
		try {
			int startPosition = searchResultsLabel.getText().indexOf("(") + 1;
			int endPosition = searchResultsLabel.getText().length()-7;
			String searchCountStr = searchResultsLabel.getText().substring(startPosition, endPosition);
			//Retrieve the 'Search Count' number
			searchCount = Integer.valueOf(searchCountStr);
			
			//Return the 'Search Count' number
			return searchCount;
		} catch (Exception e) {
			System.out.print("");
		}
		
		//Count the 'Search Count' results
		try {
			//Return the 'Search Count' number
			return searchResultsList.size()-1;
		} catch (Exception e) {
			System.out.print("");
		}
		
		//Return zero (0), since no search results were found
		return searchCount;
	}
	
	public void clickQueueCount() {
		//Output to the system and report
		System.out.println("Clicking the 'Queue Count' for the specified Queue Type");
		reportLogger.log(LogStatus.INFO,  "Clicking the 'Queue Count' for the specified Queue Type");
		
		//Click the 'Queue Count' button to check if the search capabilities work
		queueCountButton.click();
	}
	
	public int getQueueCount() {
		//Initialize Variable(s)
		String queueCountStr = "";
		int queueCount = 0;
		
		//something
		for (int i = 1; i < queueCountResultsList.size(); i = i + 2) {
			if (!queueCountResultsList.get(i).getText().equals("") && !queueCountResultsList.get(i).getText().equals(" ") && !(queueCountResultsList.get(i).getText() == null)) {
				queueCountStr = queueCountResultsList.get(i).getText();
				queueCount = queueCount + Integer.valueOf(queueCountStr);
			}
		}
		
		//Return the 'Queue Count' number
		return queueCount;
	}
	
	public SoftAssert verifySearchCapabilities(SoftAssert softAssert) {
		//Output to the system and report
		System.out.println("Checking if the user can search for all Queue types without a crash");
		reportLogger.log(LogStatus.INFO,  "Checking if the user can search for all Queue types without a crash");
		
		//Click the 'Search' button to check if the search capabilities work
		searchButton.click();
		
		//Pause the script a short bit
		genMethods.waitFor(1);
		
		//Click the 'Queue Count' button to check if the search capabilities work
		queueCountButton.click();
		
		//Pause the script a short bit
		genMethods.waitFor(1);
		
		//Check if the 'Queue Type' drop-down list is present (ensures that there has not been a force-logout or crash)
		softAssert.assertEquals(queueTypeDropDown.isDisplayed(), true);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyQueueDifference(SoftAssert softAssert, int resultsCountDifference) {
		//Output to the system and report
		System.out.println("Checking if the results difference is less than 10");
		reportLogger.log(LogStatus.INFO,  "Checking if the results difference is less than 10");
		
		//Check if the results difference is less than 10
		if (resultsCountDifference <= 10) {
			softAssert.assertEquals("The results difference is 10 or less", "The results difference is 10 or less");
		} else {
			softAssert.assertEquals("The results difference is 10 or less", "The results difference is: " + resultsCountDifference);
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}