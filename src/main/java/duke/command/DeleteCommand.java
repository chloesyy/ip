package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    private String command;
    private final int DESCRIPTION_INDEX = 7;

    public DeleteCommand(String line) {
        this.command = line;
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) {
        int taskNum = 0;
        try {
            taskNum = Integer.parseInt(command.substring(DESCRIPTION_INDEX));
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
