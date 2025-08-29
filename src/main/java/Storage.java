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
    private Scanner sc;

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
            str.append(task.description).append(" | ").append(task.getTiming());
            strings.add(str.toString());
        }
        return strings;
    }
    /*
    public ArrayList<Task> load() {
        return
    }
    */
}
