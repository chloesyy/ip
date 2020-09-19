package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.Duke.addDeadline;
import static duke.Duke.addEvent;
import static duke.Duke.addTodo;

public class Storage {
    /*
    Loads the file and creates new file if file does not exist
     */
    public static void loadFile(ArrayList<Task> tasks) throws IOException {
        File file = new File("duke.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner read = new Scanner(file);
        while (read.hasNext()) {
            loadFileTask(read.nextLine(), tasks);
        }
        read.close();
    }

    /*
    Adds individual tasks into the array
     */
    public static void loadFileTask(String line, ArrayList<Task> tasks) {
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
