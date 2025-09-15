package com.mymatrix.qa.pagemethods;

import java.util.List;

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
	
	//Header Section
	
	public String retrieveCFA() {
		return clientFileNumberLabel.getText();
	}
	
	//Traveler Section
	
	public void expandTravelerSection() {
		//Output to the system and report
		System.out.println("Expanding or Closing the 'Travelers' section");
		reportLogger.log(LogStatus.INFO,  "Expanding or Closing the 'Travelers' section");
		
		//Expand the 'Travelers' section
		expandTravelersSectionButton.click();
		
		//Pause the script a short bit
		genMethods.waitFor(1);
	}
	
	public void navigateToNewTravelerPage() {
		//Output to the system and report
		System.out.println("Navigating to the 'Create new Traveler' page");
		reportLogger.log(LogStatus.INFO,  "Navigating to the 'Create new Traveler' page");
		
		//Click the 'New Traveler' button to open the 'New Traveler' page
		newTravelerButton.click();
	}
	
	public void navigateToTravelerPage(String name, String surname) {
		//Output to the system and report
		System.out.println("Navigating to the 'Traveler' page for traveler: " + name + " " + surname);
		reportLogger.log(LogStatus.INFO,  "Navigating to the 'Traveler' page for traveler: " + name + " " + surname);
		
		//Initialize Variable(s)
		name = name.toUpperCase();
		surname = surname.toUpperCase();
		
		//Click the traveler name to bring up that traveler's detail page
		if (!name.equalsIgnoreCase("") && surname.equalsIgnoreCase("")) {
			locateTravelerPageViaName(name);
		} else if (name.equalsIgnoreCase("") && !surname.equalsIgnoreCase("")) {
			locateTravelerPageViaSurname(surname);
		} else if (!name.equalsIgnoreCase("") && !surname.equalsIgnoreCase("")) {
			locateTravelerPageViaFullName(name, surname);
		} else {
			surnameLabelList.get(0).click();
		}
	}
	
	//Itinerary Section
	
	public void expandItinerarySection() {
		//Output to the system and report
		System.out.println("Expand the 'Itineraries' section");
		reportLogger.log(LogStatus.INFO,  "Expand the 'Itineraries' section");
		
		//Expand the 'Itineraries' section
		expandItinerarySectionButton.click();
		
		//Pause the script a short bit
		genMethods.waitFor(1);
	}
	
	public void navigateToNewItineraryPage(String itineraryType) {
		//Output to the system and report
		System.out.println("Navigating to the 'Create new " + itineraryType + " Itinerary' page");
		reportLogger.log(LogStatus.INFO,  "Navigating to the 'Create new " + itineraryType + " Itinerary' page");
		
		//Click the relevant 'New Itinerary' button to open the desired 'New Itinerary' page
		switch (itineraryType.toLowerCase()) {
			case "air":
				newAirItineraryButton.click();
				break;
			case "hotel":
				newHotelItineraryButton.click();
				break;
			case "car":
				newCarItineraryButton.click();
				break;
			case "tour":
				newTourItineraryButton.click();
				break;
			case "ship":
				newShipItineraryButton.click();
				break;
			case "insurance":
				newInsuranceItineraryButton.click();
				break;
			case "misc":
				newMiscItineraryButton.click();
				break;
			case "rail":
				newRailItineraryButton.click();
				break;
			case "bus":
				newBusItineraryButton.click();
				break;
			case "print":
				printItineraryButton.click();
				break;
			default:
				System.out.println("Error locating the desired new itinerary type page for '" + itineraryType + "'");
		}
	}
	
	public boolean navigateToItineraryPage(String environment, String itineraryType, String segmentNumber) {
		//Output to the system and report
		System.out.println("Navigating to the '" + itineraryType + " Itinerary' detail page with segment number (" + segmentNumber + ")");
		reportLogger.log(LogStatus.INFO,  "Navigating to the '" + itineraryType + " Itinerary' detail page with segment number (" + segmentNumber + ")");
		
		//Initialize Variable(s)
		List<WebElement> itinerarySegmentNumberLabelList;
		
		//Select the list of the desired itinerary type
		itinerarySegmentNumberLabelList = getEnvironmentItineraryType(environment, itineraryType);
		
		//Locate and click the relevant itinerary from the list
		for (int i = 0; i < itinerarySegmentNumberLabelList.size(); i++) {
			if (segmentNumber.equals(itinerarySegmentNumberLabelList.get(i).getText())) {
				itinerarySegmentNumberLabelList.get(i).click();
				return true;
			}
		}
		
		//Return a value to indicate whether the ticket was found or not
		return false;
	}
	
	//Accounting Section
	
	//Click the 'New BSP/Arc Ticket' button
	public void clickNewBSPTicketButton() {
		newBSPButton.click();
	}
	
	//Click the 'New Non-BSP/Non-Arc Ticket' button
	public void clickNewNonBSPTicketButton() {
		newNonBSPButton.click();
	}
	
	//Click the 'New Service Fee' button
	public void clickNewServiceFeeButton() {
		newServiceFeeButton.click();
	}
	
	//Navigate to the 'Ticket Detail' page
	public boolean navigateToExistingTicketDetailPage(String environment, String documentNumber) {
		//Output to the system and report
		System.out.println("Navigating to the 'Ticket Detail' page for Ticket (" + documentNumber + ")");
		reportLogger.log(LogStatus.INFO,  "Navigating to the 'Ticket Detail' page for Ticket (" + documentNumber + ")");
		
		//Initialize Variable(s)
		List<WebElement> documentNumberLabelList;
		
		//Set the List<WebElement> to the appropriate list of WebElements
		if (environment.equalsIgnoreCase("au prod")) {
			documentNumberLabelList = auProdDocumentNumberLabelList1;
		} else if (environment.equalsIgnoreCase("ca 01 prod") || environment.equalsIgnoreCase("harvey prod")) {
			documentNumberLabelList = caProdDocumentNumberLabelList1;
		} else if (environment.equalsIgnoreCase("us prod")) {
			documentNumberLabelList = usProdDocumentNumberLabelList1;
		} else if (environment.equalsIgnoreCase("us qa")) {
			documentNumberLabelList = usQADocumentNumberLabelList1;
		} else {
			documentNumberLabelList = null;
		}
		
		//Locate and click the relevant Ticket
		for (int i = 0; i < documentNumberLabelList.size(); i++) {
			if (documentNumber.equals(documentNumberLabelList.get(i).getText())) {
				documentNumberLabelList.get(i).click();
				return true;
			}
		}
		
		//Set the List<WebElement> to the appropriate list of WebElements
		if (environment.equalsIgnoreCase("au prod")) {
			documentNumberLabelList = auProdDocumentNumberLabelList2;
		} else if (environment.equalsIgnoreCase("ca 01 prod") || environment.equalsIgnoreCase("harvey prod")) {
			documentNumberLabelList = caProdDocumentNumberLabelList2;
		} else if (environment.equalsIgnoreCase("us prod")) {
			documentNumberLabelList = usProdDocumentNumberLabelList2;
		} else if (environment.equalsIgnoreCase("us qa")) {
			documentNumberLabelList = usQADocumentNumberLabelList2;
		} else {
			documentNumberLabelList = null;
		}
		
		//Locate and click the relevant Ticket
		for (int i = 0; i < documentNumberLabelList.size(); i++) {
			if (documentNumber.equals(documentNumberLabelList.get(i).getText())) {
				documentNumberLabelList.get(i).click();
				return true;
			}
		}
		
		//Return a value to indicate whether the ticket was found or not
		return false;
	}
	
	public boolean navigateToExistingCostLinePage(String environment, String costLineNumber) {
		//Output to the system and report
		System.out.println("Navigating to the #" + costLineNumber + " Cost Line detail page");
		reportLogger.log(LogStatus.INFO,  "Navigating to the #" + costLineNumber + " Cost Line detail page");
		
		//Locate and click the relevant itinerary from the list
		for (int i = 0; i < costLineNumberList.size(); i++) {
			if (costLineNumber.equals(costLineNumberList.get(i).getText())) {
				costLineNumberList.get(i).click();
				return true;
			}
		}
		
		//Return a value to indicate whether the ticket was found or not
		return false;
	}
	
	public void navigateToAccountingSection() {
		accountingSectionButton.click();
	}
	
	public void navigateToCostLineSection() {
		costSectionButton.click();
	}
	
	public void navigateToNewCostLinePage() {
		newCostLineButton.click();
	}
	
	// ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ \\
	// *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** \\
	// ~~~ *** ~~~ Verify Checkpoints Methods ~~~ *** ~~~ \\
	// *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** \\
	// ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ *** ~~~ \\
	
	public SoftAssert verifyClientFileCreation(SoftAssert softAssert) {
		//Output to the system and report
		System.out.println("Checking if the 'PNR' appears in the Client File");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'PNR' appears in the Client File");
		
		//Check if the Client File page is up
		try {
			softAssert.assertEquals(expandTravelersSectionButton.isDisplayed(), true);
		} catch (Exception e) {
			softAssert.assertEquals("Client File not created", "Client File created");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
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
	
	private SoftAssert findDocumentNumberPositionSeven(SoftAssert softAssert, String documentNumber) {
		for (int i = 0; i < caProdDocumentNumberLabelList1.size(); i++) {
			
			//Iterate through the list of document numbers
			if (documentNumber.equalsIgnoreCase(caProdDocumentNumberLabelList1.get(i).getText())) {
				
				//Compare the document number with expectations
				softAssert.assertEquals(caProdDocumentNumberLabelList1.get(i).getText(), documentNumber);
				
				//Return the status for the SoftAssert
				return softAssert;
			}
			
			//Iterate through the list of document numbers
			if (documentNumber.equalsIgnoreCase(caProdDocumentNumberLabelList2.get(i).getText())) {
				
				//Compare the document number with expectations
				softAssert.assertEquals(caProdDocumentNumberLabelList2.get(i).getText(), documentNumber);
				
				//Return the status for the SoftAssert
				return softAssert;
			}
		}
		
		//Compare the first document number with expectations
		softAssert.assertEquals(caDocumentNumberLabel.getText(), documentNumber);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyDocumentNumber(SoftAssert softAssert, String environment, String documentNumber) {
		//Output to the system and report
		System.out.println("Checking if the 'Document Number' appears in the Client File");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Document Number' appears in the Client File");
		
		//Check if the 'Document Number' matches expectations
		if (environment.toLowerCase().contains("au prod")) {
			softAssert.assertEquals(auDocumentNumberLabel.getText(), documentNumber);
		} else if (environment.toLowerCase().contains("ca 01 prod") || environment.toLowerCase().contains("harvey prod") || environment.toLowerCase().contains("us qa")) {
//			softAssert.assertEquals(caDocumentNumberLabel.getText(), documentNumber);
			return findDocumentNumberPositionSeven(softAssert, documentNumber);
		} else {
			softAssert.assertEquals(usDocumentNumberLabel.getText(), documentNumber);
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}