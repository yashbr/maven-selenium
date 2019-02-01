package Frameworks;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;



public class CaseStudyNine {
	public static WebDriver driver;
	
	public void setupSelenium( String browserType, String url) {
		String currDir = System.getProperty("user.dir");
		
		System.out.println("Current Dir : " + currDir);
		
		if(browserType.equalsIgnoreCase("chrome")) {
			System.out.println("Using ChromDriver");
			System.setProperty("webdriver.chrome.driver", currDir+"\\..\\selenium-edureka\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		if(browserType.equalsIgnoreCase("firefox")) {
			System.out.println("Using geckoDriver");
			System.setProperty("webdriver.gecko.driver", currDir+"\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		if(browserType.equalsIgnoreCase("htmlunitdriver")) {
			driver = new HtmlUnitDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void login(String email, String password)  throws InterruptedException{
		
//		driver.findElement(By.linkText("Log In")).click();
//		//driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
//		driver.findElement(By.id("si_popup_email")).clear();
//		driver.findElement(By.id("si_popup_email")).sendKeys(email);
//		Thread.sleep(1000);
//		driver.findElement(By.id("si_popup_email")).clear();
//		driver.findElement(By.id("si_popup_email")).sendKeys(email);
//		driver.findElement(By.id("si_popup_passwd")).sendKeys(password);
//		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
			
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.linkText("Log In")));
		actions.click();
		actions.build().perform();
		Thread.sleep(1000);
		actions.moveToElement(driver.findElement(By.id("si_popup_email")));
		actions.click();
		actions.sendKeys(email);
		actions.build().perform();
		Thread.sleep(1000);
		actions.moveToElement(driver.findElement(By.id("si_popup_passwd")));
		actions.click();
		actions.sendKeys(password);
		actions.build().perform();
		Thread.sleep(1000);
		actions.moveToElement(driver.findElement(By.xpath("//button[contains(text(),'Login')]")));
		actions.click();
		actions.build().perform();
		Thread.sleep(2000);
	}
	
	
	public void browseBlog()  throws InterruptedException{
			
		// Scroll down untill the blog element is visible
		WebElement blog = driver.findElement(By.xpath("//*[@id=\"footer-blog\"]/a"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", blog);
		Thread.sleep(2000);
		
//		WebDriverWait wait = new WebDriverWait (driver, 10);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"footer-blog\"]/a")));
//		WebElement blog = driver.findElement(By.xpath("//*[@id=\"footer-blog\"]/a"));
	
		// Click on the blog and explore it	
		
		blog.click();
		
		// browse the blog section
	    Dimension initial_size = driver.manage().window().getSize();
	    int height = initial_size.getHeight();
		
		int numberOfPixelsToDragTheScrollbar = 100;
	    for (int i = 0; i < height; i = i + numberOfPixelsToDragTheScrollbar)
	    {
	    	String size = "window.scrollBy(0,"+i+")";
	    	js.executeScript(size, "");
	    	Thread.sleep(100);
	    }
	    Thread.sleep(2000);
	    for (int i = 0; i < height; i = i + numberOfPixelsToDragTheScrollbar)
	    {
	    	String size = "window.scrollBy(0,"+-i+")";
	    	js.executeScript(size, "");
	    	Thread.sleep(100);
	    }
	    
	    Thread.sleep(2000);
		driver.navigate().back();
	    Thread.sleep(2000);
	}
	public void logout()  throws InterruptedException{
	
		// Finally Logout
		driver.findElement(By.xpath("//a[@class='dropdown-toggle trackButton']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();	
		Thread.sleep(2000);
		
	}

	public void quitBrowser() {
		driver.quit();
	}

}
