package com.mymatrix.qa.pagelocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mymatrix.qa.util.GenericMethods;
import com.relevantcodes.extentreports.ExtentTest;

public class LoginLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GenericMethods genMethods;
	
	//Constructor
	public LoginLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GenericMethods();
	}
	
	// ~~~ SSO ~~~ //
	
	@FindBy(id="MainContent_btnSSO")
	protected WebElement ssoSignInButton;
	
	@FindBy(id="username")
	protected WebElement cwtUserIDField;
	
	@FindBy(id="password")
	protected WebElement cwtPasswordField;
	
	@FindBy(xpath="//*[@id='lift-up']/div[6]/button")
	protected WebElement cwtLoginButton;
	
	@FindBy(id="MainContent_ddlRegion")
	protected WebElement regionDropDown;
	
	@FindBy(id="MainContent_imgMyMatrixUSComm")
	protected WebElement myMatrixUSComm;
	
	@FindBy(id="MainContent_imgMyMatrixCA")
	protected WebElement myMatrixCA;
	
	@FindBy(id="ProfileHeader1_ImageButton1")
	protected WebElement logoutButton;
	
	@FindBy(id="ContentPlaceHolder1_passwordChangeLinkBtn")
	protected WebElement signoutButton;
	
	// ~~~ Previous ~~~ //

	//MyMatrix Login Page Identifiers
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_userIdTextBox']")
	protected WebElement mymatrixUsernameField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_passwordTextBox']")
	protected WebElement mymatrixPasswordField;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_loginImageButton']")
	protected WebElement mymatrixLoginButton;
	
	@FindBy(xpath="//input[@id='ProfileHeader1_ImageButton1']")
	protected WebElement mymatrixLogoutButton;

	@FindBy(xpath="//a[@id='ContentPlaceHolder1_passwordChangeHyperLink']")
	protected WebElement mymatrixForgotPasswordLink;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_nextButton']")
	protected WebElement forgotPasswordNextButton;
	
	@FindBy(xpath="//input[@id='ContentPlaceHolder1_cancelButton']")
	protected WebElement forgotPasswordCancelButton;

	//CMR Login Page Identifiers
	@FindBy(xpath="//frame[@name='body']")
	protected WebElement cmrLoginScreenFrame;
	
	@FindBy(xpath="//frame[@name='header']")
	protected WebElement cmrFrame;
	
	@FindBy(name="user_id")
	protected WebElement cmrUsernameField;
	
	@FindBy(name="password")
	protected WebElement cmrPasswordField;
	
	@FindBy(xpath="//input[@alt='Login']")
	protected WebElement cmrLoginButton;
	
	@FindBy(xpath="//img[@alt='Sign Out']")
	protected WebElement cmrLogoutButton;
	
	//Matrix Frontline Login Page Identifiers
	@FindBy(name="user_id")
	protected WebElement frontlineUsernameField;
	
	@FindBy(name="password")
	protected WebElement frontlinePasswordField;
	
	@FindBy(xpath="//input[@alt='Login']")
	protected WebElement frontlineLoginButton;
	
	@FindBy(xpath="//span[contains(text(),'Sign Out')]")
	protected WebElement frontlineLogoutButton;
	
	//FinSuite Login Page Identifiers
	@FindBy(id="userName")
	protected WebElement finSuiteUsernameField;
	
	@FindBy(id="password")
	protected WebElement finSuitePasswordField;
	
	@FindBy(xpath="//input[@value='Login']")
	protected WebElement finSuiteLoginButton;
	
	@FindBy(xpath="//img[@alt='Start Menu']")
	protected WebElement finSuiteStartMenuDropDown;

	@FindBy(xpath="//*[contains(text(),'SignOut')]")
	protected WebElement finSuiteLogoutButton;
	
	//MatrixED Login Page Identifiers
	@FindBy(xpath="//frame[@name='top']")
	protected WebElement matrixEDFrame;
	
	@FindBy(name="username")
	protected WebElement matrixEDUsernameField;
	
	@FindBy(name="password")
	protected WebElement matrixEDPasswordField;
	
	@FindBy(xpath="//input[@value='LogIn']")
	protected WebElement matrixEDLoginButton;
	
	@FindBy(xpath="//img[@src='./images/logout.gif']")
	protected WebElement matrixEDLogoutButton;
	
	//HotelRec Login Page Identifiers
	@FindBy(name="user_id")
	protected WebElement hotelRecUsernameField;
	
	@FindBy(name="password")
	protected WebElement hotelRecPasswordField;
	
	@FindBy(xpath="//input[@alt='Login']")
	protected WebElement hotelRecLoginButton;
	
	@FindBy(xpath="//span[contains(text(),'Logout')]")
	protected WebElement hotelRecLogoutButton;
	
	//IRDB Login Page Identifiers
	@FindBy(id="UserNameTextBox")
	protected WebElement irdbRecUsernameField;
	
	@FindBy(id="PasswordTextBox")
	protected WebElement irdbRecPasswordField;
	
	@FindBy(id="LogInButton")
	protected WebElement irdbRecLoginButton;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	protected WebElement irdbRecLogoutButton;
	
}