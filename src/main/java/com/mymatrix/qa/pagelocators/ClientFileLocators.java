package com.mymatrix.qa.pagelocators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class ClientFileLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GenericMethods genMethods;
	protected Actions actions;
	
	//Constructor
	public ClientFileLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GenericMethods();
		actions = new Actions(eDriver);
	}
	
	//Header Section
	@FindBy(xpath="//span[@id='ProfileHeader1_gdsLocatorLabel']")
	protected WebElement pnrLabel;
	
	@FindBy(xpath="//span[@id='ProfileHeader1_departureDateLabel']")
	protected WebElement departureDateLabel;
	
	@FindBy(xpath="//span[@id='ProfileHeader1_matrixClientFileLabel']")
	protected WebElement clientFileNumberLabel;
	
	@FindBy(xpath="//span[@id='ProfileHeader1_branchLabel']")
	protected WebElement bookingBranchLabel;
	
	@FindBy(xpath="//tr[@id='ContentPlaceHolder1_Main2_clientFileSnapshotTabContainer_TabPanel1_AccountingList1_ASPxGridView1_DXDataRow0']//td[6]")
	protected WebElement auDocumentNumberLabel;
	
	@FindBy(xpath="//tr[@id='ContentPlaceHolder1_Main2_clientFileSnapshotTabContainer_TabPanel1_AccountingList1_ASPxGridView1_DXDataRow0']//td[7]")
	protected WebElement caDocumentNumberLabel;
	
	@FindBy(xpath="//tr[@id='ContentPlaceHolder1_Main2_clientFileSnapshotTabContainer_TabPanel1_AccountingList1_ASPxGridView1_DXDataRow0']//td[4]")
	protected WebElement usDocumentNumberLabel;
	
	//Traveler Section
	@FindBy(xpath="//img[@id='ContentPlaceHolder1_travellerImage']")
	protected WebElement expandTravelersSectionButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_TravellerList1_addNewTravellerImageButton']")
	protected WebElement newTravelerButton;
	
	@FindBy(xpath="//td[@style='width:150px;']/preceding-sibling::td[@style='width:150px;']")
	protected List<WebElement> nameLabelList;
	
	@FindBy(xpath="//td[@style='width:150px;']/following-sibling::td[@style='width:150px;']")
	protected List<WebElement> surnameLabelList;
	
	//Itinerary Section
	@FindBy(xpath="//img[@id='ContentPlaceHolder1_itineraryImage']")
	protected WebElement expandItinerarySectionButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_Itinerary1_addAirImageButton']")
	protected WebElement newAirItineraryButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_Itinerary1_addHotelImageButton']")
	protected WebElement newHotelItineraryButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_Itinerary1_addCarImageButton']")
	protected WebElement newCarItineraryButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_Itinerary1_addTourImageButton']")
	protected WebElement newTourItineraryButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_Itinerary1_addShipImageButton']")
	protected WebElement newShipItineraryButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_Itinerary1_addInsuranceImageButton']")
	protected WebElement newInsuranceItineraryButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_Itinerary1_addOtherImageButton']")
	protected WebElement newMiscItineraryButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_Itinerary1_addRailImageButton']")
	protected WebElement newRailItineraryButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_Itinerary1_addBusImageButton']")
	protected WebElement newBusItineraryButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_Itinerary1_itinPrintImageButton']")
	protected WebElement printItineraryButton;
	
	//Air Itinerary Lists
	@FindBy(xpath="//img[@src='/AusMyMatrix/images/air.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> auProdAirItinerarySegmentNumberLabelList;
	
	@FindBy(xpath="//img[@src='/MyMatrix/images/air.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> caProdAirItinerarySegmentNumberLabelList;
	
	@FindBy(xpath="//img[@src='/MngMyMatrix/images/air.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> mngProdAirItinerarySegmentNumberLabelList;
	
	@FindBy(xpath="//img[@src='/CwtMyMatrix/images/air.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> usProdAirItinerarySegmentNumberLabelList;
	
	@FindBy(xpath="//img[@src='/USMyMatrix/images/air.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> usQAAirItinerarySegmentNumberLabelList;
	
	//Hotel Itinerary Lists
	@FindBy(xpath="//img[@src='/AusMyMatrix/images/hotel.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> auProdHotelItinerarySegmentNumberLabelList;
	
	@FindBy(xpath="//img[@src='/MyMatrix/images/hotel.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> caProdHotelItinerarySegmentNumberLabelList;
	
	@FindBy(xpath="//img[@src='/MngMyMatrix/images/hotel.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> mngProdHotelItinerarySegmentNumberLabelList;
	
	@FindBy(xpath="//img[@src='/CwtMyMatrix/images/hotel.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> usProdHotelItinerarySegmentNumberLabelList;
	
	@FindBy(xpath="//img[@src='/USMyMatrix/images/hotel.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> usQAHotelItinerarySegmentNumberLabelList;
	
	//Car Itinerary Lists
	@FindBy(xpath="//img[@src='/USMyMatrix/images/car.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> usQACarItinerarySegmentNumberLabelList;
	
	//Tour Itinerary Lists
	@FindBy(xpath="//img[@src='/USMyMatrix/images/tour.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> usQATourItinerarySegmentNumberLabelList;
	
	//Ship Itinerary Lists
	@FindBy(xpath="//img[@src='/USMyMatrix/images/ship.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> usQAShipItinerarySegmentNumberLabelList;
	
	//Insurance Itinerary Lists
	@FindBy(xpath="//img[@src='/USMyMatrix/images/insurance.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> usQAInsuranceItinerarySegmentNumberLabelList;
	
	//Misc Itinerary Lists
	@FindBy(xpath="//img[@src='/USMyMatrix/images/other.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> usQAMiscItinerarySegmentNumberLabelList;
	
	//Rail Itinerary Lists
	@FindBy(xpath="//img[@src='/USMyMatrix/images/rail.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> usQARailItinerarySegmentNumberLabelList;
	
	//Bus Itinerary Lists
	@FindBy(xpath="//img[@src='/USMyMatrix/images/bus.gif']//parent::td//following-sibling::td[7]")
	protected List<WebElement> usQABusItinerarySegmentNumberLabelList;
	
	//Tickets' Document Number Lists
	@FindBy(xpath="//tr[@class='dxgvDataRow']//td[6]")
	protected List<WebElement> auProdDocumentNumberLabelList1;
	
	@FindBy(xpath="//tr[@class='dxgvDataRow dxgvDataRowAlt']//td[6]")
	protected List<WebElement> auProdDocumentNumberLabelList2;
	
	@FindBy(xpath="//tr[@class='dxgvDataRow']//td[7]")
	protected List<WebElement> caProdDocumentNumberLabelList1;
	
	@FindBy(xpath="//tr[@class='dxgvDataRow dxgvDataRowAlt']//td[7]")
	protected List<WebElement> caProdDocumentNumberLabelList2;
	
	@FindBy(xpath="//tr[@class='dxgvDataRow']//td[4]")
	protected List<WebElement> usProdDocumentNumberLabelList1;
	
	@FindBy(xpath="//tr[@class='dxgvDataRow dxgvDataRowAlt']//td[4]")
	protected List<WebElement> usProdDocumentNumberLabelList2;
	
	@FindBy(xpath="//tr[@class='dxgvDataRow']//td[7]")
	protected List<WebElement> usQADocumentNumberLabelList1;
	
	@FindBy(xpath="//tr[@class='dxgvDataRow dxgvDataRowAlt']//td[7]")
	protected List<WebElement> usQADocumentNumberLabelList2;
	
	//Cost Line Section
	@FindBy(xpath="//span[@id='ContentPlaceHolder1_Main2_clientFileSnapshotTabContainer_TabPanel1_AccountingLabel']")
	protected WebElement accountingSectionButton;
	
	@FindBy(xpath="//span[@id='ContentPlaceHolder1_Main2_clientFileSnapshotTabContainer_TabPanel2_CostLabel']")
	protected WebElement costSectionButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_Main2_clientFileSnapshotTabContainer_TabPanel2_addNewCostLineImageButton']")
	protected WebElement newCostLineButton;
	
	@FindBy(xpath="//table[@id='ContentPlaceHolder1_Main2_clientFileSnapshotTabContainer_TabPanel2_CostList1_costListGridView']//tr//td[1]")
	protected List<WebElement> costLineNumberList;
	
	//Accounting Section
	@FindBy(xpath="//a[@id='ContentPlaceHolder1_Main2_clientFileSnapshotTabContainer_TabPanel1_bspHyperLink']/img[@title='Record an ARC document']")
	protected WebElement newBSPButton;
	
	@FindBy(xpath="//a[@id='ContentPlaceHolder1_Main2_clientFileSnapshotTabContainer_TabPanel1_nonBSPHyperLink']/img[@title='Record an Non ARC document']")
	protected WebElement newNonBSPButton;
	
	@FindBy(xpath="//a[@id='ContentPlaceHolder1_Main2_clientFileSnapshotTabContainer_TabPanel1_serviceFeeHyperLink']/img[@title='Record a service fee']")
	protected WebElement newServiceFeeButton;
	
	// ~~~ ~~~ protected methods ~~~ ~~~ //
	
	//Locate Traveler Methods
	protected void locateTravelerPageViaName(String name) {
		for (int i = 0; i < nameLabelList.size(); i++) {
			if (name.equalsIgnoreCase(nameLabelList.get(i).getText())) {
				nameLabelList.get(i).click();
				return;
			}
		}
		
		//Click the first Traveler entry, if the name was not found
		nameLabelList.get(0).click();
	}
	
	protected void locateTravelerPageViaSurname(String surname) {
		for (int i = 0; i < surnameLabelList.size(); i++) {
			if (surname.equalsIgnoreCase(surnameLabelList.get(i).getText())) {
				surnameLabelList.get(i).click();
				return;
			}
		}
		
		//Click the first Traveler entry, if the name was not found
		surnameLabelList.get(0).click();
	}
	
	protected void locateTravelerPageViaFullName(String name, String surname) {
		for (int i = 0; i < nameLabelList.size(); i++) {
			if (name.equalsIgnoreCase(nameLabelList.get(i).getText()) && surname.equalsIgnoreCase(surnameLabelList.get(i).getText())) {
				nameLabelList.get(i).click();
				return;
			}
		}
		
		//Click the first Traveler entry, if the name was not found
		nameLabelList.get(0).click();
	}
	
	//Locate Itinerary Methods
	private List<WebElement> getAUProdItineraryType(String itineraryType) {
		switch (itineraryType.toLowerCase()) {
			case "air":
				return auProdAirItinerarySegmentNumberLabelList;
			case "hotel":
				return auProdHotelItinerarySegmentNumberLabelList;
			default:
				return null;
		}
	}
	
	private List<WebElement> getCAProdItineraryType(String itineraryType) {
		switch (itineraryType.toLowerCase()) {
			case "air":
				return caProdAirItinerarySegmentNumberLabelList;
			case "hotel":
				return caProdHotelItinerarySegmentNumberLabelList;
			default:
				return null;
		}
	}
	
	private List<WebElement> getMnGProdItineraryType(String itineraryType) {
		switch (itineraryType.toLowerCase()) {
			case "air":
				return mngProdAirItinerarySegmentNumberLabelList;
			case "hotel":
				return mngProdHotelItinerarySegmentNumberLabelList;
			default:
				return null;
		}
	}
	
	private List<WebElement> getUSProdItineraryType(String itineraryType) {
		switch (itineraryType.toLowerCase()) {
			case "air":
				return usProdAirItinerarySegmentNumberLabelList;
			case "hotel":
				return usProdHotelItinerarySegmentNumberLabelList;
			default:
				return null;
		}
	}
	
	private List<WebElement> getUSQAItineraryType(String itineraryType) {
		switch (itineraryType.toLowerCase()) {
			case "air":
				return usQAAirItinerarySegmentNumberLabelList;
			case "hotel":
				return usQAHotelItinerarySegmentNumberLabelList;
			case "car":
				return usQACarItinerarySegmentNumberLabelList;
			case "tour":
				return usQATourItinerarySegmentNumberLabelList;
			case "ship":
				return usQAShipItinerarySegmentNumberLabelList;
			case "ins":
				return usQAInsuranceItinerarySegmentNumberLabelList;
			case "insurance":
				return usQAInsuranceItinerarySegmentNumberLabelList;
			case "misc":
				return usQAMiscItinerarySegmentNumberLabelList;
			case "rail":
				return usQARailItinerarySegmentNumberLabelList;
			case "bus":
				return usQABusItinerarySegmentNumberLabelList;
			default:
				return null;
		}
	}
	
	protected List<WebElement> getEnvironmentItineraryType(String environment, String itineraryType) {
		if (environment.equalsIgnoreCase("au prod")) {
			return getAUProdItineraryType(itineraryType);
		} else if (environment.equalsIgnoreCase("ca 01 prod") || environment.equalsIgnoreCase("harvey prod")) {
			return getCAProdItineraryType(itineraryType);
		} else if (environment.equalsIgnoreCase("mng prod")) {
			return getMnGProdItineraryType(itineraryType);
		} else if (environment.equalsIgnoreCase("us prod")) {
			return getUSProdItineraryType(itineraryType);
		} else if (environment.equalsIgnoreCase("us qa")) {
			return getUSQAItineraryType(itineraryType);
		} else {
			return null;
		}
	}
	
}