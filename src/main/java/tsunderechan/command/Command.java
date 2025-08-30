package tsunderechan.command;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
