package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the TaskList.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
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
}
