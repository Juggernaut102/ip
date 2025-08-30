package tsunderechan.command;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

public class AddDeadlineCommand extends AddCommand {
    private String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addDeadlineTask(description, by);
    }
}
