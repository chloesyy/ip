package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();

    /*
    MAIN METHOD
     */
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        try {
            loadFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        printWelcomeMessage();
        line = in.nextLine();

        while (!line.equals("bye")) {
            handleLine(line);
            line = in.nextLine();
        }

        printExitMessage();
    }


    /*
    Loads the file and creates new file if file does not exist
     */
    public static void loadFile() throws IOException {
        File file = new File("duke.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner read = new Scanner(file);
        while (read.hasNext()) {
            loadFileTask(read.nextLine());
        }
        read.close();
    }

    /*
    Adds individual tasks into the array
     */
    public static void loadFileTask(String line) {
        String[] taskDetails = line.split(" \\| ");

        switch (taskDetails[0]) {
        case "[T]":
            addTodo(taskDetails[2], true);
            break;
        case "[D]":
            addDeadline(taskDetails[2] + " /by " + taskDetails[3], true);
            break;
        case "[E]":
            addEvent(taskDetails[2] + " /at " + taskDetails[3], true);
            break;
        default:
            // Happens if there is nothing in the list (or any garbage text)
            return;
        }

        // Mark as done if the task is already done
        if (taskDetails[1].equals("1")) {
            tasks.get(tasks.size()-1).isDone = true;
        }
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
            String oldTaskString = task.toString();

            // Update task arraylist and the file
            task.isDone = true;
            updateFile(oldTaskString, task.toString());

            // Prints output message
            System.out.println("Good job! You've completed:");
            task.printTask();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ohno! This is an invalid task number :(");
            return;
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
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
            String oldTaskString = task.toString();

            // Updates task arraylist and file
            tasks.remove(task);
            updateFile(oldTaskString, "");

            // Print output message
            System.out.println("Alright lazy bum... I'll delete this:");
            task.printTask();
            System.out.println("Now you have " + tasks.size() + " tasks in the list!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ohno! This is an invalid task number :(");
            return;
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            return;
        }
    }

    /*
    Adds a task to the list.
     */
    public static void addTask(String line) {
        if (line.startsWith("todo")) {
            try {
                addTodo(line.substring(5), false);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Ohno! The todo description cannot be empty :(");
                return;
            }
        } else if (line.startsWith("deadline")) {
            try {
                addDeadline(line.substring(9), false);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Ohno! The deadline needs a description and a /by :(");
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Ohno! Your deadline needs a /by :(");
                return;
            }
        } else if (line.startsWith("event")) {
            try {
                addEvent(line.substring(6), false);
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
    public static void addTodo(String line, Boolean fromFile) {
        tasks.add(new Todo(line));

        if (!fromFile) {
            writeToFile("duke.txt", tasks.get(tasks.size()-1).toString());
        }
    }

    public static void addDeadline(String line, Boolean fromFile) {
        String[] descriptionAndBy = line.split(" /by ");
        tasks.add(new Deadline(descriptionAndBy[0], descriptionAndBy[1]));

        if (!fromFile) {
            writeToFile("duke.txt", tasks.get(tasks.size()-1).toString());
        }
    }

    public static void addEvent(String line, Boolean fromFile) {
        String[] descriptionAndAt = line.split(" /at ");
        tasks.add(new Event(descriptionAndAt[1], descriptionAndAt[1]));

        if (!fromFile) {
            writeToFile("duke.txt", tasks.get(tasks.size()-1).toString());
        }
    }


    /*

    FILE UPDATE AND EDIT METHODS

     */
    /*
    Writes data to file
     */
    public static void writeToFile(String filePath, String line) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(System.lineSeparator() + line);
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /*
    Updates the file
     */
    public static void updateFile(String oldTaskString, String newTaskString) throws IOException {
        File file = new File("duke.txt");
        Scanner read = new Scanner(file);
        StringBuffer buffer = new StringBuffer();

        // Puts everything from file into buffer
        while (read.hasNext()) {
            buffer.append(read.nextLine() + System.lineSeparator());
        }

        read.close();

        // Puts everything from buffer into String
        String fileContents = buffer.toString();

        // Update the file
        fileContents = fileContents.replace(oldTaskString, newTaskString);
        FileWriter writer = new FileWriter("duke.txt");
        writer.append(fileContents);
        writer.flush();
    }
}
