package duke.task;

public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.DESCRIPTOR = "[E]";
    }

    @Override
    public String getDescriptor() {
        return DESCRIPTOR;
    }

    @Override
    public String toString() {
        return DESCRIPTOR + " | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.at;
    }

    @Override
    public void printTask() {
        System.out.print("  " + DESCRIPTOR);
        System.out.print(this.getStatusIcon() + " " + this.description);
        System.out.println(" (at: " + this.at + ")");
    }
}
