package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class TasksPage extends BaseElements{

	//Elements
	@FindBy (css = "#task")
	private WebElement newTaskField;
	@FindBy (css = "#newtask_submit")
	private WebElement submitButton;
	@FindBy (css = "#newtask_adv>span")
	private WebElement addAdvancedTaskButton;
	@FindBy (css = "#total")
	private WebElement totalTasks;
	@FindBy (css = "#cmenu_delete")
	private WebElement deleteBtn;
	@FindBy (css = ".mtt-tab.mtt-tabs-selected .list-action")
	private WebElement selectedListAction;
	@FindBy (css = "#btnShowCompleted")
	private WebElement showCompletedTasksBtn;
	@FindBy (css = "#btnClearCompleted")
	private WebElement clearCompletedTasksBtn;
	@FindBy (css = "#cmenu_edit")
	private WebElement editBtn;
	@FindBy (css = "#cmenu_note")
	private WebElement editNoteBtn;
	@FindBy (css = "#cmenu_prio")
	private WebElement priorityListOption;
	@FindBy (css = ".mtt-tabs-add-button span")
	private WebElement newListBtn;
	@FindBy (css = "#cmenu_move")
	private WebElement moveToListOption;
	@FindBy (css = "#mtt-notes-show")
	private WebElement showBtn;
	@FindBy (css = "#mtt-notes-hide")
	private WebElement hideBtn;
	@FindBy (css = "#settings")
	private WebElement settingsBtn;
	@FindBy (css = "#search")
	private WebElement searchField;
	@FindBy (css = "#tagcloudbtn")
	private WebElement tagsBtn;
	@FindBy (css = "#btnDeleteList")
	private WebElement deleteListBtn;
	@FindBy (css = "#btnRenameList")
	private WebElement renameListBtn;
	@FindBy (css = "#btnHideList")
	private WebElement hideListBtn;
	@FindBy (css = ".mtt-tabs-select-button.mtt-img-button>span")
	private WebElement menuListsBtn;
	@FindBy (css = "h2")
	private WebElement pageTitle;

	//Elements Lists
	@FindBy (css = ".task-title")
	private List<WebElement> tasksNameList;
	@FindBy (css = ".taskactionbtn")
	private List<WebElement> tasksActionBtn;
	@FindBy (css = "[type='checkbox']")
	private List<WebElement> tasksCheckBox;
	@FindBy (css = ".task-row.task-completed .task-title")
	private List<WebElement> completedTasksTitle;
	@FindBy (css = ".task-row")
	private List<WebElement> tasksList;
	@FindBy (css = ".task-note-area>textarea")
	private List<WebElement> tasksNoteFieldList;
	@FindBy (css = ".mtt-action-note-save")
	private List<WebElement> saveNoteBtn;
	@FindBy (css = ".task-note>span")
	private List<WebElement> noteList;
	@FindBy (css = ".task-prio")
	private List<WebElement> tasksPriorityIcon;
	@FindBy (css = "#priopopup>span")
	private List<WebElement> priorityPopUpOptions;
	@FindBy (css = ".mtt-tab span")
	private List<WebElement> listsName;
	@FindBy (css = "#cmenulistscontainer li")
	private List<WebElement> MoveTo_listsList;
	@FindBy (css = ".task-toggle")
	private List<WebElement> tasksToggle;
	@FindBy (css = "#tagcloudcontent>a")
	private List<WebElement> tagsSearchList;
	@FindBy (css = ".task-tags>a")
	private List<WebElement> tagsList;
	@FindBy (css = ".list-action")
	private List<WebElement> listActionBtn;
	@FindBy (css = ".mtt-tab")
	private List<WebElement> listsStatus;
	@FindBy (css = "[id^='slmenu_list']")
	private List<WebElement> listsStatusAtTheMenuListsBtn;
	@FindBy (css = "#slmenucontainer a")
	private List<WebElement> listsNameAtTheMenuListsBtn;
	@FindBy (css = ".duedate")
	private List<WebElement> dueDateList;

	//Constructor
	public TasksPage(WebDriver driver) {
		super(driver);
	}

	//Methods
	@Step("Add simple task named '{taskName}'")
	public void addSimpleTask(String taskName) {
		//Fill task name field
		fillText(newTaskField, taskName);
		//Click on submit button
		click(submitButton);
		//Waiting to creating new task
		waitingForLoading();
	}

	@Step("Click on submit button of add simple task")
	public void addEmptySimpleTask() {
		//Click on submit button
		click(submitButton);
	}

	@Step("Click on add Advanced task button")
	public void addAdvancedTask() {
		//Click on add advanced task button
		click(addAdvancedTaskButton);
	}

	@Step("Delete the task: '{task}'")
	public void deleteTask(String task) {
		//Run on tasks list
		for (int i = 0; i < tasksNameList.size(); i++) {
			//Find specific task
			if (getText(tasksNameList.get(i)).equals(task)) {
				//Enable to open actions list
				moveMouseTo(tasksNameList.get(i));
				//Open actions list
				click(tasksActionBtn.get(i));
				//Click on delete option
				click(deleteBtn);
				//Accept the deleted alert
				alertOK();
				//Waiting to task deletion
				sleep(2000);
			}
		}
	}

	@Step("Check the task '{task}' as a completed task")
	public void checkTaskAsCompletedTask(String task) {
		//Run on tasks list
		for (int i = 0; i < tasksNameList.size(); i++) {
			//Find specific task
			if (getText(tasksNameList.get(i)).equals(task)) {
				//Check task check-box
				click(tasksCheckBox.get(i));
				//Waiting for task check as completed task
				waitingForLoading();
				break;
			}
		}
	}

	@Step("Uncheck the task '{task}' as a completed task")
	public void uncheckTaskAsCompletedTask(String task) {
		//Run on tasks list
		for (int i = 0; i < tasksNameList.size(); i++) {
			//Find specific task
			if (getText(tasksNameList.get(i)).equals(task)) {
				//Uncheck task check-box
				click(tasksCheckBox.get(i));
				//Waiting for task uncheck as completed task
				waitingForLoading();
				break;
			}
		}
	}

	@Step("Hide completed tasks")
	public void hideCompletedTasks() {
		//Open list actions list
		click(selectedListAction);
		//Check if show completed task option checked already
		if (showCompletedTasksBtn.getAttribute("class").contains("mtt-item-checked")) {
			//Click on show completed task option
			click(showCompletedTasksBtn);
			//Waiting to hidden completed task
			waitingForLoading();	
		}
	}

	@Step("Show completed tasks")
	public void showCompletedTasks() {
		//Open list actions list
		click(selectedListAction);
		//Check if show completed task option not checked
		if (!showCompletedTasksBtn.getAttribute("class").contains("mtt-item-checked")) {
			//Click on show completed task option
			click(showCompletedTasksBtn);
			//Waiting to showing completed task
			waitingForLoading();	
		}
	}

	@Step("Clear all completed tasks")
	public void clearAllCompletedTasks() {
		//Open list actions list
		click(selectedListAction);
		//Click on clear completed tasks button
		click(clearCompletedTasksBtn);
		//Accept the clear alert
		alertOK();
		//Waiting to clear all completed tasks
		waitingForLoading();
	}

	@Step("Click on Edit action of task '{task}'")
	public void editAllTaskFields(String task) {
		//Run on tasks list
		for (int i = 0; i < tasksNameList.size(); i++) {
			//Find specific task
			if (getText(tasksNameList.get(i)).equals(task)) {
				//Enabled task action button
				moveMouseTo(tasksNameList.get(i));
				//Click on task action button
				click(tasksActionBtn.get(i));
				//Click on edit action
				click(editBtn);
			}
		}
	}

	@Step("Edit the note of task '{task}' with the text: '{note}'")
	public void editJustTheNoteOfTheTask(String task, String note) {
		//Run on tasks list
		for (int i = 0; i < tasksNameList.size(); i++) {
			//Find specific task
			if (getText(tasksNameList.get(i)).equals(task)) {
				//Enabled task action button
				moveMouseTo(tasksNameList.get(i));
				//Click on task action button
				click(tasksActionBtn.get(i));
				//Click on edit note action
				click(editNoteBtn);
				//Fill the note field
				fillText(tasksNoteFieldList.get(i), note);
				//Save the note
				click(saveNoteBtn.get(i));
				//Waiting to saving note
				waitingForLoading();
			}
		}
	}

	@Step("Update the priority of task '{task}' from priority icon to priority={priority}")
	public void updateThePriorityFromPriorityIcon(String task, String priority) {
		//Run on tasks list
		for (int i = 0; i < tasksNameList.size(); i++) {
			//Find specific task
			if (getText(tasksNameList.get(i)).equals(task)) {
				//Enabled to choose priority
				moveMouseTo(tasksPriorityIcon.get(i));
				//Run on priority options
				for (int j = 0; j < priorityPopUpOptions.size(); j++) {
					//Find the new priority
					if (priorityPopUpOptions.get(j).getText().equals(priority)) {
						//Choose the new priority
						click(priorityPopUpOptions.get(j));
						//Waiting for priority update
						waitingForLoading();
						break;
					}
				}
			}
		}
	}

	@Step("Update the priority of task '{task}' from Action bsutton to priority={priority}")
	public void updateThePriorityFromActionButton(String task, String priority) {
		//Run on tasks list
		for (int i = 0; i < tasksNameList.size(); i++) {
			//Find specific task
			if (getText(tasksNameList.get(i)).equals(task)) {
				//Enabled task action button
				moveMouseTo(tasksNameList.get(i));
				//Click on task action button
				click(tasksActionBtn.get(i));
				//Enabled to choose priority
				moveMouseTo(priorityListOption);
				sleep(400);
				//Choose the new priority
				click(driver.findElement(By.xpath("//*[@id='cmenu_prio:" + priority + "']")));
				//Waiting for priority update
				waitingForLoading();
			}
		}
	}

	@Step("Add new list named '{0}'")
	public void addNewList(String listName) {
		//Click on new list button
		click(newListBtn);
		//Fill the alert field and accept it
		alertOKWithText(listName);
		//Waiting to created the new list
		waitingForLoading();
	}

	@Step("Move the task '{task}' to the list '{list}'")
	public void moveTaskToAnotherList(String task, String list) {
		//Run on tasks list
		for (int i = 0; i < tasksNameList.size(); i++) {
			//Find specific task
			if (getText(tasksNameList.get(i)).equals(task)) {
				//Enabled task action button
				moveMouseTo(tasksNameList.get(i));
				//Click on task action button
				click(tasksActionBtn.get(i));
				//Enabled select a list to will transfer the task
				moveMouseTo(moveToListOption);
				sleep(500);
				//Run on lists list
				for (WebElement el : MoveTo_listsList) {
					//Find specific list
					if (getText(el).equals(list)) {
						//Click on the list to transfer the task
						click(el);
						break;
					}
				}
				//Waiting until the task transfered
				waitingForLoading();
			}
		}
	}

	@Step("Click on Show button that show all the notes")
	public void showNotes() {
		//Click on show notes button
		click(showBtn);
		//Waiting to all notes shows
		sleep(500);
	}

	@Step("Click on Hide button that hide all the notes")
	public void hideNotes() {
		//Click on hide notes button
		click(hideBtn);
		//Waiting to all notes hidden
		sleep(500);
	}

	@Step("Click on the toggle of task '{task}'")
	public void clickOnTaskToggle(String task) {
		//Run on the tasks list
		for (int i = 0; i < tasksNameList.size(); i++) {
			//Find specific task
			if (getText(tasksNameList.get(i)).equals(task)) {
				//Click on task toggle
				click(tasksToggle.get(i));
			}
		}
	}

	@Step("Click on Settings button")
	public void openSettingsPage() {
		//Click on settings button
		click(settingsBtn);
		//Waiting the settings page load
		waitingForLoading();
	}

	@Step("Fill the search field with the text: '{textToSearch}'")
	public void searchFor(String textToSearch) {
		//Fill the search field with text
		fillText(searchField, textToSearch);
		//Waiting the search results
		waitingForLoading();
	}

	@Step("Click on the tag 'tag' in the tags list")
	public void searchForTag(String tag) {
		//Open the tags list
		click(tagsBtn);
		//Waiting the tags list load
		waitingForLoading();
		//Run on tags list
		for (WebElement el : tagsSearchList) {
			//Find specific tag
			if (getText(el).toLowerCase().equals(tag)) {
				//Click on the tag
				click(el);
			}
		}
		//Waiting the search results
		waitingForLoading();
	}

	@Step("Delete the list '{listName}'")
	public void deleteList(String listName) {
		//Run on lists list
		for (int i = 0; i < listsName.size(); i++) {
			//Find specific list
			if (getText(listsName.get(i)).equals(listName)) {
				//Open the list
				click(listsName.get(i));
				//Waiting the list open
				waitingForLoading();
				//Open the list actions
				click(listActionBtn.get(i));
				//Click on delete list action
				click(deleteListBtn);
				//Accept the deletion of the list
				alertOK();
				//Waiting to delete the list
				waitingForLoading();
				break;
			}
		}
	}

	@Step("Rename the list '{listName}' to '{renameTo}'")
	public void renameList(String listName, String renameTo) {
		//Run on lists list
		for (int i = 0; i < listsName.size(); i++) {
			//Find specific list
			if (getText(listsName.get(i)).equals(listName)) {
				//Open the list
				click(listsName.get(i));
				//Waiting the list open
				waitingForLoading();
				sleep(500);
				//Open the list actions
				click(listActionBtn.get(i));
				//Click on rename list action
				click(renameListBtn);
				//Fill the alert field and accept it
				alertOKWithText(renameTo);
				//Waiting to rename the list 
				waitingForLoading();
				break;
			}
		}
	}

	@Step("Hide the list '{listName}'")
	public void hideList(String listName) {
		//Run on lists list
		for (int i = 0; i < listsName.size(); i++) {
			//Find specific list
			if (getText(listsName.get(i)).equals(listName)) {
				//Open the list
				click(listsName.get(i));
				//Waiting the list open
				waitingForLoading();
				//Open the list actions
				click(listActionBtn.get(i));
				//Click on hide list action
				click(hideListBtn);
				//Waiting to hide the list
				waitingForLoading();
				break;
			}
		}
	}

	@Step("Open the hidden list '{listName}'")
	public void openAHiddenList(String listName) {
		//Check if select list button already clicked
		if (!(menuListsBtn.getAttribute("class").contains("mtt-menu-button-active"))) {
			//Click on select list button
			click(menuListsBtn);
		}
		//Run on lists in select list button
		for (int i = 0; i < listsNameAtTheMenuListsBtn.size(); i++) {
			//Find specific list
			if (getText(listsNameAtTheMenuListsBtn.get(i)).contains(listName)) {
				//Click on the list to open it
				click(listsNameAtTheMenuListsBtn.get(i));
			}
		}
	}

	@Step("Open the the list '{listName}'")
	public void openList(String listName) {
		//Run on lists list
		for (WebElement el : listsName) {
			//Find specific list
			if (getText(el).equals(listName)) {
				//Open the list
				click(el);
				break;
			}
		}
		//Waiting the list open
		waitingForLoading();
	}

	//Validation
	@Step("Check if the task '{taskName}' is exist")
	public boolean ifTheTaskExists(String taskName) {
		//Run on tasks list
		for (WebElement el : tasksNameList) {
			//Find specific task
			if (getText(el).equals(taskName)) {
				//If the task exist
				return true;
			}
		}
		//If the task not Exist
		return false;
	}

	@Step("Check if the task '{taskName}' is exist in the list '{list}'")
	public boolean ifTheTaskExistsInAnotherList(String taskName, String list) {
		//Run on lists list
		for (WebElement el : listsName) {
			//Find specific list
			if (getText(el).equals(list)) {
				//Open the list
				click(el);
				break;
			}
		}
		//Waiting the list open
		waitingForLoading();
		//Run on tasks list
		for (WebElement el : tasksNameList) {
			//Find specific task
			if (getText(el).equals(taskName)) {
				//If the task exist
				return true;
			}
		}
		//If the task not exist
		return false;
	}

	@Step("Check if the task '{taskName}' is checked as a completed task")
	public boolean ifTheTaskCompleted(String taskName) {
		//Run on completed tasks list
		for (WebElement el : completedTasksTitle) {
			//Find specific task
			if (getText(el).equals(taskName)) {
				//If the task exist on completed tasks list
				return true;
			}
		}
		//If the task not exist on completed tasks list
		return false;
	}

	@Step("Check if the task '{taskName}' is uncompleted task")
	public boolean ifTheTaskUncompleted(String taskName) {
		//Hide completed task so that they do not appear on the tasks list
		hideCompletedTasks();
		//Run on tasks list
		for (WebElement el : tasksNameList) {
			//Find specific task
			if (getText(el).equals(taskName)) {
				//If the task uncompleted
				return true;
			}
		}
		//If the task completed
		return false;
	}

	@Step("Check the tasks number")
	public int totslTasks() {
		//Get the tasks number and change it from String to integer
		int i=Integer.parseInt(getText(totalTasks));
		sleep(500);
		return i;
	}

	@Step("Check if the Note of task '{task}' edited with the text:'{note}'")
	public boolean ifNoteEdited(String task, String note) {
		//Run on tasks list
		for (int i = 0; i < tasksNameList.size(); i++) {
			//Find specific task
			if (getText(tasksNameList.get(i)).equals(task)) {
				//Go to task note
				if (getText(noteList.get(i)).equals(note)) {
					//If the note edited
					return true;
				}
			}
		}
		//If the note don't edited
		return false;
	}

	@Step("Check if the priority of task '{task}' updated to priority={priority}")
	public boolean ifThePriorityUpdated(String task,String priority) {
		//Run on tasks list
		for (int i = 0; i < tasksNameList.size(); i++) {
			//Find specific task
			if (getText(tasksNameList.get(i)).equals(task)) {
				//Go to task priority
				if (getText(tasksPriorityIcon.get(i)).contains(priority)) {
					//If the priority updated
					return true;
				}
			}
		}
		//If the priority don't updated
		return false;
	}

	@Step("Check if all the notes are shows")
	public boolean ifAllTheNotesAreShows() {
		//Run on tasks list
		for (int i = 0; i < tasksList.size(); i++) {
			//Check if the note don't null
			if (!(getText(noteList.get(i)).equals(""))) {
				//Check if the note hidden
				if (!(tasksList.get(i).getAttribute("class").contains("task-expanded"))) {
					//If the note hidden
					return false;
				}
			}
		}
		//If all the notes shows
		return true;
	}

	@Step("Check if all the notes are hidden")
	public boolean ifAllTheNotesAreHidden() {
		//Run on tasks list
		for (int i = 0; i < tasksList.size(); i++) {
			//Check if the note show
			if (tasksList.get(i).getAttribute("class").contains("task-expanded")) {
				//If the note show
				return true;
			}
		}
		//If all the notes hidden
		return false;
	}

	@Step("Check if the note of task '{task}' is show")
	public boolean ifSingleTaskNoteShow(String task) {
		//Run on tasks list
		for (int i = 0; i < tasksNameList.size(); i++) {
			//Find specific task
			if (getText(tasksNameList.get(i)).equals(task)) {
				//Check if the note show
				if (tasksList.get(i).getAttribute("class").contains("task-expanded")) {
					//If the note show
					return true;
				}
			}
		}
		//If the note hide
		return false;
	}

	@Step("Check if the search with the string '{search}' succeeded")
	public boolean ifTheSearchSucceeded(String search) {
		//Show all notes
		click(showBtn);
		//Run on tasks list
		for (int i = 0; i < tasksNameList.size(); i++) {
			//Check the task name
			if (!(getText(tasksNameList.get(i)).toLowerCase().contains(search))) {
				//Check the task note
				if (!(getText(noteList.get(i)).toLowerCase().contains(search))) {
					//If the name and the note don't contains the search letters
					return false;
				}
			}
		}
		//If all the names and the notes contains the search letters
		return true;
	}

	@Step("Check if the search with the tag '{tag}' succeeded")
	public boolean ifTheTagSearchSucceeded(String tag) {
		//Run on tags list
		for (int i = 0; i < tagsList.size(); i++) {
			//Check if the tag equal to the search tag
			if (!(getText(tagsList.get(i)).toLowerCase().equals(tag))) {
				//If the tag isn't equal to the search tag
				return false;
			}
		}
		//If all the tags are equal to the search tag
		return true;
	}

	@Step("Check if the list '{listName}' exist")
	public boolean ifTheListExsit(String listName) {
		//Run on lists list
		for (WebElement el : listsName) {
			//Check if the list name is equal to the new list name
			if (getText(el).equals(listName)) {
				//If the list name is equal to the new list name
				return true;
			}
		}
		//If all the lists names are not equal to the new list name
		return false;
	}

	@Step("Check the lists number")
	public int ListsSize() {
		//Return the lists number
		return listsName.size();
	}

	@Step("Check if the list '{listName}' hidden")
	public boolean ifTheListHidden(String listName) {
		//Open the select list menu (because the hidden lists shows there) 
		click(menuListsBtn);
		//Run on the lists list on the select menu
		for (int i = 0; i < listsNameAtTheMenuListsBtn.size(); i++) {
			//Find specific list name
			if (getText(listsNameAtTheMenuListsBtn.get(i)).equals(listName)) {
				//Check if the list hidden
				if (listsStatusAtTheMenuListsBtn.get(i).getAttribute("class").contains("mtt-list-hidden")) {
					//If the list is hidden
					return true;
				}
			}
		}
		//if the list isn't hidden
		return false;
	}

	@Step("Check if the hidden list '{listName}' opened")
	public boolean ifTheHiddenListOpened(String listName) {
		//Run on the lists names
		for (int i = 0; i < listsName.size(); i++) {
			//Find specific list
			if (getText(listsName.get(i)).equals(listName)) {
				//Check if the list is open
				if (listsStatus.get(i).getAttribute("class").contains("selected")) {
					//If the list is open
					return true;
				}
			}
		}
		//If the list isn't open
		return false;
	}

	@Step("Check if myTinyToDo title changed to '{title}'")
	public boolean ifTitleChanged(String title) {
		//Check if the title changed
		if (getText(pageTitle).equals(title)) {
			//if the title changed
			return true;
		}
		//if the title not changed
		return false;
	}
}
