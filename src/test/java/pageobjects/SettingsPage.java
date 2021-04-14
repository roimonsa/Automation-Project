package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

public class SettingsPage extends BaseElements{

	//Elements
	@FindBy (css = "label:nth-child(3) > input[name='showdate']")
	private WebElement taskDateDisabled;
	@FindBy (css = "label:nth-child(1) > input[name='showdate']")
	private WebElement taskDateEnabled;
	@FindBy (css = "[value='Submit changes']")
	private WebElement submitChangesBtn;
	@FindBy (css = "#msg")
	private WebElement submitChangesMessage;
	@FindBy (css = ".in350")
	private WebElement titleField;
	
	//Constructor
	public SettingsPage(WebDriver driver) {
		super(driver);
	}

	//Methods
	@Step("Check the Enabled check-box of Task Date In The List and submit the change")
	public void enabledATaskDateInTheList() {
		//Click on Enabled check-box
		click(taskDateEnabled);
		//Submit changes
		click(submitChangesBtn);
		//Waiting for saving changes
		waitingForLoading();
		waitingForSettingsSaved();
		waitingForLoading();
	}

	@Step("Fill the title field with text:'{title}', and submit the change")
	public void changeTitle(String title) {
		//Fill title field
		fillText(titleField, title);
		//Submit changes
		click(submitChangesBtn);
		//Waiting for saving changes
		waitingForLoading();
		waitingForSettingsSaved();
		waitingForLoading();
	}
	
	@Step("Waiting for submit changes")
	public void waitingForSettingsSaved() {
		try {
			//Waiting for appear
			wait.until(ExpectedConditions.visibilityOf(submitChangesMessage));
			//Waiting for disappear
			wait.until(ExpectedConditions.invisibilityOf(submitChangesMessage));		
		} catch (Exception TimeoutException) {

		}
	}

	//Validations
	@Step("Check if Task Date In The List is Disabled")
	public String taskDateDisabledStatus() {
		//Check if task date disabled
		return taskDateDisabled.getAttribute("checked");
	}

	@Step("Check if Task Date In The List is Enabled")
	public String taskDateEnabledStatus() {
		//Check if task date enabled
		return taskDateEnabled.getAttribute("checked");
	}
}
