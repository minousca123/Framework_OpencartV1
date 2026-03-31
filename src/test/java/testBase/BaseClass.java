package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass (groups= {"Sanity", "Regression", "Master", "Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String op, String br) throws IOException {
		
		logger = LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase()) {
		case "chrome" : driver = new ChromeDriver(); 
						logger.info("Launching Chrome browser");
						break;
		case "edge" : driver = new EdgeDriver(); 
						logger.info("Launching Edge browser");
						break;
		case "firefox" : driver = new FirefoxDriver(); 
						logger.info("Launching Firefox browser");
						break;
		default: System.out.println("Invalid Browser"); return;
		}
		
		driver.manage().deleteAllCookies();
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		driver.get(p.getProperty("appURL"));
		//driver.get("http://localhost:82/opencart/upload/");
		//driver.get("https://tutorialsninja.com/demo/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	@AfterClass (groups= {"Sanity", "Regression", "Master", "Datadriven"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		String str = RandomStringUtils.randomAlphabetic(6);
		return str;
	}
	
	public String randomNumeric() {
		String str = RandomStringUtils.randomNumeric(10);
		return str;
	}
	
	public String randomAlphaNumeric() {
		String str = RandomStringUtils.randomAlphanumeric(7);
		return str;
	}

	public String captureScreen(String tname) {
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timestamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}

}
