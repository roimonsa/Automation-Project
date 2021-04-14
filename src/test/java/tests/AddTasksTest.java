package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
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

@Epic("Add Task")
public class AddTasksTest extends BaseTest{

	//Objects
	private TasksPage tp;
	private NewAdvancedTaskPage natp;

	@Feature("Add Simple Task")
	@Story("As a User I want to add a task just with its name")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Add a simple task - TC01")
	@Description ("Add a new simple task named Smile")
	void tc01_addSimpleTask() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Add simple task
		tp.addSimpleTask("Smile");
		//Check if the new task exist
		Assert.assertTrue(tp.ifTheTaskExists("Smile"));
	}

	@Feature("Add Simple Task")
	@Story("The system will avoid creation of simple tasks without task name")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Try to Add a simple task without task title - TC02")
	@Description ("Check that it is impossible to add a simple task without task title")
	void tc02_addEmptySimpleTask() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Save the number of the tasks before the task is added 
		int x = tp.totslTasks();
		//Try to add task without name
		tp.addEmptySimpleTask();
		//Take the number of the tasks after the trying to add the task 
		int y =  tp.totslTasks();
		//Check the tasks numbers is equals 
		Assert.assertEquals(y, x);
	}

	//TC03, TC04, TC05 - Data Driven Testing
	@Feature("Add Advanced Task")
	@Story("As a User I want to add a task with all of fields, and my priority values is between -1 to 2")
	@Severity(SeverityLevel.BLOCKER)
	@Test (dataProvider="getData", description="Add an advanced task")
	@Description ("Add an advanced task with another prioriry each time using Data Driven Testing")
	void tc03_addAdvancedTaskTC03_04_05(String priority,String due,String task,String note,String tags) {
		//Click on add advanced task button
		tp.addAdvancedTask();
		//Configure of the page
		natp = new NewAdvancedTaskPage(driver);
		//Fill all the fields and save
		natp.fillAllFieldsAndSave(priority,due,task,note,tags);
		//Configure of the page
		tp = new TasksPage(driver);
		//Check if the new task exist
		Assert.assertTrue(tp.ifTheTaskExists(task));
	}

	
	@DataProvider //Method that fills tasks fields
	public Object[][] getData(){
		//Configure of large object
		Object[][] myData = {
				//Add an advanced task with all fields - TC03
				{"0","18/11/2020","To smile","Smile every two seconds","smile"},
				//Add an advanced task with priority = -1 - TC04
				{"-1","18/11/2020","To smile","Smile every two seconds","smile"},
				//Add an advanced task with priority = 2 - TC05
				{"2","18/11/2020","To smile","Smile every two seconds","smile"},
		};
		//Return the large object
		return myData;
	}

	@Feature("Add Advanced Task")
	@Story("The system will avoid enter priority > 2")
	@Severity(SeverityLevel.MINOR)
	@Test (description = "Enter impossible priority (= 3) - TC06")
	@Description ("Check that it is impossible to enter priority = 3")
	void tc04_enterImpossiblePriority() {
		//Configure of the page
		tp = new TasksPage(driver);
		//Click on add advanced task button
		tp.addAdvancedTask();
		//Configure of the page
		natp = new NewAdvancedTaskPage(driver);
		//Save the currently priority
		String expected = natp.actuallyPriority();
		//Try to enter priority not from priority options
		natp.enterImpossiblePriority("3");
		//Get the priority after the act
		String actual = natp.actuallyPriority();
		//Verify the priority not changed
		Assert.assertEquals(actual, expected);
	}

	@Feature("Add Advanced Task")
	@Story("The system will avoid enter priority < -1")
	@Severity(SeverityLevel.MINOR)
	@Test (description = "Enter impossible priority (= -2) - TC07")
	@Description ("Check that it is impossible to enter priority = -2")
	void tc05_enterImpossiblePriority() {
		//Configure of the page
		natp = new NewAdvancedTaskPage(driver);
		//Save the currently priority
		String expected = natp.actuallyPriority();
		//Try to enter priority not from priority options
		natp.enterImpossiblePriority("-2");
		//Get the priority after the act
		String actual = natp.actuallyPriority();
		//Verify the priority not changed
		Assert.assertEquals(actual, expected);
	}

	@Feature("Add Advanced Task")
	@Story("The system will avoid creation of advanced tasks without task name")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Add an advanced task without task title - TC08")
	@Description ("Check that it is impossible to add an advanced task without task title")
	void tc06_addAnAdvancedTaskWithoutTaskTitle() {
		//Configure of the page
		natp = new NewAdvancedTaskPage(driver);
		//Fill all the fields except from task title and save
		natp.fillAllFieldsAndSave("0","18/11/2020","","Smile every two seconds","smile");
		//Verify the task not saved
		Assert.assertEquals(natp.stillInTheSamePage(), "New Task");
	}

	@Feature("Add Advanced Task")
	@Story("The system will avoid creation of advanced tasks without all fields")
	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Add an advanced task without all the fields - TC09")
	@Description ("Check that it is impossible to add an advanced task without all the fields")
	void tc07_addAnAdvancedTaskWithoutAllTheFields() {
		//Configure of the page
		natp = new NewAdvancedTaskPage(driver);
		//Try to save task without all the fields
		natp.saveTaskWithoutAllFields();
		//Verify the task not saved
		Assert.assertEquals(natp.stillInTheSamePage(), "New Task");
		AllureAttachment.attachText("Keep smile on your face!!!");
	}
}
