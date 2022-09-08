package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(tags = "@toDo", features = "src/test/resources/features/create-todos.feature", 
glue = "stepdefination",monochrome=true,publish=true)

public class CucumberTestNGRunner extends AbstractTestNGCucumberTests{

}
