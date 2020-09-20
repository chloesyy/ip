package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

/**
 * Represents a command which lists the current TaskList to the user.
 */
public class ListCommand extends Command {
    private final String command;
    private final int DATE_INDEX = 5;

    public ListCommand(String command) {
        this.command = command;
    }

    /**
     * Prints the entire list of tasks to the user.
     *
     * @param tasks Current TaskList.
     * @param ui Interactions with the user.
     * @param storage Saves and loads files.
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        if (command.equals("list")) {
            ui.printList(tasks);
        } else {
            LocalDate date = LocalDate.parse(command.substring(DATE_INDEX));
            TaskList filteredList = tasks.filterByDate(date);
            ui.printList(filteredList);
        }
    }
}
