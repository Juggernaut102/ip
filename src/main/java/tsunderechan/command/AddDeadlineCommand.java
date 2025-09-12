package tsunderechan.command;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

/**
 * Represents a command to add a deadline task when executed.
 */
public class AddDeadlineCommand extends AddCommand {
    private String by;

    /**
     * Instantiates an AddDeadlineCommand object.
     *
     * @param description Description of the Deadline Task.
     * @param by The time the Deadline Task should be completed by.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String s = tasks.addDeadlineTask(description, by);
        storage.save(tasks);
        return s;
    }
}
