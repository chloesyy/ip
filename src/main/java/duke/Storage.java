package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static duke.command.AddCommand.addDeadline;
import static duke.command.AddCommand.addEvent;
import static duke.command.AddCommand.addTodo;

/**
 * Represents the file for data to be loaded from and saved into.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from the file "duke.txt".
     * Creates a new file if the file does not exist.
     *
     * @param tasks Current TaskList.
     * @param ui Interactions with the user.
     * @throws IOException If the file is corrupted.
     */
    public void loadFile(TaskList tasks, Ui ui) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner read = new Scanner(file);
        while (read.hasNext()) {
            loadFileTask(read.nextLine(), tasks, ui);
        }
        read.close();
    }

    /**
     * Adds individual tasks from the file into the TaskList.
     *
     * @param line Line from the file
     * @param tasks Current TaskList.
     * @param ui Interactions with the user.
     */
    public void loadFileTask(String line, TaskList tasks, Ui ui) {
        String[] taskDetails = line.split(" \\| ");

        switch (taskDetails[0]) {
        case "[T]":
            addTodo(taskDetails[2], tasks, this, true, ui);
            break;
        case "[D]":
            addDeadline(taskDetails[2] + " /by " + taskDetails[3], tasks, this, true, ui);
            break;
        case "[E]":
            addEvent(taskDetails[2] + " /at " + taskDetails[3], tasks, this, true, ui);
            break;
        default:
            // Happens if there is nothing in the list (or any garbage text)
            return;
        }

        // Mark as done if the task is already done
        if (taskDetails[1].equals("1")) {
            tasks.getTask(tasks.getSize() - 1).isDone = true;
        }
    }

    /**
     * Adds a line to the file.
     *
     * @param filePath File path of the file to be saved: "duke.txt".
     * @param line Line to be written into the file.
     * @param ui Interactions with the user.
     */
    public void writeToFile(String filePath, String line, Ui ui) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(System.lineSeparator() + line);
            writer.close();
        } catch (IOException e) {
            ui.printIOExceptionError(e);
        }
    }

    /**
     * Updates a certain line in the file.
     *
     * @param oldTaskString Old string to be updated.
     * @param newTaskString New string to be updated to.
     * @throws IOException If the file is corrupted.
     */
    public void updateFile(String oldTaskString, String newTaskString) throws IOException {
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
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
    }
}
