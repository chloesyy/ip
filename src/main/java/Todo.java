public class Todo extends Task {
    private static final String DESCRIPTOR = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public void printTask() {
        System.out.print("  " + DESCRIPTOR);
        super.printTask();
    }
}
