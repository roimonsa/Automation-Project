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
import pageobjects.TasksPage;
import utils.AllureAttachment;

@Epic("Search options")

public class SearchTasksTest extends BaseTest{

	//Objects
	private TasksPage tp;
	private NewAdvancedTaskPage natp;

	@Feature("Search options")
	@Story("As a User I want to look for a task by its name")
	@Severity(SeverityLevel.NORMAL)
	@Test (description = "Search for tasks by task title - TC25")
	@Description ("Add two advanced tasks and search one of them by its task title")
	void tc01_searchForTasksByTaskTitle() {
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
		//Configure of the task to search
		String taskToSearch = "smile";
		//Enter the task name to search field
		tp.searchFor(taskToSearch);
		//Verify just tasks with these word appears
		Assert.assertTrue(tp.ifTheSearchSucceeded(taskToSearch));
	}

	@Feature("Search options")
	@Story("As a User I want to look for a task by its note")
	@Severity(SeverityLevel.MINOR)
	@Test (description = "Search for tasks by note - TC26")
	@Description ("Search for task by its note")
	void tc02_searchForTasksByTaskNote() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the note to search
		String noteToSearch = "smile every two seconds";
		//Enter the note to search field
		tp.searchFor(noteToSearch);
		//Verify just tasks with all of these words appears
		Assert.assertTrue(tp.ifTheSearchSucceeded(noteToSearch));	
	}
	
	@Feature("Search options")
	@Story("As a User I want to look for a task by single word from its name or note")
	@Severity(SeverityLevel.MINOR)
	@Test (description = "Search for tasks by single word - TC27")
	@Description ("Search for tasks by single word from their task or note")
	void tc03_searchForTasksBySingleWord() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the word to search
		String search = "every";
		//Enter the word to search field
		tp.searchFor(search);
		//Verify just tasks with these word appears
		Assert.assertTrue(tp.ifTheSearchSucceeded(search));	
	}
	
	@Feature("Search options")
	@Story("As a User I want to look for a task by single letter from its name or note")
	@Severity(SeverityLevel.TRIVIAL)
	@Test (description = "Search for tasks by single letter - TC28")
	@Description ("Search for tasks by single letter from their task or note")
	void tc04_searchForTasksBySingleLetter() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the letter to search
		String search = "e";
		//Enter the letter to search field
		tp.searchFor(search);
		//Verify just tasks with these letter appears
		Assert.assertTrue(tp.ifTheSearchSucceeded(search));	
	}

	@Feature("Tags list")
	@Story("The system will collect all the tags under list and support to look for a task by its tag")
	@Severity(SeverityLevel.NORMAL)
	@Test (description = "Search for tasks by tag - TC29")
	@Description ("Search for tasks by tag from tags list")
	void tc05_searchForTasksByTagFromTagsList() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the tag to search
		String tag = "smile";
		//Click on the tag on the tags list search
		tp.searchForTag(tag);
		//Verify just tasks with these tag appears
		Assert.assertTrue(tp.ifTheTagSearchSucceeded(tag));
		AllureAttachment.attachText("Hacol letova!!!");
	}
}

