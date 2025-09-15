package com.mymatrix.qa.regressiontestcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.base.TestBase;
import com.mymatrix.qa.pagemethods.ClientFilePage;
import com.mymatrix.qa.pagemethods.HotelItineraryPage;
import com.mymatrix.qa.pagemethods.MainPage;
import com.mymatrix.qa.pagemethods.SearchClientFilePage;
import com.mymatrix.qa.util.ExcelMethods;
import com.mymatrix.qa.util.ExtentFactory;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.LogStatus;

public class HotelItineraryTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public HotelItineraryTest() {
		super();
	}
	
	//Setup variable(s) and other info for the class
	@BeforeClass
	@Parameters({"dataTable"})
	public void beforeClass(String excelPath) {
		//Initialize Variable(s)
		genMethods = new GenericMethods();
		excelMethods = new ExcelMethods();
		excelMethods.setDataTablePath(excelPath);
		excelMethods.setSheetName("Hotel Itinerary");
		column = 11;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("HotelItineraryPageTest Script");
		
		//Initialize PageFactories
		System.out.println("Initializing the script's PageFactories");
		reportLogger.log(LogStatus.INFO, "Initializing the script's PageFactories");
		
		mainPage = new MainPage(eDriver, reportLogger);
		searchClientFilePage = new SearchClientFilePage(eDriver, reportLogger);
		clientFilePage = new ClientFilePage(eDriver, reportLogger);
		hotelItineraryPage = new HotelItineraryPage(eDriver, reportLogger);
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void hotelItineraryPageTest(String active, String environment, String createOrModify, String segmentNumber, String hotelChain, String checkinDate, String checkoutDate, String city, String roomType, String gdsSegmentNumber, String finalResult, String dataRow) {
		System.out.println("@Test - hotelItineraryPageTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		boolean foundItinerary;
		
		//Remove the output values from a previous script run
		if (iteration > 1) {
			excelMethods.setDataTableCell("", iteration, column);
		}
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			
			//Expand the 'Itineraries' section
			clientFilePage.expandItinerarySection();
			
			if (createOrModify.equalsIgnoreCase("create")) {
				//Navigate to the 'Create New Itinerary' page
				clientFilePage.navigateToNewItineraryPage("Hotel");
				
				//Enter the necessary itinerary info
				hotelItineraryPage.enterHotelItineraryInfo(hotelChain, checkinDate, checkoutDate, city, roomType, gdsSegmentNumber);
				
				//Select the first Traveler
				hotelItineraryPage.selectTraveler();
				
				//Click 'Save' to create the itinerary
				hotelItineraryPage.clickSaveButton();
				
			} else if (createOrModify.equalsIgnoreCase("modify")) {
				//Navigate to the itinerary page
				clientFilePage.navigateToItineraryPage(environment, "Hotel", segmentNumber);
				
				//Enter the necessary itinerary info
				hotelItineraryPage.enterHotelItineraryInfo(hotelChain, checkinDate, checkoutDate, city, roomType, gdsSegmentNumber);
				
				//Click 'Save' to create the itinerary
				hotelItineraryPage.clickUpdateButton();
			}
			
			//Pause the script for a bit
			genMethods.waitFor(3);
			
			//Navigate to the 'ClientFile Information' page
			foundItinerary = clientFilePage.navigateToItineraryPage(environment, "Hotel", segmentNumber);
			
			//Check if the Itinerary info is correct
			if (foundItinerary) {
				checkpoint = hotelItineraryPage.verifyHotelItineraryDetails(checkpoint, hotelChain, checkinDate, checkoutDate, city, roomType, gdsSegmentNumber);
			} else {
				checkpoint.assertEquals("Itinerary not found", "Itinerary found");
			}
			
			//Pause the script for a bit
			genMethods.waitFor(3);
			
			//Exit the Itinerary Page
			hotelItineraryPage.clickCancelButton();
			
			//Close the Itinerary Section
			clientFilePage.expandItinerarySection();
			
			//Assert all Checkpoints
			checkpoint.assertAll();
			
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}