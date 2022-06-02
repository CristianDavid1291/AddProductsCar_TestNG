package base_resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
static ExtentReports extent;
	
	public static ExtentReports getReportObject()
	{
		// ExtentReports, ExtentSparkReporter
				//path to create our local html file
				String path = System.getProperty("user.dir") + "\\reports\\index.html";
				//this variable control the report settings
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				//Configuring our report settings
				reporter.config().setReportName("Web Automation Results");
				reporter.config().setDocumentTitle("Test Results");
				extent = new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Cristian");
				return extent;
	}

}
