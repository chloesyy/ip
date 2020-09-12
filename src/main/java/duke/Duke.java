package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();

    /*
    MAIN METHOD
     */
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        printWelcomeMessage();
        line = in.nextLine();

        while (!line.equals("bye")) {
            handleLine(line);
            line = in.nextLine();
        }

        printExitMessage();
    }

    public static void printWelcomeMessage() {
        System.out.println("Welcome! :D This is TaskThomas.");
        System.out.println("What are you doing today?");
    }

    public static void printExitMessage() {
        System.out.println("Bye bye :( Hope to see you again soon!");
    }

    public static void handleLine(String line) {
        if (line.equals("list")) {
            printList();
        } else if (line.startsWith("done")) {
            markAsDone(line);
        } else if (line.startsWith("delete")) {
            deleteTask(line);
        } else {
            addTask(line);
        }
    }

    /*
    Prints the list of tasks and indicates whether they are done.
    */
    public static void printList() {
        if (tasks.size() == 0) {
            System.out.println("Oops! You have no tasks in your list.");
            return;
        }

        System.out.println("These are the tasks you have now!");
        for (Task task : tasks) {
            System.out.print(tasks.indexOf(task)+1 + ".");
            task.printTask();
        }
    }

    /*
    Marks a task as done.
     */
    public static void markAsDone(String line) {
        int taskNum = 0;
        try {
            taskNum = Integer.parseInt(line.substring(5));
        } catch (NumberFormatException e) {
            System.out.println("Ohno! Please list a task number to be marked done :(");
            return;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Ohno! You didn't list the task number :(");
            return;
        }

        try {
            Task task = tasks.get(taskNum-1);
            task.isDone = true;
            System.out.println("Good job! You've completed:");
            task.printTask();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ohno! This is an invalid task number :(");
            return;
        }
    }

    /*
    Deletes a task from list.
     */
    public static void deleteTask(String line) {
        int taskNum = 0;
        try {
            taskNum = Integer.parseInt(line.substring(7));
        } catch (NumberFormatException e) {
            System.out.println("Ohno! Please list a task number to be marked done :(");
            return;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Ohno! You didn't list the task number :(");
            return;
        }

        try {
            Task task = tasks.get(taskNum - 1);
            tasks.remove(task);
            System.out.println("Alright lazy bum... I'll delete this:");
            task.printTask();
            System.out.println("Now you have " + tasks.size() + " tasks in the list!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ohno! This is an invalid task number :(");
            return;
        }
    }

    /*
    Adds a task to the list.
     */
    public static void addTask(String line) {
        if (line.startsWith("todo")) {
            try {
                addTodo(line.substring(5));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Ohno! The todo description cannot be empty :(");
                return;
            }
        } else if (line.startsWith("deadline")) {
            try {
                addDeadline(line.substring(9));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Ohno! The deadline needs a description and a /by :(");
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Ohno! Your deadline needs a /by :(");
                return;
            }
        } else if (line.startsWith("event")) {
            try {
                addEvent(line.substring(6));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Ohno! The event needs a description and an /at :(");
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Ohno! Your event needs an /at :(");
                return;
            }
        } else {
            // Happens if the task is not labeled
            System.out.println("Ohno! You should label your task with 'todo', 'deadline', or 'event'...");
            return;
        }

        System.out.println("Okay! I added:");
        tasks.get(tasks.size()-1).printTask();
        System.out.println("Now you have " + tasks.size() + " tasks in the list!");
    }

    /*
    Adds either a to-do task, deadline task or event task to the list.
     */
    public static void addTodo(String line) {
        tasks.add(new Todo(line));
    }

    public static void addDeadline(String line) {
        String[] descriptionAndBy = line.split(" /by ");
        tasks.add(new Deadline(descriptionAndBy[0], descriptionAndBy[1]));
    }

    public static void addEvent(String line) {
        String[] descriptionAndAt = line.split(" /at ");
        tasks.add(new Event(descriptionAndAt[1], descriptionAndAt[1]));
    }
}
