package duke.task;

/**
 * Represents a deadline. A <code>Deadline</code> object corresponds to
 * a task with a date as the deadline.
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        descriptor = "[D]";
        this.setDate(LocalDate.parse(by));
    }

    /**
     * Returns the descriptor of the deadline: [D]
     *
     * @return Descriptor of deadline.
     */
    @Override
    public String getDescriptor() {
        return descriptor;
    }

    /**
     * Returns a string format of the deadline. Used to save into files.
     *
     * @return String format of deadline.
     */
    @Override
    public String toString() {
        return descriptor + " | " + (isDone ? "1 | " : "0 | ") + description + " | " + date;
    }

    /**
     * Prints the deadline in the format:
     * <code>[D][status] description (by: deadline)</code>
     */
    @Override
    public void printTask() {
        System.out.print("  " + descriptor);
        System.out.print(getStatusIcon() + " " + description);
        System.out.println(" (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}
