package com.mymatrix.qa.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class CostLineLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GenericMethods genMethods;
	
	//Constructor
	public CostLineLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GenericMethods();
	}
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_ItineraryList1_ItineraryGridView_costItinerariesCheckBox_0']")
	protected WebElement firstItineraryCheckbox;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_TravellerList1_travellerGridView_travellerCheckBox_0']")
	protected WebElement firstTravelerCheckbox;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_AddNewCostControl1_currencyDropDownList']")
	protected WebElement currencyTypeDropDown;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_AddNewCostControl1_currencyDropDownList']//option[@selected='selected']")
	protected WebElement currencyTypeSelectedOption;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_AddNewCostControl1_baseCostTextBox']")
	protected WebElement baseCostField;
	
	@FindBy(xpath="//textarea[@id='ContentPlaceHolder1_AddNewCostControl1_descriptionTextBox']")
	protected WebElement descriptionTextBox;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_AddNewCostControl1_taxesGridView_taxAmountTextBox_0']")
	protected WebElement taxAmountField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_AddNewCostControl1_taxesGridView_taxCodeTextBox_0']")
	protected WebElement taxCodeField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_saveButton']")
	protected WebElement saveButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_addAnotherChargeButton']")
	protected WebElement addAnotherButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_cancelButton']")
	protected WebElement cancelButton;
	
}