package tsunderechan.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task. It has a description, and a timing to be completed by.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime dateTime;

    /**
     * Instantiates a Deadline object.
     *
     * @param description Description of the Deadline Task.
     * @param by The time the Deadline Task should be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            dateTime = LocalDateTime.parse(by, inputFormatter);
            this.by = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            // Warn user that format is wrong, but otherwise does nothing
            // Ui.showDateTimeFormatError("by");
        }
    }

    /**
     * Instantiates a Deadline object.
     * This overloaded method allows for manually setting the isDone field.
     *
     * @param description Description of the Deadline Task.
     * @param by The time the Deadline Task should be completed by.
     * @param isDone Whether the task has already been completed.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            dateTime = LocalDateTime.parse(by, inputFormatter);
            this.by = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            // Warn user that format is wrong, but otherwise does nothing
            // Ui.showDateTimeFormatError("by");
        }
    }

    @Override
    public String getIcon() {
        return "D";
    }

    @Override
    public String getTiming() {
        return " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
