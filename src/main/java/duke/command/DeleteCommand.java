package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * Represents a command which deletes a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private String line;

    public DeleteCommand(String line) {
        this.line = line;
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param tasks Current TaskList.
     * @param ui Interactions with the user.
     * @param storage Saves and loads files.
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        int taskNum = 0;
        try {
            taskNum = Integer.parseInt(line.substring(7));
        } catch (NumberFormatException e) {
            ui.printNumberFormatExceptionError();
            return;
        } catch (StringIndexOutOfBoundsException e) {
            ui.printStringIndexOutOfBoundsError();
            return;
        }

        try {
            Task task = tasks.getTask(taskNum - 1);
            String oldTaskString = task.toString();

            // Updates task arraylist and file
            tasks.remove(task);
            storage.updateFile(oldTaskString, "");

            ui.printDeletedTaskMessage(task, tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexOutOfBoundsError();
            return;
        } catch (IOException e) {
            ui.printIOExceptionError(e);
            return;
        }
    }
}
