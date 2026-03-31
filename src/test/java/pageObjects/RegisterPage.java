package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{
	
	public RegisterPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//input[@name='firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@name='lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtPhone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@id='input-newsletter']")
	WebElement chkSubscribe;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPolicy;
	
	@FindBy(xpath="//button[text()='Continue']")
	WebElement btnSubmit;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setPhone(String phone) {
		txtPhone.sendKeys(phone);
	}
	
	public String setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
		return pwd;
	}
	
	public void setConfirmPassword(String cnfPwd) {
		txtConfirmPassword.sendKeys(cnfPwd);
	}
	
	
	public void setSubscribe() {
		chkSubscribe.click();
	}
	
	public void setPolicyAgreement(){
		chkPolicy.click();
	}
	
	public void clickContinue() {
		btnSubmit.click();
	}
	
	public String readConfirmationMessage() {
		try {
			return msgConfirmation.getText();
		}
		catch (Exception e) {
			return e.getMessage();
		}
		
		
	}
}
