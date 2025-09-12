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
        Scanner sc = new Scanner(fullCommand);
        if (!sc.hasNext()) {
            return new EmptyCommand();
        }
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
        case "find":
            if (!sc.hasNextLine()) {
                ui.showNoKeywordDuringFind();
            }
            str = sc.nextLine().trim();
            sc.close();
            return new FindCommand(str);
        default:
            sc.close();
            return new InvalidCommand();
        }
    }
}
