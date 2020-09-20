package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ListCommand extends Command {
    private final String command;
    private final int DATE_INDEX = 5;

    public ListCommand(String command) {
        this.command = command;
    }

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
