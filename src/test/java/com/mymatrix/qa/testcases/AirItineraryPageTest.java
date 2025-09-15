package com.mymatrix.qa.testcases;

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

public class AirItineraryPageTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public AirItineraryPageTest() {
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
		column = 12;
		
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
	public void airItineraryPageTest(String active, String environment, String clientFileNumber, String segmentNumber, String airlineCode, String flightNumber, String classCode, String departureCity, String arrivalCity, String departureDate, String gdsSegmentNumber, String finalResult, String dataRow) {
		System.out.println("@Test - airItineraryPageTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Initialize Variable(s)
		boolean foundTicket;
		
		//Remove the output values from a previous script run
		if (iteration > 1) {
			excelMethods.setDataTableCell("", iteration, column);
		}
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			
			//Search for the relevant Client File
			mainPage.navigateToSearchClientFilePage(environment);
			searchClientFilePage.enterClientFileNumber(clientFileNumber);
			searchClientFilePage.clickSearchButton();
			
			//Expand the 'Itineraries' section
			clientFilePage.expandItinerarySection();
			
			//Navigate to the 'ClientFile Information' page
			foundTicket = clientFilePage.navigateToItineraryPage(environment, "Air", segmentNumber);
			
			//Check if the Ticket info is correct
			if (foundTicket) {
				checkpoint = airItineraryPage.verifyAirItineraryDetails(checkpoint, airlineCode, flightNumber, classCode, departureCity, arrivalCity, departureDate, gdsSegmentNumber);
			} else {
				checkpoint.assertEquals("Itinerary not found", "Itinerary found");
			}
			
			//Assert all Checkpoints
			checkpoint.assertAll();
			
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}