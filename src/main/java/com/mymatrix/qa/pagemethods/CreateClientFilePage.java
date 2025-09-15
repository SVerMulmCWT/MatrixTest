package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.mymatrix.qa.pagelocators.CreateClientFileLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CreateClientFilePage extends CreateClientFileLocators {
	
	public CreateClientFilePage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	//Enter the Required Info to Create the Client File
	public void enterRequiredInfo(String environment, String departureDate, String deletionDate, String clientFileCode, String clientFileType, String departmentCode, String firstName, String surname, String destination, String phoneNumber) {
		//Output to the system and report
		System.out.println("Entering the Required Info to Create the Client File");
		reportLogger.log(LogStatus.INFO,  "Entering the Required Info to Create the Client File");
		
		//Initialize Variable(s)
		Actions actions = new Actions(eDriver);
		
		//Click the Departure Date Field
		departureDateField.click();
		
		//Move the cursor to the farthest left part of the input field
		for (int i = 0; i < 6; i++) {
			actions.sendKeys(Keys.ARROW_LEFT).perform();
		}
		
		//Enter the Departure Date
		departureDateField.sendKeys(departureDate);
		
		//Click the Deletion Date Field
		deletionDateField.click();
		
		//Move the cursor to the farthest left part of the input field
		for (int i = 0; i < 6; i++) {
			actions.sendKeys(Keys.ARROW_LEFT).perform();
		}
		
		//Enter the Deletion Date
		deletionDateField.sendKeys(deletionDate);
		
		//Enter the CFA Code
		clientFileCodeField.sendKeys(clientFileCode);
		departmentCodeField.click();
		genMethods.waitFor(1);
		
		//Select the Client File Type
		Select selectClientFileType = new Select(clientFileTypeDropDown);
		selectClientFileType.selectByVisibleText(clientFileType);
		genMethods.waitFor(1);
		
		//Enter the Department Code
		departmentCodeField.sendKeys(departmentCode);
		
		//Enter the Traveler's First Name
		firstNameField.sendKeys(firstName);
		
		//Enter the Traveler's Surname
		surnameField.sendKeys(surname);
		
		//Enter the Destination
		if (environment.contains("ca")) {
			destinationField.sendKeys(destination);
		}
		
		//Enter the Phone Number
		if (phoneNumber.equalsIgnoreCase("")) {
			System.out.println("No phone number to add");
		} else if (phoneNumber.length() == 10) {
			mobileNumberField1.sendKeys(phoneNumber.substring(0, 3));
			mobileNumberField2.sendKeys(phoneNumber.substring(3, 6));
			mobileNumberField3.sendKeys(phoneNumber.substring(6, 10));
		} else {
			System.out.println("The provided phone number does not match the necessaryy phone number length");
		}
	}
	
	//Click the 'Save' button to create the Client File
	public void clickNewClientFileButton() {
		//Output to the system and report
		System.out.println("Creating the new Client File");
		reportLogger.log(LogStatus.INFO,  "Creating the new Client File");
		
		//Click the 'Save' button
		saveButton.click();
	}
	
}