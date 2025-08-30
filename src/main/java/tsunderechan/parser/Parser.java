package tsunderechan.parser;

import java.util.Scanner;

import tsunderechan.command.AddDeadlineCommand;
import tsunderechan.command.AddEventCommand;
import tsunderechan.command.AddTodoCommand;
import tsunderechan.command.Command;
import tsunderechan.command.DeleteCommand;
import tsunderechan.command.ExitCommand;
import tsunderechan.command.InvalidCommand;
import tsunderechan.command.ListCommand;
import tsunderechan.command.MarkCommand;
import tsunderechan.command.UnmarkCommand;
import tsunderechan.ui.Ui;


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
        case "todo":
            if (!sc.hasNextLine()) {
                ui.showInsufficientInformationError(command);
            }
            String str = sc.nextLine().trim();
            sc.close();
            return new AddTodoCommand(str);
        case "deadline":
            if (!sc.hasNextLine()) {
                ui.showInsufficientInformationError(command);
            }
            str = sc.nextLine().trim();
            String[] deadline = str.split("/by", 2);
            if (deadline.length < 2) {
                ui.showDeadlineInvalidFormatError();
            }
            sc.close();
            return new AddDeadlineCommand(deadline[0].trim(), deadline[1].trim());
        case "event":
            if (!sc.hasNextLine()) {
                ui.showInsufficientInformationError(command);
            }
            str = sc.nextLine().trim();
            String[] event = str.split("/from|/to", 3);
            if (event.length < 3) {
                ui.showEventInvalidFormatError();
            }
            sc.close();
            return new AddEventCommand(event[0].trim(), event[1].trim(), event[2].trim());
        default:
            sc.close();
            return new InvalidCommand();
        }
    }
}
