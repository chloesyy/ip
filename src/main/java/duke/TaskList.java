package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(Integer index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void remove(Task task) {
        tasks.remove(task);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void print() {
        for (Task task : tasks) {
            System.out.print(tasks.indexOf(task) + 1 + ".");
            task.printTask();
        }
    }

    public TaskList filterByDate(LocalDate date) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> !(t instanceof Todo))
                .filter((t) -> t.getDate().equals(date))
                .collect(Collectors.toList());

        return new TaskList(filteredList);
    }

}
