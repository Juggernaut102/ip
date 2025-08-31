package tsunderechan.command;

import java.util.List;

import tsunderechan.storage.Storage;
import tsunderechan.task.Task;
import tsunderechan.task.TaskList;
import tsunderechan.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.find(keyword);
        if (matches.isEmpty()) {
            ui.showNoMatchFound();
            return;
        }
        ui.showFindResults(matches);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}