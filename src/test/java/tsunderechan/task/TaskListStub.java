package tsunderechan.task;

public class TaskListStub extends TaskList {
    public TaskList getTodoTask() {
        tasks.add(new Todo("homework"));
        pointer++;
        return this;
    }

    public TaskList getDeadlineTask() {
        tasks.add(new Deadline("homework", "tomorrow"));
        pointer++;
        return this;
    }

    public TaskList getEventTask() {
        tasks.add(new Event("CCA", "5pm", "9pm"));
        pointer++;
        return this;
    }

    public TaskList getMultipleTask() {
        tasks.add(new Event("CCA", "5pm", "9pm"));
        tasks.add(new Deadline("homework", "tomorrow"));
        tasks.add(new Todo("homework"));
        pointer += 3;
        return this;
    }

    public TaskList getMarkedTask() {
        tasks.add(new Event("CCA", "5pm", "9pm", true));
        tasks.add(new Deadline("homework", "tomorrow", true));
        tasks.add(new Todo("homework", true));
        pointer += 3;
        return this;
    }
}
