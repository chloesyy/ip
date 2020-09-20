package duke.task;

import java.time.LocalDate;

public abstract class Task {
    protected String description;
    public boolean isDone;
    protected String descriptor;
    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        // Return tick or cross symbols
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public abstract String getDescriptor();

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public abstract String toString();

    public void printTask() {
        System.out.println(getStatusIcon() + " " + description);
    }
}
