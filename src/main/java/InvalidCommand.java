public class InvalidCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showNoKeywordError();
    }

    public boolean isExit() {
        return false;
    }
}
