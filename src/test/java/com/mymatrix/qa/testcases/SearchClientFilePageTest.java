package com.mymatrix.qa.testcases;

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

public class SearchClientFilePageTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public SearchClientFilePageTest() {
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
		excelMethods.setSheetName("Search Client File");
		column = 10;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("SearchClientFilePageTest Script");
		
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
	public void searchClientFilePageTest(String active, String environment, String clientFileNumber, String pnr, String bookingBranch, String name, String surname, String documentType, String documentNumber, String finalResult, String dataRow) {
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
			
			//Navigate to the 'ClientFile Information' page
			mainPage.navigateToSearchClientFilePage(environment);
			
			//Enter the search criteria
			if (!clientFileNumber.isEmpty()) {
				searchClientFilePage.enterClientFileNumber(clientFileNumber);
			}
			
			if (!pnr.isEmpty()) {
				searchClientFilePage.enterPNR(pnr);
			}
			
			if (!bookingBranch.isEmpty()) {
				searchClientFilePage.enterBookingBranch(bookingBranch);
			}
			
			if (!name.isEmpty() || !surname.isEmpty()) {
				searchClientFilePage.enterFirstName(name);
				searchClientFilePage.enterSurname(surname);
			}
			
			if (!documentType.isEmpty() && !documentNumber.isEmpty()) {
				searchClientFilePage.enterDocumentType(documentType);
				searchClientFilePage.enterDocumentNumber(documentNumber);
			}
			
			//Click the 'Search' button
			searchClientFilePage.clickSearchButton();
			
			//Check if the user is on the correct branch
			if (!clientFileNumber.isEmpty()) {
				checkpoint = clientFilePage.verifyClientFileNumber(checkpoint, clientFileNumber);
			}
			
			if (!pnr.isEmpty()) {
				checkpoint = clientFilePage.verifyPNR(checkpoint, pnr);
			}
			
			if (!bookingBranch.isEmpty()) {
				checkpoint = clientFilePage.verifyBookingBranch(checkpoint, bookingBranch);
			}
			
			if (!name.isEmpty() || !surname.isEmpty()) {
				clientFilePage.expandTravelerSection();
				clientFilePage.navigateToTravelerPage(name, surname);
				checkpoint = travelerPage.verifyFullName(checkpoint, name, surname);
				travelerPage.clickCancelButton();
			}
			
			if (!documentNumber.isEmpty()) {
				checkpoint = clientFilePage.verifyDocumentNumber(checkpoint, environment, documentNumber);
			}
			
			//Assert all Checkpoints
			checkpoint.assertAll();
			
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}