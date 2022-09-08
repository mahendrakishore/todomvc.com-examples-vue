package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ReusableMethods {
 WebDriver webdriver ;
	
	public  void screenShot(String name) {		
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            try {
                  FileUtils.copyFile(SrcFile, new File("/vueScreenshot"+name+".png"));
              } catch (IOException e) {
                  System.out.println(e.getMessage());
              }
}
	
	public  String getDataFromFile(String key) {
		
		FileReader reader = null;
		try {
			reader = new FileReader("src/test/resources/property/data.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	      
	    Properties p=new Properties();  
	    try {
			p.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	      
	    String data = p.getProperty(key);  
	    System.out.println(data);  
		
		return data;
	}

}
