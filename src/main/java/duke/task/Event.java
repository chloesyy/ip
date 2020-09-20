package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate date;

    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        descriptor = "[E]";
        date = LocalDate.parse(at);
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
        System.out.println(" (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}
