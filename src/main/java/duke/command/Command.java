package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    public boolean isExit;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
