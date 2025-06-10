package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject.Homepage;
import pageobject.LoginPage;
import pageobject.MyAccount;
import testbases.BaseClass;

public class TC_002_LoginTest  extends BaseClass {
	
	@Test(groups ={"Sanity","Master"})
	public void verifylogin() {
		
		logger.info("Starting the login test");
		try {
		Homepage hp = new Homepage(driver);
		hp.clickAccount();
		logger.info("Clicked on my account");
		hp.clicklogin();
		logger.info("Clicked on my login");
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccount macc = new MyAccount(driver);
		boolean targetpage= macc.isMyAccountPageExists();
		Assert.assertEquals(targetpage, true, "Login Failed");
		} 
		catch(Exception e){
			Assert.fail();
		}
		logger.info("Finished Testing");
		
	}

}
