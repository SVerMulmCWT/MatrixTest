package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.InsuranceItineraryLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class InsuranceItineraryPage extends InsuranceItineraryLocators {
	
	public InsuranceItineraryPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void enterInsuranceItineraryInfo(String insuranceCode, String departureCity, String arrivalCity, String departureDate, String arrivalDate) {
		//Output to the system and report
		System.out.println("Entering new Insurance Itinerary info");
		reportLogger.log(LogStatus.INFO,  "Entering new Insurance Itinerary info");
		
		//Enter the Insurance Code
		insuranceCodeField.clear();
		insuranceCodeField.sendKeys(insuranceCode);
		
		//Enter the Departure City
		departureCityField.clear();
		departureCityField.sendKeys(departureCity);
		
		//Enter the Arrival City
		arrivalCityField.clear();
		arrivalCityField.sendKeys(arrivalCity);
		
		//Enter the Departure Date
		genMethods.enterDateField(eDriver, departureDateField, departureDate);
		insuranceCodeField.click();
		
		//Enter the Arrival Date
		genMethods.enterDateField(eDriver, arrivalDateField, arrivalDate);
		insuranceCodeField.click();
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
	
	public SoftAssert verifyInsuranceItineraryDetails(SoftAssert softAssert, String insuranceCode, String departureCity, String arrivalCity, String departureDate, String arrivalDate) {
		//Output to the system and report
		System.out.println("Checking if the 'Insurance Itinerary' details matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Insurance Itinerary' details matches expectations");
		
		//Check if the 'Insurance Provider' matches expectations
		insuranceCode = genMethods.formatSupplier(insuranceCode);
		String actualInsuranceCode = insuranceCodeField.getAttribute("value").substring(insuranceCodeField.getAttribute("value").length()-insuranceCode.length());
		softAssert.assertEquals(actualInsuranceCode, insuranceCode);
		
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
		
		//Return the status for the SoftAssert
		return softAssert;
	}
}