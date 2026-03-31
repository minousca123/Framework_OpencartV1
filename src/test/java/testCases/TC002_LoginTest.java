package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	@Test(groups= {"Sanity", "Master"})
	public void login() {
		
		logger.info("*** Starting TC002_LoginTest ***");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			logger.info("**** Enter Credentials for Login ****");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			
			logger.info("Validating successful Login");
			MyAccountPage ap = new MyAccountPage(driver);
			boolean targetPage = ap.isMyAccountPageDisplayed();
			Assert.assertEquals(targetPage, true, "Login Failed");
			//Assert.assertTrue(targetPage);
		}
		catch(Exception e) {
			Assert.fail();
			logger.info("Test Failed");
		}
		
		logger.info("*** FInished TC002_LoginTest ***");
	}
}
