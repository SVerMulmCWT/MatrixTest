package com.mymatrix.qa.pagelocators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class IRQueueCountLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GenericMethods genMethods;
	protected Actions actions;
	
	//Constructor
	public IRQueueCountLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GenericMethods();
		actions = new Actions(eDriver);
	}
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_IRQueuesTypeDropDownList1']")
	protected WebElement queueTypeDropDown;
	
	@FindBy(xpath="//select[@id='ContentPlaceHolder1_statusDropDownList']")
	protected WebElement statusDropDown;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_IRQueuesSearchButton']")
	protected WebElement searchButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_IRQueuesQueueCountButton']")
	protected WebElement queueCountButton;
	
	@FindBy(xpath="//td[@class='dxgvCommandColumn dxgv']")
	protected List<WebElement> searchResultsList;
	
	@FindBy(xpath="//b[@class='dxp-lead dxp-summary']")
	protected WebElement searchResultsLabel;
	
	@FindBy(xpath="//table[@id='ContentPlaceHolder1_branchQueueCountGridView']//tr//td")
	protected List<WebElement> queueCountResultsList;
	
}