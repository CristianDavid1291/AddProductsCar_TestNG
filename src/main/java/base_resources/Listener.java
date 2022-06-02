package base_resources;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listener extends Base implements ITestListener {
	ExtentReports extent = ExtentReportNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public static Logger log = LogManager.getLogger(Base.class.getName());

	@Override
	public void onTestStart(ITestResult result) {
		log.info("Starting method" + result.getMethod().getMethodName());
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
		log.info("Test: "+result.getMethod().getMethodName()+" passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		try {
			//screenshot
			WebDriver driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			
			//Extent report and attach screenshot
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(result.getMethod().getMethodName(),driver), result.getMethod().getMethodName());
			
			//Extent Report failure
			extentTest.get().fail(result.getThrowable());
			
			
		} catch (IOException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
	
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
