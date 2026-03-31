package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"Regression", "Master","Sanity"})
	public void accountRegistration() {

		logger.info("**** Starting TC001_AccountRegistrationTest Test ****");
		

		try {
			HomePage hp = new HomePage(driver);

			hp.clickMyAccount();
			logger.info("Clicked My Account Link");

			hp.clickRegister();
			logger.info("Clicked Register Link");

			logger.info("Providing customer details");
			RegisterPage rp = new RegisterPage(driver);
			rp.setFirstName(randomString());
			rp.setLastName(randomString());
			rp.setEmail(randomString() + "@gmail.com");
			//rp.setPhone(randomNumeric());
			String pwd = rp.setPassword(randomAlphaNumeric());
			//rp.setConfirmPassword(pwd);
			rp.setSubscribe();
			rp.setPolicyAgreement();
			rp.clickContinue();

			logger.info("Validating expected message");
			String cnfMsg = rp.readConfirmationMessage();
			
			/*
			 * if(cnfMsg=="Your Account Has Been Created!") { Assert.assertTrue(true);
			 * logger.info("Validation successful"); } else { Assert.assertTrue(false);
			 * logger.error("Test Failed"); // logger.debug("Debug logs..."); Assert.fail();
			 * }
			 */
			Assert.assertEquals(cnfMsg, "Your Account Has Been Created!");
		} 
		catch (Exception e) {
			logger.error("Test Failed");
		//	logger.debug("Debug logs...");
			Assert.fail();
		}
		
		logger.info("**** Finished TC001_AccountRegistrationTest Test ****");
	}

}
