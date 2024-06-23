package data_Provider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login {
	String[][] data= {
			{"practice","dummy"},
			{"practice1","SuperSecretPassword!"},
			{"practice","SuperSecretPassword!"},
			{"practice1","dummy"}};
	
	@DataProvider(name="loginData")
	public String[][] loginDataProvider(){
		return data;
	}
	
	@Test(dataProvider="loginData")
	public void loginData(String uname,String pword) {
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
			
			
	}


