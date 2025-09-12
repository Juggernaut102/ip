package tsunderechan.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tsunderechan.task.Deadline;
import tsunderechan.task.Event;
import tsunderechan.task.Task;
import tsunderechan.task.TaskList;
import tsunderechan.task.Todo;


/**
 * Represents a Storage object, that allows for saving and loading of files.
 */
public class Storage {
    private String filePath;

    /**
     * Instantiates a Storage object.
     *
     * @param filePath Filepath to follow to find the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the user's list of tasks as a file in the hard drive.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void save(TaskList tasks) {
        File file = new File(filePath);
        try {
            // Check if parent folder exists
            file.getParentFile().mkdirs();

            try (FileWriter fw = new FileWriter(filePath)) {
                List<String> strings = tasksToString(tasks);
                assert strings != null : "strings must not be null";
                for (String str : strings) {
                    fw.write(str + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Ehehe... There's an IO Exception... Teehee!");
        }
    }

    /**
     * Converts tasks to a list of strings and returns the list.
     *
     * @param tasks The tasks to be converted into a list of strings.
     * @return List of strings to be saved to a file.
     */
    public List<String> tasksToString(TaskList tasks) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            StringBuilder str = new StringBuilder(task.getIcon());
            if (task.isDone()) {
                str.append(" | 1 | ");
            } else {
                str.append(" | 0 | ");
            }
            str.append(task.getDescription()).append(task.getTiming());
            strings.add(str.toString());
        }
        return strings;
    }

    /**
     * Returns an ArrayList of tasks to be loaded into the instance.
     *
     * @return ArrayList of tasks to be loaded.
     * @throws FileNotFoundException If file is not found in filePath.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            stringToTasks(input, tasks);
        }
        return tasks;
    }

    /**
     * Converts strings of tasks into an ArrayList of tasks.
     *
     * @param input The strings of the tasks to be converted into task objects.
     * @param tasks The ArrayList of tasks to be modified by inserting the new input tasks.
     * @throws IllegalArgumentException If the save data is corrupted.
     */
    public void stringToTasks(String input, ArrayList<Task> tasks) {
        String[] parts = input.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        switch (parts[0]) {
        case "T":
            if (parts[1].equals("0")) {
                tasks.add(new Todo(parts[2], false));
            } else {
                tasks.add(new Todo(parts[2], true));
            }
            break;
        case "D":
            if (parts[1].equals("0")) {
                tasks.add(new Deadline(parts[2], parts[3], false));
            } else {
                tasks.add(new Deadline(parts[2], parts[3], true));
            }
            break;
        case "E":
            if (parts[1].equals("0")) {
                tasks.add(new Event(parts[2], parts[3], parts[4], false));
            } else {
                tasks.add(new Event(parts[2], parts[3], parts[4], true));
            }
            break;
        default:
            throw new IllegalArgumentException();
        }
    }
}
