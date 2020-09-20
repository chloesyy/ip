package duke;

import duke.task.Task;

import java.util.ArrayList;
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

    public void print() {
        for (Task task : tasks) {
            System.out.print(tasks.indexOf(task) + 1 + ".");
            task.printTask();
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public TaskList find(String keyword) {
        ArrayList<Task> keywordList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(keyword))
                .collect(Collectors.toList());

        return new TaskList(keywordList);
    }
}
