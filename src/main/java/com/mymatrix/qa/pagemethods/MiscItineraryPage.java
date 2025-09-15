package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.MiscItineraryLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MiscItineraryPage extends MiscItineraryLocators {
	
	public MiscItineraryPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void enterMiscItineraryInfo(String tourOperator, String departureCity, String arrivalCity, String departureDate, String arrivalDate, String gdsSegmentNumber) {
		//Output to the system and report
		System.out.println("Entering new Tour Itinerary info");
		reportLogger.log(LogStatus.INFO,  "Entering new Tour Itinerary info");
		
		//Enter the Tour Code
		tourOperatorField.clear();
		tourOperatorField.sendKeys(tourOperator);
		
		//Enter the Departure City
		departureCityField.clear();
		departureCityField.sendKeys(departureCity);
		
		//Enter the Arrival City
		arrivalCityField.clear();
		arrivalCityField.sendKeys(arrivalCity);
		
		//Enter the Departure Date
		genMethods.enterDateField(eDriver, departureDateField, departureDate);
		tourOperatorField.click();
		
		//Enter the Arrival Date
		genMethods.enterDateField(eDriver, arrivalDateField, arrivalDate);
		tourOperatorField.click();
		
		//Enter the GDS Segment Number
		gdsSegmentNumberField.clear();
		gdsSegmentNumberField.sendKeys(gdsSegmentNumber);
	}
	
	//Select the First Traveler
	public void selectTraveler() {
		selectTravelerCheckbox.click();
	}
	
	//Click the 'Save' button to save the Itinerary info
	public void clickSaveButton() {
		saveButton.click();
	}
	
	//Click the 'Save' button to update the Itinerary info
	public void clickUpdateButton() {
		updateButton.click();
	}
	
	//Click the 'Add Another' button to save the Itinerary info & immediately enter the 'Create New' page for the Itinerary
	public void clickAddAnotherButton() {
		addAnotherButton.click();
	}
	
	//Click the 'Cancel' button to exit the Air Itinerary page
	public void clickCancelButton() {
		cancelButton.click();
	}
	
	public SoftAssert verifyMiscItineraryDetails(SoftAssert softAssert, String tourOperator, String departureCity, String arrivalCity, String departureDate, String arrivalDate, String gdsSegmentNumber) {
		//Output to the system and report
		System.out.println("Checking if the 'Misc Itinerary' details matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Misc Itinerary' details matches expectations");
		
		//Check if the 'Tour Operator' matches expectations
		tourOperator = genMethods.formatSupplier(tourOperator);
		String actualTourCode = tourOperatorField.getAttribute("value").substring(tourOperatorField.getAttribute("value").length()-tourOperator.length());
		softAssert.assertEquals(actualTourCode, tourOperator);
		
		//Check if the 'Departure City' matches expectations
		departureCity = genMethods.formatLocation(departureCity);
		String actualDepartureCity = departureCityField.getAttribute("value").substring(departureCityField.getAttribute("value").length()-departureCity.length());
		softAssert.assertEquals(actualDepartureCity, departureCity);
		
		//Check if the 'Arrival City' matches expectations
		arrivalCity = genMethods.formatLocation(arrivalCity);
		String actualArrivalCity = arrivalCityField.getAttribute("value").substring(arrivalCityField.getAttribute("value").length()-arrivalCity.length());
		softAssert.assertEquals(actualArrivalCity, arrivalCity);
		
		//Check if the 'Departure Date' matches expectations
		softAssert.assertEquals(departureDateField.getAttribute("value"), departureDate);
		
		//Check if the 'Arrival Date' matches expectations
		softAssert.assertEquals(arrivalDateField.getAttribute("value"), arrivalDate);
		
		//Check if the 'GDS Segment Number' matches expectations
		softAssert.assertEquals(gdsSegmentNumberField.getAttribute("value"), gdsSegmentNumber);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
}