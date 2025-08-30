public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks, tasks.getSize());
    }

    public boolean isExit() {
        return false;
    }
}
