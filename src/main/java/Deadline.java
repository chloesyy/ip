public class Deadline extends Task {
    private static final String DESCRIPTOR = "[D]";
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void printTask() {
        System.out.print("  " + DESCRIPTOR);
        System.out.print("[" + this.getStatusIcon() + "] " + this.description);
        System.out.println(" (by: " + this.by + ")");
    }
}
