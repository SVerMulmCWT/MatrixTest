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
	
	public void enterTravelerInfo(String name, String surname) {
		//Output to the system and report
		System.out.println("Entering new Traveler info");
		reportLogger.log(LogStatus.INFO,  "Entering new Traveler info");
		
		//Enter the Traveler's name
		nameField.clear();
		nameField.sendKeys(name);
		
		surnameField.clear();
		surnameField.sendKeys(surname);
	}
	
	public void clickSaveButton() {
		//Output to the system and report
		System.out.println("Clicking the 'Save' button to save the Traveler info");
		reportLogger.log(LogStatus.INFO,  "Clicking the 'Save' button to save the Traveler info");
		
		//Click the 'Save' button
		saveButton.click();
	}
	
	public void clickAddAnotherButton() {
		//Output to the system and report
		System.out.println("Clicking the 'Add Another' button to save the Traveler info and enter another 'New Traveler' page");
		reportLogger.log(LogStatus.INFO,  "Clicking the 'Add Another' button to save the Traveler info and enter another 'New Traveler' page");
		
		//Click the 'Add Another' button
		addAnotherButton.click();
	}
	
	public void clickCancelButton() {
		//Output to the system and report
		System.out.println("Clicking the 'Cancel' button to exit the 'Traveler' page");
		reportLogger.log(LogStatus.INFO,  "Clicking the 'Cancel' button to exit the 'Traveler' page");
		
		//Click the 'Cancel' button
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
		if (!name.equalsIgnoreCase("") && name != null) {
			softAssert.assertEquals(nameField.getAttribute("value"), name);
		}
		
		if (!surname.equalsIgnoreCase("") && surname != null) {
			softAssert.assertEquals(surnameField.getAttribute("value"), surname);
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyCreditCardMask(SoftAssert softAssert) {
		//Output to the system and report
		System.out.println("Checking if the credit card is properly masked in the Traveler's page");
		reportLogger.log(LogStatus.INFO,  "Checking if the credit card is properly masked in the Traveler's page");
		
		//Check if the credit card is masked
		if (creditCardNumberField.getAttribute("value").substring(0, 11).equals("***********")) {
			softAssert.assertEquals("Credit Card is masked", "Credit Card is masked");
		} else {
			softAssert.assertEquals("Credit Card is not masked", "Credit Card is masked");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}