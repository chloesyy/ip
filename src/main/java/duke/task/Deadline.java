package duke.task;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        descriptor = "[D]";
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public String toString() {
        return descriptor + " | " + (isDone ? "1 | " : "0 | ") + description + " | " + by;
    }

    @Override
    public void printTask() {
        System.out.print("  " + descriptor);
        System.out.print(getStatusIcon() + " " + description);
        System.out.println(" (by: " + by + ")");
    }
}
