package com.mymatrix.qa.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class CreateClientFileLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GenericMethods genMethods;
	protected Actions actions;
	
	//Constructor
	public CreateClientFileLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GenericMethods();
		actions = new Actions(eDriver);
	}
	
	//Enter Client File Info
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_departureDateTextBox']")
	protected WebElement departureDateField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_deletionDateTextBox']")
	protected WebElement deletionDateField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_cfaCodeTextBox']")
	protected WebElement clientFileCodeField;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_cfaTypeDropDownList']")
	protected WebElement clientFileTypeDropDown;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_deptCodeTextBox']")
	protected WebElement departmentCodeField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_firstNameTextBox']")
	protected WebElement firstNameField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_lastNameTextBox']")
	protected WebElement surnameField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_destinationTextBox']")
	protected WebElement destinationField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_mobilePhoneACodeTextBox']")
	protected WebElement mobileNumberField1;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_mobilePhone3TextBox']")
	protected WebElement mobileNumberField2;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_mobilePhone4TextBox']")
	protected WebElement mobileNumberField3;
	
	//Save Info & Create Client File
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_saveButton']")
	protected WebElement saveButton;
	
}