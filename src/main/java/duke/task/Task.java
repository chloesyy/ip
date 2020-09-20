package duke.task;

/**
 * Represents a task. A task can be either a Todo, Event or Deadline.
 */
public abstract class Task {
    protected String description;
    public boolean isDone;
    protected String DESCRIPTOR;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    public abstract String toString();

    public void printTask() {
        System.out.println(this.getStatusIcon() + " " + this.description);
    }
}
