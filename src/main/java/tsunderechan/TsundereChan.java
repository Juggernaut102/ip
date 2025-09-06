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
            } catch (InsufficientInformationException e) {
                System.out.println(e);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        TsundereChan tsundereChan = new TsundereChan("data/TsundereChan.txt");
        tsundereChan.run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
