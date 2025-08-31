package tsunderechan.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import tsunderechan.task.Deadline;
import tsunderechan.task.Event;
import tsunderechan.task.Task;
import tsunderechan.task.TaskListStub;
import tsunderechan.task.Todo;

public class StorageTest {
    @Test
    public void tasksToString_emptyTaskList_success() {
        // empty taskList should return empty ArrayList
        assertEquals(new ArrayList<String>(),
                new Storage("").tasksToString(new TaskListStub()));
    }

    @Test
    public void taskToString_todoTaskList_success() {
        // todo task should return in proper format
        List<String> expected = new ArrayList<>();
        expected.add("T | 0 | homework");
        assertEquals(expected,
                new Storage("").tasksToString(new TaskListStub().getTodoTask()));
    }

    @Test
    public void taskToString_deadlineTaskList_success() {
        // deadline task should return in proper format
        List<String> expected = new ArrayList<>();
        expected.add("D | 0 | homework | tomorrow");
        assertEquals(expected,
                new Storage("").tasksToString(new TaskListStub().getDeadlineTask()));
    }

    @Test
    public void taskToString_eventTaskList_success() {
        // event task should return in proper format
        List<String> expected = new ArrayList<>();
        expected.add("E | 0 | CCA | 5pm | 9pm");
        assertEquals(expected,
                new Storage("").tasksToString(new TaskListStub().getEventTask()));
    }

    @Test
    public void taskToString_multipleTaskList_success() {
        List<String> expected = new ArrayList<>();
        expected.add("E | 0 | CCA | 5pm | 9pm");
        expected.add("D | 0 | homework | tomorrow");
        expected.add("T | 0 | homework");
        assertEquals(expected,
                new Storage("").tasksToString(new TaskListStub().getMultipleTask()));
    }

    @Test
    public void taskToString_markedTaskList_success() {
        List<String> expected = new ArrayList<>();
        expected.add("E | 1 | CCA | 5pm | 9pm");
        expected.add("D | 1 | homework | tomorrow");
        expected.add("T | 1 | homework");
        assertEquals(expected,
                new Storage("").tasksToString(new TaskListStub().getMarkedTask()));
    }

    @Test
    public void stringToTasks_emptyString_exceptionThrown() {
        try {
            ArrayList<Task> expected = new ArrayList<>();
            ArrayList<Task> actual = new ArrayList<>();
            new Storage("").stringToTasks("", actual);
            assertEquals(expected, actual);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void stringToTasks_todoString_success() {
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Todo("homework"));

        ArrayList<Task> actual = new ArrayList<>();
        new Storage("").stringToTasks("T | 0 | homework", actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void stringToTasks_deadlineString_success() {
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Deadline("homework", "tomorrow"));

        ArrayList<Task> actual = new ArrayList<>();
        new Storage("").stringToTasks("D | 0 | homework | tomorrow", actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void stringToTasks_eventString_success() {
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Event("CCA", "5pm", "9pm"));

        ArrayList<Task> actual = new ArrayList<>();
        new Storage("").stringToTasks("E | 0 | CCA | 5pm | 9pm", actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void stringToTasks_multipleString_success() {
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Event("CCA", "5pm", "9pm"));
        expected.add(new Deadline("homework", "tomorrow"));
        expected.add(new Todo("homework"));

        ArrayList<Task> actual = new ArrayList<>();
        Storage storage = new Storage("");
        storage.stringToTasks("E | 0 | CCA | 5pm | 9pm", actual);
        storage.stringToTasks("D | 0 | homework | tomorrow", actual);
        storage.stringToTasks("T | 0 | homework", actual);
        assertEquals(expected.toString(), actual.toString());
    }
}
