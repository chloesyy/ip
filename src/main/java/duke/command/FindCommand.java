package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which finds tasks based on the keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String command) throws StringIndexOutOfBoundsException {
        this.keyword = command.substring(5);
    }

    /**
     * Finds tasks with the corresponding keyword.
     *
     * @param tasks Current TaskList.
     * @param ui Interactions with the user.
     * @param storage Saves and loads files.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (keyword.length() == 0) {
            throw new StringIndexOutOfBoundsException();
        }
        TaskList keywordList = tasks.find(keyword);
        ui.printList(keywordList, "keyword", keyword);
    }
}
