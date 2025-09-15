package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.AirItineraryLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AirItineraryPage extends AirItineraryLocators {
	
	public AirItineraryPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void enterAirItineraryInfo(String airlineCode, String flightNumber, String classCode, String departureCity, String arrivalCity, String departureDate, String arrivalDate, String gdsSegmentNumber) {
		//Output to the system and report
		System.out.println("Entering new Air Itinerary info");
		reportLogger.log(LogStatus.INFO,  "Entering new Air Itinerary info");
		
		//Enter the Airline Code
		airlineField.clear();
		airlineField.sendKeys(airlineCode);
		
		//Enter the Flight Number
		flightNumberField.clear();
		flightNumberField.sendKeys(flightNumber);
		
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
		gdsSegmentNumberField.click();
		
		//Enter the Arrival Date
		genMethods.enterDateField(eDriver, arrivalDateField, arrivalDate);
		gdsSegmentNumberField.click();
		
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
	
	public SoftAssert verifyAirItineraryDetails(SoftAssert softAssert, String airlineCode, String flightNumber, String classCode, String departureCity, String arrivalCity, String departureDate, String gdsSegmentNumber) {
		//Output to the system and report
		System.out.println("Checking if the 'Air Itinerary' details matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Air Itinerary' details matches expectations");
		
		//Check if the 'Airline Code' matches expectations
		airlineCode = genMethods.formatSupplier(airlineCode);
		String actualAirlineCode = airlineField.getAttribute("value").substring(airlineField.getAttribute("value").length()-airlineCode.length());
		softAssert.assertEquals(actualAirlineCode, airlineCode);
		
		//Check if the 'Flight Number' matches expectations
		softAssert.assertEquals(flightNumberField.getAttribute("value"), flightNumber);
		
		//Check if the 'Class' matches expectations
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
		
		//Check if the 'GDS Segment Number' matches expectations
		softAssert.assertEquals(gdsSegmentNumberField.getAttribute("value"), gdsSegmentNumber);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
}