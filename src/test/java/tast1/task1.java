package tast1;

import static org.testng.Assert.assertEquals;

import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class task1 {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
	}

	@Test
	public void mainTask1() {
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/broken_images");
		WebElement img1 = driver.findElement(By.xpath("//img[@src='asdf.jpg']"));
		WebElement img2 = driver.findElement(By.xpath("//img[@src='hjkl.jpg']"));
		WebElement img3 = driver.findElement(By.xpath("//img[@src='img/avatar-blank.jpg']"));
		if( img3.getAttribute("naturalWidth").equals("0")) {
			System.out.println(img1.getAttribute("outerHTML") + " ---> BROKEN");
		}
	}
	
	@Test
	public void mainTask2() {
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		
		driver.findElement(By.xpath("//*[.='Start']")).click();
		WebElement helloEl = driver.findElement(By.xpath("//h4[.='Hello World!']"));
		
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(helloEl));
		
		System.out.println( "is element displayed ? ;) \n" + helloEl.isDisplayed() );
	}
	
	@Test
	public void mainTask3() {
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/upload");
		
		driver.findElement(By.id("file-upload")).sendKeys("/Users/sofiianalizhyta/Desktop/IMG-20181101-WA0042_1.jpg");
		
		driver.findElement(By.id("file-submit")).click();
		wait = new WebDriverWait(driver,30);
		WebElement done = driver.findElement(By.xpath("//*[.='File Uploaded!']"));
		
		wait.until(ExpectedConditions.visibilityOf(done));
		assertEquals(done.isDisplayed(),true);
		System.out.println("DONE --> ");
	}
	
	@Test
	public void mainTask4() {		
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/javascript_error");
		driver.manage().logs().get(LogType.BROWSER);
		
		LogEntries entries = driver.manage().logs().get(LogType.BROWSER);
		entries.filter(Level.SEVERE);
		System.out.println(" are we done ? :) ");		
	}
}