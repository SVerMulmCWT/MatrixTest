package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.TicketLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TicketPage extends TicketLocators {
	
	public TicketPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public SoftAssert verifyTicketDetailPage(SoftAssert softAssert, String paymentType, String baseCost) {
		//Output to the system and report
		System.out.println("Checking if the 'Payment Type', 'Base Cost', and 'Credit Card Masking' matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Payment Type', 'Base Cost', and 'Credit Card Masking' matches expectations");
		
		//Check if the 'Payment Type' matches expectations
		softAssert.assertEquals(paymentTypeDropDown.getText(), paymentType);
		
		//Check if the 'Base Cost' matches expectations
		softAssert.assertEquals(baseCostLabel.getAttribute("value"), baseCost);
		
		//Check if the credit card number is masked
		softAssert.assertEquals(creditCardField.getAttribute("value").substring(0, 11), "***********");
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
	public SoftAssert verifyServiceFeeDocumentDetailPage(SoftAssert softAssert, String paymentType, String baseCost) {
		//Output to the system and report
		System.out.println("Checking if the 'Payment Type', 'Base Cost', and 'Credit Card Masking' matches expectations");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Payment Type', 'Base Cost', and 'Credit Card Masking' matches expectations");
		
		//Check if the 'Payment Type' matches expectations
		softAssert.assertEquals(serviceFeePaymentTypeDropDown.getText(), paymentType);
		
		//Check if the 'Base Cost' matches expectations
		softAssert.assertEquals(serviceFeeAmountField.getAttribute("value"), baseCost);
		
		//Check if the credit card number is masked
		softAssert.assertEquals(serviceFeeCreditCardField.getAttribute("value").substring(0, 11), "***********");
		
		//Return the status for the SoftAssert
		return softAssert;
	}
	
}