import java.util.Scanner;

public class Parser {
    public static Command parse(String fullCommand, Ui ui) {
        Scanner sc = new Scanner(fullCommand);
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
            return new AddCommand(fullCommand);
        }
    }
}
