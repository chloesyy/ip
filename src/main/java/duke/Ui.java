package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.Scanner;

public class Ui {
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        String line;

        line = in.nextLine();

        return line;
    }

    public void printWelcomeMessage() {
        System.out.println("Welcome! :D This is TaskThomas.");
        System.out.println("What are you doing today? \n");
    }

    public void printExitMessage() {
        System.out.println("Bye bye :( Hope to see you again soon!");
    }

    /*
    Prints the list of tasks and indicates whether they are done.
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

    public void printMarkedDoneMessage(Task task) {
        System.out.println("Good job! You've completed:");
        task.printTask();
        System.out.println();
    }

    public void printDeletedTaskMessage(Task task, TaskList tasks) {
        System.out.println("Alright lazy bum... I'll delete this:");
        task.printTask();
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list! \n");
    }

    public void printAddedTaskMessage(Task task, TaskList tasks) {
        System.out.println("Okay! I added:");
        tasks.getTask(tasks.getSize()-1).printTask();
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list! \n");
    }

    public void printEmptyDescriptionError() {
        System.out.println("Ohno! The description cannot be empty :( \n");
    }

    public void printMissingByError() {
        System.out.println("Ohno! Your deadline needs a /by :( \n");
    }

    public void printMissingAtError() {
        System.out.println("Ohno! Your event needs an /at :( \n");
    }

    public void printMissingLabelError() {
        System.out.println("Ohno! You should label your task with 'todo', 'deadline', or 'event'... \n");
    }

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
