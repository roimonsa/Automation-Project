package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobjects.SettingsPage;
import pageobjects.TasksPage;
import utils.AllureAttachment;
import utils.Utils;

@Epic("List & Page Title")
public class ListsAndRenameTitleTest extends BaseTest{

	//Objects
	private TasksPage tp;
	private SettingsPage sp;

	@Feature("List options")
	@Story("As a User I want to add a new list of tasks")
	@Severity(SeverityLevel.BLOCKER)
	@Test (description = "Add a list - TC30")
	@Description ("Add a new list with a list title")
	void tc01_addAListWithAListTitle() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the list name
		String listName = "Happiness!";
		//Add new list
		tp.addNewList(listName);
		//Check if the list created
		Assert.assertTrue(tp.ifTheListExsit(listName));
	}

	@Feature("List options")
	@Story("The system will avoid creation of list without list name")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Add a list without a list title - TC31")
	@Description ("Chek that it is impossible to add a new list without a list title")
	void tc02_addAListWithoutAListTitle() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the lists number before adding Untitled list
		int expected = tp.ListsSize();
		//Configure of the Untitled list name
		String listName = "";
		//Add new Untitled list
		tp.addNewList(listName);
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the lists number after adding Untitled list
		int actual = tp.ListsSize();
		//Check if the Untitled list not added
		Assert.assertEquals(actual, expected);
	}
	
	@Feature("List options")
	@Story("As a User I want to delete unused list")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Delete list - TC32")
	@Description ("Add a new list and deletes it")
	void tc03_deleteList() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the list name
		String listName = "Happiness!!";
		//Add new list
		tp.addNewList(listName);
		//Delete the new list
		tp.deleteList(listName);
		//Check if the list deleted
		Assert.assertFalse(tp.ifTheListExsit(listName));
	}

	@Feature("List options")
	@Story("As a User I want to rename list depending on its use")
	@Severity(SeverityLevel.NORMAL)
	@Test (description = "Rename list - TC33")
	@Description ("Add a new list and renames it")
	void tc04_renameList() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the list name
		String listName = "Happiness!!!";
		//Add new list
		tp.addNewList(listName);
		//Configure of the new list name
		String renameTo = "Joy";
		//Rename list to the new list name
		tp.renameList(listName, renameTo);
		//Check if the list renamed
		Assert.assertTrue(tp.ifTheListExsit(renameTo));
	}
	
	@Feature("List options")
	@Story("As a User I want to hide secret list")
	@Severity(SeverityLevel.MINOR)
	@Test (description = "Hide list - TC34")
	@Description ("Add a new list and hides it")
	void tc05_hideList() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the list name
		String listName = "Happiness:)";
		//Add new list
		tp.addNewList(listName);
		//Hide the list
		tp.hideList(listName);
		//Check if the list hidden
		Assert.assertTrue(tp.ifTheListHidden(listName));
	}
	
	@Feature("List options")
	@Story("As a User I want to open hidden list")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Open a hidden list - TC35")
	@Description ("Add a new list, hides it and than opens it")
	void tc06_openAHiddenList() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the list name
		String listName = "Happiness(:";
		//Add new list
		tp.addNewList(listName);
		//Hide the list
		tp.hideList(listName);
		//Open the list
		tp.openAHiddenList(listName);
		//Check if the hidden list open
		Assert.assertTrue(tp.ifTheHiddenListOpened(listName));
	}
	
	@Feature("List options")
	@Story("The system keep hide hidden list also if it opened")
	@Severity(SeverityLevel.MINOR)
	@Test (description = "A hidden list remains hidden after it is opened - TC36")
	@Description ("Check that a hidden list remains hidden after it is opened")
	void tc07_aHiddenListRemainsHiddenAfterItIsOpened() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the lists names
		String listName1 = "Happiness-1";
		String listName2 = "Joy!";
		//Add new lists
		tp.addNewList(listName1);
		tp.addNewList(listName2);
		//Hide the first list
		tp.hideList(listName1);
		//Open the first list
		tp.openAHiddenList(listName1);
		//Open the second list
		tp.openList(listName2);
		//Check if the first list still hidden
		Assert.assertTrue(tp.ifTheListHidden(listName1));
	}
	
	@Feature("Page Title")
	@Story("As a User I want to rename page title depending on its use")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Rename the title in top line - TC37")
	@Description ("Rename the title in top line of myTinyToDo site")
	void tc08_renameTheTitleInTopLine() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Configure of the new title name
		String title = Utils.readProperty("title");
		//Click on settings button
		tp.openSettingsPage();
		//Configure of the page
		sp = new SettingsPage(driver);
		//Fill the title field with the new name and save
		sp.changeTitle(title);
		//Configure of the page
		tp = new TasksPage(driver);
		//Check if the title renamed
		Assert.assertTrue(tp.ifTitleChanged(title));
		AllureAttachment.attachText("Say tanks on your smile!!!");
	}
}
