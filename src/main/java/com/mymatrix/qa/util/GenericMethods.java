package com.mymatrix.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class GenericMethods {

	public void waitFor(int seconds) {
		//Initialize Variable(s)
		seconds = seconds * 1000;
		
		//Wait for the specified amount of time
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enterDateField(EventFiringWebDriver eDriver, WebElement dateField, String dateInput) {
		//Initialize Variable(s)
		Actions actions = new Actions(eDriver);
		
		//Click the Date Field
		dateField.click();
		
		//Move the cursor to the farthest left part of the input field
		for (int i = 0; i < 6; i++) {
			actions.sendKeys(Keys.ARROW_LEFT).perform();
		}
		
		//Delete any existing date info
		for (int i = 0; i < 9; i++) {
			actions.sendKeys(Keys.DELETE).perform();
		}
		
		//Enter the Date Input
		dateField.sendKeys(dateInput);
	}
	
	public String formatSupplier(String supplier) {
		//Add the relevant parentheses
		if (supplier.length() == 2) {
			supplier = "(" + supplier + " )";
		} else {
			supplier = "(" + supplier + ")";
		}
		
		//Return the Supplier Code
		return supplier;
	}
	
	public String formatTicketSupplier(String supplier) {
		//Add the relevant parentheses
		if (supplier.length() == 2) {
			supplier = "" + supplier + " ";
		} else {
			supplier = "" + supplier + "";
		}
		
		//Return the Supplier Code
		return supplier;
	}
	
	public String formatLocation(String location) {
		//Add the relevant parentheses
		if (location.length() == 3) {
			location = "(" + location + " )";
		} else {
			location = "(" + location + ")";
		}
		
		//Return the Supplier Code
		return location;
	}
	
	/*
	 * This method takes a screenshot of the entire web browser
	 * 
	 * This method is used in the @AfterMethod method in the 'LoginPageTest.java' class (underneath the 'src/test/java' folder & 'com.jostens.qa.testcases' package)
	 */
	public String getScreenshot(EventFiringWebDriver eDriver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) eDriver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		//FileUtils.copyFile(source, finalDestination);
		FileHandler.copy(source, finalDestination);
		return destination;
	}
	
	/*
	 * This method is used when the xpath of a WebElement cannot be pre-determined
	 *    (aka. Either the WebElement has a changing xpath, or a different WebElement may need to be referenced, depending on the test)
	 * This method can be called by using...
	 *    .createXPath("//a[contains(text(), \"{0}\")]", varName0);
	 *    .createXPath("//a[contains(text(), \"{0}\") and @name=\"{1}\"]", varName0, varName1);
	 *    .createXPath("//a[contains(text(), \"{0}\") and @name=\"{1}\"] and @type=\"{2}\"]", varName0, varName1, varName2);
	 *    etc..., where varName0 will replace {0}, varName1 will replace {1}, varName2 will replace {2}, etc.
	 *    Calling this method will work with any number of varName# entries, as long as there are {#} to coincide with them
	 * 
	 * This method is used in the 'selectProduct' method in the 'SchoolStorePage.java' class (underneath the 'src/main/java' folder & 'com.jostens.qa.pages' package)
	 */
	public String createXPath(String xpathExp, Object ...args) {
		
		for (int i = 0; i < args.length; i++) {
			xpathExp = xpathExp.replace("{" + i + "}", (CharSequence) args[i]);
		}
		
		return xpathExp;
	}
	
}