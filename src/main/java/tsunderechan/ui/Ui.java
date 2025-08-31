package tsunderechan.ui;

import java.util.Scanner;

import tsunderechan.exception.InsufficientInformationException;
import tsunderechan.task.Task;
import tsunderechan.task.TaskList;

/**
 * Represents an object that reads user input and outputs things to the screen.
 */
public class Ui {
    public static final String LINE = "***************************************";
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints welcome statement when application begins.
     */
    public void showWelcome() {
        System.out.println(LINE
                + "\nHmph! I'm Tsundere-chan.\nWhat do you want? If you ask nicely, I just MIGHT help you...\n"
                + LINE);
    }

    /**
     * Prints goodbye statement when application exits.
     */
    public void showGoodbye() {
        System.out.println(LINE + "\nHmph, done already? D-don't talk to me anymore, you idiot!\n"
                + "It's not l-like I enjoyed talking to you or anything...\n"
                + LINE);
    }

    /**
     * Prints a line separator.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints a save corrupted message.
     */
    public void showLoadingError() {
        System.out.println("O-oops! The save data has been corrupted...\n"
                + "I guess I owe you one, so let me off this time, okay?");
    }

    /**
     * Prints a prompt to the user to tell them proper input format.
     *
     * @throws IllegalArgumentException All the time.
     */
    public void showMarkError() throws IllegalArgumentException {
        throw new IllegalArgumentException(LINE + "\nYou must have a task number after mark!\n" + LINE);
    }

    /**
     * Prints a prompt to the user when marking an already marked task.
     */
    public void showAlreadyMarkedError() {
        System.out.println(LINE + "\nYou've already asked me to mark it, geez!\n" + LINE);
    }

    /**
     * Prints out the newly marked task.
     *
     * @param task Task to be printed out.
     */
    public void showMarkTask(Task task) {
        System.out.println(LINE);
        System.out.println("W-well, it seems even you can get something done, I guess... "
                + "N-not like I'm impressed or anything!");
        System.out.println("    " + task);
        System.out.println(LINE);
    }

    /**
     * Prints a prompt to the user to tell them proper input format.
     *
     * @throws IllegalArgumentException All the time.
     */
    public void showUnmarkError() throws IllegalArgumentException {
        throw new IllegalArgumentException(LINE + "\nYou must have a task number after unmark!\n" + LINE);
    }

    /**
     * Prints a prompt to the user when unmarking an already unmarked task.
     */
    public void showAlreadyUnmarkedError() {
        System.out.println(LINE + "\nIt's not even done yet, geeez!\n" + LINE);
    }

    /**
     * Prints out the newly unmarked task.
     *
     * @param task Task to be printed out.
     */
    public void showUnmarkTask(Task task) {
        System.out.println(LINE);
        System.out.println("And here I was, expecting something from you... Why do I feel disappointed?");
        System.out.println("    " + task);
        System.out.println(LINE);
    }

    /**
     * Throws an IllegalArgumentException when marking a task that is not in the TaskList.
     */
    public void showInvalidTaskError() throws IllegalArgumentException {
        throw new IllegalArgumentException(LINE + "\nThat's not a valid task, you iiiiidiot!\n" + LINE);
    }

    /**
     * Prints a prompt to the user to tell them proper input format.
     *
     * @throws IllegalArgumentException All the time.
     */
    public void showDeleteError() throws IllegalArgumentException {
        throw new IllegalArgumentException(LINE + "\nYou must have a task number after delete!\n" + LINE);
    }

    /**
     * Throws an IllegalArgumentException when attempting to get a task out of bounds of the TaskList.
     */
    public void showInvalidIndexError() throws IllegalArgumentException {
        throw new IllegalArgumentException(LINE + "\nIndex is out of bounds, dummy!\n" + LINE);
    }

    /**
     * Prints out the newly deleted task at specified index.
     *
     * @param task Task to be printed out.
     * @param index Index of Task to be deleted.
     */
    public void showDeleteTask(Task task, int index) {
        System.out.println(LINE + "\nHmph, fine. I'll remove this task, so you better be thankful.");
        System.out.println("    " + task);
        System.out.println("Now you only have " + index + " tasks in your list. "
                + "U-um, it's not like I care or anything but, you can probably take a little break now, right?");
        System.out.println(LINE);
    }

    /**
     * Prints a prompt to the user to tell them proper input format.
     *
     * @throws InsufficientInformationException All the time.
     */
    public void showInsufficientInformationError(String task) throws InsufficientInformationException {
        throw new InsufficientInformationException("You gotta tell me the description of the " + task
                + " at least, or I can't help you, doofus!");
    }

    /**
     * Prints a prompt to the user to tell them proper input format.
     *
     * @throws InsufficientInformationException All the time.
     */
    public void showDeadlineInvalidFormatError() throws InsufficientInformationException {
        throw new InsufficientInformationException("You need to include /by, dummy!");
    }

    /**
     * Prints a prompt to the user to tell them proper input format.
     *
     * @throws InsufficientInformationException All the time.
     */
    public void showEventInvalidFormatError() throws InsufficientInformationException {
        throw new InsufficientInformationException("You need to include both /from and /to, IN THAT ORDER, dummy!");
    }

    /**
     * Prints a prompt to the user to tell them proper input format.
     *
     * @throws InsufficientInformationException All the time.
     */
    public void showNoKeywordError() throws InsufficientInformationException {
        throw new InsufficientInformationException("You need to include a keyword, "
                + "or I have no idea what you're talking about, doofus!");
    }

    /**
     * Prints a prompt to the user to tell them proper input format for time and dates.
     */
    public static void showDateTimeFormatError(String context) {
        System.out.println(LINE + "\nI'll only say this once so listen up!\n"
                + "If you want the " + context + " section to be understood as a date and time, "
                + "you got to write it as yyyy-MM-dd HH:mm, got it?");
    }

    /**
     * Prints out the newly added task.
     *
     * @param task Task to be printed out.
     * @param index Index of task added.
     */
    public void showAddTask(Task task, int index) {
        System.out.println(LINE + "\nW-well, I guess I can help you just this once. "
                + "B-but don't expect this everytime, got it?!");
        System.out.println("    " + task);
        System.out.println("Now you have " + index + " tasks in your list. Better get to work!");
        System.out.println(LINE);
    }

    /**
     * Prints a prompt to the user when listing an empty list.
     *
     * @throws IllegalArgumentException All the time.
     */
    public void showNoTaskToListError() throws IllegalArgumentException {
        throw new IllegalArgumentException(LINE + "\nWhat?? There's nothing to list, you idiot!\n" + LINE);
    }

    /**
     * Prints out the list of tasks.
     *
     * @param tasks Tasks to be printed out.
     * @param index Number of tasks in the list.
     */
    public void printList(TaskList tasks, int index) {
        if (index == 0) {
            this.showNoTaskToListError();
        }
        this.showLine();
        for (int i = 0; i < index; i++) {
            String output = String.format("%d.%s", i + 1, tasks.getTask(i));
            System.out.println(output);
        }
        this.showLine();
    }

    /**
     * Returns a string that user has inputted.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Closes scanner.
     */
    public void closeScanner() {
        sc.close();
    }
}
