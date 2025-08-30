package tsunderechan.task;

import tsunderechan.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int pointer;
    private Ui ui;

    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui();
        pointer = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        ui = new Ui();
        pointer = tasks.size();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
    
    public void addTodoTask(String description) {
        tasks.add(new Todo(description));
        pointer++;
        ui.showAddTask(tasks.get(pointer-1), pointer);
    }

    public void addDeadlineTask(String description, String by) {
        tasks.add(new Deadline(description, by));
        pointer++;
        ui.showAddTask(tasks.get(pointer-1), pointer);
    }

    public void addEventTask(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
        pointer++;
        ui.showAddTask(tasks.get(pointer-1), pointer);
    }

    public void mark(int index) {
        if (index < 1 ||  index > pointer) {
            ui.showInvalidTaskError();
        }
        Task task = tasks.get(index-1);
        if (task.isDone) {
            ui.showAlreadyMarkedError();
            return;
        }
        task.mark();
        ui.showMarkTask(task);
    }

    public void unmark(int index) {
        if (index < 1 ||  index > pointer) {
            ui.showInvalidTaskError();
        }
        Task task = tasks.get(index-1);
        if (!task.isDone) {
            ui.showAlreadyUnmarkedError();
            return;
        }
        task.unmark();
        ui.showUnmarkTask(task);
    }

    public void delete(int index) {
        if (index < 1 ||  index > pointer) {
            ui.showInvalidTaskError();
        }
        Task task = tasks.remove(index-1);
        pointer--;
        ui.showDeleteTask(task, pointer);
    }
}
