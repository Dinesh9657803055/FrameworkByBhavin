package com.openxcell.OnlineShopping.Resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	static DateFormat dateFormat  = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
	static DateFormat reportFolder = new SimpleDateFormat("dd-MMM-yyyy");
	static String ExtentReportName = System.getProperty("user.home")+"\\AutomationTemp\\Reports\\"+reportFolder.format(new Date())+"\\AutomationReport" + "_"  + dateFormat.format(new Date())+".html";	

	public static ExtentReports getReportObject() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(ExtentReportName);
		ExtentReports extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		reporter.config().setReportName("ECommerce - Smoke Test Report");
        reporter.config().setDocumentTitle("ECommerce - Test Result");
    
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA Name: ", "Bhavin Dholakiya");
        extent.setSystemInfo("Environment: ", "Production");
        extent.createTest(ExtentReportName);
        return extent;
	}
}
