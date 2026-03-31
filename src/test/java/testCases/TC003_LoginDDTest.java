package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import freemarker.log.Logger;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDTest extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups={"Datadriven", "Master"})
	public void verify_loginDDT(String email, String pwd, String res) {

		logger.info("*** Starting TC003_LoginDDTest ***");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			MyAccountPage map = new MyAccountPage(driver);
			boolean targetPage = map.isMyAccountPageDisplayed();

			/*
			 * Data is valid - login success - test pass - logout 
			 * login fail - test fail
			 * 
			 * Data is invalid - login success - test fail - 
			 * logout login failed - test pass
			 */

			if (res.equalsIgnoreCase("valid")) {
				
				if (targetPage == true) {
					map.clickLogout();
					Assert.assertTrue(true);
					logger.info("Exp: Valid Log In, Act: Logged In");
				} else {
					Assert.assertTrue(false);
					logger.info("Exp: Valid Log In, Act: Login Uncessful");
				}
					
			} 
			if (res.equalsIgnoreCase("invalid")) {
				if (targetPage == false) {
					Assert.assertTrue(true);
					logger.info("Exp: Invalid Log In, Act: Login Uncessful");
				} else {
					map.clickLogout();
					Assert.assertTrue(false);
					logger.info("Exp: Invalid Log In, Act: Logged In");
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("*** Finished TC003_LoginDDTest ***");

	}

}
