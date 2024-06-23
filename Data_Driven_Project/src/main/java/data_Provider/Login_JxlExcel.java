package data_Provider;

import java.io.FileInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Login_JxlExcel {
	Object[][] data=null;
	WebDriver driver;

	@DataProvider(name="loginData")
	public Object[][] loginDataProvider() throws BiffException, IOException {
		data=getExcelData();
		return data;
	}

	public String[][] getExcelData() throws BiffException, IOException {
		FileInputStream excel=new FileInputStream("C:\\Users\\sound\\OneDrive\\Documents\\Testdata.xls");
		Workbook workbook=Workbook.getWorkbook(excel);
		Sheet sheet=workbook.getSheet(0);
		int rowCount=sheet.getRows();
		int columnCount=sheet.getColumns();
		String testData[][]=new String[rowCount-1][columnCount];
		for(int i=1;i<rowCount;i++) {
			for(int j=0;j<columnCount;j++) {
				testData[i-1][j]=sheet.getCell(j,i).getContents();
			}
		}
		return testData;

	}

	@BeforeTest
	public void beforeTest() {
		driver=new ChromeDriver();
	}

    @Test(dataProvider="loginData")
	public void loginData(String uname,String pword) {
		driver.get("https://practice.expandtesting.com/login");
		WebElement userName=driver.findElement(By.xpath("//input[@name=\"username\"]"));
		userName.sendKeys(uname);

		WebElement password=driver.findElement(By.xpath("//input[@name=\"password\"]"));
		password.sendKeys(pword);

		WebElement login=driver.findElement(By.xpath("//button[@type=\"submit\"]"));
		login.click();
	}
    
    @AfterTest
    public void afterTest() {
    	driver.quit();
    }
}


