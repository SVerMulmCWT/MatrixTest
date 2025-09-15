package com.mymatrix.qa.util;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {
	
//	private static String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\jostens\\qa\\testdata\\TestSuiteResults.html";
	private static String path = System.getProperty("user.dir") + "\\test-output\\ExtentReport.html";
	
	public static ExtentReports getInstance() {
		ExtentReports extent;
		extent = new ExtentReports(path, false);
		
		return extent;
	}
	
	public static void deleteExtentReport() {
		File file = new File(path);
		
		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete file, located at: " + path);
		}
	}
	
}