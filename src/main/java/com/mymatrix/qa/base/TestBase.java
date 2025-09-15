package com.mymatrix.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.mymatrix.qa.pagemethods.AirItineraryPage;
import com.mymatrix.qa.pagemethods.BusItineraryPage;
import com.mymatrix.qa.pagemethods.CarItineraryPage;
import com.mymatrix.qa.pagemethods.ChangeBranchPage;
import com.mymatrix.qa.pagemethods.ClientFilePage;
import com.mymatrix.qa.pagemethods.CostLinePage;
import com.mymatrix.qa.pagemethods.CreateClientFilePage;
import com.mymatrix.qa.pagemethods.HotelItineraryPage;
import com.mymatrix.qa.pagemethods.IRQueueCountPage;
import com.mymatrix.qa.pagemethods.InsuranceItineraryPage;
import com.mymatrix.qa.pagemethods.LoginPage;
import com.mymatrix.qa.pagemethods.MainPage;
import com.mymatrix.qa.pagemethods.MiscItineraryPage;
import com.mymatrix.qa.pagemethods.NonBSPTicketPage;
import com.mymatrix.qa.pagemethods.QueueCountPage;
import com.mymatrix.qa.pagemethods.RailItineraryPage;
import com.mymatrix.qa.pagemethods.SearchClientFilePage;
import com.mymatrix.qa.pagemethods.ServiceFeePage;
import com.mymatrix.qa.pagemethods.ShipItineraryPage;
import com.mymatrix.qa.pagemethods.BSPTicketPage;
import com.mymatrix.qa.pagemethods.TourItineraryPage;
import com.mymatrix.qa.pagemethods.TravelerPage;
import com.mymatrix.qa.util.EventHandler;
import com.mymatrix.qa.util.ExcelMethods;
import com.mymatrix.qa.util.ExtentFactory;
import com.mymatrix.qa.util.GenericMethods;

public class TestBase {
	//Define Variable(s)
	public static Properties prop;
	public GenericMethods genMethods;
	public ExcelMethods excelMethods;
	public String sheetName;
	
	//Variable(s) used to export script results
	public int iteration;
	public int column;
	
	//Variable(s) used to initialize the WebDriver
	public static DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
	private static WebDriver driver; //used to setup a connection to one or more browsers for testing
	private static EventHandler eHandler;  //used in conjunction with the EventFiringWebDriver
	public static EventFiringWebDriver eDriver; //used in conjunction with the WebDriver
	
	//Setup the report logger
	public ExtentReports report; //used to setup a report that will hold the testing info of the script(s)
	public ExtentTest reportLogger; //used to store testing details in the report
	
	//Define PageFactories
	public LoginPage loginPage;
	public MainPage mainPage;
	public ChangeBranchPage changeBranchPage;
	public SearchClientFilePage searchClientFilePage;
	public CreateClientFilePage createClientFilePage;
	public ClientFilePage clientFilePage;
	public TravelerPage travelerPage;
	public AirItineraryPage airItineraryPage;
	public HotelItineraryPage hotelItineraryPage;
	public CarItineraryPage carItineraryPage;
	public TourItineraryPage tourItineraryPage;
	public ShipItineraryPage shipItineraryPage;
	public InsuranceItineraryPage insuranceItineraryPage;
	public MiscItineraryPage miscItineraryPage;
	public RailItineraryPage railItineraryPage;
	public BusItineraryPage busItineraryPage;
	public CostLinePage costLinePage;
	public BSPTicketPage bspTicketPage;
	public NonBSPTicketPage nonBSPTicketPage;
	public ServiceFeePage serviceFeePage;
	public QueueCountPage queueCountPage;
	public IRQueueCountPage irQueueCountPage;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\mymatrix\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void initializeDriver() {
		String testingBrowser = prop.getProperty("browser");
		
		//Initialize the relevant browser driver
		if (testingBrowser.equalsIgnoreCase("internetexplorer")) {
			//Setup caps properties for IE, if IE is going to be used as the Driver
			caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver(caps);
		} else if (testingBrowser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (testingBrowser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else {
			System.out.println("Cannot setup the driver due to invalid input");
			driver.quit();
		}
		
		//Setup the Event Driver
		eDriver = new EventFiringWebDriver(driver);
		eHandler = new EventHandler();
		eDriver.register(eHandler);
		
		eDriver.manage().window().maximize();
		eDriver.manage().deleteAllCookies();
		eDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		eDriver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}
	
	@BeforeTest
	public void beforeSuite() {
		System.out.println("Performing the script's setups (@BeforeSuite)");
		
		//Delete previous Extent Report
		ExtentFactory.deleteExtentReport();
		
		//Initialize Variable(s)
		initializeDriver(); //Sets up WebDriver with Listeners
		genMethods = new GenericMethods();
	}
	
	@AfterSuite
	public void afterSuite() {
//		eDriver.quit();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.FAILURE) {
			excelMethods.setDataTableCell("Failure - " + excelMethods.getCurrentDateTime(), iteration, column);
			reportLogger.log(LogStatus.FAIL,  "The Test Case that failed is: " + result.getName()); //adds name to ExtentReport
			reportLogger.log(LogStatus.FAIL,  "The Test Case that failed is: " + result.getThrowable()); //adds error/exception to ExtentReport
			
//			String screenshotPath = genMethods.getScreenshot(eDriver, result.getName());
//			reportLogger.log(LogStatus.FAIL,  reportLogger.addScreenCapture(screenshotPath)); //adds screenshot to ExtentReport
//			//reportLogger.log(LogStatus.FAIL,  reportLogger.addScreencast(screenshotPath)); //adds screencast/video to ExtentReport
			
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) eDriver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			
			String destination = System.getProperty("user.dir") + "\\screenshots\\" + result.getName() + dateName + ".png";
			File finalDestination = new File(destination);
			//FileUtils.copyFile(source, finalDestination);
			FileHandler.copy(source, finalDestination);
			
			reportLogger.log(LogStatus.FAIL,  reportLogger.addScreenCapture(destination)); //adds screenshot to ExtentReport
		} else if (result.getStatus() == ITestResult.SKIP) {
			System.out.println("The Test Case that was skipped is: " + result.getName());
			reportLogger.log(LogStatus.SKIP,  "The Test Case that was skipped is: " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			excelMethods.setDataTableCell("Success - " + excelMethods.getCurrentDateTime(), iteration, column);
			System.out.println("The Test Case that passed is: " + result.getName());
			reportLogger.log(LogStatus.PASS,  "The Test Case that passed is: " + result.getName());
		} else {
			reportLogger.log(LogStatus.UNKNOWN,  "Unknown error upon completion of a @Test - the @Test neither passed, failed, or was skipped");
		}
		
		report.endTest(reportLogger);
		report.flush();
//		eDriver.quit();
	}
	
}