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
                Command c = this.action(command);
                c.execute(tasks, ui, storage);
            } catch (InsufficientInformationException e) {
                System.out.println(e);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        ui.showGoodbye();
    }

    public Command action(String string) {
        Scanner sc = new Scanner(string);
        String command = sc.next();
        int index;
        switch (command) {
            case "list":
                sc.close();
                return new ListCommand();
            case "bye":
                sc.close();
                return new ExitCommand();
            case "mark":
                if (!sc.hasNextInt()) {
                    ui.showMarkError();
                }
                index = sc.nextInt();
                sc.close();
                return new MarkCommand(index);
            case "unmark":
                if (!sc.hasNextInt()) {
                    ui.showUnmarkError();
                }
                index = sc.nextInt();
                sc.close();
                return new UnmarkCommand(index);
            case "delete":
                if (!sc.hasNextInt()) {
                    ui.showDeleteError();
                }
                index = sc.nextInt();
                sc.close();
                return new DeleteCommand(index);
            default:
                sc.close();
                return new AddCommand(string);
        }
    }

    public static void main(String[] args) {
        TsundereChan tsundereChan = new TsundereChan();
        tsundereChan.run();
    }
}
