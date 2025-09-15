package com.mymatrix.qa.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.base.TestBase;
import com.mymatrix.qa.pagemethods.ChangeBranchPage;
import com.mymatrix.qa.pagemethods.MainPage;
import com.mymatrix.qa.pagemethods.QueueCountPage;
import com.mymatrix.qa.util.ExcelMethods;
import com.mymatrix.qa.util.ExtentFactory;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.LogStatus;

public class QueueCountPageTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public QueueCountPageTest() {
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
		excelMethods.setSheetName("Queue Count");
		column = 10;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("QueueCountPageTest Script");
		
		//Initialize PageFactories
		System.out.println("Initializing the script's PageFactories");
		reportLogger.log(LogStatus.INFO, "Initializing the script's PageFactories");
		
		mainPage = new MainPage(eDriver, reportLogger);
		changeBranchPage = new ChangeBranchPage(eDriver, reportLogger);
		queueCountPage = new QueueCountPage(eDriver, reportLogger);
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void queueCountPageTest(String active, String environment, String queueType, String searchItemCount, String queueCount, String queueCountDifference, String irSearchItemCount, String irQueueCount, String irQueueCountDifference, String finalResult, String dataRow) {
		System.out.println("@Test - queueCountPageTest()");
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		int searchCountResult = 0;
		int queueCountResult = 0;
		int resultsCountDifference = 11;
		
		//Remove the output values from a previous script run
		if (iteration > 1) {
			excelMethods.setDataTableCell("", iteration, column);
		}
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			
			//Navigate to the 'Queue Count' (Calendar/Tasks/Queues) page
			mainPage.navigateToQueuePage(environment);
			
			if (queueType.equalsIgnoreCase("queues - all")) {
				//Check if the user is on the correct branch
				checkpoint = queueCountPage.verifySearchCapabilities(checkpoint);
			} else {
				//Select the relevant Queue Type
				queueCountPage.selectQueueType(environment, queueType);
				
				//Pause the script for a bit
				genMethods.waitFor(2);
				
				//Click the 'Search' button
				queueCountPage.clickSearchButton();
				
				//Pause the script for a bit
				genMethods.waitFor(2);
				
				//Retrieve the number of 'Search' results
				searchCountResult = queueCountPage.getSearchCount(environment);
				
				//Click the 'Queue Count' button
				queueCountPage.clickQueueCount();
				
				//Retrieve the number of 'Queue Count' results
				queueCountResult = queueCountPage.getQueueCount();
				
				//Find the difference between the 'Search' count and 'Queue Count' difference
				resultsCountDifference = Math.abs(searchCountResult - queueCountResult);
				
				//Output the 'Search' & 'Queue Count' result counts to the Excel file
				excelMethods.setDataTableCell(searchCountResult, iteration, 3);
				excelMethods.setDataTableCell(queueCountResult, iteration, 4);
				excelMethods.setDataTableCell(resultsCountDifference, iteration, 5);
				
				//Check if the results difference is less than 10
				checkpoint = queueCountPage.verifyQueueDifference(checkpoint, resultsCountDifference);
			}
			
			//Assert all Checkpoints
			checkpoint.assertAll();
			
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}