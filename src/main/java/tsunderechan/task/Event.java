package tsunderechan.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task. It has a description, and a timing from x to y.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDateTime dateTimeFrom;
    protected LocalDateTime dateTimeTo;

    /**
     * Instantiates an Event object.
     *
     * @param description Description of the Event Task.
     * @param from The time the Event Task will start.
     * @param to The time the Event Task will last until.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            dateTimeFrom = LocalDateTime.parse(from, inputFormatter);
            this.from = dateTimeFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
            dateTimeTo = LocalDateTime.parse(to, inputFormatter);
            this.to = dateTimeTo.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            // Warn user that format is wrong, but otherwise does nothing
            // Ui.showDateTimeFormatError("from and to");
        }
    }

    /**
     * Instantiates an Event object.
     * This overloaded method allows for manually setting the isDone field.
     *
     * @param description Description of the Event Task.
     * @param from The time the Event Task will start.
     * @param to The time the Event Task will last until.
     * @param isDone Whether the task has already been completed.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            dateTimeFrom = LocalDateTime.parse(from, inputFormatter);
            this.from = dateTimeFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
            dateTimeTo = LocalDateTime.parse(to, inputFormatter);
            this.to = dateTimeTo.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            // Warn user that format is wrong, but otherwise does nothing
            // Ui.showDateTimeFormatError("from and to");
        }
    }

    @Override
    public String getIcon() {
        return "E";
    }

    @Override
    public String getTiming() {
        return " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
