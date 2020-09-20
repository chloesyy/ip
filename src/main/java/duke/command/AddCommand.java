package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Represents a command which adds a task to the list.
 */
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String line;
    private final int TODO_DESCRIPTION_INDEX = 5;
    private final int DEADLINE_DESCRIPTION_INDEX = 9;
    private final int EVENT_DESCRIPTION_INDEX = 6;

    public AddCommand(String line) {
        this.line = line;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param tasks Current TaskList.
     * @param ui Interactions with the user.
     * @param storage Used to save and load files.
     */
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

    /**
     * Adds a todo task to the TaskList.
     *
     * @param command User command input.
     * @param tasks Current TaskList.
     * @param storage Saves and loads files.
     * @param isFromFile If adding the todo is from loading the file.
     * @param ui Interactions with the user.
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

    /**
     * Adds a deadline task to the TaskList.
     *
     * @param command User command input.
     * @param tasks Current TaskList.
     * @param storage Saves and loads files.
     * @param isFromFile If adding the deadline is from loading the file.
     * @param ui Interactions with the user.
     */
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

    /**
     * Adds an event task to the TaskList.
     *
     * @param command User command input.
     * @param tasks Current TaskList.
     * @param storage Saves and loads files.
     * @param isFromFile If adding the event is from loading the file.
     * @param ui Interactions with the user.
     */
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
