package com.mymatrix.qa.regressiontestcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.base.TestBase;
import com.mymatrix.qa.pagemethods.ClientFilePage;
import com.mymatrix.qa.pagemethods.MainPage;
import com.mymatrix.qa.pagemethods.SearchClientFilePage;
import com.mymatrix.qa.pagemethods.TravelerPage;
import com.mymatrix.qa.util.ExcelMethods;
import com.mymatrix.qa.util.ExtentFactory;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.LogStatus;

public class TravelerTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public TravelerTest() {
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
		excelMethods.setSheetName("Traveler");
		column = 8;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("TravelerPageTest Script");
		
		//Initialize PageFactories
		System.out.println("Initializing the script's PageFactories");
		reportLogger.log(LogStatus.INFO, "Initializing the script's PageFactories");
		
		mainPage = new MainPage(eDriver, reportLogger);
		searchClientFilePage = new SearchClientFilePage(eDriver, reportLogger);
		clientFilePage = new ClientFilePage(eDriver, reportLogger);
		travelerPage = new TravelerPage(eDriver, reportLogger);
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void travelerPageTest(String active, String environment, String createOrModify, String name, String surname, String modifiedName, String modifiedSurname, String finalResult, String dataRow) {
		System.out.println("@Test - travelerPageTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Remove the output values from a previous script run
		if (iteration > 1) {
			excelMethods.setDataTableCell("", iteration, column);
		}
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			
			//Expand the 'Travelers' section
			clientFilePage.expandTravelerSection();
			
			if (createOrModify.equalsIgnoreCase("create")) {
				//Navigate to the 'Create New Traveler' page
				clientFilePage.navigateToNewTravelerPage();
				
				//Enter the new Traveler's info
				travelerPage.enterTravelerInfo(name, surname);
				
				//Save the new Traveler's info
				travelerPage.clickSaveButton();
				
				//Navigate to the newly created Traveler's detail page
				clientFilePage.navigateToTravelerPage(name, surname);
				
				//Check if the name & surname exist
				checkpoint = travelerPage.verifyFullName(checkpoint, name, surname);
			} else if (createOrModify.equalsIgnoreCase("modify")) {
				//Navigate to the newly created Traveler's detail page
				clientFilePage.navigateToTravelerPage(name, surname);
				
				//Enter the new Traveler's info
				travelerPage.enterTravelerInfo(modifiedName, modifiedSurname);
				
				//Save the new Traveler's info
				travelerPage.clickSaveButton();
				
				//Navigate to the newly created Traveler's detail page
				clientFilePage.navigateToTravelerPage(modifiedName, modifiedSurname);
				
				//Check if the name & surname exist
				checkpoint = travelerPage.verifyFullName(checkpoint, modifiedName, modifiedSurname);
			}
			
			//Cancel out of the Traveler page
			travelerPage.clickCancelButton();
			
			//Close the 'Travelers' section
			clientFilePage.expandTravelerSection();
			
			//Assert all Checkpoints
			checkpoint.assertAll();
			
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}