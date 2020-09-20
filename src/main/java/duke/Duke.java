package duke;

import duke.command.Command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
        try {
            storage.loadFile(tasks, ui);
        } catch (IOException e) {
            ui.printIOExceptionError(e);
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();

    }

    public void run() {
        ui.printWelcomeMessage();
        Boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand, tasks, ui);
            try {
                c.execute(tasks, ui, storage);
            } catch (DateTimeParseException e) {
                ui.printDateTimeParseError();
            } catch (StringIndexOutOfBoundsException e) {
                ui.printEmptyDescriptionError();
            }
            isExit = c.isExit;
        }

        ui.printExitMessage();
    }
}
