public class AddEventCommand extends AddCommand {
    private String from;
    private String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addEventTask(description, from, to);
    }
}
