package com.mymatrix.qa.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class HotelItineraryLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GenericMethods genMethods;
	
	//Constructor
	public HotelItineraryLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GenericMethods();
	}
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_supIdTextBox']")
	protected WebElement hotelChainField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_startTextBox']")
	protected WebElement checkinDateField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_endTextBox']")
	protected WebElement checkoutDateField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_originTextBox']")
	protected WebElement cityCodeField;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_roomTypeDropDownList']")
	protected WebElement roomTypeDropDown;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_roomTypeDropDownList']//option[@selected='selected']")
	protected WebElement roomTypeSelectedOption;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_GDSSegmentNumberTextBox']")
	protected WebElement gdsSegmentNumberField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_TravellerList1_travellerGridView_travellerCheckBox_0']")
	protected WebElement selectTravelerCheckbox;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_saveButton']")
	protected WebElement saveButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_updateButton']")
	protected WebElement updateButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_addAnotherButton']")
	protected WebElement addAnotherButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_cancelButton']")
	protected WebElement cancelButton;
	
}