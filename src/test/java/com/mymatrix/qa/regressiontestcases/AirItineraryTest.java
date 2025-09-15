package com.mymatrix.qa.regressiontestcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.base.TestBase;
import com.mymatrix.qa.pagemethods.AirItineraryPage;
import com.mymatrix.qa.pagemethods.ClientFilePage;
import com.mymatrix.qa.pagemethods.MainPage;
import com.mymatrix.qa.pagemethods.SearchClientFilePage;
import com.mymatrix.qa.util.ExcelMethods;
import com.mymatrix.qa.util.ExtentFactory;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.LogStatus;

public class AirItineraryTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public AirItineraryTest() {
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
		excelMethods.setSheetName("Air Itinerary");
		column = 13;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("AirItineraryPageTest Script");
		
		//Initialize PageFactories
		System.out.println("Initializing the script's PageFactories");
		reportLogger.log(LogStatus.INFO, "Initializing the script's PageFactories");
		
		mainPage = new MainPage(eDriver, reportLogger);
		searchClientFilePage = new SearchClientFilePage(eDriver, reportLogger);
		clientFilePage = new ClientFilePage(eDriver, reportLogger);
		airItineraryPage = new AirItineraryPage(eDriver, reportLogger);
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void airItineraryPageTest(String active, String environment, String createOrModify, String segmentNumber, String airlineCode, String flightNumber, String classCode, String departureCity, String arrivalCity, String departureDate, String arrivalDate, String gdsSegmentNumber, String finalResult, String dataRow) {
		System.out.println("@Test - airItineraryPageTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Initialize Variable(s)
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
				clientFilePage.navigateToNewItineraryPage("Air");
				
				//Enter the necessary itinerary info
				airItineraryPage.enterAirItineraryInfo(airlineCode, flightNumber, classCode, departureCity, arrivalCity, departureDate, arrivalDate, gdsSegmentNumber);
				
				//Select the first Traveler
				airItineraryPage.selectTraveler();
				
				//Click 'Save' to create the itinerary
				airItineraryPage.clickSaveButton();
				
			} else if (createOrModify.equalsIgnoreCase("modify")) {
				//Navigate to the itinerary page
				clientFilePage.navigateToItineraryPage(environment, "Air", segmentNumber);
				
				//Enter the necessary itinerary info
				airItineraryPage.enterAirItineraryInfo(airlineCode, flightNumber, classCode, departureCity, arrivalCity, departureDate, arrivalDate, gdsSegmentNumber);
				
				//Click 'Save' to create the itinerary
				airItineraryPage.clickUpdateButton();
			}
			
			//Pause the script for a bit
			genMethods.waitFor(3);
			
			//Navigate to the itinerary page
			foundItinerary = clientFilePage.navigateToItineraryPage(environment, "Air", segmentNumber);
			
			//Check if the Itinerary info is correct
			if (foundItinerary) {
				checkpoint = airItineraryPage.verifyAirItineraryDetails(checkpoint, airlineCode, flightNumber, classCode, departureCity, arrivalCity, departureDate, gdsSegmentNumber);
			} else {
				checkpoint.assertEquals("Itinerary not found", "Itinerary found");
			}
			
			//Exit the Itinerary Page
			airItineraryPage.clickCancelButton();
			
			//Close the Itinerary Section
			clientFilePage.expandItinerarySection();
			
			//Assert all Checkpoints
			checkpoint.assertAll();
			
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}