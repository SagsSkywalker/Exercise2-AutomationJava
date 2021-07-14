package com.skywalker.selenium;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;

public class MyTest {
	public WebDriver SetUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}
	
	/*Driver Functions*/
	public void SendText(WebElement element, String value) 
	{
		element.sendKeys(value);
	}
	
	public void Click(WebElement element) 
	{
		element.click();
	}
	
	public void NavigateTo(WebDriver driver,String url) {
		driver.get(url);
	}
	
	public void ClickWhenEnabled(WebDriver driver, WebElement element) 
	{
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void WaitToBeDisplayed(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void SelectFromCombo(WebElement element, String option) {
		Select dropdown = new Select(element);
		dropdown.selectByValue(option);
	}
	
	/*Web Elements*/
	By EnglishLang = By.xpath("//a[contains(text(),'English (US)')]");
//	By EspLang = By.xpath("//a[contains(text(),'Español')]");
	By SignUpButton = By.cssSelector("a[data-testid='open-registration-form-button']");
	By FirstNameIn = By.cssSelector("input[name='firstname']");
	By LastNameIn = By.cssSelector("input[name='lastname']");
	By EmailIn = By.cssSelector("input[name='reg_email__']");
	By EmailConfIn = By.cssSelector("input[name='reg_email_confirmation__']");
	By PwdIn = By.cssSelector("input[name='reg_passwd__']");
	By MonthCmb = By.cssSelector("select[name='birthday_month']");
	By DayCmb = By.cssSelector("select[name='birthday_day']");
	By YearCmb = By.cssSelector("select[name='birthday_year']");
	By FemaleLabel = By.xpath("//label[contains(text(),'Female')]");
	By FemaleRad = By.cssSelector("input[value='1']");
	By TextVal = By.cssSelector("h2._8eso");
	By FacebookText = By.xpath("//h2[contains(text(),'Connect with friends and the world around you on F')]");
	By SignUpBtnForm = By.xpath("//button[@id='u_5_s_GD']");
	By ErrorMessage = By.xpath("//div[@id='reg_error_inner']");
	
	public static void main(String[] args) {
		MyTest program = new MyTest();
		WebDriver driver = program.SetUp();
		WebElement element;
		String currUrl = driver.getCurrentUrl();
		String currTitle;
		String facebookText;
		String errorMsg;
		
		element = driver.findElement(program.EnglishLang);
		program.Click(element);
		Assert.assertEquals(currUrl, "https://www.facebook.com/");
//		element = driver.findElement(program.EspLang);
//		program.ClickWhenEnabled(driver, element);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		currTitle = driver.getTitle();
		Assert.assertEquals(currTitle, "Facebook - Log In or Sign Up");
		element = driver.findElement(program.SignUpButton);
		program.Click(element);
		
		/*Filling the Sign Up page*/
		element = driver.findElement(program.FirstNameIn);
		program.SendText(element, "FirstTest");
		element = driver.findElement(program.LastNameIn);
		program.SendText(element, "LastNTest");
		element = driver.findElement(program.EmailIn);
		program.SendText(element, "sagsskyusa+facebook@gmail.com");
		element = driver.findElement(program.EmailConfIn);
		program.SendText(element, "sagsskyusa+facebook@gmail.com");
		element = driver.findElement(program.PwdIn);
		program.SendText(element, "Password01!");
		element = driver.findElement(program.MonthCmb);
		program.SelectFromCombo(element, "5");
		element = driver.findElement(program.DayCmb);
		program.SelectFromCombo(element, "6");
		element = driver.findElement(program.YearCmb);
		program.SelectFromCombo(element, "1996");
		
		/*Assert [Female] text*/
		element = driver.findElement(program.FemaleLabel);
		Assert.assertEquals(element.getText(), "Female");
		element = driver.findElement(program.FemaleRad);
		program.ClickWhenEnabled(driver, element);
		program.Click(element);
		
		/*Validate text*/
		element = driver.findElement(program.FacebookText);
		program.WaitToBeDisplayed(driver, element);
		facebookText = element.getText();
		Assert.assertEquals(facebookText, "Connect with friends and the world around you on Facebook.");
		
		/*Validate Error*/
		element = driver.findElement(program.SignUpBtnForm);
		program.Click(element);
		element = driver.findElement(program.ErrorMessage);
		program.WaitToBeDisplayed(driver, element);
		errorMsg = element.getText();
		Assert.assertEquals(errorMsg, "It looks like you may have entered an incorrect email address. Please correct it if necessary, then click to continue.");
	}
}
