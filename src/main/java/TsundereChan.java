import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TsundereChan {
    private TaskList tasks;
    private final Storage storage = new Storage("./data/TsundereChan.txt");
    private Ui ui;

    public TsundereChan() {
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (IllegalArgumentException e) {
            ui.showLoadingError();
            tasks = new TaskList();
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
                ui.printList(tasks, tasks.getSize());
                break;
            case "bye":
                break;
            case "mark":
                if (!sc.hasNextInt()) {
                    ui.showMarkError();
                }
                index = sc.nextInt();
                tasks.mark(index);
                storage.save(tasks);
                break;
            case "unmark":
                if (!sc.hasNextInt()) {
                    ui.showUnmarkError();
                }
                index = sc.nextInt();
                tasks.unmark(index);
                storage.save(tasks);
                break;
            case "delete":
                if (!sc.hasNextInt()) {
                    ui.showDeleteError();
                }
                index = sc.nextInt();
                tasks.delete(index);
                storage.save(tasks);
                break;
            default:
                tasks.addTask(string);
                storage.save(tasks);
        }
        sc.close();
    }

    public static void main(String[] args) {
        TsundereChan tsundereChan = new TsundereChan();
        tsundereChan.run();
    }
}
