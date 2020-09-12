package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.DESCRIPTOR = "[T]";
    }

    @Override
    public String getDescriptor() {
        return DESCRIPTOR;
    }

    @Override
    public String toString() {
        return DESCRIPTOR + " | " + (this.isDone ? "1 | " : "0 | ") + this.description;
    }

    @Override
    public void printTask() {
        System.out.print("  " + DESCRIPTOR);
        super.printTask();
    }
}
