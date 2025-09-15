package com.mymatrix.qa.pagemethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.mymatrix.qa.pagelocators.LoginLocators;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends LoginLocators {
	
	public LoginPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}

	public void accessWebsite(String website) {
		//Output a message to the report & system
		System.out.println("Navigating to the website -> " + website);
		reportLogger.log(LogStatus.INFO, "Navigating to the website -> " + website);
		
		//Access the specified website
		for (int i = 0; i < 3; i++) {
			try {
				eDriver.get(website);
				break;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	public void login(String environment, String username, String password) {
		//Output to the system and report
		System.out.println("Attempting to login with the userid -> " + username);
		reportLogger.log(LogStatus.INFO, "Attempting to login with the userid -> " + username);
		
		//Initialize Variable(s)
		environment = environment.toLowerCase();
		
		//Log into the relevant environment
		if(environment.contains("qa") || environment.contains("preprod") || environment.contains("pre-prod") || environment.contains("prod")) {
			mymatrixUsernameField.sendKeys(username);
			mymatrixPasswordField.sendKeys(password);
			mymatrixLoginButton.click();
			
			//Pause the script a short bit
			genMethods.waitFor(3);
		} else if(environment.contains("cmr")) {
			eDriver.switchTo().frame(cmrLoginScreenFrame);
			cmrUsernameField.sendKeys(username);
			cmrPasswordField.sendKeys(password);
			cmrLoginButton.click();
			eDriver.switchTo().defaultContent();
		} else if(environment.contains("frontline")) {
			frontlineUsernameField.sendKeys(username);
			frontlinePasswordField.sendKeys(password);
			frontlineLoginButton.click();
		} else if(environment.contains("financial suite")) {
			finSuiteUsernameField.sendKeys(username);
			finSuitePasswordField.sendKeys(password);
			finSuiteLoginButton.click();
		} else if(environment.contains("matrixed")) {
			matrixEDUsernameField.sendKeys(username);
			matrixEDPasswordField.sendKeys(password);
			matrixEDLoginButton.click();
		} else if(environment.contains("hotelrec") || environment.contains("hotel rec")) {
			hotelRecUsernameField.sendKeys(username);
			hotelRecPasswordField.sendKeys(password);
			hotelRecLoginButton.click();
		} else if(environment.contains("irdb")) {
			irdbRecUsernameField.sendKeys(username);
			irdbRecPasswordField.sendKeys(password);
			irdbRecLoginButton.click();
		}
	}
	
	public SoftAssert verifySuccessfulLogin(SoftAssert softAssert, String environment, String expectedResult) {
		//Initialize Variable(s)
		environment = environment.toLowerCase();
		expectedResult = expectedResult.toLowerCase();
		
		//Output to the system and report
		if (expectedResult.equals("successful login")) {
			System.out.println("Checking if the login was successful");
			reportLogger.log(LogStatus.INFO,  "Checking if the login was successful");
		} else {
			System.out.println("Checking if the login failed, as expected");
			reportLogger.log(LogStatus.INFO,  "Checking if the login failed, as expected");
		}
		
		//Check for the relevant logout button (if the logout button exists, then the user has successfully logged in)
		try {
			
			if (expectedResult.equals("successful login")) {
				if(environment.contains("qa") || environment.contains("preprod") || environment.contains("pre-prod") || environment.contains("prod")) {
					softAssert.assertEquals(mymatrixLogoutButton.isDisplayed(), true);
				} else if(environment.contains("cmr")) {
					eDriver.switchTo().frame(cmrFrame);
					genMethods.waitFor(1);
					softAssert.assertEquals(cmrLogoutButton.isDisplayed(), true);
					eDriver.switchTo().defaultContent();
				} else if(environment.contains("frontline")) {
					softAssert.assertEquals(frontlineLogoutButton.isDisplayed(), true);
				} else if(environment.contains("financial suite")) {
					finSuiteStartMenuDropDown.click();
					softAssert.assertEquals(finSuiteLogoutButton.isDisplayed(), true);
				} else if(environment.contains("matrixed")) {
					eDriver.switchTo().frame(matrixEDFrame);
					softAssert.assertEquals(matrixEDLogoutButton.isDisplayed(), true);
					eDriver.switchTo().defaultContent();
				} else if(environment.contains("hotelrec") || environment.contains("hotel rec")) {
					softAssert.assertEquals(hotelRecLogoutButton.isDisplayed(), true);
				} else if(environment.contains("irdb")) {
					softAssert.assertEquals(irdbRecLogoutButton.isDisplayed(), true);
				} else {
					softAssert.assertEquals("Logout button not found", "Logout button exists");
				}
			} else {
				if(environment.contains("qa") || environment.contains("preprod") || environment.contains("pre-prod") || environment.contains("prod")) {
					softAssert.assertEquals(mymatrixLoginButton.isDisplayed(), true);
				} else if(environment.contains("cmr")) {
					eDriver.switchTo().frame(cmrFrame);
					softAssert.assertEquals(cmrLoginButton.isDisplayed(), true);
					eDriver.switchTo().defaultContent();
				} else if(environment.contains("frontline")) {
					softAssert.assertEquals(frontlineLoginButton.isDisplayed(), true);
				} else if(environment.contains("financial suite")) {
					finSuiteStartMenuDropDown.click();
					softAssert.assertEquals(finSuiteLoginButton.isDisplayed(), true);
				} else if(environment.contains("matrixed")) {
					eDriver.switchTo().frame(matrixEDFrame);
					softAssert.assertEquals(matrixEDLoginButton.isDisplayed(), true);
					eDriver.switchTo().defaultContent();
				} else if(environment.contains("hotelrec") || environment.contains("hotel rec")) {
					softAssert.assertEquals(hotelRecLoginButton.isDisplayed(), true);
				} else if(environment.contains("irdb")) {
					softAssert.assertEquals(irdbRecLoginButton.isDisplayed(), true);
				} else {
					softAssert.assertEquals("Logout button not found", "Logout button exists");
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
			softAssert.assertEquals(e, "Logout button exists");
		}
		
		//Return the status for the SoftAssert
		return softAssert;
	}

	public void logout(String environment) {
		//Output to the system and report
		System.out.println("Attempting to logout");
		reportLogger.log(LogStatus.INFO, "Attempting to logout");
		
		//Initialize Variable(s)
		environment = environment.toLowerCase();
		
		//Click to logout
		if(environment.contains("qa") || environment.contains("preprod") || environment.contains("pre-prod") || environment.contains("prod")) {
			mymatrixLogoutButton.click();
		} else if(environment.contains("cmr")) {
			eDriver.switchTo().frame(cmrFrame);
			genMethods.waitFor(1);
			cmrLogoutButton.click();
			eDriver.switchTo().defaultContent();
		} else if(environment.contains("frontline")) {
			//frontlineLogout.click();
		} else if(environment.contains("financial suite")) {
			finSuiteStartMenuDropDown.click();
			finSuiteLogoutButton.click();
		} else if(environment.contains("matrixed")) {
			eDriver.switchTo().frame(matrixEDFrame);
			matrixEDLogoutButton.click();
			eDriver.switchTo().defaultContent();
		} else if(environment.contains("hotelrec") || environment.contains("hotel rec")) {
			hotelRecLogoutButton.click();
		} else if(environment.contains("irdb")) {
			//irdbRecLogout.click();
		}
	}
	
	public void clickForgotPassword() {
		mymatrixForgotPasswordLink.click();
	}
	
	public SoftAssert verifyForgotPasswordPagePresence(SoftAssert softAssert) {
		//Output to the system and report
		System.out.println("Checking if the 'Forgot Password' page is up");
		reportLogger.log(LogStatus.INFO,  "Checking if the 'Forgot Password' page is up");
		
		//Check if the relevant buttons/page exists
		softAssert.assertEquals((forgotPasswordNextButton.isDisplayed() && forgotPasswordCancelButton.isDisplayed()), true);
		
		//Return the status for the SoftAssert
		return softAssert;
	}
}