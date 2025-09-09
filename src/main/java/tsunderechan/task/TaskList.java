package tsunderechan.task;

import java.util.ArrayList;
import java.util.List;

import tsunderechan.ui.Ui;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    protected int pointer;
    private Ui ui;

    /**
     * Instantiates an empty TaskList object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui();
        pointer = 0;
    }

    /**
     * Instantiates a TaskList object with the specified tasks.
     *
     * @param tasks Tasks to be loaded into the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        ui = new Ui();
        pointer = tasks.size();
    }

    /**
     * Returns the size of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the Task at the specified index.
     *
     * @param index Index of Task to be returned.
     *
     * @throws IllegalArgumentException If index is negative or larger than size of list.
     */
    public Task getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            ui.showInvalidIndexError();
        }
        return tasks.get(index);
    }

    /**
     * Adds a Todo task to the TaskList.
     *
     * @param description Description of Todo task to be added.
     */
    public String addTodoTask(String description) {
        tasks.add(new Todo(description));
        pointer++;
        return ui.showAddTask(tasks.get(pointer - 1), pointer);
    }

    /**
     * Adds a deadline task to the TaskList.
     *
     * @param description Description of deadline task to be added.
     * @param by The time the Deadline Task should be completed by.
     */
    public String addDeadlineTask(String description, String by) {
        tasks.add(new Deadline(description, by));
        pointer++;
        return ui.showAddTask(tasks.get(pointer - 1), pointer);
    }

    /**
     * Adds an event task to the TaskList.
     *
     * @param description Description of deadline task to be added.
     * @param from The time the Event Task will start.
     * @param to The time the Event Task will last until.
     */
    public String addEventTask(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
        pointer++;
        return ui.showAddTask(tasks.get(pointer - 1), pointer);
    }

    /**
     * Marks task at the specified index as completed.
     *
     * @param index Index of the task being marked as completed.
     *
     * @throws IllegalArgumentException If index is negative or larger than current size of list.
     */
    public String mark(int index) {
        if (index < 1 || index > pointer) {
            ui.showInvalidTaskError();
        }
        Task task = tasks.get(index - 1);
        if (task.isDone) {
            return ui.showAlreadyMarkedError();
        }
        task.mark();
        return ui.showMarkTask(task);
    }

    /**
     * Marks task at the specified index as uncompleted.
     *
     * @param index Index of the task being marked as uncompleted.
     *
     * @throws IllegalArgumentException If index is negative or larger than current size of list.
     */
    public String unmark(int index) {
        if (index < 1 || index > pointer) {
            ui.showInvalidTaskError();
        }
        Task task = tasks.get(index - 1);
        if (!task.isDone) {
            return ui.showAlreadyUnmarkedError();
        }
        task.unmark();
        return ui.showUnmarkTask(task);
    }

    /**
     * Deletes task at the specified index.
     *
     * @param index Index of the task being deleted.
     *
     * @throws IllegalArgumentException If index is negative or larger than current size of list.
     */
    public String delete(int index) {
        if (index < 1 || index > pointer) {
            ui.showInvalidTaskError();
            return "";
        }
        Task task = tasks.remove(index - 1);
        pointer--;
        return ui.showDeleteTask(task, pointer);
    }

    /**
     * Returns tasks whose descriptions match the keyword.
     *
     * @param keyword Keyword to match with description of tasks..
     */
    public List<Task> find(String keyword) {
        List<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                results.add(task);
            }
        }
        return results;
    }
}
