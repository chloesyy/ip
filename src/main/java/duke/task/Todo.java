package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        descriptor = "[T]";
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public String toString() {
        return descriptor + " | " + (isDone ? "1 | " : "0 | ") + description;
    }

    @Override
    public void printTask() {
        System.out.print("  " + descriptor);
        super.printTask();
    }
}
