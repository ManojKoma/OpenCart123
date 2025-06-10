package testbases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	
	public void setup(String os, String br) throws IOException
	{
		//loading config properties file
		
		FileReader file = new FileReader("./src//test//resources//Config.properties");
		p = new Properties();
		p.load(file);
		
		logger= LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_environment").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap1 = new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				cap1.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				if(os.equalsIgnoreCase("mac")) {
					cap1.setPlatform(Platform.MAC);
				}
			}
			else {
				System.out.println("Invalid Operating System");
				return;
			}
			
			switch(br.toLowerCase()) {
			case "chrome": cap1.setBrowserName("chrome"); break;
			case "edge" : cap1.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("Invalid browser"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444/ui/#"),cap1);
		}
		
		if(p.getProperty("execution_environment").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {
			case "chrome": driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			default : System.out.println("Invalid Browser");
			return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.close();
	}
	

	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()  //password
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
}
