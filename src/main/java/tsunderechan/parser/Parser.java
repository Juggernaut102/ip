package tsunderechan.parser;

import java.util.Scanner;

import tsunderechan.command.AddDeadlineCommand;
import tsunderechan.command.AddEventCommand;
import tsunderechan.command.AddTodoCommand;
import tsunderechan.command.Command;
import tsunderechan.command.DeleteCommand;
import tsunderechan.command.ExitCommand;
import tsunderechan.command.EmptyCommand;
import tsunderechan.command.FindCommand;
import tsunderechan.command.InvalidCommand;
import tsunderechan.command.ListCommand;
import tsunderechan.command.MarkCommand;
import tsunderechan.command.UnmarkCommand;
import tsunderechan.ui.Ui;

/**
 * Represents an object that can read and make sense of user input.
 */
public class Parser {
    /**
     * Returns a Command parsed from user input.
     * If the position is unset, NaN is returned.
     *
     * @param fullCommand The entire line typed by the user.
     * @param ui Ui used to read input and print output.
     * @return Command to be executed.
     * @throws IllegalArgumentException If input does not follow specified requirements.
     */
    public static Command parse(String fullCommand, Ui ui) {
        assert fullCommand != null : "fullCommand should not be null";
        Scanner sc = new Scanner(fullCommand.trim());

        if (!sc.hasNext()) {
            sc.close();
            return new EmptyCommand();
        }

        String command = sc.next();

        switch (command) {
        case "list":
            sc.close();
            return new ListCommand();
        case "bye":
            sc.close();
            return new ExitCommand();
        case "mark":
            return createMarkCommand(ui, sc);
        case "unmark":
            return createUnmarkCommand(ui, sc);
        case "delete":
            return createDeleteCommand(ui, sc);
        case "todo":
            return createTodoCommand(ui, sc, command);
        case "deadline":
            return createDeadlineCommand(ui, sc, command);
        case "event":
            return createEventCommand(ui, sc, command);
        case "find":
            return createFindCommand(ui, sc);
        default:
            sc.close();
            return new InvalidCommand();
        }
    }

    private static Command createMarkCommand(Ui ui, Scanner sc) {
        if (!sc.hasNextInt()) {
            ui.showMarkError();
        }
        int index = sc.nextInt();
        sc.close();
        return new MarkCommand(index);
    }

    private static Command createUnmarkCommand(Ui ui, Scanner sc) {
        if (!sc.hasNextInt()) {
            ui.showUnmarkError();
        }
        int index = sc.nextInt();
        sc.close();
        return new UnmarkCommand(index);
    }

    private static Command createDeleteCommand(Ui ui, Scanner sc) {
        if (!sc.hasNextInt()) {
            ui.showDeleteError();
        }
        int index = sc.nextInt();
        sc.close();
        return new DeleteCommand(index);
    }

    private static Command createTodoCommand(Ui ui, Scanner sc, String command) {
        if (!sc.hasNextLine()) {
            ui.showInsufficientInformationError(command);
        }
        String str = sc.nextLine().trim();
        sc.close();
        return new AddTodoCommand(str);
    }

    private static Command createDeadlineCommand(Ui ui, Scanner sc, String command) {
        if (!sc.hasNextLine()) {
            ui.showInsufficientInformationError(command);
        }
        String str = sc.nextLine().trim();
        String[] deadline = str.split("/by", 2);
        if (deadline.length < 2) {
            ui.showDeadlineInvalidFormatError();
        }
        sc.close();
        return new AddDeadlineCommand(deadline[0].trim(), deadline[1].trim());
    }

    private static Command createEventCommand(Ui ui, Scanner sc, String command) {
        if (!sc.hasNextLine()) {
            ui.showInsufficientInformationError(command);
        }
        String str = sc.nextLine().trim();
        String[] event = str.split("/from|/to", 3);
        if (event.length < 3) {
            ui.showEventInvalidFormatError();
        }
        sc.close();
        return new AddEventCommand(event[0].trim(), event[1].trim(), event[2].trim());
    }

    private static Command createFindCommand(Ui ui, Scanner sc) {
        if (!sc.hasNextLine()) {
            ui.showNoKeywordDuringFind();
        }
        String str = sc.nextLine().trim();
        sc.close();
        return new FindCommand(str);
    }
}