// Read a file
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Write to a file
import java.io.FileWriter;
import java.io.IOException;

// Copy a file or delete a file
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> tasks) {
        File file = new File(filePath);
        try {
            // Check if parent folder exists
            file.getParentFile().mkdirs();

            try (FileWriter fw = new FileWriter(filePath)) {
                List<String> strings = tasksToString(tasks);
                for (String str : strings) {
                    fw.write(str + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }
    }

    public List<String> tasksToString(ArrayList<Task> tasks) {
        List<String> strings = new ArrayList<>();
        for (Task task : tasks) {
            StringBuilder str = new StringBuilder(task.getIcon());
            if (task.isDone) {
                str.append(" | 1 | ");
            } else {
                str.append(" | 0 | ");
            }
            str.append(task.description).append(task.getTiming());
            strings.add(str.toString());
        }
        return strings;
    }

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
