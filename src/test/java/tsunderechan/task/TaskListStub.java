package tsunderechan.task;

/**
 * Represents a stub of a TaskList for better unit testing.
 */
public class TaskListStub extends TaskList {

    /**
     * Returns a TaskList containing a Todo task.
     */
    public TaskList getTodoTask() {
        tasks.add(new Todo("homework"));
        pointer++;
        return this;
    }

    /**
     * Returns a TaskList containing a deadline task.
     */
    public TaskList getDeadlineTask() {
        tasks.add(new Deadline("homework", "tomorrow"));
        pointer++;
        return this;
    }

    /**
     * Returns a TaskList containing an event task.
     */
    public TaskList getEventTask() {
        tasks.add(new Event("CCA", "5pm", "9pm"));
        pointer++;
        return this;
    }

    /**
     * Returns a TaskList containing 1 of each task.
     */
    public TaskList getMultipleTask() {
        tasks.add(new Event("CCA", "5pm", "9pm"));
        tasks.add(new Deadline("homework", "tomorrow"));
        tasks.add(new Todo("homework"));
        pointer += 3;
        return this;
    }

    /**
     * Returns a TaskList containing 1 of each task, all marked as completed.
     */
    public TaskList getMarkedTask() {
        tasks.add(new Event("CCA", "5pm", "9pm", true));
        tasks.add(new Deadline("homework", "tomorrow", true));
        tasks.add(new Todo("homework", true));
        pointer += 3;
        return this;
    }
}
