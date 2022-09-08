package stepdefination;



import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class VueToDoStep {
	
	private String baseUrl = "https://www.todomvc.com/examples/vue/";  
	String driverPath = "src/test/resources/drivers/chromedriver.exe";  
	public WebDriver driver ;  
	JavascriptExecutor jse ;
	List<String> taskList ;
	static Logger log;
	Actions actions ;
	WebDriverWait wait;
	 @Before
	    public void setUp() {
		 log = Logger.getLogger(VueToDoStep.class);	
		 PropertyConfigurator.configure("src/test/resources/property/log4j.properties");
	        System.setProperty("webdriver.chrome.driver",driverPath);
	    	driver = new ChromeDriver(); 	    	
	    	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    	driver.manage().window().maximize();      	
	    	wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	    	actions = new Actions(driver);
	    	taskList = new ArrayList();
	    	jse  = ( JavascriptExecutor) driver;	
	    }
	 
	   void captureScreenshot() throws IOException {

	        Date d = new Date();

	        String FileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";

	        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	        FileUtils.copyFile(screenshot, new File("src/test/resources/screenshot" + FileName));

	    }
    @Given("^user is on the vuetodo page$")
    public void userOnVueToDoPage() {
			  driver.get(baseUrl);
    	System.out.print("User is on "+driver.getTitle().toString());
    }
 
    @When("^user creat new vuetodo list$")
    public void checkList(DataTable table) throws InterruptedException, IOException  {
    	List<List<String>> rows = table.asLists(String.class);             
    	
    	//jse.executeScript("arguments[0].value='Milk'",text);
    	// jse.executeScript("document.getElementsByClassName('new-todo')[0].value='Milk'", args );
    	//document.getElementsByClassName("new-todo")[0].value="Milk";
      	String heading = driver.findElement(By.xpath("/html/body/section/header/h1")).getText();
    	System.out.println(heading);
    	Assert.assertEquals(heading, "todos");
       //String text = "//input[@placeholder='What needs to be done?']";
    	 for (List<String> columns : rows) {
             String task = columns.get(0);
             taskList.add(task);
             WebElement text = driver.findElement(By.xpath("//input[@class='new-todo']"));
             text.click();
         	text.clear();
         	text.sendKeys(task);    	
         	actions.sendKeys(Keys.ENTER).build().perform();
         	System.out.println("user clicks enter key : "+task);
         	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='selected']")));
         	captureScreenshot();
         	Thread.sleep(1000);//on for view purpose there is not use of this line of code
         }
    	
    	
    	//label[contains(text(),'test1')]//preceding::input[1]
    	
    	//button[@class='clear-completed']
 
    }
    @Then("^task should be added sucessfully$")
    public void listDetails() {
    	for(int i=0;i<taskList.size();i++) {
    		log.info("List : "+i+" value = "+taskList.get(i));
    	}
    }

    @Then("^user delete the task$")
    public void deleteListDetails() throws InterruptedException, IOException {
    	for(int i=0;i<taskList.size();i++) {
    		
    String getTaskList = "//label[contains(text(),'"+taskList.get(i)+"')]//preceding::input[1]";
    	WebElement radioButton = driver.findElement(By.xpath(getTaskList));
    	if(radioButton.isSelected()){
    		log.info(taskList.get(i) + " radio button is already selected");
    	}
    	radioButton.click();
    	log.info("deleting the todolist task : "+taskList.get(i));
    	driver.findElement(By.xpath("//button[@class='clear-completed']")).click();
    	captureScreenshot();
    	Thread.sleep(1000);//on for view purpose there is not use of this line of code
    }
	}    
    
  //label[@for='toggle-all']
  //a[@class='selected']
    
    
    @Then("^user delete all the task together$")
    public void deleteAllListDetailsTogether() throws InterruptedException, IOException {
    	
    	WebElement allButton = driver.findElement(By.xpath(" //a[@class='selected']"));
    	if(allButton.isSelected()){
    		log.info(" all button is already selected");
    	}
    	allButton.click();
    	WebElement radioAllButton = driver.findElement(By.xpath("//label[@for='toggle-all']"));
    	if(radioAllButton.isSelected()){
    		log.info( " all radio button is already selected");
    	}
    	radioAllButton.click();
    	log.info("deleting all todolist task together");
    	driver.findElement(By.xpath("//button[@class='clear-completed']")).click();
    	captureScreenshot();
    	Thread.sleep(1000);//on for view purpose there is not use of this line of code
    }
	
    
    
    @After
    public void teardown() {
 
        driver.quit();
    }
 
}
