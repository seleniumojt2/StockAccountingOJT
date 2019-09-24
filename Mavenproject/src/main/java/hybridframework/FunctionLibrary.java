package hybridframework;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.FileReader;
import java.util.Date;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import propertyutilities.propertyfile;

public class FunctionLibrary {
WebDriver driver;
//startBrowser
	public static WebDriver startBrowser(WebDriver driver) throws Throwable
	{
	
		System.out.println(propertyfile.getvalueforkey("Browser"));
		if(propertyfile.getvalueforkey("Browser").equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\deepthi.m\\workspace\\Mavenproject\\Executable Files\\chromedriver.exe");
		     driver=new ChromeDriver();
		} else 
			if(propertyfile.getvalueforkey("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\Users\\deepthi.m\\workspace\\Mavenproject\\Executable Files\\geckodriver.exe");
		     driver=new  FirefoxDriver();
		}else if(propertyfile.getvalueforkey("Browser").equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver","C:\\Users\\deepthi.m\\workspace\\Mavenproject\\Executable Files\\IEDriverServer.exe");
		     driver=new InternetExplorerDriver();
		}	
	return driver;
	
	}
	
	//openApplication
	
	public static void openApplication(WebDriver driver) throws Throwable
	{
		driver.get(propertyfile.getvalueforkey("URL"));
		driver.manage().window().maximize();
	}
	
	//clickAction
	
	public static void clickAction(WebDriver driver, String locatorType,String locatorValue)
	{
	if (locatorType.equalsIgnoreCase("id")) 
	{
		driver.findElement(By.id(locatorValue)).click();
	}else if(locatorType.equalsIgnoreCase("name"))
	{
		driver.findElement(By.name(locatorValue)).click();
	}else if(locatorType.equalsIgnoreCase("xpath"))
	{
		driver.findElement(By.xpath(locatorValue)).click();
	}
	
	else if(locatorType.equalsIgnoreCase("linktext"))
	{
		driver.findElement(By.linkText(locatorValue)).click();
	}
	
	}
//typeAction
	public static void typeAction(WebDriver driver, String locatorType,String locatorValue,String data)
	{
		if (locatorType.equalsIgnoreCase("id")) 
		{
			driver.findElement(By.id(locatorValue)).clear();
			driver.findElement(By.id(locatorValue)).sendKeys(data);
		}else if(locatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorValue)).clear();
			driver.findElement(By.name(locatorValue)).sendKeys(data);
		}else if(locatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorValue)).clear();
			driver.findElement(By.xpath(locatorValue)).sendKeys(data);
		}else if(locatorType.equalsIgnoreCase("linktext"))
		{
			driver.findElement(By.linkText(locatorValue)).clear();
			driver.findElement(By.linkText(locatorValue)).sendKeys(data);
		}
	}
	//closeBrowser
	
public static void closeBrowser(WebDriver driver)
{
	driver.close();
}
//waitForElement

public static void waitForElement(WebDriver driver, String locatorType,String locatorValue,String waittime)

{
	WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(waittime));
if (locatorType.equalsIgnoreCase("id")) 
{
	mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
}
else if (locatorType.equalsIgnoreCase("name")) 
{
	mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
}

else if (locatorType.equalsIgnoreCase("xpath")) 
{
	mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
}
else if (locatorType.equalsIgnoreCase("linktext")) 
{
	mywait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
}


}
//scroll page down
public static void pageDown(WebDriver driver)
{
	Actions action=new Actions(driver);
	action.sendKeys(Keys.PAGE_DOWN).build().perform();
}

// Capture Data
public static void captureData(WebDriver driver,String locatorType,String locatorValue ) throws Throwable

{
	  String data="";
	  if (locatorType.equalsIgnoreCase("id")) {
	data=	driver.findElement(By.id(locatorValue)).getAttribute("value");
	}else if(locatorType.equalsIgnoreCase("name"))
	{
		data=	driver.findElement(By.name(locatorValue)).getAttribute("value");	
	}
	else if(locatorType.equalsIgnoreCase("xpath"))
	{
		data=	driver.findElement(By.xpath(locatorValue)).getAttribute("value");	
	}
   FileWriter fw=new FileWriter("C:\\Users\\deepthi.m\\workspace\\Mavenproject\\CaptureData\\data.txt");
   BufferedWriter bw=new BufferedWriter(fw);
  bw.write(data);
 // System.out.println(data);
   bw.flush();
   bw.close();
   


}

//Table validation

public static void tableVadilation(WebDriver driver, String column) throws Throwable
{
//reading the data from text file
	FileReader fr=new  java.io.FileReader("C:\\Users\\deepthi.m\\workspace\\Mavenproject\\CaptureData\\data.txt");
	BufferedReader br=new BufferedReader(fr);
	String exp_data =br.readLine();
	//converting String value into integer
	int colnumber=Integer.parseInt(column);
	//saerch panel
	if (driver.findElement(By.xpath(propertyfile.getvalueforkey("Search.panel"))).isDisplayed())
	{
		driver.findElement(By.xpath(propertyfile.getvalueforkey("Search.panel"))).click();
		Thread.sleep(5000);
		driver.findElement(By.id(propertyfile.getvalueforkey("Search.box"))).clear();
		driver.findElement(By.id(propertyfile.getvalueforkey("Search.box"))).sendKeys(exp_data);;
		driver.findElement(By.id(propertyfile.getvalueforkey("Search.button"))).click();;;
	}else 
	{// searchbox
		driver.findElement(By.id(propertyfile.getvalueforkey("Search.box"))).clear();
		driver.findElement(By.id(propertyfile.getvalueforkey("Search.box"))).sendKeys(exp_data);;
		driver.findElement(By.id(propertyfile.getvalueforkey("Search.button"))).click();;;
	}
	//supplier webtable
	WebElement webtable=driver.findElement(By.xpath(propertyfile.getvalueforkey("webtable")));
	//row count
	List<WebElement> rows=driver.findElements(By.tagName("tr"));
    for (int i = 1; i <=rows.size(); i++)
    
    {
	String act_data=driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+colnumber+"]/div/span")).getText();
	System.out.println(act_data);
    //validation
	Assert.assertEquals(exp_data,act_data);
	break;
    }
}
//generateDate
public static String generateDate()
{
Date date=new Date();
SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_DD_SS");
return sdf.format(date);
}
//mouseClick
public static void mouseClick(WebDriver driver)
{
	Actions action=new Actions(driver);
	action.moveToElement(driver.findElement(By.id("mi_a_stock_items"))).build().perform();
	action.moveToElement(driver.findElement(By.id("mi_a_stock_categories"))).click().build().perform();
}
//tableVAlidation1
/*public static void tableValidation1(WebDriver driver, String column) throws Throwable
{
	FileReader fr=new  java.io.FileReader("C:\\Users\\deepthi.m\\workspace\\StckAccounting\\CaptureData\\data.txt");
	BufferedReader br=new BufferedReader(fr);
	String exp_data =br.readLine();
	if (driver.findElement(By.xpath("//button[@class='btn btn-default ewSearchToggle']")).isDisplayed()) {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@class='btn btn-default ewSearchToggle']")).click();
		driver.findElement(By.id("psearch")).sendKeys(exp_data);
		driver.findElement(By.id("btnsubmit")).click();
	} else {
		driver.findElement(By.xpath("//button[@class='btn btn-default ewSearchToggle']")).click();
		driver.findElement(By.id("psearch")).sendKeys(exp_data);
		driver.findElement(By.id("btnsubmit")).click();
	}
	String act_data=driver.findElement(By.xpath("//span[@id='el1_a_stock_categories_Category_Name']")).getText();
	System.out.println(act_data);
	for (int i = 0; i<=act_data.size(); i++) {
		
	
	Assert.assertEquals(act_data, exp_data);
	break;
	}
	}*/

// sample method
	public void samplemethod()
	{
		




	}
}










