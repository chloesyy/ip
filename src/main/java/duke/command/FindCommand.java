package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String command) throws StringIndexOutOfBoundsException {
        this.keyword = command.substring(5);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (keyword.length() == 0) {
            throw new StringIndexOutOfBoundsException();
        }
        TaskList keywordList = tasks.find(keyword);
        ui.printList(keywordList);
    }
}
