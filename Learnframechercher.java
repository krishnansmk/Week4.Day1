package week4.day1Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Learnframechercher 
{

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		
		//Print window title
		System.out.println(driver.getTitle());
		
		//Skip to frame1
		driver.switchTo().frame("frame1");
		//Enter value in the frame1 textbox
		driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys("Mohanakrishnan");
		
		//Skip to frame3
		driver.switchTo().frame("frame3");
		
		//click on the checkbox in frame3
		driver.findElement(By.xpath("//b[text()='Inner Frame Check box :']/following-sibling::input")).click();
		
		//Quit from two frames or move control to parent screen
		driver.switchTo().defaultContent();
		
		//Skip to frame2
		driver.switchTo().frame("frame2");
		Select anival=new Select(driver.findElement(By.id("animals")));
		anival.selectByValue("babycat");
		
		//Quit from frame2 or move control to parent screen
		driver.switchTo().defaultContent();	
		
		//Print window title
		System.out.println(driver.getTitle());		

	}

}
