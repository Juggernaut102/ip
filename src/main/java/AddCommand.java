public abstract class AddCommand extends Command {
    protected String description;

    public boolean isExit() {
        return false;
    }
}
