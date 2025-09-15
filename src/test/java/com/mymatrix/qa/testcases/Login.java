package com.mymatrix.qa.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.base.TestBase;
import com.mymatrix.qa.pagemethods.LoginPage;
import com.mymatrix.qa.util.ExcelMethods;
import com.mymatrix.qa.util.ExtentFactory;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.LogStatus;

public class Login extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public Login() {
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
		excelMethods.setSheetName("Login");
		column = 9;
		
		//Setup the Report
		report = ExtentFactory.getInstance();
		reportLogger = report.startTest("LoginPageTest Script");
		
		//Initialize PageFactories
		System.out.println("Initializing the script's PageFactories");
		reportLogger.log(LogStatus.INFO, "Initializing the script's PageFactories");
		
		loginPage = new LoginPage(eDriver, reportLogger);
	}
	
	//Test the login functionality
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void loginTest(String active, String environment, String expectedResult, String website, String username, String password, String matrixEnvironment, String logout, String finalResult, String dataRow) {
		System.out.println("@Test - loginTest()");
		
		//Pause the script a short bit
		genMethods.waitFor(1);
		
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Remove the output values from a previous script run
		if (iteration > 1) {
			excelMethods.setDataTableCell("", iteration, column);
		}
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			
			//Go to the desired Website
			loginPage.accessWebsite(website);
			
//			genMethods.waitFor(999999);
			
			//Check if the script will login or reset a password
			if (expectedResult.equalsIgnoreCase("forgot password")) {
				//Navigate to the 'Forgot Password' page
				loginPage.clickForgotPassword();
				
				//Check if the 'Forgot Password' page is up
				checkpoint = loginPage.verifyForgotPasswordPagePresence(checkpoint);
			} else {
				//Login to the website
//				loginPage.login(environment, username, password);
				loginPage.SSOSignin(matrixEnvironment, username, password);
				
				//Pause the script for a bit
				genMethods.waitFor(3);
				
				//Check if the login was successful
				checkpoint = loginPage.verifySuccessfulLogin(checkpoint, environment, expectedResult);
				
				//Logout, if necessary
				if (logout.equalsIgnoreCase("y") || logout.equalsIgnoreCase("yes")) {
					//logout
					loginPage.logout(environment);
					loginPage.signout(matrixEnvironment);
					
					//Pause the script a short bit
					genMethods.waitFor(1);
				}
			}
			
			//Assert all Checkpoints
			checkpoint.assertAll();
			
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}