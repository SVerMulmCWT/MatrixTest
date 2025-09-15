package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.HotelItineraryLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HotelItineraryPage extends HotelItineraryLocators {
	
	public HotelItineraryPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void enterHotelItineraryInfo(String hotelChain, String checkinDate, String checkoutDate, String city, String roomType, String gdsSegmentNumber) {
		//Output to the system and report
		System.out.println("Entering new Hotel Itinerary info");
		reportLogger.log(LogStatus.INFO,  "Entering new Hotel Itinerary info");
		
		//Enter the Hotel Chain
		hotelChainField.clear();
		hotelChainField.sendKeys(hotelChain);
		
		//Enter the Check-in Date
		genMethods.enterDateField(eDriver, checkinDateField, checkinDate);
		hotelChainField.click();
		
		//Enter the Check-out Date
		genMethods.enterDateField(eDriver, checkoutDateField, checkoutDate);
		hotelChainField.click();
		
		//Enter the City Code
		cityCodeField.clear();
		cityCodeField.sendKeys(city);
		
		//Enter the Room Type
		Select selectRoomType = new Select(roomTypeDropDown);
		selectRoomType.selectByVisibleText(roomType);
		
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
	
	public SoftAssert verifyHotelItineraryDetails(SoftAssert softAssert, String hotelChain, String checkinDate, String checkoutDate, String city, String roomType, String gdsSegmentNumber) {
		//Output to the system and report
		System.out.println("Checking if the 'Hotel Itinerary' details matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Hotel Itinerary' details matches expectations");
		
		//Check if the 'Hotel Chain' matches expectations
		hotelChain = genMethods.formatSupplier(hotelChain);
		String actualAirlineCode = hotelChainField.getAttribute("value").substring(hotelChainField.getAttribute("value").length()-hotelChain.length());
		softAssert.assertEquals(actualAirlineCode, hotelChain);
		
		//Check if the 'Check-in Date' matches expectations
		softAssert.assertEquals(checkinDateField.getAttribute("value"), checkinDate);
		
		//Check if the 'Check-out Date' matches expectations
		softAssert.assertEquals(checkoutDateField.getAttribute("value"), checkoutDate);
		
		//Check if the 'City' matches expectations
		city = genMethods.formatLocation(city);
		String actualDepartureCity = cityCodeField.getAttribute("value").substring(cityCodeField.getAttribute("value").length()-city.length());
		softAssert.assertEquals(actualDepartureCity, city);
		
		//Check if the 'Room Type' matches expectations
		softAssert.assertEquals(roomTypeSelectedOption.getText(), roomType);
		
		//Check if the 'GDS Segment Number' matches expectations
		softAssert.assertEquals(gdsSegmentNumberField.getAttribute("value"), gdsSegmentNumber);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
}