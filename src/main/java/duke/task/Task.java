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
        // Return Y or N symbols
        return (isDone ? "[Y]" : "[N]");
    }

    public abstract String getDescriptor();

    public abstract String toString();

    public void printTask() {
        System.out.println(this.getStatusIcon() + " " + this.description);
    }
}
