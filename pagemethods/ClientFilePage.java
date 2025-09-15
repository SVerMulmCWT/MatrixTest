package com.mymatrix.qa.pagemethods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.ClientFileLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ClientFilePage extends ClientFileLocators {
	
	public ClientFilePage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void navigateToTravellerPage(String name, String surname) {
		//Output to the system and report
		System.out.println("Navigating to the 'Traveler' page for traveler: " + name + " " + surname);
		reportLogger.log(LogStatus.INFO,  "Navigating to the 'Traveler' page for traveler: " + name + " " + surname);
		
		//Initialize Variable(s)
		name = name.toUpperCase();
		surname = surname.toUpperCase();
		
		//Open the Traveler section
		expandTravellersSectionButton.click();
		
		//Pause the script a short bit
		genMethods.waitFor(1);
		
		//Click the traveler name to bring up that traveler's detail page
		eDriver.findElement(By.xpath("//td[contains(text(), '" + name + "')]//following-sibling::td[contains(text(), '" + surname + "')]")).click();
	}
	
	//Expand the 'Itinerary' section
	public void expandItinerarySection() {
		//Output to the system and report
		System.out.println("Expand the 'Itinerary' section");
		reportLogger.log(LogStatus.INFO,  "Expand the 'Itinerary' section");
		
		//Expand the 'Itinerary' section
		expandItinerarySectionButton.click();
	}
	
	public List<WebElement> getAUItineraryType(String itineraryType) {
		switch (itineraryType.toLowerCase()) {
			case "air":
				return auAirItinerarySegmentNumberLabelList;
			case "hotel":
				return auHotelItinerarySegmentNumberLabelList;
			default:
				return null;
		}
	}
	
	public List<WebElement> getCAItineraryType(String itineraryType) {
		switch (itineraryType.toLowerCase()) {
			case "air":
				return caAirItinerarySegmentNumberLabelList;
			case "hotel":
				return caHotelItinerarySegmentNumberLabelList;
			default:
				return null;
		}
	}
	
	public List<WebElement> getMnGItineraryType(String itineraryType) {
		switch (itineraryType.toLowerCase()) {
			case "air":
				return mngAirItinerarySegmentNumberLabelList;
			case "hotel":
				return mngHotelItinerarySegmentNumberLabelList;
			default:
				return null;
		}
	}
	
	public List<WebElement> getUSItineraryType(String itineraryType) {
		switch (itineraryType.toLowerCase()) {
			case "air":
				return usAirItinerarySegmentNumberLabelList;
			case "hotel":
				return usHotelItinerarySegmentNumberLabelList;
			default:
				return null;
		}
	}
	
	public List<WebElement> getEnvironmentItineraryType(String environment, String itineraryType) {
		if (environment.equalsIgnoreCase("au prod")) {
			return getAUItineraryType(itineraryType);
		} else if (environment.equalsIgnoreCase("ca 01 prod") || environment.equalsIgnoreCase("harvey prod")) {
			return getCAItineraryType(itineraryType);
		} else if (environment.equalsIgnoreCase("mng prod")) {
			return getMnGItineraryType(itineraryType);
		} else if (environment.equalsIgnoreCase("mng prod")) {
			return getUSItineraryType(itineraryType);
		} else {
			return null;
		}
	}
	
	public boolean navigateToItineraryPage(String environment, String itineraryType, String segmentNumber) {
		//Output to the system and report
		System.out.println("Navigating to the 'Air Itinerary' detail page with segment number (" + segmentNumber + ")");
		reportLogger.log(LogStatus.INFO,  "Navigating to the 'Air Itinerary' detail page with segment number (" + segmentNumber + ")");
		
		//Initialize Variable(s)
		List<WebElement> itinerarySegmentNumberLabelList;
		boolean foundTicket = false;
		
		//Expand the 'Itinerary' section
		expandItinerarySectionButton.click();
		
		//Select the list of the desired itinerary type
		itinerarySegmentNumberLabelList = getEnvironmentItineraryType(environment, itineraryType);
		
		//Locate and click the relevant itinerary from the list
		for (int i = 0; i < itinerarySegmentNumberLabelList.size(); i++) {
			
			//Pause the script a short bit
			genMethods.waitFor(1);
			
			if (segmentNumber.equals(itinerarySegmentNumberLabelList.get(i).getText())) {
				itinerarySegmentNumberLabelList.get(i).click();
				foundTicket = true;
			}
		}
		
		//Return a value to indicate whether the ticket was found or not
		return foundTicket;
	}
	
	//Navigate to the 'Ticket Detail' page
	public boolean navigateToTicketDetailPage(String environment, String documentNumber) {
		//Output to the system and report
		System.out.println("Navigating to the 'Ticket Detail' page for Ticket (" + documentNumber + ")");
		reportLogger.log(LogStatus.INFO,  "Navigating to the 'Ticket Detail' page for Ticket (" + documentNumber + ")");
		
		//Initialize Variable(s)
		List<WebElement> documentNumberLabelList;
		
		//Set the List<WebElement> to the appropriate list of WebElements
		if (environment.equalsIgnoreCase("au prod")) {
			documentNumberLabelList = auDocumentNumberLabelList1;
		} else if (environment.equalsIgnoreCase("ca 01 prod") || environment.equalsIgnoreCase("harvey prod")) {
			documentNumberLabelList = caDocumentNumberLabelList1;
		} else {
			documentNumberLabelList = usDocumentNumberLabelList1;
		}
		
		//Locate and click the relevant Ticket
		for (int i = 0; i < documentNumberLabelList.size(); i++) {
			if (documentNumber.equals(documentNumberLabelList.get(i).getText()) || documentNumber.equals(documentNumberLabelList.get(i).getText())) {
				documentNumberLabelList.get(i).click();
				return true;
			}
		}
		
		//Set the List<WebElement> to the appropriate list of WebElements
		if (environment.equalsIgnoreCase("au prod")) {
			documentNumberLabelList = auDocumentNumberLabelList2;
		} else if (environment.equalsIgnoreCase("ca 01 prod") || environment.equalsIgnoreCase("harvey prod")) {
			documentNumberLabelList = caDocumentNumberLabelList2;
		} else {
			documentNumberLabelList = usDocumentNumberLabelList2;
		}
		
		//Locate and click the relevant Ticket
		for (int i = 0; i < documentNumberLabelList.size(); i++) {
			if (documentNumber.equals(documentNumberLabelList.get(i).getText()) || documentNumber.equals(documentNumberLabelList.get(i).getText())) {
				documentNumberLabelList.get(i).click();
				return true;
			}
		}
		
		//Return a value to indicate whether the ticket was found or not
		return false;
	}
	
	// ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ \\
	// *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** \\
	// ~~~ *** ~~~ Verify Checkpoints Methods ~~~ *** ~~~ \\
	// *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** \\
	// ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ \\
	
	public SoftAssert verifyPNR(SoftAssert softAssert, String pnr) {
		//Output to the system and report
		System.out.println("Checking if the 'PNR' appears in the Client File");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'PNR' appears in the Client File");
		
		//Check if the 'PNR' matches expectations
		softAssert.assertEquals(pnrLabel.getText(), pnr);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyDepartureDate(SoftAssert softAssert, String departureDate) {
		//Output to the system and report
		System.out.println("Checking if the 'Departure Date' appears in the Client File");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Departure Date' appears in the Client File");
		
		//Check if the 'Departure Date' matches expectations
		softAssert.assertEquals(departureDateLabel.getText(), departureDate);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyClientFileNumber(SoftAssert softAssert, String clientFileNumber) {
		//Output to the system and report
		System.out.println("Checking if the 'Client File Number' appears in the Client File");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Client File Number' appears in the Client File");
		
		//Check if the 'Client File Number' matches expectations
		softAssert.assertEquals(clientFileNumberLabel.getText(), clientFileNumber);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyBookingBranch(SoftAssert softAssert, String bookingBranch) {
		//Output to the system and report
		System.out.println("Checking if the 'Booking Branch' appears in the Client File");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Booking Branch' appears in the Client File");
		
		//Check if the 'Booking Branch' matches expectations
		softAssert.assertEquals(bookingBranchLabel.getText().substring(2), bookingBranch);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyDocumentNumber(SoftAssert softAssert, String environment, String documentNumber) {
		//Output to the system and report
		System.out.println("Checking if the 'Document Number' appears in the Client File");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Document Number' appears in the Client File");
		
		//Check if the 'Document Number' matches expectations
		if (environment.equalsIgnoreCase("au prod")) {
			softAssert.assertEquals(auDocumentNumberLabel.getText(), documentNumber);
		} else if (environment.equalsIgnoreCase("ca 01 prod") || environment.equalsIgnoreCase("harvey prod")) {
			softAssert.assertEquals(caDocumentNumberLabel.getText(), documentNumber);
		} else {
			softAssert.assertEquals(usDocumentNumberLabel.getText(), documentNumber);
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}