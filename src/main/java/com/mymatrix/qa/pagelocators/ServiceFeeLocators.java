package com.mymatrix.qa.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class ServiceFeeLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GenericMethods genMethods;
	protected Actions actions;
	
	//Constructor
	public ServiceFeeLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GenericMethods();
		actions = new Actions(eDriver);
	}
	
	//Passenger Service Fee
	@FindBy(xpath="//span[@id='__tab_ContentPlaceHolder1_TabContainer1_TabPanel1']")
	protected WebElement passengerTab;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_TabContainer1_TabPanel1_ServiceFeePassenger1_passengerAmountTextBox']")
	protected WebElement amountField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_TabContainer1_TabPanel1_ServiceFeePassenger1_passengerDescriptionTextBox']")
	protected WebElement descriptionField;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_TabContainer1_TabPanel1_ServiceFeePassenger1_formOfPaymentDropDownList']")
	protected WebElement paymentTypeDropDown;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_TabContainer1_TabPanel1_ServiceFeePassenger1_formOfPaymentDropDownList']//option[@selected='selected']")
	protected WebElement paymentTypeOption;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_TabContainer1_TabPanel1_ServiceFeePassenger1_TravellerList1_travellerGridView_travellerCheckBox_0']")
	protected WebElement firstCostlineCheckbox;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_TabContainer1_TabPanel1_ServiceFeePassenger1_saveButton']")
	protected WebElement saveButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_TabContainer1_TabPanel1_ServiceFeePassenger1_addAnotherButton']")
	protected WebElement addAnotherButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_TabContainer1_TabPanel1_ServiceFeePassenger1_cancelButton']")
	protected WebElement cancelButton;
	
	//Hotel Service Fee
	@FindBy(xpath="//span[@id='__tab_ContentPlaceHolder1_TabContainer1_TabPanel2']")
	protected WebElement hotelTab;
	
	//Car Service Fee
	@FindBy(xpath="//span[@id='__tab_ContentPlaceHolder1_TabContainer1_TabPanel3']")
	protected WebElement carTab;
	
	//Document Service Fee
	@FindBy(xpath="//span[@id='__tab_ContentPlaceHolder1_TabContainer1_TabPanel4']")
	protected WebElement documentTab;
	
}