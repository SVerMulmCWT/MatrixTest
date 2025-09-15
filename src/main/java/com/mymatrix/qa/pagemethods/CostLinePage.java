package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.CostLineLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CostLinePage extends CostLineLocators {
	
	public CostLinePage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void enterCostLineItineraryInfo(String currencyType, String baseCost, String description, String taxAmount, String taxCode) {
		//Output to the system and report
		System.out.println("Entering new Cost Line info");
		reportLogger.log(LogStatus.INFO,  "Entering new Cost Line info");
		
		//Select Currency Type
		Select selectCurrencyDropDown = new Select(currencyTypeDropDown);
		selectCurrencyDropDown.selectByVisibleText(currencyType);
		
		//Enter the Base Cost
		baseCostField.clear();
		baseCostField.sendKeys(baseCost);
		
		//Enter the Description
		descriptionTextBox.clear();
		descriptionTextBox.sendKeys(description);
		
		//Enter the Tax Amount
		taxAmountField.clear();
		taxAmountField.sendKeys(taxAmount);
		
		//Enter the Tax Code
		taxCodeField.clear();
		taxCodeField.sendKeys(taxCode);
	}
	
	//Select the First Itinerary
	public void selectItinerary() {
		firstItineraryCheckbox.click();
	}
	
	//Select the First Passenger
	public void selectPassenger() {
		if (!firstTravelerCheckbox.isSelected()) {
			firstTravelerCheckbox.click();
		}
	}
	
	//Click the 'Save' button to save the Itinerary info
	public void clickSaveButton() {
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
	
	public SoftAssert verifyCostLineItineraryDetails(SoftAssert softAssert, String currencyType, String baseCost, String description, String taxAmount, String taxCode) {
		//Output to the system and report
		System.out.println("Checking if the 'Cost Line' details matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Cost Line' details matches expectations");
		
		//Initialize Variable(s)
		description = description.toUpperCase();
		taxCode = taxCode.toUpperCase();
		
		//Check if the 'Currency Type' matches expectations
		softAssert.assertEquals(currencyTypeSelectedOption.getText(), currencyType);
		
		//Check if the 'Base Cost' matches expectations
		softAssert.assertEquals(baseCostField.getAttribute("value"), baseCost);
		
		//Check if the 'Description' matches expectations
		softAssert.assertEquals(descriptionTextBox.getAttribute("value"), description);
		
		//Check if the 'Tax Amount' matches expectations
		softAssert.assertEquals(taxAmountField.getAttribute("value"), taxAmount);
		
		//Check if the 'Tax Code' matches expectations
		softAssert.assertEquals(taxCodeField.getAttribute("value"), taxCode);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
}