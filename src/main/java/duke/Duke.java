package duke;

import duke.command.Command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Instantiates a Duke instance.
     *
     * @param filePath File path of the file which the data is to be saved into.
     */
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

    /**
     * Runs Duke instance.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.printWelcomeMessage();
        Boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;
            } catch (DateTimeParseException e) {
                ui.printDateTimeParseError();
            } catch (StringIndexOutOfBoundsException e) {
                ui.printEmptyDescriptionError();
            }
        }

        ui.printExitMessage();
    }
}
