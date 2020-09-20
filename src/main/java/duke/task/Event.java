package duke.task;

/**
 * Represents an event. An <code>Event</code> object corresponds to
 * a task occurring on a specific date.
 */
public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.DESCRIPTOR = "[E]";
    }

    /**
     * Returns the descriptor of the event: [E]
     *
     * @return Descriptor of event.
     */
    @Override
    public String getDescriptor() {
        return DESCRIPTOR;
    }

    /**
     * Returns the string format of the event. Used to save into files.
     *
     * @return String format of event.
     */
    @Override
    public String toString() {
        return DESCRIPTOR + " | " + (this.isDone ? "1 | " : "0 | ") + this.description + this.at;
    }

    /**
     * Prints the event in the format:
     * <code>[E][status] description (at: date)</code>
     */
    @Override
    public void printTask() {
        System.out.print("  " + DESCRIPTOR);
        System.out.print(this.getStatusIcon() + " " + this.description);
        System.out.println(" (at: " + this.at + ")");
    }
}
