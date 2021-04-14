package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobjects.EditTaskPage;
import pageobjects.NewAdvancedTaskPage;
import pageobjects.TasksPage;
import utils.AllureAttachment;

@Epic("Edit task")
public class DeleteAndEditTaskTest extends BaseTest{

	//Objects
	private TasksPage tp;
	private EditTaskPage etp;
	private NewAdvancedTaskPage natp;
	
	@Feature("Delete task")
	@Story("As a User I want to delete non-use task")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Delete task - TC10")
	@Description ("Add a new simple task and delete it")
	void tc01_deleteTask() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the task name
		String task = "Smile!!!";
		//Add a task
		tp.addSimpleTask(task);
		//Delete the task
		tp.deleteTask(task);
		//Check that the task not exist
		Assert.assertFalse(tp.ifTheTaskExists(task));
	}
	
	@Feature("Delete task")
	@Story("As a User I want to delete all completed tasks")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Clear all completed tasks - TC11")
	@Description ("Configure tasks as a completed tasks and delete them")
	void tc02_clearAllCompletedTasks() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the tasks names
		String task1 = "Smile!!";
		String task2 = "Laugh!!";
		//Add a tasks
		tp.addSimpleTask(task1);
		tp.addSimpleTask(task2);
		//hide completed tasks
		tp.hideCompletedTasks();
		//Check the tasks check-box
		tp.checkTaskAsCompletedTask(task1);
		tp.checkTaskAsCompletedTask(task2);
		//Show completed tasks
		tp.showCompletedTasks();
		//Clear all completed tasks
		tp.clearAllCompletedTasks();
		//Check that the tasks not exists
		Assert.assertFalse(tp.ifTheTaskExists(task1));
		Assert.assertFalse(tp.ifTheTaskExists(task2));
	}
	
	@Feature("Check task")
	@Story("As a User I want check completed task")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Check a task as a completed task - TC12")
	@Description ("Configure task as a completed task")
	void tc03_CheckATaskAsACompletedTask() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the task name
		String task = "Smile!";
		//Add a task
		tp.addSimpleTask(task);
		//Check the task check-box
		tp.checkTaskAsCompletedTask(task);
		//Show completed tasks
		tp.showCompletedTasks();
		//Check if the task marked as a completed task
		Assert.assertTrue(tp.ifTheTaskCompleted(task));
	}
	
	@Feature("Check task")
	@Story("As a User I want the option to uncheck checked task")
	@Severity(SeverityLevel.NORMAL)
	@Test (description = "Uncheck a task as a completed task - TC13")
	@Description ("Configure task as a completed task and Uncheck it back")
	void tc04_UncheckATaskAsACompletedTask() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the task name
		String task = "Smile:)";
		//Add a task
		tp.addSimpleTask(task);
		//Check the task check-box
		tp.checkTaskAsCompletedTask(task);
		//Show completed tasks
		tp.showCompletedTasks();
		//Check the task check-box again
		tp.uncheckTaskAsCompletedTask(task);
		//Check if the task marked as a uncompleted task
		Assert.assertTrue(tp.ifTheTaskUncompleted(task));
	}
	
	@Feature("Edit task")
	@Story("As a User I want the option to edit all task fields")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Edit all task fields - TC14")
	@Description ("Add a simple task and edit all his fields")
	void tc05_editAllTaskFields() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the task name
		String task = "Smille";
		//Add a task
		tp.addSimpleTask(task);
		//Click on edit task action
		tp.editAllTaskFields(task);
		//Configure of the page
		etp = new EditTaskPage(driver);
		//Edit all fields and save
		etp.fillAllFieldsAndSave("0", "11/18/2020", "To smile", "Smile every two seconds", "smile");
		//Configure of the page
		tp = new TasksPage(driver);
		//Check if task and note fields edited
		Assert.assertTrue(tp.ifTheTaskExists("To smile"));
		tp.showNotes();
		Assert.assertTrue(tp.ifNoteEdited("To smile", "Smile every two seconds"));
	}

	@Feature("Edit task")
	@Story("As a User I want the option to edit just the task note")
	@Severity(SeverityLevel.TRIVIAL)
	@Test (description = "Edit just the note of the task - TC15")
	@Description ("Add a simple task and edit just his note")
	void tc06_editJustTheNoteOfTheTask() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the task name
		String task = "Smillle";
		//Configure of the task note
		String note = "Smile every two seconds";
		//Add new task
		tp.addSimpleTask(task);
		//Edit task note
		tp.editJustTheNoteOfTheTask(task, note);
		//Check if task note field edited
		Assert.assertTrue(tp.ifNoteEdited(task, note));
	}
	
	@Feature("Edit task")
	@Story("As a User I want the option to edit the priority from icon")
	@Severity(SeverityLevel.TRIVIAL)
	@Test (description = "Update the priority from priority icon - TC16")
	@Description ("Add an advanced task and update his priority from priority icon")
	void tc07_updateThePriorityFromPriorityIcon() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Click on add advanced task button
		tp.addAdvancedTask();
		//Configure of the task name
		String task = "Smilee";
		//Configure of the page
		natp = new NewAdvancedTaskPage(driver);
		//Fill all task fields and save
		natp.fillAllFieldsAndSave("1", "18/11/2020", task, "Smile every two seconds", "smile");
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the new priority
		String priority = "+2";
		//Update task priority from priority icon
		tp.updateThePriorityFromPriorityIcon(task, priority);
		//Check if task priority updated 
		Assert.assertTrue(tp.ifThePriorityUpdated(task, priority));
	}

	@Feature("Edit task")
	@Story("As a User I want the option to edit the priority from the action button")
	@Severity(SeverityLevel.TRIVIAL)
	@Test (description = "Update the priority from action button - TC17")
	@Description ("Add an advanced task and update his priority from action button")
	void tc08_updateThePriorityFromActionButton() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Click on add advanced task button
		tp.addAdvancedTask();
		//Configure of the task name
		String task = "Smileee";
		//Configure of the page
		natp = new NewAdvancedTaskPage(driver);
		//Fill all task fields and save
		natp.fillAllFieldsAndSave("1", "18/11/2020", task, "Smile every two seconds", "smile");
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the new priority
		String priority = "2";
		//Update task priority from action button
		tp.updateThePriorityFromActionButton(task, priority);
		//Check if task priority updated 
		Assert.assertTrue(tp.ifThePriorityUpdated(task, priority));
		AllureAttachment.attachText("Laughed out loud!!!");
	}
}
