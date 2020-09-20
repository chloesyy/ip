package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which exits the program.
 */
public class ExitCommand extends Command {
    /**
     * Sets the isExit boolean of a command to true. Program will exit in main loop.
     *
     * @param tasks Current TaskList.
     * @param ui Interactions with the user.
     * @param storage Saves and loads files.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.isExit = true;
    }
}
