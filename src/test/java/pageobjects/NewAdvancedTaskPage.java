package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class NewAdvancedTaskPage extends BaseElements{

	//Elements
	@FindBy (css = "[name='prio']")
	private WebElement selectPriority;
	@FindBy (css = "[selected='selected']")
	private WebElement selectedPrio;
	@FindBy (css = "#duedate")
	private WebElement dueField;
	@FindBy (css = ".form-row>[name='task']")
	private WebElement taskField;
	@FindBy (css = "[name='note']")
	private WebElement noteField;
	@FindBy (css = "#edittags")
	private WebElement tagsField;
	@FindBy (css = "[value='Save']")
	private WebElement saveBtn;
	@FindBy (css = ".mtt-inadd .mtt-inadd")
	private WebElement pageTitle;
	
	//Constructor
	public NewAdvancedTaskPage(WebDriver driver) {
		super(driver);
	}
	
	//Methods
	@Step("Fill all fields of the new task with the following data and save it: priority='{priority}', due='{due}', task='{task}', note='{note}', tags='{tags}'.")
	public void fillAllFieldsAndSave(String priority, String due, String task, String note, String tags) {
		//Choose from Select element
		selectByValue(selectPriority, priority);
		//Fill due field
		fillText(dueField, due);
		//Fill task field
		fillText(taskField, task);
		//Fill note field
		fillText(noteField, note);
		//Fill tags field
		fillText(tagsField, tags);
		//Click on save button
		click(saveBtn);
		//Waiting for saving
		waitingForLoading();
	}
	
	@Step("Try to choose priority='{priority}'")
	public void enterImpossiblePriority(String priority) {
		try {
			//Choose from Select element
			selectByValue(selectPriority, priority);
		} catch (Exception NoSuchElementException) {
			
		}
	}
	
	@Step("Click on save button")
	public void saveTaskWithoutAllFields() {
		//Click on save button
		click(saveBtn);
		//Waiting for saving
		waitingForLoading();
	}

	
	//Validation
	@Step("Checking which page is on")
	public String stillInTheSamePage() {
		//Checking which page is on
		return getText(pageTitle);
	}
	
	@Step("Check what is the priority")
	public String actuallyPriority() {
		//Check what is the priority
		return getText(selectedPrio);
	}
}
