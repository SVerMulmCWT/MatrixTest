package com.mymatrix.qa.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class CarItineraryLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GenericMethods genMethods;
	
	//Constructor
	public CarItineraryLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GenericMethods();
	}
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_supIdTextBox']")
	protected WebElement carCodeField;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_carTypeDropDownList']")
	protected WebElement carTypeDropDown;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_carTypeDropDownList']//option[@selected='selected']")
	protected WebElement carTypeSelectedOption;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_originTextBox']")
	protected WebElement pickupCityField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_startTextBox']")
	protected WebElement pickupDateField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_endTextBox']")
	protected WebElement dropoffDateField;
	
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