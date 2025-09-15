package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.NonBSPTicketLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class NonBSPTicketPage extends NonBSPTicketLocators {
	
	public NonBSPTicketPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void enterTicketInfo(String supplierCode, String bookingNumber, String ticketNumber, String paymentType) {
		//Output to the system and report
		System.out.println("Entering new Ticket info");
		reportLogger.log(LogStatus.INFO,  "Entering new Ticket info");
		
		//Enter Supplier Code
		supplierField.clear();
		supplierField.sendKeys(supplierCode);
		
		//Confirm the Supplier Code
		confirmSupplierButton.click();
		
		//Enter Booking Number
		if (!bookingNumber.equalsIgnoreCase("") && !bookingNumber.equalsIgnoreCase(null)) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		
		//Enter Ticket Number
		ticketNumberField.clear();
		ticketNumberField.sendKeys(ticketNumber);
		
		//Select Payment Type
		Select selectPaymentType = new Select(paymentTypeDropDown);
		selectPaymentType.selectByVisibleText(paymentType);
	}
	
	public void modifyTicketInfo(String bookingNumber, String paymentType, String description, String baseCost, String taxAmount, String taxCode, String cityCode) {
		//Output to the system and report
		System.out.println("Modifying Ticket info");
		reportLogger.log(LogStatus.INFO,  "Modifying Ticket info");
		
		//Enter Booking Number
		if (!bookingNumber.equalsIgnoreCase("") && !bookingNumber.equalsIgnoreCase(null)) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		
		//Select Payment Type
		Select selectPaymentType = new Select(paymentTypeDropDown);
		selectPaymentType.selectByVisibleText(paymentType);
		
		//Enter Description
		descriptionField.clear();
		descriptionField.sendKeys(description);
		
		//Enter Base Cost
		baseCostField.clear();
		baseCostField.sendKeys(baseCost);
		
		//Enter Tax Amount
		firstTaxAmountField.clear();
		firstTaxAmountField.sendKeys(taxAmount);
		
		//Enter Tax Code
		firstTaxCode.clear();
		firstTaxCode.sendKeys(taxCode);
		
		//Enter City Code
		firstCityCode.clear();
		firstCityCode.sendKeys(cityCode);
	}
	
	//Select the first Passenger from the list
	public void selectPassenger() {
		if (!firstTravelerCheckbox.isSelected()) {
			genMethods.waitFor(1);
			firstTravelerCheckbox.click();
		}
	}
	
	//Select the first Cost Line
	public void selectCostLine() {
		if (!firstCostlineCheckbox.isSelected()) {
			firstCostlineCheckbox.click();
		}
	}
	
	//Click the 'Save' button to save the Itinerary info
	public void clickSaveButton() {
		//Output to the system and report
		System.out.println("Saving the Ticket info");
		reportLogger.log(LogStatus.INFO,  "Saving the Ticket info");
		
		//Click the 'Save' button
		saveButton.click();
	}
	
	//Click the 'Add Another' button to save the Itinerary info & immediately enter the 'Create New' page for the Itinerary
	public void clickAddAnotherButton() {
		addAnotherButton.click();
	}
	
	//Click the 'Cancel' button to exit the Air Itinerary page
	public void clickCancelButton() {
		cancelButton.click();
	}
	
	public SoftAssert verifyTicketCreation(SoftAssert softAssert, String supplierCode, String paymentType) {
		//Output to the system and report
		System.out.println("Checking if the Ticket that was created matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the Ticket that was created matches expectations");
		
		//Check if the 'Supplier Code' matches expectations
		supplierCode = genMethods.formatTicketSupplier(supplierCode);
		softAssert.assertEquals(supplierLabel.getText(), supplierCode);
		
		//Check if the 'Payment Type' matches expectations
		softAssert.assertEquals(paymentTypeOption.getText(), paymentType);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyTicketModification(SoftAssert softAssert, String supplierCode, String paymentType, String description, String baseCost, String taxAmount, String taxCode, String cityCode) {
		//Output to the system and report
		System.out.println("Checking if the Ticket that was modified matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the Ticket that was modified matches expectations");
		
		//Check if the 'Supplier Code' matches expectations
		supplierCode = genMethods.formatTicketSupplier(supplierCode);
		softAssert.assertEquals(supplierLabel.getText(), supplierCode);
		
		//Check if the 'Payment Type' matches expectations
		softAssert.assertEquals(paymentTypeOption.getText(), paymentType);
		
		//Check if the 'Description' matches expectations
		description = description.toUpperCase();
		softAssert.assertEquals(descriptionField.getText(), description);
		
		//Check if the 'Base Cost' matches expectations
		softAssert.assertEquals(baseCostField.getAttribute("value"), baseCost);
		
		//Check if the 'Tax Amount' matches expectations
		softAssert.assertEquals(firstTaxAmountField.getAttribute("value"), taxAmount);
		
		//Check if the 'Tax Code' matches expectations
		softAssert.assertEquals(firstTaxCode.getAttribute("value"), taxCode);
		
		//Check if the 'City Code' matches expectations
		softAssert.assertEquals(firstCityCode.getAttribute("value"), cityCode);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}