package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class EditTaskPage extends BaseElements{

	//Elements
	@FindBy (css = "[name='prio']")
	private WebElement selectPriority; 
	@FindBy (css = "#duedate")
	private WebElement dueField;
	@FindBy (css = "#taskedit_form [name='task']")
	private WebElement taskField;
	@FindBy (css = "[name='note']")
	private WebElement noteField;
	@FindBy (css = "#edittags")
	private WebElement tagsField;
	@FindBy (css = "[value='Save']")
	private WebElement saveBtn;
	@FindBy (css = "[selected='selected']")
	private WebElement selectedPriority;
	@FindBy (css = ".ui-datepicker-trigger")
	private WebElement datePickerBtn;
	@FindBy (css = ".ui-state-default.ui-state-active")
	private WebElement activeDate;
	
	//Constructor
	public EditTaskPage(WebDriver driver) {
		super(driver);
	}
	
	//Methods
	@Step("Fill all fields of the task with the following data and save it: priority='{priority}', due='{due}', task='{task}', note='{note}', tags='{tags}'.")
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
}
