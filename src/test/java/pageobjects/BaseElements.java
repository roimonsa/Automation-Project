package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

public class BaseElements extends BasePage{

	//Elements
	@FindBy (css = "#loading")
	WebElement loading;
	
	//Constructor
	public BaseElements(WebDriver driver) {
		super(driver);
	}

	//Methods
	@Step("Waiting for loading")
	public void waitingForLoading() {
		try {
			//Waiting for appear
			wait.until(ExpectedConditions.visibilityOf(loading));
			//Waiting for disappear
			wait.until(ExpectedConditions.invisibilityOf(loading));		
		} catch (Exception TimeoutException) {
		}
	}
}
