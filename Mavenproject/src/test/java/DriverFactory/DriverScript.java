package DriverFactory;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import excelutils.utilities;
import hybridframework.FunctionLibrary;

public class DriverScript {
WebDriver driver;
ExtentReports report;
ExtentTest logger;
public void startTest() throws Throwable
{
	utilities excel=new utilities();
	
	
//MasterTestCases sheet
	for (int i=1;i<=excel.rowcount("MasterTestCases");i++) 
	{
		String ModuleStatus=" "; 
	if(excel.readdata("MasterTestCases",i,2).equalsIgnoreCase("y"))
	{
		//Define Module Name
		String TcModule=excel.readdata("MasterTestCases",i, 1);
	     report=new ExtentReports("C:\\Users\\deepthi.m\\workspace\\Mavenproject\\Reports\\"+TcModule+FunctionLibrary.generateDate()+".html");
		 logger=report.startTest(TcModule);
		int rowcount=excel.rowcount(TcModule);
	  
	   //TcModule Sheet
	  
	for (int j = 1; j <=rowcount; j++)
	{
    String Description=excel.readdata(TcModule, j, 0);
    String Object_Type=excel.readdata(TcModule, j, 1);
    System.out.println(Object_Type);
    String locator_type=excel.readdata(TcModule, j, 2);	
    String locator_value=excel.readdata(TcModule, j, 3);	
    String test_data=excel.readdata(TcModule, j, 4);	
	try
	{	
	if (Object_Type.equalsIgnoreCase("StartBrowser"))
	{
		System.out.println("start brower method");
	driver=FunctionLibrary.startBrowser(driver);
	logger.log(LogStatus.INFO, Description);
	}	
	if (Object_Type.equalsIgnoreCase("openApplication")) 
	{
			
	FunctionLibrary.openApplication(driver);
	logger.log(LogStatus.INFO, Description);
	}
	if (Object_Type.equalsIgnoreCase("typeAction"))
	{
		FunctionLibrary.typeAction(driver,locator_type,locator_value,test_data);
		logger.log(LogStatus.INFO, Description);
	}	
	if (Object_Type.equalsIgnoreCase("waitForElement"))
	{
		FunctionLibrary.waitForElement(driver,locator_type,locator_value,test_data);
		logger.log(LogStatus.INFO, Description);
	}	
	if (Object_Type.equalsIgnoreCase("clickAction")) 
	{
	 FunctionLibrary.clickAction(driver,locator_type,locator_value);	
	 logger.log(LogStatus.INFO, Description);
	}
	if (Object_Type.equalsIgnoreCase("closeBrowser"))
	{
		FunctionLibrary.closeBrowser(driver);
		logger.log(LogStatus.INFO, Description);
	}
	if (Object_Type.equalsIgnoreCase("pageDown"))
	{
		FunctionLibrary.pageDown(driver);;
		logger.log(LogStatus.INFO, Description);
	}
	if (Object_Type.equalsIgnoreCase("captureData"))
	{
		FunctionLibrary.captureData(driver, locator_type, locator_value);;
		logger.log(LogStatus.INFO, Description);
	}
	if (Object_Type.equalsIgnoreCase("mouseClick")) {
		FunctionLibrary.mouseClick(driver);;
		
		logger.log(LogStatus.INFO, Description);
	
	}
	if (Object_Type.equalsIgnoreCase("tableValidation"))
    {
		FunctionLibrary.tableVadilation(driver,test_data);
	
		logger.log(LogStatus.INFO, Description);}
	
	//status update in tcmodule sheet as pass
	excel.setcelldata(TcModule, j,5,"PASS");
	ModuleStatus ="true";
	logger.log(LogStatus.PASS, Description+"pass");
	}catch(Exception e)
	{
	//status update in tcmodule sheet as fail
		excel.setcelldata(TcModule, j, 5,"FAil");
		ModuleStatus ="false";
		logger.log(LogStatus.FAIL, Description+"fail");
		File srcfile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(srcfile,new java.io.File("C:\\Users\\deepthi.m\\workspace\\Mavenproject\\Screenshots\\"+TcModule+" "+FunctionLibrary.generateDate()+".png"));
		break;	
	}
	
	}
	if (ModuleStatus.equalsIgnoreCase("true")) 
	{
		//status update in mastertestcases sheet as pass
	excel.setcelldata("MasterTestCases", i, 3, "PASS");
	
	}else if(ModuleStatus.equalsIgnoreCase("flase"))
	{
		//status update in mastertestcases sheet as fail
		excel.setcelldata("MasterTestCases", i, 3, "FAIL");
		
	}
	report.endTest(logger);	
	report.flush();
}
	else{
		excel.setcelldata("MasterTestCases", i, 3, "Not Executed");
	}

}

	
}	
}
