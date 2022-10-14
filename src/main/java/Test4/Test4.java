package Test4;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.BeforeClass;
import io.netty.util.internal.ThreadLocalRandom;
import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import org.junit.Before;
import org.junit.After;


public class Test4 {

	static ChromeDriver driver;
	static String[] LogIn = new String[2];

	@BeforeClass		//pries visus 1 karta 
    public static void createAccount (){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Agota\\3D Objects\\chromedriver_win32\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		driver.findElement(By.xpath("//input[@class = 'button-1 register-button']")).click();
		Random random = new Random();
		int gender = random.nextInt(2);
		if(gender == 1) driver.findElement(By.xpath("//input[@id = 'gender-male']")).click();
		else  driver.findElement(By.xpath("//input[@id = 'gender-female']")).click();
		driver.findElement(By.xpath("//input[@id = 'FirstName']")).sendKeys("Automatic test 1");
		driver.findElement(By.xpath("//input[@id = 'LastName']")).sendKeys("Automatic test 1");
		int x = ThreadLocalRandom.current().nextInt(100000, 1000000);
		LogIn[0] = "Test4" + x + "@gmail.com";
		LogIn[1] = Integer.toString(x);
		driver.findElement(By.xpath("//input[@id = 'Email']")).sendKeys(LogIn[0]);
		driver.findElement(By.xpath("//input[@name = 'Password']")).sendKeys(LogIn[1]);
		driver.findElement(By.xpath("//input[@name = 'ConfirmPassword']")).sendKeys(LogIn[1]);
		driver.findElement(By.xpath("//input[@name = 'register-button']")).click();
		
		//log out jei reikia
		driver.findElement(By.xpath("//a[@href='/logout']")).click();
		driver.quit();
    }
	
	@Before
	public void startSession() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Agota\\3D Objects\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
		driver.findElement(By.xpath("//a[@href='/login']")).click();			
		driver.findElement(By.xpath("//input[@id = 'Email']")).sendKeys(LogIn[0]);
		driver.findElement(By.xpath("//input[@name = 'Password']")).sendKeys(LogIn[1]);
		driver.findElement(By.xpath("//input[@value = 'Log in']")).click();
		//continue test 
		driver.findElement(By.xpath("//div[@class='listbox']//a[@href='/digital-downloads']")).click();
	}
	
	@After
	public void closeSession() {
		driver.findElement(By.xpath("//input[@title= 'Continue']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.findElement(By.xpath("//input[@class = 'button-1 payment-method-next-step-button']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.findElement(By.xpath("//input[@class = 'button-1 payment-info-next-step-button']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.findElement(By.xpath("//input[@class = 'button-1 confirm-order-next-step-button']")).click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		//driver.findElement(By.xpath("//input[@class = 'button-2 order-completed-continue-button']")).click();
		
		//is cart empty?
		String cart = driver.findElement(By.xpath("//div[@class = 'title']/strong")).getText();
		if (cart.equals("Your order has been successfully processed!"))
			System.out.println("Your order has been placed successfully");
		else
			System.out.println("Mistake with handling information occured!");
		
		driver.quit();
	}
	
	
	@Test
	public void test1() {
		try {
		      File myObj = new File("C:\\Users\\Agota\\3D Objects\\chromedriver_win32\\data1.txt");
		      Scanner myReader = new Scanner(myObj);
		      String data = null;
		      int size = 0;
		      while (myReader.hasNextLine()) {
		    	size++;
		    	data =  myReader.nextLine();
		    	driver.findElement(By.xpath("//h2[@class = 'product-title']/a[text() = '" + data +"']/../..//input[@value = 'Add to cart']")).click();
		    	
		    	WebElement cart = driver.findElement(By.xpath("//span[@class = 'cart-qty']"));
		    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		    	wait.pollingEvery(Duration.ofMillis(1000));
		    	wait.until(ExpectedConditions.textToBePresentInElement(cart, "(" +size + ")")); 
		    	
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred while trying to read file data1.txt.");
		      e.printStackTrace();
		    }
		driver.findElement(By.xpath("//span[@class = 'cart-label']")).click();
		driver.findElement(By.xpath("//input[@id = 'termsofservice']")).click();
		driver.findElement(By.xpath("//button[@id = 'checkout']")).click();
		driver.findElement(By.xpath("//select[@id = 'BillingNewAddress_CountryId']")).click();
		driver.findElement(By.xpath("//select[@id = 'BillingNewAddress_CountryId']/option[@value='1']")).click();

		driver.findElement(By.xpath("//input[@id = 'BillingNewAddress_City']")).sendKeys("Automatic test City 1");
		driver.findElement(By.xpath("//input[@id = 'BillingNewAddress_Address1']")).sendKeys("Automatic test City 1");
		driver.findElement(By.xpath("//input[@id = 'BillingNewAddress_ZipPostalCode']")).sendKeys("Postal Code");
		driver.findElement(By.xpath("//input[@id = 'BillingNewAddress_PhoneNumber']")).sendKeys("123456789");
		
		
	}
	
	@Test
	public void test2() {
		try {
		      File myObj = new File("C:\\Users\\Agota\\3D Objects\\chromedriver_win32\\data2.txt");
		      Scanner myReader = new Scanner(myObj);
		      String data = null;
		      int size = 0;
		      while (myReader.hasNextLine()) {
		    	size++;
		    	data =  myReader.nextLine();
		    	driver.findElement(By.xpath("//h2[@class = 'product-title']/a[text() = '" + data +"']/../..//input[@value = 'Add to cart']")).click();
		    	
		    	WebElement cart = driver.findElement(By.xpath("//span[@class = 'cart-qty']"));
		    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		    	wait.pollingEvery(Duration.ofMillis(1000));
		    	wait.until(ExpectedConditions.textToBePresentInElement(cart, "(" +size + ")")); 
		    	
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred while trying to read file data2.txt.");
		      e.printStackTrace();
		    }
		driver.findElement(By.xpath("//span[text() = 'Shopping cart']")).click();
		driver.findElement(By.xpath("//input[@id = 'termsofservice']")).click();
		driver.findElement(By.xpath("//button[@id = 'checkout']")).click();
		
		
//		driver.findElement(By.xpath("//input[@id = 'BillingNewAddress_City']")).sendKeys("Automatic test City 1");
//		driver.findElement(By.xpath("//input[@id = 'BillingNewAddress_Address1']")).sendKeys("Automatic test City 1");
//		driver.findElement(By.xpath("//input[@id = 'BillingNewAddress_ZipPostalCode']")).sendKeys("Postal Code");
//		driver.findElement(By.xpath("//input[@id = 'BillingNewAddress_PhoneNumber']")).sendKeys("123456789");
	}
}
