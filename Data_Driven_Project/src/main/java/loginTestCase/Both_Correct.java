package loginTestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Both_Correct {
	@Test
	@Parameters({"username","password"})
	public void loginWithBothCorrect(String uname,String pword) {
		WebDriver driver= new ChromeDriver();
		driver.get("https://practice.expandtesting.com/login");
		//driver.manage().window().maximize();
		
		WebElement userName=driver.findElement(By.xpath("//input[@name=\"username\"]"));
		userName.sendKeys(uname);
		
		WebElement password=driver.findElement(By.xpath("//input[@name=\"password\"]"));
		password.sendKeys(pword);
		
		WebElement login=driver.findElement(By.xpath("//button[@type=\"submit\"]"));
		login.click();
		
		driver.quit();
		
		
	}


}
