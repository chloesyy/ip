package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which lists the current TaskList to the user.
 */
public class ListCommand extends Command {

    /**
     * Prints the entire list of tasks to the user.
     *
     * @param tasks Current TaskList.
     * @param ui Interactions with the user.
     * @param storage Saves and loads files.
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
