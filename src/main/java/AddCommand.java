public class AddCommand extends Command {
    private String string;

    public AddCommand(String string) {
        this.string = string;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(string);
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
