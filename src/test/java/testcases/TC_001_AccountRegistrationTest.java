package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject.AccountRegistration;
import pageobject.Homepage;
import testbases.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups="Sanity")
	public void verify_account() {
		
		logger.info("Test Execution Started");
		
		try {
		Homepage hp = new Homepage(driver);
		hp.clickAccount();
		logger.info("Clicked on My Account");
		hp.clickregister();
		logger.info("Registration Started");
		AccountRegistration ar = new AccountRegistration(driver);
		logger.info("Providing Customer details");
		ar.setFirstName(randomeString().toUpperCase());
		ar.setLastName(randomeString().toUpperCase());
		ar.setEmail(randomeString()+"@gmail.com");
		ar.setTelephone(randomeNumber());
		String password = randomAlphaNumeric();
		ar.setPassword(password);
		ar.setConfirmPassword(password);
		ar.setPrivacyPolicy();
		ar.clickContinue();
		logger.info("Validating expected message");
		String confmsg = ar.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
		logger.info("Testing is finished");
		
		
		
	}
	
}
