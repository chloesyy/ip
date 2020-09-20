package duke.task;

public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        descriptor = "[E]";
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public String toString() {
        return descriptor + " | " + (isDone ? "1 | " : "0 | ") + description + at;
    }

    @Override
    public void printTask() {
        System.out.print("  " + descriptor);
        System.out.print(getStatusIcon() + " " + description);
        System.out.println(" (at: " + at + ")");
    }
}
