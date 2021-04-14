package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobjects.NewAdvancedTaskPage;
import pageobjects.SettingsPage;
import pageobjects.TasksPage;
import utils.AllureAttachment;

@Epic("View changing")
public class MoveShowAndHideTasksInformationTest extends BaseTest{

	//Objects
	private TasksPage tp;
	private NewAdvancedTaskPage natp;
	private SettingsPage sp;

	@Feature("Task transfer")
	@Story("As a User I want to transfer task between lists")
	@Severity(SeverityLevel.MINOR)
	@Test (description = "Move a task to another list - TC18")
	@Description ("Create new task and new list and move the new task to the new list")
	void tc02_moveATaskToAnotherList() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the task name
		String task = "Smile(:";
		//Configure of the list name
		String list = "Happiness";
		//Add a task
		tp.addSimpleTask(task);
		//Add a list
		tp.addNewList(list);
		//Move the new task to the new list
		tp.moveTaskToAnotherList(task, list);
		//Check if the task not exist in the old list
		Assert.assertFalse(tp.ifTheTaskExists(task));
		//Check if the task exist in the new list
		Assert.assertTrue(tp.ifTheTaskExistsInAnotherList(task, list));
	}

	@Feature("Show/Hide Note")
	@Story("As a User I want to shown all the exists notes and hide them back")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Show notes from 'Show' button - TC19 and Hide notes from 'Hide' button - TC21")
	@Description ("Create two advenced tasks and show their notes from 'Show' button and hide their notes from 'Hide' button")
	void tc03_showTasksNotesFromShowButtonAndHideTasksNotesFromHideButton() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Click on add advanced task button
		tp.addAdvancedTask();
		//Configure of the page
		natp = new NewAdvancedTaskPage(driver);
		//Fill all task fields and save
		natp.fillAllFieldsAndSave("1", "19/11/2020", "Smile", "Smile every two seconds", "smile");
		//Configure of the page
		tp = new TasksPage(driver);
		//Click on add advanced task button
		tp.addAdvancedTask();
		//Configure of the page
		natp = new NewAdvancedTaskPage(driver);
		//Fill all task fields and save
		natp.fillAllFieldsAndSave("2", "19/11/2020", "Laugh", "Laugh from the heart", "laugh");
		//Configure of the page
		tp = new TasksPage(driver);
		//Click on show button
		tp.showNotes();
		//Verify all notes are shown
		Assert.assertTrue(tp.ifAllTheNotesAreShows());
		//Click on hide button
		tp.hideNotes();
		//Verify all notes are hidden
		Assert.assertFalse(tp.ifAllTheNotesAreHidden());
	}

	@Feature("Show/Hide Note")
	@Story("As a User I want to shown single note and hide it back")
	@Severity(SeverityLevel.MINOR)
	@Test (description = "Show single note from task toggle (TC20) and Hide single note from task toggle (TC22)")
	@Description ("Create advenced task and show his note from task toggle and hide his note from task toggle")
	void tc04_showSingleTaskNoteFromTaskLineAndHideSingleTaskNoteFromTaskLine() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Click on add advanced task button
		tp.addAdvancedTask();
		//Configure of the task name
		String task = "Smile!:)";
		//Configure of the page
		natp = new NewAdvancedTaskPage(driver);
		//Fill all task fields and save
		natp.fillAllFieldsAndSave("1", "19/11/2020", task, "Smile every two seconds", "smile");
		//Configure of the page
		tp = new TasksPage(driver);
		//Click on the task toggle
		tp.clickOnTaskToggle(task);
		//Verify the note shown
		Assert.assertTrue(tp.ifSingleTaskNoteShow(task));
		//Click on the task toggle
		tp.clickOnTaskToggle(task);
		//Verify the note hidden
		Assert.assertFalse(tp.ifSingleTaskNoteShow(task));
	}

	@Feature("Task date")
	@Story("The system not shown tasks date in the list by default")
	@Severity(SeverityLevel.NORMAL)
	@Test (description = "Disabled a task date in the list by default - TC24")
	@Description ("Check the tasks date is not shown in the list by default")
	void tc05_disabledATaskDateInTheListBydefault() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Click on settings button
		tp.openSettingsPage();
		//Configure of the page
		sp = new SettingsPage(driver);
		//Verify task date in the list is disabled
		Assert.assertEquals(sp.taskDateDisabledStatus(), "true");
	}

	@Feature("Task date")
	@Story("As a User I want to shown the tasks date in the list")
	@Severity(SeverityLevel.NORMAL)
	@Test (description = "Enabled a task date in the list - TC23")
	@Description ("Sowing the tasks date in the list")
	void tc06_enabledATaskDateInTheList() {
		//Click on enabled a task date in the list check box and save the change
		sp.enabledATaskDateInTheList();
		//Configure of the page
		tp = new TasksPage(driver);
		//Click on settings button
		tp.openSettingsPage();
		//Configure of the page
		sp = new SettingsPage(driver);
		//Check if the change saved
		Assert.assertEquals(sp.taskDateEnabledStatus(), "true");
		AllureAttachment.attachText("Look at the sky!!!");
	}
}