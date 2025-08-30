import java.util.ArrayList;
import java.util.Scanner;

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

    public void addTask(String command) {
        Scanner sc = new Scanner(command);
        String task = sc.next();
        String str;
        switch (task) {
        case "todo":
            if (!sc.hasNextLine()) {
                ui.showInsufficientInformationError(task);
            }
            str = sc.nextLine().trim();
            tasks.add(new Todo(str));
            break;
        case "deadline":
            if (!sc.hasNextLine()) {
                ui.showInsufficientInformationError(task);
            }
            str = sc.nextLine().trim();
            String[] deadline = str.split("/by", 2);
            if (deadline.length < 2) {
                ui.showDeadlineInvalidFormatError();
            }
            Task t = new Deadline(deadline[0].trim(), deadline[1].trim());
            tasks.add(t);
            break;
        case "event":
            if (!sc.hasNextLine()) {
                ui.showInsufficientInformationError(task);
            }
            str = sc.nextLine().trim();
            String[] event = str.split("/from|/to", 3);
            if (event.length < 3) {
                ui.showEventInvalidFormatError();
            }
            Task t2 = new Event(event[0].trim(), event[1].trim(), event[2].trim());
            tasks.add(t2);
            break;
        default:
            ui.showNoKeywordError();
        }
        sc.close();
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
