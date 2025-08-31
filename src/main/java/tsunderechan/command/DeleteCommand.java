package tsunderechan.command;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a command to delete a task when executed.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Instantiates a DeleteCommand object.
     *
     * @param index Index of the Task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(index);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
