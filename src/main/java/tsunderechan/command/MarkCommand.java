package tsunderechan.command;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a command to mark a task when executed.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Instantiates a MarkCommand object.
     *
     * @param index Index of the Task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(index);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
