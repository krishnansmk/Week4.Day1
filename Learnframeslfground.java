package week4.day1Assignment;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Learnframeslfground 
{
	public static void main(String[] args) throws IOException 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		
		//Print window title & frame count
		System.out.println(driver.getTitle());
		List<WebElement> framecount=driver.findElements(By.tagName("iframe"));
		System.out.println("Total number of frames is..."+framecount.size());
		
		//Switch to first frame
		driver.switchTo().frame(0);
		
		//Click on 'Click Me' button under 'Iam inside a frame'
		driver.findElement(By.xpath("//button[@id='Click']")).click();
		
		//Take screenshot after clicked on 'Click Me' button
		File srcref=driver.getScreenshotAs(OutputType.FILE);
		File destref=new File("./snaps/frameclickme.png");
		
		FileUtils.copyFile(srcref, destref);
		

	}

}
