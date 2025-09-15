package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.TravelerLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TravelerPage extends TravelerLocators {
	
	public TravelerPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void clickCancel() {
		cancelButton.click();
	}

	public SoftAssert verifyFullName(SoftAssert softAssert, String name, String surname) {
		//Output to the system and report
		System.out.println("Checking if the 'Full Name' appears in the Client File");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Full Name' appears in the Client File");
		
		//Initialize Variable(s)
		name = name.toUpperCase();
		surname = surname.toUpperCase();
		
		//Check if the Traveler detail page matches expectations
		softAssert.assertEquals(nameField.getAttribute("value"), name);
		softAssert.assertEquals(surnameField.getAttribute("value"), surname);
		
		//Check if the credit card is masked
		if (creditCardNumber.getAttribute("value").substring(0, 11).equals("***********")) {
			softAssert.assertEquals("Credit Card is masked", "Credit Card is masked");
		} else {
			softAssert.assertEquals("Credit Card is not masked", "Credit Card is masked");
		}
		
		//Pause the script a short bit
		genMethods.waitFor(1);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}