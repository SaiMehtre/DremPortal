package DreamPortal;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;
	
public class ChromeNew {
	WebDriver driver;
	@Parameters("browser")
	// Test to select Browser
	public void selectBrower(String browser) {
		// if browser is Chrome
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver-win64\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
	        options.addArguments("--start-maximized");
	        driver = new ChromeDriver(options);
        } else {
            System.out.println("Unsupported browser. Please choose 'chrome' or 'firefox'.");
            System.exit(0);
        }
		driver.get("https://arjitnigam.github.io/myDreams/");	
	}
	
	@Test(priority=0)
	// Test to maximize window
	public void maxScreen() {
		driver.manage().window().maximize();
	}
	
	public static void main(String[] args) throws InterruptedException, TimeoutException {
	    System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver-win64\\chromedriver.exe");

	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("--start-maximized");

	    WebDriver driver = new ChromeDriver(options);
	    driver.get("https://arjitnigam.github.io/myDreams/");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-animation")));

	    
	    WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("dreamButton")));
	    btn.click();
	    System.out.println("Clicked the button successfully!");
	    
	    driver.get("https://arjitnigam.github.io/myDreams/dreams-diary.html");

	    Thread.sleep(2000); 

	 
	 List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	     By.xpath("//*[@id='dreamsDiary']//tbody/tr")
	 ));

	 System.out.println("Rows found: " + rows.size());

	 // Validate row count
	 if (rows.size() != 10) {
	     System.out.println("Row count is not 10. Found: " + rows.size());
	 } else {
	     System.out.println("10 rows found.");
	 }
	 
	 int goodCount = 0;
	 int badCount = 0;	 

	 for (int i = 0; i < rows.size(); i++) {
		    WebElement row = rows.get(i);
		    List<WebElement> cols = row.findElements(By.tagName("td"));

		    // Print column count for this row
		    System.out.println("Row " + (i + 1) + " has " + cols.size() + " columns.");

		    if (cols.size() != 3) {
		        System.out.println("Row " + (i + 1) + " doesn't have exactly 3 columns.");
		        continue;
		    }

		    String name = cols.get(0).getText().trim();
		    String days = cols.get(1).getText().trim();
		    String type = cols.get(2).getText().trim();
		    	    

		    if (name.isEmpty() || days.isEmpty() || type.isEmpty()) {
		        System.out.println("Row " + (i + 1) + " has empty values: " + name + " | " + days + " | " + type);
		    }

		    if (!(type.equals("Good") || type.equals("Bad"))) {
		        System.out.println("Row " + (i + 1) + " has invalid type: " + type);
		    }
		    
		    if (type.equalsIgnoreCase("Good")) {
		        goodCount++;
		    } else if (type.equalsIgnoreCase("Bad")) {
		        badCount++;
		    } else {
		        System.out.println("Row " + (i + 1) + " has invalid type: " + type);
		    }
		}
	 
	 System.out.println("Total Good dreams: " + goodCount);
	 System.out.println("Total Bad dreams: " + badCount);
	 
	 
	//  Go to dreams-diary.html and collect recurring titles
	 driver.get("https://arjitnigam.github.io/myDreams/dreams-diary.html");
	 Thread.sleep(2000);

	 List<WebElement> rows1 = driver.findElements(By.xpath("//*[@id='dreamsDiary']//tbody/tr"));

	 Map<String, Integer> titleCountMap = new HashMap<>();

	 for (WebElement row : rows1) {
	     List<WebElement> cols = row.findElements(By.tagName("td"));
	     if (cols.size() >= 1) {
	         String title = cols.get(0).getText().trim();
	         titleCountMap.put(title, titleCountMap.getOrDefault(title, 0) + 1);
	     }
	 }

	 // Find recurring titles
	 List<String> recurringTitles = new ArrayList<>();
	 for (Map.Entry<String, Integer> entry : titleCountMap.entrySet()) {
	     if (entry.getValue() > 1) {
	         recurringTitles.add(entry.getKey());
	     }
	 }

	 System.out.println("Recurring Dreams Found: " + recurringTitles);

	 
	 driver.get("https://arjitnigam.github.io/myDreams/dreams-total.html");
	 Thread.sleep(2000);

	 Map<String, String> expectedStats = new HashMap<>();
	 expectedStats.put("Good Dreams", "6");
	 expectedStats.put("Bad Dreams", "4");
	 expectedStats.put("Total Dreams", "10");
	 expectedStats.put("Recurring Dreams", String.valueOf(recurringTitles.size()));  
	 expectedStats.put("Dreams This Week", "7"); 

	 List<WebElement> statRows = driver.findElements(By.xpath("//table[@id='dreamsTotal']/tbody/tr"));

	 for (WebElement row : statRows) {
	     List<WebElement> cols = row.findElements(By.tagName("td"));
	     if (cols.size() == 2) {
	         String type = cols.get(0).getText().trim();
	         String value = cols.get(1).getText().trim();

	         if (expectedStats.containsKey(type)) {
	             String expected = expectedStats.get(type);
	             if (value.equals(expected)) {
	                 System.out.println(" " + type + ": " + value);
	             } else {
	                 System.out.println(" " + type + " mismatch. Expected: " + expected + ", Found: " + value);
	             }
	         }
	     }
	 }

	 
	 List<String> expectedRecurring = Arrays.asList("Flying over mountains", "Lost in maze");

	 for (String dream : expectedRecurring) {
	     if (recurringTitles.contains(dream)) {
	         System.out.println("Expected recurring dream present: " + dream);
	     } else {
	         System.out.println("Expected recurring dream MISSING: " + dream);
	     }
	 }


	    driver.quit(); 
    }
}

