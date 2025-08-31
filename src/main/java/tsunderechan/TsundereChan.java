package tsunderechan;

import java.io.FileNotFoundException;

import tsunderechan.command.Command;
import tsunderechan.exception.InsufficientInformationException;
import tsunderechan.parser.Parser;
import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

public class TsundereChan {
    private TaskList tasks;
    private final Storage storage;
    private Ui ui;

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
}
