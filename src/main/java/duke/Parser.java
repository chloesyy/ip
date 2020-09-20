package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

/**
 * Represents a parser to parse command inputs by the user.
 */
public class Parser {
    public static Command parse(String line, TaskList tasks, Ui ui) {
        if (line.equals("bye")) {
            return new ExitCommand();
        } else if (line.equals("list")) {
            return new ListCommand();
        } else if (line.startsWith("done")) {
            return new DoneCommand(line);
        } else if (line.startsWith("delete")) {
            return new DeleteCommand(line);
        } else {
            return new AddCommand(line);
        }
    }
}
