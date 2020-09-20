package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static duke.command.AddCommand.addDeadline;
import static duke.command.AddCommand.addEvent;
import static duke.command.AddCommand.addTodo;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /*
    Loads the file and creates new file if file does not exist
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

    /*
    Adds individual tasks into the array
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

    /*

    FILE UPDATE AND EDIT METHODS

     */
    /*
    Writes data to file
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

    /*
    Updates the file
     */
    public void updateFile(String oldTaskString, String newTaskString) throws IOException {
        File file = new File("duke.txt");
        Scanner scanner = new Scanner(file);
        StringBuffer buffer = new StringBuffer();

        // Puts everything from file into buffer
        while (scanner.hasNext()) {
            buffer.append(scanner.nextLine() + System.lineSeparator());
        }

        scanner.close();

        // Puts everything from buffer into String
        String fileContents = buffer.toString();

        // Update the file
        fileContents = fileContents.replace(oldTaskString, newTaskString);
        FileWriter writer = new FileWriter("duke.txt");
        writer.append(fileContents);
        writer.flush();
    }
}
