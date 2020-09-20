package duke.task;

public abstract class Task {
    protected String description;
    public boolean isDone;
    protected String DESCRIPTOR;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        // Return tick or cross symbols
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public abstract String getDescriptor();

    public String getDescription() {
        return description;
    }

    public abstract String toString();

    public void printTask() {
        System.out.println(this.getStatusIcon() + " " + this.description);
    }
}
