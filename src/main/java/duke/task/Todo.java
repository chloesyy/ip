package duke.task;

/**
 * Represents a todo. A <code>Todo</code> object corresponds to
 * a task with an action.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
        descriptor = "[T]";
    }

    /**
     * Returns the todo's descriptor: [T]
     *
     * @return Descriptor of Todo.
     */
    @Override
    public String getDescriptor() {
        return descriptor;
    }

    /**
     * Returns the string format of the Todo. Used to save into files.
     *
     * @return String format of Todo.
     */
    @Override
    public String toString() {
        return descriptor + " | " + (isDone ? "1 | " : "0 | ") + description;
    }

    /**
     * Prints the Todo in the format:
     * <code>[T][status] description</code>
     */
    @Override
    public void printTask() {
        System.out.print("  " + descriptor);
        super.printTask();
    }
}
