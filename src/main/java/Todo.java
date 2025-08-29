public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getIcon() {
        return "T";
    }

    @Override
    public String getTiming() {
        return "";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
