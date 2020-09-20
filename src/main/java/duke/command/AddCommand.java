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
public class AddCommand extends Command {
    private String line;

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
            try {
                addTodo(line.substring(5), tasks, storage, false, ui);
            } catch (StringIndexOutOfBoundsException e) {
                return;
            }
        } else if (line.startsWith("deadline")) {
            try {
                addDeadline(line.substring(9), tasks, storage, false, ui);
            } catch (StringIndexOutOfBoundsException e) {
                ui.printEmptyDescriptionError();
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printMissingByError();
                return;
            }
        } else if (line.startsWith("event")) {
            try {
                addEvent(line.substring(6), tasks, storage, false, ui);
            } catch (StringIndexOutOfBoundsException e) {
                ui.printEmptyDescriptionError();
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printMissingAtError();
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
        tasks.add(new Event(descriptionAndAt[0], descriptionAndAt[1]));

        if (!isFromFile) {
            storage.writeToFile("duke.txt", tasks.getTask(tasks.getSize()-1).toString(), ui);
        }
    }
}
