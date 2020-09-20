package duke;

import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents the TaskList.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the task based on the index.
     *
     * @param index Index of task.
     * @return Task with the corresponding index.
     */
    public Task getTask(Integer index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Removes a task from the task list.
     *
     * @param task Task to be removed.
     */
    public void remove(Task task) {
        tasks.remove(task);
    }

    /**
     * Prints the entire task list.
     */
    public void print() {
        for (Task task : tasks) {
            System.out.print(tasks.indexOf(task) + 1 + ".");
            task.printTask();
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a list of tasks which has deadlines on a specific date or
     * events which occur on that specific date.
     * Filters the task list by the date.
     *
     * @param date Date to be filtered by.
     * @return List of tasks which are filtered by the date.
     */
    public TaskList filterByDate(LocalDate date) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> !(t instanceof Todo))
                .filter((t) -> t.getDate().equals(date))
                .collect(Collectors.toList());

        return new TaskList(filteredList);
    }

    /**
     * Returns a list of tasks with the keyword.
     * Filters the task list by the keyword.
     *
     * @param keyword Keyword to be filtered by.
     * @return List of tasks containing the keyword.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> keywordList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(keyword))
                .collect(Collectors.toList());

        return new TaskList(keywordList);
    }
}
