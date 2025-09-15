package com.mymatrix.qa.regressiontestcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.base.TestBase;
import com.mymatrix.qa.pagemethods.ClientFilePage;
import com.mymatrix.qa.pagemethods.MainPage;
import com.mymatrix.qa.pagemethods.BSPTicketPage;
import com.mymatrix.qa.util.ExcelMethods;
import com.mymatrix.qa.util.ExtentFactory;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.LogStatus;

public class BSPTicketTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public BSPTicketTest() {
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
		excelMethods.setSheetName("BSP Ticket");
		column = 12;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("TicketPageTest Script");
		
		//Initialize PageFactories
		System.out.println("Initializing the script's PageFactories");
		reportLogger.log(LogStatus.INFO, "Initializing the script's PageFactories");
		
		mainPage = new MainPage(eDriver, reportLogger);
		clientFilePage = new ClientFilePage(eDriver, reportLogger);
		bspTicketPage = new BSPTicketPage(eDriver, reportLogger);
	}
	
	//Test the ability to create & modify Tickets functionalities
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void ticketPageTest(String active, String environment, String createOrModify, String supplierCode, String ticketNumber, String paymentType, String description, String baseCost, String taxAmount, String taxCode, String cityCode, String finalResult, String dataRow) {
		System.out.println("@Test - ticketPageTest()");
		
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
			
			if (createOrModify.equalsIgnoreCase("create")) {
				//Navigate to the 'Create New Ticket' page
				clientFilePage.clickNewBSPTicketButton();
				
				//Enter the necessary Ticket info
				bspTicketPage.enterTicketInfo(supplierCode, ticketNumber, paymentType);
				
				//Select the first Passenger
				bspTicketPage.selectPassenger();
				
				//Select the first Cost Line
				bspTicketPage.selectCostLine();
				
				//Click 'Save' to create the Ticket
				bspTicketPage.clickSaveButton();
				
			} else if (createOrModify.equalsIgnoreCase("modify")) {
				//Navigate to the existing 'Ticket' page
				foundTicket = clientFilePage.navigateToExistingTicketDetailPage(environment, ticketNumber);
				
				//Enter the necessary Ticket info
				bspTicketPage.modifyTicketInfo(supplierCode, paymentType, description, baseCost, taxAmount, taxCode, cityCode);
				
				//Click 'Save' to modify the Ticket
				bspTicketPage.clickSaveButton();
			}
			
			//Pause the script for a bit
			genMethods.waitFor(12);
			
			//Navigate to the existing 'Ticket' page
			foundTicket = clientFilePage.navigateToExistingTicketDetailPage(environment, ticketNumber);
			
			//Check if the Ticket info is correct
			if (foundTicket && createOrModify.equalsIgnoreCase("create")) {
				checkpoint = bspTicketPage.verifyTicketCreation(checkpoint, supplierCode, paymentType);
			} else if (foundTicket && createOrModify.equalsIgnoreCase("modify")) {
				checkpoint = bspTicketPage.verifyTicketModification(checkpoint, supplierCode, paymentType, description, baseCost, taxAmount, taxCode, cityCode);
			} else {
				checkpoint.assertEquals("Ticket " + ticketNumber + " not found", "Ticket " + ticketNumber + " found");
			}
			
			try {
				//Exit the Itinerary Page
				bspTicketPage.clickCancelButton();
			} catch (Exception e) {
				System.out.println("Ticket 'Cancel' button not found");
			}
			
			//Assert all Checkpoints
			checkpoint.assertAll();
			
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}