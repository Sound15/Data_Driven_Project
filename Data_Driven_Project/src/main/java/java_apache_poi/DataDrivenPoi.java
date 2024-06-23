package java_apache_poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class DataDrivenPoi {
	static List<String> usernameList=new ArrayList<String>();
	static List<String> passwordList=new ArrayList<String>();
	
	public void readExcel() throws IOException {
		FileInputStream excel=new FileInputStream("C:\\Users\\sound\\OneDrive\\Documents\\Testdatanew.xlsx");
		Workbook workbook=new XSSFWorkbook(excel);
		Sheet sheet=workbook.getSheetAt(0);
		Iterator<Row> rowIterator=sheet.iterator();
		while(rowIterator.hasNext()) {
			Row rowvalue=rowIterator.next();
			Iterator<Cell> columnIterator=rowvalue.iterator();
			int i=2;
			while(columnIterator.hasNext()) {
				if(i%2==0) {
					usernameList.add(columnIterator.next().getStringCellValue());
				}else {
					passwordList.add(columnIterator.next().getStringCellValue());
				}
				i++;
			}
		}
	}

    public void executeTest() {
    	for(int i=0;i<usernameList.size();i++) {
    		login(usernameList.get(i),passwordList.get(i));
    	}
    }
    
    public void login(String uname,String pword) {
		WebDriver driver= new ChromeDriver();
		driver.get("https://practice.expandtesting.com/login");
		
		WebElement userName=driver.findElement(By.xpath("//input[@name=\"username\"]"));
		userName.sendKeys(uname);
		
		WebElement password=driver.findElement(By.xpath("//input[@name=\"password\"]"));
		password.sendKeys(pword);
		
		WebElement login=driver.findElement(By.xpath("//button[@type=\"submit\"]"));
		login.click();
		
		driver.quit();
		
	}
	public static void main(String[] args) throws IOException {
		 
		DataDrivenPoi usingpoi=new DataDrivenPoi();
		usingpoi.readExcel();
		usingpoi.executeTest();
		
		
	}

}
