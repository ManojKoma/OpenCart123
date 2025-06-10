package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends Basepage {
	
	public Homepage(WebDriver driver) {
		super(driver);
	}
	
@FindBy(xpath="//span[normalize-space()='My Account']")
WebElement InkMyAccount;
	
@FindBy(xpath="//a[normalize-space()='Register']")
WebElement InkRegister;

@FindBy(linkText = "Login")   // Login link added in step5
WebElement linkLogin;

public void clickAccount() {
	InkMyAccount.click();
}

public void clickregister() {
	InkRegister.click();
}

public void clicklogin() {
	linkLogin.click();
}
	
	

}
