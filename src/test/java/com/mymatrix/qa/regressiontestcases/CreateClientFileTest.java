package com.mymatrix.qa.regressiontestcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.base.TestBase;
import com.mymatrix.qa.pagemethods.ChangeBranchPage;
import com.mymatrix.qa.pagemethods.ClientFilePage;
import com.mymatrix.qa.pagemethods.CreateClientFilePage;
import com.mymatrix.qa.pagemethods.MainPage;
import com.mymatrix.qa.pagemethods.SearchClientFilePage;
import com.mymatrix.qa.pagemethods.TravelerPage;
import com.mymatrix.qa.util.ExcelMethods;
import com.mymatrix.qa.util.ExtentFactory;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.LogStatus;

public class CreateClientFileTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public CreateClientFileTest() {
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
		excelMethods.setSheetName("Client File");
		column = 13;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("SearchClientFilePageTest Script");
		
		//Initialize PageFactories
		System.out.println("Initializing the script's PageFactories");
		reportLogger.log(LogStatus.INFO, "Initializing the script's PageFactories");
		
		mainPage = new MainPage(eDriver, reportLogger);
		changeBranchPage = new ChangeBranchPage(eDriver, reportLogger);
		createClientFilePage = new CreateClientFilePage(eDriver, reportLogger);
		searchClientFilePage = new SearchClientFilePage(eDriver, reportLogger);
		clientFilePage = new ClientFilePage(eDriver, reportLogger);
		travelerPage = new TravelerPage(eDriver, reportLogger);
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void searchClientFilePageTest(String active, String environment, String changeBranchID, String departureDate, String deletionDate, String clientFileCode, String clientFileType, String departmentCode, String firstName, String surname, String destination, String phoneNumber, String finalResult, String cfa, String dataRow) {
		System.out.println("@Test - searchClientFilePageTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Remove the output values from a previous script run
		if (iteration > 1) {
			excelMethods.setDataTableCell("", iteration, column);
		}
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			
			//Navigate to the 'Change Branch' page
			mainPage.navigateToChangeBranchPage();
			
			//Change Branch
			changeBranchPage.changeBranch(changeBranchID);
			
			//Navigate to the 'ClientFile Information' page
			mainPage.navigateToSearchClientFilePage(environment);
			
			//Click the 'New' button
			searchClientFilePage.clickNewClientFileButton();
			
			//Enter the Required Info to Create the Client File
			createClientFilePage.enterRequiredInfo(environment, departureDate, deletionDate, clientFileCode, clientFileType, departmentCode, firstName, surname, destination, phoneNumber);
			
			//Click the 'Save' button to create the Client File
			createClientFilePage.clickNewClientFileButton();
			
			//Check if the Client File was created successfully
			clientFilePage.verifyClientFileCreation(checkpoint);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
			
			//Output the newly created Client File's CFA
			excelMethods.setDataTableCell(clientFilePage.retrieveCFA(), iteration, column + 1);
			
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}