public class Event extends Task {
    private static final String DESCRIPTOR = "[E]";
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public void printTask() {
        System.out.print("  " + DESCRIPTOR);
        System.out.print("[" + this.getStatusIcon() + "] " + this.description);
        System.out.println(" (at: " + this.at + ")");
    }
}
