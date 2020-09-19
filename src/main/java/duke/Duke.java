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
            Storage.loadFile(tasks);
        } catch (IOException e) {
            Ui.printIOExceptionError(e);
        }

        Ui.printWelcomeMessage();
        line = in.nextLine();

        while (!line.equals("bye")) {
            handleLine(line);
            line = in.nextLine();
        }

        Ui.printExitMessage();
    }

    public static void handleLine(String line) {
        if (line.equals("list")) {
            Ui.printList(tasks);
        } else if (line.startsWith("done")) {
            markAsDone(line);
        } else if (line.startsWith("delete")) {
            deleteTask(line);
        } else {
            addTask(line);
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
            Ui.printNumberFormatExceptionError();
            return;
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printStringIndexOutOfBoundsError();
            return;
        }

        try {
            Task task = tasks.get(taskNum-1);
            String oldTaskString = task.toString();

            // Update task arraylist and the file
            task.isDone = true;
            Storage.updateFile(oldTaskString, task.toString());

            // Prints output message
            Ui.printMarkedDoneMessage(task);
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundsError();
            return;
        } catch (IOException e) {
            Ui.printIOExceptionError(e);
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
            Ui.printNumberFormatExceptionError();
            return;
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printStringIndexOutOfBoundsError();
            return;
        }

        try {
            Task task = tasks.get(taskNum - 1);
            String oldTaskString = task.toString();

            // Updates task arraylist and file
            tasks.remove(task);
            Storage.updateFile(oldTaskString, "");

            // Print output message
            Ui.printDeletedTaskMessage(task, tasks);
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundsError();
            return;
        } catch (IOException e) {
            Ui.printIOExceptionError(e);
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
                Ui.printEmptyDescriptionError();
                return;
            }
        } else if (line.startsWith("deadline")) {
            try {
                addDeadline(line.substring(9), false);
            } catch (StringIndexOutOfBoundsException e) {
                Ui.printEmptyDescriptionError();
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printMissingByError();
                return;
            }
        } else if (line.startsWith("event")) {
            try {
                addEvent(line.substring(6), false);
            } catch (StringIndexOutOfBoundsException e) {
                Ui.printEmptyDescriptionError();
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printMissingAtError();
                return;
            }
        } else {
            // Happens if the task is not labeled
            Ui.printMissingLabelError();
            return;
        }

        Ui.printAddedTaskMessage(tasks.get(tasks.size() -1), tasks);
    }



    /*
    Adds either a to-do task, deadline task or event task to the list.
     */
    public static void addTodo(String line, Boolean fromFile) {
        tasks.add(new Todo(line));

        if (!fromFile) {
            Storage.writeToFile("duke.txt", tasks.get(tasks.size()-1).toString());
        }
    }

    public static void addDeadline(String line, Boolean fromFile) {
        String[] descriptionAndBy = line.split(" /by ");
        tasks.add(new Deadline(descriptionAndBy[0], descriptionAndBy[1]));

        if (!fromFile) {
            Storage.writeToFile("duke.txt", tasks.get(tasks.size()-1).toString());
        }
    }

    public static void addEvent(String line, Boolean fromFile) {
        String[] descriptionAndAt = line.split(" /at ");
        tasks.add(new Event(descriptionAndAt[0], descriptionAndAt[1]));

        if (!fromFile) {
            Storage.writeToFile("duke.txt", tasks.get(tasks.size()-1).toString());
        }
    }



}
