package tsunderechan.ui;

import tsunderechan.exception.InsufficientInformationException;
import tsunderechan.task.Task;
import tsunderechan.task.TaskList;

import java.nio.channels.IllegalChannelGroupException;
import java.util.Scanner;

public class Ui {
    public static final String LINE = "***************************************";
    private final Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(LINE
                + "\nHmph! I'm Tsundere-chan.\nWhat do you want? If you ask nicely, I just MIGHT help you...\n"
                + LINE);
    }

    public void showGoodbye() {
        System.out.println(LINE + "\nHmph, done already? D-don't talk to me anymore, you idiot!\n" +
                "It's not l-like I enjoyed talking to you or anything...\n"
                + LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println("O-oops! The save data has been corrupted...\n"
                + "I guess I owe you one, so let me off this time, okay?");
    }

    public void showMarkError() throws IllegalArgumentException {
        throw new IllegalArgumentException(LINE + "\nYou must have a task number after mark!\n" + LINE);
    }

    public void showAlreadyMarkedError() {
        System.out.println(LINE + "\nYou've already asked me to mark it, geez!\n" + LINE);
    }

    public void showMarkTask(Task task) {
        System.out.println(LINE);
        System.out.println("W-well, it seems even you can get something done, I guess... N-not like I'm impressed or anything!");
        System.out.println("    " + task);
        System.out.println(LINE);
    }

    public void showUnmarkError() throws IllegalArgumentException {
        throw new IllegalArgumentException(LINE + "\nYou must have a task number after unmark!\n" + LINE);
    }

    public void showAlreadyUnmarkedError() {
        System.out.println(LINE + "\nIt's not even done yet, geeez!\n" + LINE);
    }

    public void showUnmarkTask(Task task) {
        System.out.println(LINE);
        System.out.println("And here I was, expecting something from you... Why do I feel disappointed?");
        System.out.println("    " + task);
        System.out.println(LINE);
    }

    public void showInvalidTaskError() throws IllegalChannelGroupException {
        throw new IllegalArgumentException(LINE + "\nThat's not a valid task, you iiiiidiot!\n" + LINE);
    }

    public void showDeleteError() throws IllegalArgumentException {
        throw new IllegalArgumentException(LINE + "\nYou must have a task number after delete!\n" + LINE);
    }

    public void showDeleteTask(Task task, int index) {
        System.out.println(LINE + "\nHmph, fine. I'll remove this task, so you better be thankful.");
        System.out.println("    " + task);
        System.out.println("Now you only have " + index + " tasks in your list. " +
                "U-um, it's not like I care or anything but, you can probably take a little break now, right?");
        System.out.println(LINE);
    }

    public void showInsufficientInformationError(String task) throws InsufficientInformationException {
        throw new InsufficientInformationException("You gotta tell me the description of the " + task
                + " at least, or I can't help you, doofus!");
    }

    public void showDeadlineInvalidFormatError() throws InsufficientInformationException {
        throw new InsufficientInformationException("You need to include /by, dummy!");
    }

    public void showEventInvalidFormatError() throws InsufficientInformationException {
        throw new InsufficientInformationException("You need to include both /from and /to, IN THAT ORDER, dummy!");
    }

    public void showNoKeywordError() throws InsufficientInformationException {
        throw new InsufficientInformationException("You need to include a keyword, " +
                "or I have no idea what you're talking about, doofus!");
    }

    public static void showDateTimeFormatError(String context) {
        System.out.println(LINE + "\nHey, I'll only say this once so listen up!\n"
                + "If you want the " + context + " section to be understood as a date and time, "
                + "you got to write it as yyyy-MM-dd HH:mm, got it?");
    }

    public void showAddTask(Task task, int index) {
        System.out.println(LINE + "\nW-well, I guess I can help you just this once. B-but don't expect this everytime, got it?!");
        System.out.println("    " + task);
        System.out.println("Now you have " + index + " tasks in your list. Better get to work!");
        System.out.println(LINE);
    }

    public void showNoTaskToListError() throws IllegalArgumentException {
        throw new IllegalArgumentException(LINE + "\nWhat?? There's nothing to list, you idiot!\n" + LINE);
    }

    public void printList(TaskList tasks, int index) {
        if (index == 0) {
            this.showNoTaskToListError();
        }
        this.showLine();
        for (int i = 0; i < index; i++) {
            String output = String.format("%d.%s", i+1, tasks.getTask(i));
            System.out.println(output);
        }
        this.showLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void closeScanner() {
        sc.close();
    }
}
