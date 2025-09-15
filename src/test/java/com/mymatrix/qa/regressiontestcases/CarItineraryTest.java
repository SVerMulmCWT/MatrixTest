package com.mymatrix.qa.regressiontestcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.base.TestBase;
import com.mymatrix.qa.pagemethods.CarItineraryPage;
import com.mymatrix.qa.pagemethods.ClientFilePage;
import com.mymatrix.qa.pagemethods.MainPage;
import com.mymatrix.qa.pagemethods.SearchClientFilePage;
import com.mymatrix.qa.util.ExcelMethods;
import com.mymatrix.qa.util.ExtentFactory;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.LogStatus;

public class CarItineraryTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public CarItineraryTest() {
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
		excelMethods.setSheetName("Car Itinerary");
		column = 11;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("CarItineraryPageTest Script");
		
		//Initialize PageFactories
		System.out.println("Initializing the script's PageFactories");
		reportLogger.log(LogStatus.INFO, "Initializing the script's PageFactories");
		
		mainPage = new MainPage(eDriver, reportLogger);
		searchClientFilePage = new SearchClientFilePage(eDriver, reportLogger);
		clientFilePage = new ClientFilePage(eDriver, reportLogger);
		carItineraryPage = new CarItineraryPage(eDriver, reportLogger);
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void carItineraryPageTest(String active, String environment, String createOrModify, String segmentNumber, String carCode, String carType, String pickupCity, String pickupDate, String dropoffDate, String gdsSegmentNumber, String finalResult, String dataRow) {
		System.out.println("@Test - carItineraryPageTest()");
		
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
				clientFilePage.navigateToNewItineraryPage("Car");
				
				//Enter the necessary itinerary info
				carItineraryPage.enterCarItineraryInfo(carCode, carType, pickupCity, pickupDate, dropoffDate, gdsSegmentNumber);
				
				//Select the first Traveler
				carItineraryPage.selectTraveler();
				
				//Click 'Save' to create the itinerary
				carItineraryPage.clickSaveButton();
				
			} else if (createOrModify.equalsIgnoreCase("modify")) {
				//Navigate to the itinerary page
				clientFilePage.navigateToItineraryPage(environment, "Car", segmentNumber);
				
				//Enter the necessary itinerary info
				carItineraryPage.enterCarItineraryInfo(carCode, carType, pickupCity, pickupDate, dropoffDate, gdsSegmentNumber);
				
				//Click 'Save' to create the itinerary
				carItineraryPage.clickUpdateButton();
			}
			
			//Pause the script for a bit
			genMethods.waitFor(3);
			
			//Navigate to the 'ClientFile Information' page
			foundItinerary = clientFilePage.navigateToItineraryPage(environment, "Car", segmentNumber);
			
			//Check if the Itinerary info is correct
			if (foundItinerary) {
				checkpoint = carItineraryPage.verifyCarItineraryDetails(checkpoint, carCode, carType, pickupCity, pickupDate, dropoffDate, gdsSegmentNumber);
			} else {
				checkpoint.assertEquals("Itinerary not found", "Itinerary found");
			}
			
			//Pause the script for a bit
			genMethods.waitFor(3);
			
			//Exit the Itinerary Page
			carItineraryPage.clickCancelButton();
			
			//Close the Itinerary Section
			clientFilePage.expandItinerarySection();
			
			//Assert all Checkpoints
			checkpoint.assertAll();
			
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}