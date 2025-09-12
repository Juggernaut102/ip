package tsunderechan.command;

import tsunderechan.storage.Storage;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a todo task when executed.
 */
public class AddTodoCommand extends AddCommand {

    /**
     * Instantiates an AddTodoCommand object.
     *
     * @param description Description of the Todo Task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String s = tasks.addTodoTask(description);
        storage.save(tasks);
        return s;
    }
}
