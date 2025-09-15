package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.CarItineraryLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CarItineraryPage extends CarItineraryLocators {
	
	public CarItineraryPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void enterCarItineraryInfo(String carCode, String carType, String pickupCity, String pickupDate, String dropoffDate, String gdsSegmentNumber) {
		//Output to the system and report
		System.out.println("Entering new Car Itinerary info");
		reportLogger.log(LogStatus.INFO,  "Entering new Car Itinerary info");
		
		//Enter the Car Code
		carCodeField.clear();
		carCodeField.sendKeys(carCode);
		
		//Enter the Car Type
		Select selectRoomType = new Select(carTypeDropDown);
		selectRoomType.selectByVisibleText(carType);
		
		//Enter the Pick-up City
		pickupCityField.clear();
		pickupCityField.sendKeys(pickupCity);
		
		//Enter the Pick-up Date
		genMethods.enterDateField(eDriver, pickupDateField, pickupDate);
		carCodeField.click();
		
		//Enter the Drop-off Date
		genMethods.enterDateField(eDriver, dropoffDateField, dropoffDate);
		carCodeField.click();
		
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
	
	public SoftAssert verifyCarItineraryDetails(SoftAssert softAssert, String carCode, String carType, String pickupCity, String pickupDate, String dropoffDate, String gdsSegmentNumber) {
		//Output to the system and report
		System.out.println("Checking if the 'Car Itinerary' details matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Car Itinerary' details matches expectations");
		
		//Check if the 'Car Chain' matches expectations
		carCode = genMethods.formatSupplier(carCode);
		String actualAirlineCode = carCodeField.getAttribute("value").substring(carCodeField.getAttribute("value").length()-carCode.length());
		softAssert.assertEquals(actualAirlineCode, carCode);
		
		//Check if the 'Car Type' matches expectations
		softAssert.assertEquals(carTypeSelectedOption.getText(), carType);
		
		//Check if the 'Pick-up City' matches expectations
		pickupCity = genMethods.formatLocation(pickupCity);
		String actualDepartureCity = pickupCityField.getAttribute("value").substring(pickupCityField.getAttribute("value").length()-pickupCity.length());
		softAssert.assertEquals(actualDepartureCity, pickupCity);
		
		//Check if the 'Pick-up Date' matches expectations
		softAssert.assertEquals(pickupDateField.getAttribute("value"), pickupDate);
		
		//Check if the 'Drop-off Date' matches expectations
		softAssert.assertEquals(dropoffDateField.getAttribute("value"), dropoffDate);
		
		//Check if the 'GDS Segment Number' matches expectations
		softAssert.assertEquals(gdsSegmentNumberField.getAttribute("value"), gdsSegmentNumber);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
}