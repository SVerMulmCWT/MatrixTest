package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import com.mymatrix.qa.pagelocators.SearchClientFileLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SearchClientFilePage extends SearchClientFileLocators {
	
	public SearchClientFilePage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	//Enter a 'First Name' as search criteria
	public void enterFirstName(String name) {
		//Output to the system and report
		System.out.println("Entering the 'first name' criteria -> " + name);
		reportLogger.log(LogStatus.INFO,  "Entering the 'first name' criteria -> " + name);
		
		//Enter a 'First Name' as search criteria
		firstNameInputField.sendKeys(name);
	}
	
	//Enter a 'Surname' as search criteria
	public void enterSurname(String surname) {
		//Output to the system and report
		System.out.println("Entering the 'surname' criteria -> " + surname);
		reportLogger.log(LogStatus.INFO,  "Entering the 'surname' criteria -> " + surname);
		
		//Enter a 'Surname' as search criteria
		surnameInputField.sendKeys(surname);
	}
	
	//Enter a 'PNR' as search criteria
	public void enterPNR(String pnr) {
		//Output to the system and report
		System.out.println("Entering the 'PNR' criteria -> " + pnr);
		reportLogger.log(LogStatus.INFO,  "Entering the 'PNR' criteria -> " + pnr);
		
		//Enter a 'PNR' as search criteria
		pnrInputField.sendKeys(pnr);
	}
	
	//Enter a 'Departure Date' as search criteria
	public void enterDepartureDate(String departureDate) {
		//Output to the system and report
		System.out.println("Entering the 'Departure Date' criteria -> " + departureDate);
		reportLogger.log(LogStatus.INFO,  "Entering the 'Departure Date' criteria -> " + departureDate);
		
		//Enter a 'Departure Date' as search criteria
		departureDateInputField.sendKeys(departureDate);
	}
	
	//Enter a 'Client File Number' as search criteria
	public void enterClientFileNumber(String clientFileNumber) {
		//Output to the system and report
		System.out.println("Entering the 'Client File Number' criteria -> " + clientFileNumber);
		reportLogger.log(LogStatus.INFO,  "Entering the 'Client File Number' criteria -> " + clientFileNumber);
		
		//Enter a 'Client File Number' as search criteria
		clientFileNumberInputField.sendKeys(clientFileNumber);
	}
	
	//Enter a 'Booking Branch' as search criteria
	public void enterBookingBranch(String bookingBranch) {
		//Output to the system and report
		System.out.println("Entering the 'Booking Branch' criteria -> " + bookingBranch);
		reportLogger.log(LogStatus.INFO,  "Entering the 'Booking Branch' criteria -> " + bookingBranch);
		
		//Enter a 'Booking Branch' as search criteria
		bookingBranchInputField.sendKeys(bookingBranch);
	}
	
	//Enter a 'Document Type' as search criteria
	public void enterDocumentType(String documentType) {
		//Output to the system and report
		System.out.println("Entering the 'Document Type' criteria -> " + documentType);
		reportLogger.log(LogStatus.INFO,  "Entering the 'Document Type' criteria -> " + documentType);
		
		//Initialie Variable(s)
		Select documentTypeList = new Select(documentTypeDropDown);
		
		//Enter a 'Document Type' as search criteria
		documentTypeList.selectByVisibleText(documentType);
	}
	
	//Enter a 'Document Number' as search criteria
	public void enterDocumentNumber(String documentNumber) {
		//Output to the system and report
		System.out.println("Entering the 'Document Number' criteria -> " + documentNumber);
		reportLogger.log(LogStatus.INFO,  "Entering the 'Document Number' criteria -> " + documentNumber);
		
		//Enter a 'Document Number' as search criteria
		documentNumberInputField.sendKeys(documentNumber);
	}
	
	//Enter a 'Supplier Booking Number' as search criteria
	public void enterSupplierBookingNumber(String supplierBookingNumber) {
		//Output to the system and report
		System.out.println("Entering the 'Supplier Booking Number' criteria -> " + supplierBookingNumber);
		reportLogger.log(LogStatus.INFO,  "Entering the 'Supplier Booking Number' criteria -> " + supplierBookingNumber);
		
		//Enter a 'Supplier Booking Number' as search criteria
		supplierBookingNumberInputField.sendKeys(supplierBookingNumber);
	}
	
	//Enter a 'Client File Code' as search criteria
	public void enterClientFileCode(String clientFileCode) {
		//Output to the system and report
		System.out.println("Entering the 'Client File Code' criteria -> " + clientFileCode);
		reportLogger.log(LogStatus.INFO,  "Entering the 'Client File Code' criteria -> " + clientFileCode);
		
		//Enter a 'Client File Code' as search criteria
		clientFileCodeInputField.sendKeys(clientFileCode);
	}
	
	//Click the 'Search' button to submit the search criteria
	public void clickSearchButton() {
		//Output to the system and report
		System.out.println("Click the 'Search' button to submit the search criteria");
		reportLogger.log(LogStatus.INFO,  "Click the 'Search' button to submit the search criteria");
		
		//Click the 'Search' button to submit the search criteria
		searchButton.click();
		
		//Click the first entry of the search results list
		if (firstSearchResult1.size() > 0) {
			firstSearchResult1.get(0).click();
		} else if (firstSearchResult2.size() > 0) {
			firstSearchResult2.get(0).click();
		}
	}
	
	
}