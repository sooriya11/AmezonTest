package amezon.amezon;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.google.common.collect.Table.Cell;

public class TestMain {
	public static String userName;
	public static String password;
	public static String type;
	public static String validate;
	public static String validate1;
	WebDriver driver =null;
	FileInputStream fis;
	FileInputStream sec;
	XSSFWorkbook workbook2=null;
	XSSFWorkbook sheet;
	XSSFWorkbook tesxtbox;
	String textbook;
	XSSFWorkbook workbook1=null;
	int totalxns=0;
	Cell cl=null;
	Row rl= null;
	WebElement textbox;
	ExtentSparkReporter htmlReporter;
	ExtentReports extent;
	ExtentTest test;
	
	// sheet sheet;
	
	@BeforeMethod
	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void setUp()throws InterruptedException, IOException,FileNotFoundException{
		htmlReporter = new ExtentSparkReporter("test-output/report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		File src1=new File("");
		fis=new FileInputStream(src1);
		workbook1 =(XSSFWorkbook) WorkbookFactory.create(fis);
		XSSFSheet spreadsheet=workbook2.getSheetAt(0);
		
		File src2=new File("C:\\Users\\HP\\eclipse-workspace\\amezon\\Excel\\amezon.xlsx");
		sec=new FileInputStream(src2);
		workbook2=(XSSFWorkbook) WorkbookFactory.create(sec);
		XSSFSheet spreadsheet1=workbook2.getSheetAt(0);
		
		String sheetname=spreadsheet.getSheetName();
		int NumberoflastRow=spreadsheet.getLastRowNum();
		int cellcount=spreadsheet.getRow(0).getLastCellNum();
		rl=spreadsheet.getRow(0);
		cl=rl.getCell(0);
		cl.setCellType(CellType.STRING);
		userName=rl.getCell(0).getStringCellValue().trim(); 
		password=rl.getCell(1).getStringCellValue().trim(); 
		validate=spreadsheet.getRow(0).getCell(2).getStringCellValue().trim();
		validate1=spreadsheet1.getRow(0).getCell(0).getStringCellValue().trim();
		if(validate.equals(validate1))
		{
			type=validate;
		}
		
		System.out.println(sheetname);
		System.out.println(NumberoflastRow);
		System.out.println(cellcount);
		System.out.println(userName);
		System.out.println(password);
		System.out.println(type);
		
		System.setProperty("webdriver.Edge.driver","C:\\Users\\HP\\eclipse-workspace\\amezon\\edge\\msedgedriver.exe");
		driver=new EdgeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		Reporter.log("edge browser is launched");
	}
		
	
	
	@Test(priority = 1)
	public void Login() throws InterruptedException, IOException {
		test = extent.createTest("Test Case 1", "PASSED test case");
        
		test.log(Status.INFO, "status of the browser");
		test.info("started browser");

		driver.get("https://amazon.in");
		WebElement siginBtn = driver.findElement(By.xpath("//span[text()='Hello, sign in']"));
		siginBtn.click();
			
	}
@Test(priority = 2)
	
	public void textBox() throws InterruptedException,IOException {
		test = extent.createTest("Test Case 2", "PASSED test case");
		driver.findElement(By.id("twotabsearchtextbox")).click();
		driver.

		textbox = driver.findElement(By.id("twotabsearchtextbox"));

		textbox.sendKeys(type);

		driver.findElement(By.xpath("//*[@id='nav-search-submit-button']")).click();
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(".//Screenshot/Screen.png"));
    }
     
@Test(priority = 3)
public void addCart() throws InterruptedException, IOException {
	test = extent.createTest("Test Case 3", "PASSED test case");

	String parentWindow= driver.getWindowHandle();

	driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[4]")).click();

	Set<String> allWindows = driver.getWindowHandles();

	for(String curWindow : allWindows)
	{

		driver.switchTo().window(curWindow);

	}

	driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();

	Thread.sleep(1000);

	driver.close();

	driver.switchTo().window(parentWindow);

	Thread.sleep(1000);
	driver.findElement(By.xpath("//a[@id='nav-cart']")).click();

	Thread.sleep(1000);
	File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(screenshotFile, new File(".//Screenshot/Screen2.png"));
}

@Test(priority = 4)
public void signOut() throws InterruptedException, IOException {
	test = extent.createTest("Test Case 4", "PASSED test case");

	driver.findElement(By.className("hm-icon-label")).click();

	Thread.sleep(1000);

	driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	Thread.sleep(1000);
	driver.close();
}


@AfterMethod
@AfterSuite
public void tearDown() throws InterruptedException
{
	Thread.sleep(1000);
	extent.flush();
	driver.quit();
	
}



	
	
	}
	

	
	
	
	
	
	
	


