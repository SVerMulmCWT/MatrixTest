package com.mymatrix.qa.pagelocators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class SearchClientFileLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GenericMethods genMethods;
	protected Actions actions;
	
	//Constructor
	public SearchClientFileLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GenericMethods();
		actions = new Actions(eDriver);
	}
	
	//Search Criteria Fields
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_firstNameTextBox']")
	protected WebElement searchFirstNameField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_lastNameTextBox']")
	protected WebElement searchSurnameField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_pnrLocatorTextBox']")
	protected WebElement searchPNRField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_departureDateTextBox']")
	protected WebElement searchDepartureDateField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_cfaTextBox']")
	protected WebElement searchClientFileNumberField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_bookingBranchTextBox']")
	protected WebElement searchBookingBranchField;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_documentTypeDropDownList']")
	protected WebElement searchDocumentTypeDropDown;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_documentNumberTextBox']")
	protected WebElement searchDocumentNumberField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_supplierBookingNumber']")
	protected WebElement searchSupplierBookingNumberField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_clientFileCodeTextBox']")
	protected WebElement searchClientFileCodeField;
	
	//Click the 'Search' Button
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_searchButton']")
	protected WebElement searchButton;
	
	//List of Search Results
	@FindBy(xpath="//tr[@id='ContentPlaceHolder1_ClientfileSearchUserControlGrid_clientFileSearchGridViewDX_DXDataRow0']//td[2]")
	protected List<WebElement> firstSearchResult1;
	
	@FindBy(xpath="//tr[@id='ContentPlaceHolder1_QBD_TicketUserControlGrid_qbdTicketSearchGridViewDX_DXDataRow0']//td[2]")
	protected List<WebElement> firstSearchResult2;
	
	//Create New Client File
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_newButton']")
	protected WebElement newClientFileButton;
	
}