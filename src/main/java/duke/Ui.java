package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class Ui {
    public static void printWelcomeMessage() {
        System.out.println("Welcome! :D This is TaskThomas.");
        System.out.println("What are you doing today? \n");
    }

    public static void printExitMessage() {
        System.out.println("Bye bye :( Hope to see you again soon!");
    }

    /*
    Prints the list of tasks and indicates whether they are done.
    */
    public static void printList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("Oops! You have no tasks in your list. \n");
            return;
        }

        System.out.println("These are the tasks you have now!");
        for (Task task : tasks) {
            System.out.print(tasks.indexOf(task)+1 + ".");
            task.printTask();
        }
        System.out.println();
    }

    public static void printMarkedDoneMessage(Task task) {
        System.out.println("Good job! You've completed:");
        task.printTask();
        System.out.println();
    }

    public static void printDeletedTaskMessage(Task task, ArrayList<Task> tasks) {
        System.out.println("Alright lazy bum... I'll delete this:");
        task.printTask();
        System.out.println("Now you have " + tasks.size() + " tasks in the list! \n");
    }

    public static void printAddedTaskMessage(Task task, ArrayList<Task> tasks) {
        System.out.println("Okay! I added:");
        tasks.get(tasks.size()-1).printTask();
        System.out.println("Now you have " + tasks.size() + " tasks in the list! \n");
    }

    public static void printEmptyDescriptionError() {
        System.out.println("Ohno! The description cannot be empty :( \n");
    }

    public static void printMissingByError() {
        System.out.println("Ohno! Your deadline needs a /by :( \n");
    }

    public static void printMissingAtError() {
        System.out.println("Ohno! Your event needs an /at :( \n");
    }

    public static void printMissingLabelError() {
        System.out.println("Ohno! You should label your task with 'todo', 'deadline', or 'event'... \n");
    }

    public static void printNumberFormatExceptionError() {
        System.out.println("Ohno! Please list a task number :( \n");
    }


    public static void printStringIndexOutOfBoundsError() {
        System.out.println("Ohno! You didn't list the task number :( \n");
    }

    public static void printIndexOutOfBoundsError() {
        System.out.println("Ohno! This is an invalid task number :( \n");
    }

    public static void printIOExceptionError(IOException e) {
        System.out.println("Something went wrong: " + e.getMessage() + "\n");
    }
}
