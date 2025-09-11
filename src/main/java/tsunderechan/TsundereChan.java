package tsunderechan;

import java.io.FileNotFoundException;

import tsunderechan.command.Command;
import tsunderechan.exception.InsufficientInformationException;
import tsunderechan.parser.Parser;
import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a chatbot application.
 */
public class TsundereChan {
    private TaskList tasks;
    private final Storage storage;
    private Ui ui;
    private String commandType;

    /**
     * Instantiates a TsundereChan object, loading from the specified filePath.
     * If no save file found in filePath, start from clean slate.
     *
     * @param filePath Path to find the save file to load from.
     */
    public TsundereChan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (IllegalArgumentException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command, ui);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input, ui);
            commandType = c.getClass().getSimpleName();
            return c.execute(tasks, ui, storage);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a String with the specified command type
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Returns a String showing the welcome message
     */
    public static String showWelcome() {
        return Ui.showWelcome();
    }
}
