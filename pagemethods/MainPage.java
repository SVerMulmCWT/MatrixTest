package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.pagelocators.MainLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MainPage extends MainLocators {
	
	public MainPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	//Navigate to the 'Search Client File' page
	public void navigateToSearchClientFilePage(String environment) {
		//Output to the system and report
		System.out.println("Navigating to the 'Search Client File' page");
		reportLogger.log(LogStatus.INFO,  "Navigating to the 'Search Client File' page");
		
		//Bring up the 'GoTo' tab's drop-down list of options
		if (environment.equalsIgnoreCase("us prod") || environment.equalsIgnoreCase("mng prod") || environment.equalsIgnoreCase("au prod")) {
			actions.moveToElement(goToTab).perform();
		} else {
			actions.moveToElement(viewTab).perform();
		}
		
		//Pause the script a short bit
		genMethods.waitFor(1);
		
		//Navigate to the 'Clientfile Information' page
		clientFileInformationItem.click();
	}
	
	public void navigateToQueuePage(String environment) {
		//Output to the system and report
		System.out.println("Navigating to the 'Queue Count' page");
		reportLogger.log(LogStatus.INFO,  "Navigating to the 'Queue Count' page");
		
		//changeBranchTab.click();
		if (environment.equalsIgnoreCase("us prod") || environment.equalsIgnoreCase("mng prod") || environment.equalsIgnoreCase("au prod")) {
			actions.moveToElement(goToTab).perform();
		} else {
			actions.moveToElement(viewTab).perform();
		}
		
		//Pause the script a short bit
		genMethods.waitFor(1);
		
		//Navigate to the 'Change Branch' page
		calendarTasksQueuesItem.click();
	}
	
	public void navigateToIRQueuePage(String environment) {
		//Output to the system and report
		System.out.println("Navigating to the 'IR Queue Count' page");
		reportLogger.log(LogStatus.INFO,  "Navigating to the 'IR Queue Count' page");
		
		//Bring up the 'GoTo' tab's drop-down list of options
		if (environment.equalsIgnoreCase("us prod") || environment.equalsIgnoreCase("mng prod") || environment.equalsIgnoreCase("au prod")) {
			actions.moveToElement(goToTab).perform();
		} else {
			actions.moveToElement(viewTab).perform();
		}
		
		//Pause the script a short bit
		genMethods.waitFor(1);
		
		//Navigate to the 'Change Branch' page
		agencySalesReportItem.click();
		
		//Pause the script a short bit
		genMethods.waitFor(1);
		
		//Navigate to the 'IR Queues' section
		irQueuesLink.click();
	}
	
	public void navigateToChangeBranchPage(String changeBranchCode) {
		//Output to the system and report
		System.out.println("Navigating to the 'Change Branch' page");
		reportLogger.log(LogStatus.INFO,  "Navigating to the 'Change Branch' page");
		
		//changeBranchTab.click();
		actions.moveToElement(changeBranchTab).perform();
		
		//Pause the script a short bit
		genMethods.waitFor(1);
		
		//Navigate to the 'Change Branch' page
		changeBranchItem.click();
	}
}