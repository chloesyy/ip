package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
//    public LocalDate date;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        descriptor = "[D]";
//        date = LocalDate.parse(by);
        this.setDate(LocalDate.parse(by));
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public String toString() {
        return descriptor + " | " + (isDone ? "1 | " : "0 | ") + description + " | " + date;
    }

    @Override
    public void printTask() {
        System.out.print("  " + descriptor);
        System.out.print(getStatusIcon() + " " + description);
        System.out.println(" (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}
