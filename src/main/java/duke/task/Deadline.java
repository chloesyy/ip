package duke.task;

public class Deadline extends Task {
//    private static final String DESCRIPTOR = "[D]";
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.DESCRIPTOR = "[D]";
    }

    @Override
    public String getDescriptor() {
        return DESCRIPTOR;
    }

    @Override
    public String toString() {
        return DESCRIPTOR + " | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.by;
    }

    @Override
    public void printTask() {
        System.out.print("  " + DESCRIPTOR);
        System.out.print("[" + this.getStatusIcon() + "] " + this.description);
        System.out.println(" (by: " + this.by + ")");
    }
}
