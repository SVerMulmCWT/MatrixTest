package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.RailItineraryLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class RailItineraryPage extends RailItineraryLocators {
	
	public RailItineraryPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void enterRailItineraryInfo(String supplierCode, String trainNumber, String classCode, String departureCity, String arrivalCity, String departureDate, String arrivalDate, String gdsSegmentNumber) {
		//Output to the system and report
		System.out.println("Entering new Rail Itinerary info");
		reportLogger.log(LogStatus.INFO,  "Entering new Rail Itinerary info");
		
		//Enter the Supplier Code
		supplierCodeField.clear();
		supplierCodeField.sendKeys(supplierCode);
		
		//Enter the Train Number
		trainNumberField.clear();
		trainNumberField.sendKeys(trainNumber);
		
		//Enter the Class Code
		classCodeField.clear();
		classCodeField.sendKeys(classCode);
		
		//Enter the Departure City
		departureCityField.clear();
		departureCityField.sendKeys(departureCity);
		
		//Enter the Arrival City
		arrivalCityField.clear();
		arrivalCityField.sendKeys(arrivalCity);
		
		//Enter the Departure Date
		genMethods.enterDateField(eDriver, departureDateField, departureDate);
		supplierCodeField.click();
		
		//Enter the Arrival Date
		genMethods.enterDateField(eDriver, arrivalDateField, arrivalDate);
		supplierCodeField.click();
		
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
	
	public SoftAssert verifyRailItineraryDetails(SoftAssert softAssert, String supplierCode, String trainNumber, String classCode, String departureCity, String arrivalCity, String departureDate, String arrivalDate, String gdsSegmentNumber) {
		//Output to the system and report
		System.out.println("Checking if the 'Rail Itinerary' details matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Rail Itinerary' details matches expectations");
		
		//Check if the 'Supplier Code' matches expectations
		supplierCode = genMethods.formatSupplier(supplierCode);
		String actualSupplierCode = supplierCodeField.getAttribute("value").substring(supplierCodeField.getAttribute("value").length()-supplierCode.length());
		softAssert.assertEquals(actualSupplierCode, supplierCode);
		
		//Check if the 'Train Number' matches expectations
		softAssert.assertEquals(trainNumberField.getAttribute("value"), trainNumber);
		
		//Check if the 'Class Code' matches expectations
		softAssert.assertEquals(classCodeField.getAttribute("value"), classCode);
		
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