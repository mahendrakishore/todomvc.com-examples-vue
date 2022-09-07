package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(tags = "@getToDoList", features = "src/test/resources/features/create-todos.feature", 
glue = "stepdefination")

public class CucumberTestNGRunner extends AbstractTestNGCucumberTests{

}
