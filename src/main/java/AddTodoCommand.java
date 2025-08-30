public class AddTodoCommand extends AddCommand {

    public AddTodoCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTodoTask(description);
    }
}
