package tsunderechan.command;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a command to unmark a task when executed.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Instantiates an UnmarkCommand object.
     *
     * @param index Index of the Task to be unmarked as incomplete.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String s = tasks.unmark(index);
        storage.save(tasks);
        return s;
    }
}
