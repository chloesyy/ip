package duke.task;

/**
 * Represents a deadline. A <code>Deadline</code> object corresponds to
 * a task with a date as the deadline.
 */
public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.DESCRIPTOR = "[D]";
    }

    /**
     * Returns the descriptor of the deadline: [D]
     *
     * @return Descriptor of deadline.
     */
    @Override
    public String getDescriptor() {
        return DESCRIPTOR;
    }

    /**
     * Returns a string format of the deadline. Used to save into files.
     *
     * @return String format of deadline.
     */
    @Override
    public String toString() {
        return DESCRIPTOR + " | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.by;
    }

    /**
     * Prints the deadline in the format:
     * <code>[D][status] description (by: deadline)</code>
     */
    @Override
    public void printTask() {
        System.out.print("  " + DESCRIPTOR);
        System.out.print(this.getStatusIcon() + " " + this.description);
        System.out.println(" (by: " + this.by + ")");
    }
}
