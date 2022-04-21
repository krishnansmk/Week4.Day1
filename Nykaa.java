package week4.day1Assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa 
{
	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.nykaa.com");
		driver.manage().window().maximize();
		
		//Print window title
		System.out.println(driver.getTitle());
				
		//Move mouse control to 'Brands' menu on top.
		Actions builder=new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("//a[text()='brands']"))).perform();
		Thread.sleep(3000);
		
		//Click on the link "Loreal Paris".
		builder.click(driver.findElement(By.linkText("L'Oreal Paris"))).perform();
		
		//verify the title of the page contains "Loreal paris".
		String pagetitle=driver.getTitle();
		if (pagetitle.contains("L'Oreal Paris"))
		{
			System.out.println("Page title contains the text 'Loreal Paris'..");
		}
		else
		{
			System.out.println("Page title doesnt contain text 'Loreal Paris'..");
		}
		
		//Click on the 'Sort by' link and choose customer top rated option.
		builder.click(driver.findElement(By.xpath("//span[contains(text(),'Sort By :')]"))).perform();
		Thread.sleep(2000);
		builder.click(driver.findElement(By.xpath("//span[text()='customer top rated']"))).perform();
		
		
		//Click on Category and select Hair-->Haircare -->shampoo
		builder.click(driver.findElement(By.xpath("//span[text()='Category']"))).perform();
		builder.click(driver.findElement(By.xpath("//span[text()='Hair'][1]"))).perform();
		builder.click(driver.findElement(By.xpath("//span[text()='Hair Care'][1]"))).perform();
		builder.click(driver.findElement(By.xpath("//span[text()='Shampoo'][1]"))).perform();
		
		Thread.sleep(2000);
		
		//Click on Concern and select Haircare protection
		builder.click(driver.findElement(By.xpath("//span[text()='Concern']"))).perform();
		//builder.click(driver.findElement(By.xpath("//span[text()='Color Protection']"))).perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		
		//Check whether the filter applied with 'Shampoo'.
		String filtertxt=driver.findElement(By.xpath("//div[@id='filters-listing']//span[@class='filter-value'][1]")).getText();
		if (filtertxt.contains("Shampoo"))
		{
			System.out.println("Product filter contains 'Shampoo'");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.findElement(By.xpath("//div[contains(text(),'Oreal Paris Colour Protect Shampoo')]"))
			.click();
			//Move control to new window and select shampoo '175ml' and display its cost.
			Set<String> wincount=driver.getWindowHandles();
			List<String> lstwincount=new ArrayList<String>(wincount);
			System.out.println(lstwincount.size());
			driver.switchTo().window(lstwincount.get(1));
			Thread.sleep(5000);
			
			Select shamsize=new Select(driver.findElement(By.xpath("//select[@title='SIZE']")));
			shamsize.selectByValue("0");
			String prodcost=driver.findElement(By.xpath("//span[text()='MRP:']/following-sibling::span")).getText();
			
			System.out.println("Cost of 175ml Loreal shampoo is.."+prodcost.substring(1));
			
			//Click on 'Add to Bag' option and proceed.
			builder.click(driver.findElement(By.xpath("//span[@class='btn-text' and text()='ADD TO BAG'][1]"))).perform();
			
			//Go to shopping bag and print the grand total.
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			builder.click(driver.findElement(By.xpath("//span[@class='cart-count']/parent::button"))).perform();
			Thread.sleep(3000);
			
			driver.switchTo().frame(0);
			String grndtot=driver.findElement(By.xpath("//span[text()='Grand Total']//following::div")).getText().substring(1);
			System.out.println("Shopping bag grand total is.."+grndtot);
			
			//Click on Proceed button and continue as Guest.
			driver.findElement(By.xpath("//span[text()='PROCEED']/ancestor::button")).click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.switchTo().defaultContent();
			
			driver.findElement(By.xpath("//button[@class='btn full big']")).click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			
			//Check whether the grand total displayed is correct.
			if (grndtot.equals(driver.findElement(By.xpath("//div[text()='Grand Total']/following::div/span[1]")).getText()))
			{
				System.out.println("Grand total displayed is correct");
			}
			else
			{
				System.out.println("Grand total displayed is errorneous");
			}
			driver.quit();
		}
		else
		{
			System.out.println("Product filter doesnt contain 'Shampoo'");
			driver.quit();
		}
		
	
		
		
		
		
		
		
		
		

	}

}
