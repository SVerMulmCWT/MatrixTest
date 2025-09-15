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
	
	public SoftAssert verifyAirItineraryDetails(SoftAssert softAssert, String airlineCode, String flightNumber, String classCode, String departureCity, String arrivalCity, String departureDate) {
		//Output to the system and report
		System.out.println("Checking if the 'Air Itinerary' details matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Air Itinerary' details matches expectations");
		
		//Check if the 'Airline Code' matches expectations
		softAssert.assertEquals(airlineInputField.getAttribute("value"), airlineCode);
		
		//Check if the 'Flight Number' matches expectations
		softAssert.assertEquals(flightNumberInputField.getAttribute("value"), flightNumber);
		
		//Check if the 'Class' matches expectations
		softAssert.assertEquals(classInputField.getAttribute("value"), classCode);
		
		//Check if the 'Departure City' matches expectations
		softAssert.assertEquals(departureCityInputField.getAttribute("value"), departureCity);
		
		//Check if the 'Arrival City' matches expectations
		softAssert.assertEquals(arrivalCityInputField.getAttribute("value"), arrivalCity);
		
		//Check if the 'Departure Date' matches expectations
		softAssert.assertEquals(departureDateInputField.getAttribute("value"), departureDate);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
}