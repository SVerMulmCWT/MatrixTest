package com.mymatrix.qa.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class TravelerLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GenericMethods genMethods;
	
	//Constructor
	public TravelerLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GenericMethods();
	}
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_firstNameTextBox']")
	protected WebElement nameField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_lastNameTextBox']")
	protected WebElement surnameField;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_FormOfPayment1_ccVendorDropDownList']")
	protected WebElement creditCardVendorDropDown;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_FormOfPayment1_ccNumberTextBox']")
	protected WebElement creditCardNumberField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_FormOfPayment1_ccExpiryDateTextBox']")
	protected WebElement creditCardExpiryDateField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_FormOfPayment1_authNumTextBox']")
	protected WebElement creditCardAuthorizationNumberField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_saveButton']")
	protected WebElement saveButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_addAnotherButton']")
	protected WebElement addAnotherButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_cancelButton']")
	protected WebElement cancelButton;
	
}