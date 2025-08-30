import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TsundereChan {
    private ArrayList<Task> list = new ArrayList<>();
    private int pointer = 0;
    private final Storage storage = new Storage("./data/TsundereChan.txt");
    private Ui ui;

    public TsundereChan() {
        ui = new Ui();
        try {
            list = storage.load();
            pointer = list.size();
        } catch (FileNotFoundException e) {
            // Do nothing
        } catch (IllegalArgumentException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String command;
        while (true) {
            try {
                command = sc.nextLine();
                if (command.equals("bye")) {
                    break;
                }
                this.action(command);
            } catch (InsufficientInformationException e) {
                System.out.println(e);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        ui.showGoodbye();
    }

    public void action(String string) {
        Scanner sc = new Scanner(string);
        String command = sc.next();
        int index;
        switch (command) {
            case "list":
                ui.printList(list, pointer);
                break;
            case "bye":
                break;
            case "mark":
                if (!sc.hasNextInt()) {
                    ui.showMarkError();
                }
                index = sc.nextInt();
                this.mark(index);
                storage.save(list);
                break;
            case "unmark":
                if (!sc.hasNextInt()) {
                    ui.showUnmarkError();
                }
                index = sc.nextInt();
                this.unmark(index);
                storage.save(list);
                break;
            case "delete":
                if (!sc.hasNextInt()) {
                    ui.showDeleteError();
                }
                index = sc.nextInt();
                this.delete(index);
                storage.save(list);
                break;
            default:
                this.addTask(string);
                storage.save(list);
        }
        sc.close();
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
                list.add(new Todo(str));
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
                list.add(t);
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
                list.add(t2);
                break;
            default:
               ui.showNoKeywordError();
        }
        sc.close();
        pointer++;
        ui.showAddTask(list.get(pointer-1), pointer);
    }

    public void mark(int index) {
        if (index < 1 ||  index > pointer) {
            ui.showInvalidTaskError();
        }
        Task task = list.get(index-1);
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
        Task task = list.get(index-1);
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
        Task task = list.remove(index-1);
        pointer--;
        ui.showDeleteTask(task, pointer);
    }

    public static void main(String[] args) {
        TsundereChan tsundereChan = new TsundereChan();
        tsundereChan.run();
    }
}
