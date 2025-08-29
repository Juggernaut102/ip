public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public abstract String getIcon();

    public abstract String getTiming();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
