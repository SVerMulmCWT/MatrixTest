package com.mymatrix.qa.regressiontestcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.base.TestBase;
import com.mymatrix.qa.pagemethods.ClientFilePage;
import com.mymatrix.qa.pagemethods.CostLinePage;
import com.mymatrix.qa.pagemethods.MainPage;
import com.mymatrix.qa.pagemethods.SearchClientFilePage;
import com.mymatrix.qa.util.ExcelMethods;
import com.mymatrix.qa.util.ExtentFactory;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.LogStatus;

public class CostLineTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public CostLineTest() {
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
		excelMethods.setSheetName("Cost Line");
		column = 12;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("CostLinePageTest Script");
		
		//Initialize PageFactories
		System.out.println("Initializing the script's PageFactories");
		reportLogger.log(LogStatus.INFO, "Initializing the script's PageFactories");
		
		mainPage = new MainPage(eDriver, reportLogger);
		searchClientFilePage = new SearchClientFilePage(eDriver, reportLogger);
		clientFilePage = new ClientFilePage(eDriver, reportLogger);
		costLinePage = new CostLinePage(eDriver, reportLogger);
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void costLinePageTest(String active, String environment, String createOrModify, String costLineNumber, String currencyType, String baseCost, String taxAmount, String taxCode, String description, String associatedCostCode, String itineraryType, String finalResult, String dataRow) {
		System.out.println("@Test - costLinePageTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		boolean foundCostLine;
		
		//Remove the output values from a previous script run
		if (iteration > 1) {
			excelMethods.setDataTableCell("", iteration, column);
		}
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			
			//Open the 'Cost Line' section
			clientFilePage.navigateToCostLineSection();
			
			if (createOrModify.equalsIgnoreCase("create")) {
				//Navigate to the 'Create New Cost Line' page
				clientFilePage.navigateToNewCostLinePage();
				
				//Enter the necessary cost line info
				costLinePage.enterCostLineItineraryInfo(currencyType, baseCost, description, taxAmount, taxCode);
				
				//Select the first Itinerary
				costLinePage.selectItinerary();
				
				//Select the first Passenger
				costLinePage.selectPassenger();
				
				//Click 'Save' to create the Cost Line
				costLinePage.clickSaveButton();
				
			} else if (createOrModify.equalsIgnoreCase("modify")) {
				//Navigate to the itinerary page
				clientFilePage.navigateToExistingCostLinePage(environment, costLineNumber);
				
				//Enter the necessary Cost Line info
				costLinePage.enterCostLineItineraryInfo(currencyType, baseCost, description, taxAmount, taxCode);
				
				//Click 'Save' to modify the Cost Line
				costLinePage.clickSaveButton();
			}
			
			//Pause the script for a bit
			genMethods.waitFor(3);
			
			//Navigate to the 'Cost Line' page
			foundCostLine = clientFilePage.navigateToExistingCostLinePage(environment, costLineNumber);
			
			//Check if the Ticket info is correct
			if (foundCostLine) {
				checkpoint = costLinePage.verifyCostLineItineraryDetails(checkpoint, currencyType, baseCost, description, taxAmount, taxCode);
			} else {
				checkpoint.assertEquals("Cost Line not found", "Cost Line found");
			}
			
			//Pause the script for a bit
			genMethods.waitFor(3);
			
			//Exit the Cost Line Page
			costLinePage.clickCancelButton();
			
			//Return to the Ticket Section
			clientFilePage.navigateToAccountingSection();
			
			//Assert all Checkpoints
			checkpoint.assertAll();
			
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}