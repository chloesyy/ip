package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents interactions with the user.
 */
public class Ui {
    /**
     * Returns command input from the user.
     *
     * @return Command input string.
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        String line;

        line = in.nextLine();

        return line;
    }

    /**
     * Prints the welcome message at the start of the program.
     */
    public void printWelcomeMessage() {
        System.out.println("Welcome! :D This is TaskThomas.");
        System.out.println("What are you doing today? \n");
    }

    /**
     * Prints the exit message before the program exits.
     */
    public void printExitMessage() {
        System.out.println("Bye bye :( Hope to see you again soon!");
    }

    /**
     * Prints the list of tasks and indicates whether they are done.
     *
     * @param tasks Current TaskList.
     */
    public void printList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("Oops! You have no tasks in your list. \n");
            return;
        }

        System.out.println("These are the tasks you have now!");
        tasks.print();
        System.out.println();
    }

    /**
     * Prints success message when the task is successfully marked as done.
     *
     * @param task Current TaskList.
     */
    public void printMarkedDoneMessage(Task task) {
        System.out.println("Good job! You've completed:");
        task.printTask();
        System.out.println();
    }

    /**
     * Prints success message when the task is deleted successfully.
     *
     * @param task Task to be deleted.
     * @param tasks Current TaskList.
     */
    public void printDeletedTaskMessage(Task task, TaskList tasks) {
        System.out.println("Alright lazy bum... I'll delete this:");
        task.printTask();
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list! \n");
    }

    /**
     * Prints success message when the task is added successfully.
     *
     * @param task Task to be added.
     * @param tasks Current TaskList.
     */
    public void printAddedTaskMessage(Task task, TaskList tasks) {
        System.out.println("Okay! I added:");
        tasks.getTask(tasks.getSize()-1).printTask();
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list! \n");
    }

    /**
     * Prints error message when the description is not specified.
     */
    public void printEmptyDescriptionError() {
        System.out.println("Ohno! The description cannot be empty :( \n");
    }

    /**
     * Prints error message when the deadline is not specified.
     */
    public void printMissingByError() {
        System.out.println("Ohno! Your deadline needs a /by :( \n");
    }

    /**
     * Prints error message when the date an event is occurring on is not specified.
     */
    public void printMissingAtError() {
        System.out.println("Ohno! Your event needs an /at :( \n");
    }

    /**
     * Prints error message when the task is not starting with todo, deadline or event.
     */
    public void printMissingLabelError() {
        System.out.println("Ohno! You should label your task with 'todo', 'deadline', or 'event'... \n");
    }

    /**
     * The following methods prints error messages which can be thrown up.
     */
    public void printNumberFormatExceptionError() {
        System.out.println("Ohno! Please list a task number :( \n");
    }
    public void printStringIndexOutOfBoundsError() {
        System.out.println("Ohno! You didn't list the task number :( \n");
    }
    public void printIndexOutOfBoundsError() {
        System.out.println("Ohno! This is an invalid task number :( \n");
    }
    public void printIOExceptionError(IOException e) {
        System.out.println("Something went wrong: " + e.getMessage() + "\n");
    }


}
