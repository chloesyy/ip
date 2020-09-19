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

        Ui.printWelcomeMessage();
        line = in.nextLine();

        while (!line.equals("bye")) {
            handleLine(line);
            line = in.nextLine();
        }

        Ui.printExitMessage();
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
            updateFile(oldTaskString, task.toString());

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
            updateFile(oldTaskString, "");

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
        tasks.add(new Event(descriptionAndAt[0], descriptionAndAt[1]));

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
            Ui.printIOExceptionError(e);
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
