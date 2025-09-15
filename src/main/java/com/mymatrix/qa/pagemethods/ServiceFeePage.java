package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.ServiceFeeLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ServiceFeePage extends ServiceFeeLocators {
	
	public ServiceFeePage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void enterServiceFeeInfo(String paymentAmount, String description, String paymentType) {
		//Output to the system and report
		System.out.println("Entering new Service Fee info");
		reportLogger.log(LogStatus.INFO,  "Entering new Service Fee info");
		
		//Enter the Cruise Line Code
		amountField.clear();
//		amountField.sendKeys(amount);
		
		//Enter the Departure City
		descriptionField.clear();
		descriptionField.sendKeys(description);
		
		//Enter the Arrival City
		Select selectPaymentType = new Select(paymentTypeDropDown);
//		selectPaymentType.selectByVisibleText(formOfPayment);
	}
	
	//Select the First Traveler
	public void selectTraveler() {
		firstCostlineCheckbox.click();
	}
	
	//Click the 'Save' button to save the Itinerary info
	public void clickSaveButton() {
		saveButton.click();
	}
	
	//Click the 'Add Another' button to save the Itinerary info & immediately enter the 'Create New' page for the Itinerary
	public void clickAddAnotherButton() {
		addAnotherButton.click();
	}
	
	//Click the 'Cancel' button to exit the Air Itinerary page
	public void clickCancelButton() {
		cancelButton.click();
	}
	
	public SoftAssert verifyShipItineraryDetails(SoftAssert softAssert, String amount, String description, String formOfPayment) {
		//Output to the system and report
		System.out.println("Checking if the 'Ship Itinerary' details matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Ship Itinerary' details matches expectations");
		
		//Check if the 'Departure Date' matches expectations
		softAssert.assertEquals(amountField.getAttribute("value"), amount);
		
		//Check if the 'Arrival Date' matches expectations
		softAssert.assertEquals(descriptionField.getAttribute("value"), description);
		
		//Check if the 'GDS Segment Number' matches expectations
		softAssert.assertEquals(paymentTypeOption.getText(), formOfPayment);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
}