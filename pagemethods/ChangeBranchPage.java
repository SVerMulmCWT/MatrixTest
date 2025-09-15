package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.ChangeBranchLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ChangeBranchPage extends ChangeBranchLocators {
	
	public ChangeBranchPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	//Changing the active branch
	public void changeBranch(String changeBranchCode) {
		//Output to the system and report
		System.out.println("Changing branch with " + changeBranchCode);
		reportLogger.log(LogStatus.INFO,  "Changing branch with " + changeBranchCode);
		
		//Enter the 'Change Branch ID'
		changeBranchInputField.sendKeys(changeBranchCode);
		
		//Click the 'Change' button to change the branch
		changeBranchButton.click();
	}
	
	public SoftAssert verifySuccessfulChangedBranch(SoftAssert softAssert, String expectedBranchLabel) {
		//Output to the system and report
		System.out.println("Checking if the user successfully changed to the expected branch");
		reportLogger.log(LogStatus.INFO,  "Checking if the user successfully changed to the expected branch");
		
		//Check if the relevant buttons/page exists
		softAssert.assertEquals(branchLabel.getText(), expectedBranchLabel);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
}