package tsunderechan.command;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(index);
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
