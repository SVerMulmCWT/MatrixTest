package com.mymatrix.qa.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class ChangeBranchLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GenericMethods genMethods;
	protected Actions actions;
	
	//Constructor
	public ChangeBranchLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GenericMethods();
		actions = new Actions(eDriver);
	}
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_quickChangeTextBox']")
	protected WebElement changeBranchInputField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_saveButton']")
	protected WebElement changeBranchButton;
	
	@FindBy(xpath="//span[@id='ProfileHeader1_sigInBranchLabel']")
	protected WebElement branchLabel;
	
}