package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.HotelItineraryLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HotelItineraryPage extends HotelItineraryLocators {
	
	public HotelItineraryPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public SoftAssert verifyHotelItineraryDetails(SoftAssert softAssert, String hotelChain, String checkinDate, String city, String roomType) {
		//Output to the system and report
		System.out.println("Checking if the 'Air Itinerary' details matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Air Itinerary' details matches expectations");
		
		//Check if the 'Airline Code' matches expectations
		softAssert.assertEquals(hotelChainInputField.getAttribute("value"), hotelChain);
		
		//Check if the 'Flight Number' matches expectations
		softAssert.assertEquals(checkinDateInputField.getAttribute("value"), checkinDate);
		
		//Check if the 'Class' matches expectations
		softAssert.assertEquals(cityInputField.getAttribute("value"), city);
		
		//Check if the 'Departure City' matches expectations
		softAssert.assertEquals(roomTypeSelectedOption.getAttribute("value"), roomType);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
}