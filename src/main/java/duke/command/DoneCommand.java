package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * Represents a command which marks a task as done.
 */
public class DoneCommand extends Command {
    private String line;

    public DoneCommand(String line) {
        this.line = line;
    }

    /**
     * Marks a task as done.
     *
     * @param tasks Current TaskList.
     * @param ui Interactions with the user.
     * @param storage Saves and loads files.
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        int taskNum = 0;
        try {
            taskNum = Integer.parseInt(line.substring(5));
        } catch (NumberFormatException e) {
            ui.printNumberFormatExceptionError();
            return;
        } catch (StringIndexOutOfBoundsException e) {
            ui.printStringIndexOutOfBoundsError();
            return;
        }

        try {
            Task task = tasks.getTask(taskNum-1);
            String oldTaskString = task.toString();

            // Update task arraylist and the file
            task.isDone = true;
            storage.updateFile(oldTaskString, task.toString());

            ui.printMarkedDoneMessage(task);
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexOutOfBoundsError();
            return;
        } catch (IOException e) {
            ui.printIOExceptionError(e);
            return;
        }
    }
}
