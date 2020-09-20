package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String line;
    private final int TODO_DESCRIPTION_INDEX = 5;
    private final int DEADLINE_DESCRIPTION_INDEX = 9;
    private final int EVENT_DESCRIPTION_INDEX = 6;

    public AddCommand(String line) {
        this.line = line;
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) {
        if (line.startsWith("todo")) {
            addTodo(line.substring(TODO_DESCRIPTION_INDEX), tasks, storage, false, ui);
        } else if (line.startsWith("deadline")) {
            try {
                addDeadline(line.substring(DEADLINE_DESCRIPTION_INDEX), tasks, storage, false, ui);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printMissingByError();
                return;
            }
        } else if (line.startsWith("event")) {
            try {
                addEvent(line.substring(EVENT_DESCRIPTION_INDEX), tasks, storage, false, ui);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printMissingAtError();
                return;
            } catch (DateTimeParseException e) {
                ui.printDateTimeParseError();
                return;
            }
        } else {
            // Happens if the task is not labeled
            ui.printMissingLabelError();
            return;
        }

        ui.printAddedTaskMessage(tasks.getTask(tasks.getSize() -1), tasks);
    }

    /*
    Adds either a to-do task, deadline task or event task to the list.
     */
    public static void addTodo(String command, TaskList tasks, Storage storage, Boolean isFromFile, Ui ui) {
        if (command.length() == 0) {
            throw new StringIndexOutOfBoundsException();
        }
        tasks.add(new Todo(command));

        if (!isFromFile) {
            storage.writeToFile("duke.txt", tasks.getTask(tasks.getSize()-1).toString(), ui);
        }
    }

    public static void addDeadline(String command, TaskList tasks, Storage storage, Boolean isFromFile, Ui ui) {
        String[] descriptionAndBy = command.split(" /by ");

        if (descriptionAndBy[0].length() == 0) {
            throw new StringIndexOutOfBoundsException();
        }

        tasks.add(new Deadline(descriptionAndBy[0], descriptionAndBy[1]));

        if (!isFromFile) {
            storage.writeToFile("duke.txt", tasks.getTask(tasks.getSize()-1).toString(), ui);
        }
    }

    public static void addEvent(String command, TaskList tasks, Storage storage, Boolean isFromFile, Ui ui) {
        String[] descriptionAndAt = command.split(" /at ");

        if (descriptionAndAt[0].length() == 0) {
            throw new StringIndexOutOfBoundsException();
        }
        tasks.add(new Event(descriptionAndAt[0], descriptionAndAt[1]));

        if (!isFromFile) {
            storage.writeToFile("duke.txt", tasks.getTask(tasks.getSize()-1).toString(), ui);
        }
    }
}
