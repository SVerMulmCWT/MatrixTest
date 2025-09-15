package com.mymatrix.qa.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class BSPTicketLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GenericMethods genMethods;
	protected Actions actions;
	
	//Constructor
	public BSPTicketLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GenericMethods();
		actions = new Actions(eDriver);
	}
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_supplierTextBox']")
	protected WebElement supplierField;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_formOfPaymentDropDownList']")
	protected WebElement paymentTypeDropDown;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_formOfPaymentDropDownList']//option[@selected='selected']")
	protected WebElement paymentTypeOption;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_ticketNumberTextBox']")
	protected WebElement ticketNumberField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_TravellerList1_travellerGridView_travellerCheckBox_0']")
	protected WebElement firstTravelerCheckbox;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_costListGridView_costListCheckBox_0']")
	protected WebElement firstCostlineCheckbox;
	
	@FindBy(xpath="//textarea[@id='ContentPlaceHolder1_descriptionTextBox']")
	protected WebElement descriptionField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_baseCostTextBox']")
	protected WebElement baseCostField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_formOfPaymentControl_ccNumberTextBox']")
	protected WebElement creditCardField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_taxesGridView_taxAmountTextBox_0']")
	protected WebElement firstTaxAmountField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_taxesGridView_taxCodeTextBox_0']")
	protected WebElement firstTaxCode;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_taxesGridView_cityCodeTextBox_0']")
	protected WebElement firstCityCode;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_saveButton']")
	protected WebElement saveButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_addAnotherButton']")
	protected WebElement addAnotherButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_cancelButton']")
	protected WebElement cancelButton;
	
	//'Service Fee - Document' Labels
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_TabContainer1_TabPanel4_TicketList1_FormOfPayment1_ccVendorDropDownList']//option[@selected='selected']")
	protected WebElement serviceFeePaymentTypeDropDown;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_TabContainer1_TabPanel4_TicketList1_ticketAmountTextBox']")
	protected WebElement serviceFeeAmountField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_TabContainer1_TabPanel4_TicketList1_FormOfPayment1_ccNumberTextBox']")
	protected WebElement serviceFeeCreditCardField;
	
}