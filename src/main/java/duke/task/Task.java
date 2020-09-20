package duke.task;

/**
 * Represents a task. A task can be either a Todo, Event or Deadline.
 */
import java.time.LocalDate;

public abstract class Task {
    protected String description;
    public boolean isDone;
    protected String descriptor;
    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns a tick or cross depending on whether a task is marked done.
     *
     * @return Tick or Cross
     */
    public String getStatusIcon() {
        // Return tick or cross symbols
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public abstract String getDescriptor();

    /**
     * Returns the description of the task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the date associated with the task.
     * Can only be used with deadlines and events.
     * For deadlines, returns the date of the deadline.
     * For events, returns the date the event is occurring.
     *
     * @return Either the deadline of the date of occurrence of task.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the task.
     * For deadlines, sets the date of the deadline.
     * For events, sets the date of occurrence of event.
     *
     * @param date Date of task.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public abstract String toString();

    public void printTask() {
        System.out.println(getStatusIcon() + " " + description);
    }
}
