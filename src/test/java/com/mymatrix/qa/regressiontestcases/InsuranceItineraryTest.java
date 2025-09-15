package com.mymatrix.qa.regressiontestcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.base.TestBase;
import com.mymatrix.qa.pagemethods.ClientFilePage;
import com.mymatrix.qa.pagemethods.InsuranceItineraryPage;
import com.mymatrix.qa.pagemethods.MainPage;
import com.mymatrix.qa.pagemethods.SearchClientFilePage;
import com.mymatrix.qa.util.ExcelMethods;
import com.mymatrix.qa.util.ExtentFactory;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.LogStatus;

public class InsuranceItineraryTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public InsuranceItineraryTest() {
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
		excelMethods.setSheetName("Insurance Itinerary");
		column = 10;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("InsuranceItineraryPageTest Script");
		
		//Initialize PageFactories
		System.out.println("Initializing the script's PageFactories");
		reportLogger.log(LogStatus.INFO, "Initializing the script's PageFactories");
		
		mainPage = new MainPage(eDriver, reportLogger);
		searchClientFilePage = new SearchClientFilePage(eDriver, reportLogger);
		clientFilePage = new ClientFilePage(eDriver, reportLogger);
		insuranceItineraryPage = new InsuranceItineraryPage(eDriver, reportLogger);
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void insuranceItineraryPageTest(String active, String environment, String createOrModify, String segmentNumber, String insuranceCode, String departureCity, String arrivalCity, String departureDate, String arrivalDate, String finalResult, String dataRow) {
		System.out.println("@Test - insuranceItineraryPageTest()");
		
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
				clientFilePage.navigateToNewItineraryPage("Insurance");
				
				//Enter the necessary itinerary info
				insuranceItineraryPage.enterInsuranceItineraryInfo(insuranceCode, departureCity, arrivalCity, departureDate, arrivalDate);
				
				//Select the first Traveler
				insuranceItineraryPage.selectTraveler();
				
				//Click 'Save' to create the itinerary
				insuranceItineraryPage.clickSaveButton();
				
			} else if (createOrModify.equalsIgnoreCase("modify")) {
				//Navigate to the itinerary page
				clientFilePage.navigateToItineraryPage(environment, "Insurance", segmentNumber);
				
				//Enter the necessary itinerary info
				insuranceItineraryPage.enterInsuranceItineraryInfo(insuranceCode, departureCity, arrivalCity, departureDate, arrivalDate);
				
				//Click 'Save' to create the itinerary
				insuranceItineraryPage.clickUpdateButton();
			}
			
			//Pause the script for a bit
			genMethods.waitFor(3);
			
			//Navigate to the 'ClientFile Information' page
			foundItinerary = clientFilePage.navigateToItineraryPage(environment, "Insurance", segmentNumber);
			
			//Check if the Itinerary info is correct
			if (foundItinerary) {
				checkpoint = insuranceItineraryPage.verifyInsuranceItineraryDetails(checkpoint, insuranceCode, departureCity, arrivalCity, departureDate, arrivalDate);
			} else {
				checkpoint.assertEquals("Itinerary not found", "Itinerary found");
			}
			
			//Pause the script for a bit
			genMethods.waitFor(3);
			
			//Exit the Itinerary Page
			insuranceItineraryPage.clickCancelButton();
			
			//Close the Itinerary Section
			clientFilePage.expandItinerarySection();
			
			//Assert all Checkpoints
			checkpoint.assertAll();
			
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}