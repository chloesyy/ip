package duke.task;

public class Task {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        // Return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    public void printTask() {
        System.out.println("[" + this.getStatusIcon() + "] " + this.description);
    }
}
