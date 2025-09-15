package com.mymatrix.qa.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class MainLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GenericMethods genMethods;
	protected Actions actions;
	
	//Constructor
	public MainLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GenericMethods();
		actions = new Actions(eDriver);
	}
	
	//MyMatrix Navigate to 'Clientfile Information'
	@FindBy(xpath="//a[@class='menuButton' and contains(text(), 'GoTo')]")
	protected WebElement goToTab;
	
	@FindBy(xpath="//a[@class='menuButton' and contains(text(), 'View')]")
	protected WebElement viewTab;
	
	@FindBy(xpath="//a[@class='menuItem' and contains(text(), 'Clientfile Information')]")
	protected WebElement clientFileInformationItem;
	
	@FindBy(xpath="//a[@class='menuItem' and contains(text(), 'Calendar/Tasks/Queues')]")
	protected WebElement calendarTasksQueuesItem;
	
	@FindBy(xpath="//a[@class='menuItem' and contains(text(), 'Agency Sales Report')]")
	protected WebElement agencySalesReportItem;
	
	@FindBy(xpath="//span[@id='ContentPlaceHolder1_Label2']")
	protected WebElement irQueuesLink;
	
	//MyMatrix Change Branch Page Identifiers
	@FindBy(xpath="//a[@class='menuButton' and contains(text(), 'Change Branch')]")
	protected WebElement changeBranchTab;
	
	@FindBy(xpath="//a[@class='menuItem' and contains(text(), 'Change Branch')]")
	protected WebElement changeBranchItem;
	
}