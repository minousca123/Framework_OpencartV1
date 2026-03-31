package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//h1[normalize-space()='My Account']")
	WebElement pageHeading;
	
	@FindBy(xpath="//div[@class='list-group mb-3']//a[text()='Logout']")
	WebElement lnkLogout;
	
	public boolean isMyAccountPageDisplayed() {
		try {
			return pageHeading.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}

}
