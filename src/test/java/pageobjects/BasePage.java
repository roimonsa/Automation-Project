package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class BasePage {

	//Objects
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;
	JavascriptExecutor js;

	//Constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
		js = (JavascriptExecutor) driver;
		actions = new Actions(driver);
	}
	
	//Methods
	@Step("Fill the {0} field with the text: '{1}'")
	public void fillText(WebElement el, String text) {
		//High light the element
		highlightElement(el, "yellow");
		//Clear the field
		el.clear();
		//Fill the field
		el.sendKeys(text);
	}
	
	@Step("Click on {0}")
	public void click(WebElement el) {
		//High light the element
		highlightElement(el, "yellow");
		//Click on the element
		el.click();
	}
	
	@Step("Get text of {0}")
	public String getText(WebElement el) {
		//High light the element
		highlightElement(el, "orange");
		//Get text of the element
		return el.getText();
	}
	
	@Step("Select the option: '{1}'")
	public void selectByValue(WebElement el, String value) {
		//Select object
		Select s = new Select(el);
		//High light the element
		highlightElement(el, "yellow");
		//Choose element from Select object
		s.selectByValue(value);
	}
	
	@Step("Sleep for {0} mills")
	public void sleep(long mills) {
		try {
			//Sleep for X milliseconds
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Step("Move mouse to {0}")
	public void moveMouseTo(WebElement el) {
		//High light the element
		highlightElement(el, "yellow");
		//Move mouse to element
		actions.moveToElement(el).build().perform();        
	}
	
	@Step("Accept the popup alert")
	public void alertOK() {
		//Accept the alert
		driver.switchTo().alert().accept();
	}
	
	@Step("Enter the text: '{0}' to the popup alert and accept it")
	public void alertOKWithText(String text) {
		//Fill the alert field
		driver.switchTo().alert().sendKeys(text);
		//Accept the alert
		driver.switchTo().alert().accept();

	}
	
	@Step("Highlight element {0} with color {1}")
	private void highlightElement(WebElement element, String color) {
		//keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		//Style for change
		String newStyle = "background-color:" + color + ";" + originalStyle;
		//Change the style 
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
				element);
		//Change the style back after few milliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},350);", element);
	}
}
