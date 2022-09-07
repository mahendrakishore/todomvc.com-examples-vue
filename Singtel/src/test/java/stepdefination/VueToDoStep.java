package stepdefination;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VueToDoStep {
	
	private String baseUrl = "https://www.todomvc.com/examples/vue/";  
	String driverPath = "src/test/resources/drivers/chromedriver.exe";  
	public WebDriver driver ;   
	
	 @Before
	    public void setUp() {
	        System.setProperty("webdriver.chrome.driver",driverPath);
	    	driver = new ChromeDriver();  
	    	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	    	driver.manage().window().maximize();  
	    	
	    }
    @Given("user is on the vuetodo page")
    public void userOnVueToDoPage() {
 
    	driver.get(baseUrl);  
    }
 
    @When("user check the vuetodo list")
    public void checkList()  {
 
       
 
    }
    @Then("user gets the vuetodo list details")
    public void listDetails() {
    	
    }

    @After
    public void teardown() {
 
        driver.quit();
    }
 
}
