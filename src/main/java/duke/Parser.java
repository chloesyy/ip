package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

public class Parser {
    public static Command parse(String command, TaskList tasks, Ui ui) {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.startsWith("list")) {
            return new ListCommand(command);
        } else if (command.startsWith("done")) {
            return new DoneCommand(command);
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        } else {
            return new AddCommand(command);
        }
    }
}
