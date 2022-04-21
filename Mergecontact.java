package week4.day1Assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Mergecontact 
{

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/");
		driver.manage().window().maximize();
		
		// Enter Username & Password
		driver.findElement(By.id("username")).sendKeys("DemoCSR");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		
		//Click on CRM/SFA link
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Click on Contacts link.
		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on Merge contacts link
		driver.findElement(By.linkText("Merge Contacts")).click();
		System.out.println(driver.getTitle());
		Thread.sleep(3000);
		
		//Select a contact from 'From Contact' field
		driver.findElement(By.xpath("//input[@id='partyIdFrom']/following-sibling::a[1]")).click();
		Set<String> frmcontact=driver.getWindowHandles();
		List<String> lstfrmcnt=new ArrayList<String>(frmcontact);
		
		driver.switchTo().window(lstfrmcnt.get(1));
		System.out.println(driver.getTitle());
		
		//Select first contactID from the table.
		driver.findElement(By.xpath("//table[@class='x-grid3-row-table']//a[1]")).click();
		
		driver.switchTo().window(lstfrmcnt.get(0));
		System.out.println(driver.getTitle());
		
		//Select a contact from 'To Contact' field
		driver.findElement(By.xpath("//input[@id='partyIdTo']/following-sibling::a")).click();
		Set<String> tocontact=driver.getWindowHandles();
		List<String> lsttocnt=new ArrayList<String>(tocontact);
		
		driver.switchTo().window(lsttocnt.get(1));
		System.out.println(driver.getTitle());
		
		//Select second contactID from the table.
		driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/following::a")).click();
		
		//Click on the merge button
		driver.switchTo().window(lsttocnt.get(0));
		driver.findElement(By.className("buttonDangerous")).click();
		
		//Handle alert and accept contact merge
		Alert alert=driver.switchTo().alert();
		alert.accept();
		Thread.sleep(2000);
		
		System.out.println(driver.getTitle());
		
		
		
		
		
		
		
		
		

	}

}
