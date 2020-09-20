package duke.task;

/**
 * Represents an event. An <code>Event</code> object corresponds to
 * a task occurring on a specific date.
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        descriptor = "[E]";
        this.setDate(LocalDate.parse(at));
    }

    /**
     * Returns the descriptor of the event: [E]
     *
     * @return Descriptor of event.
     */
    @Override
    public String getDescriptor() {
        return descriptor;
    }

    /**
     * Returns the string format of the event. Used to save into files.
     *
     * @return String format of event.
     */
    @Override
    public String toString() {
        return descriptor + " | " + (isDone ? "1 | " : "0 | ") + description + " | " + date;
    }

    /**
     * Prints the event in the format:
     * <code>[E][status] description (at: date)</code>
     */
    @Override
    public void printTask() {
        System.out.print("  " + descriptor);
        System.out.print(getStatusIcon() + " " + description);
        System.out.println(" (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}
