package duke;

import duke.command.Command;

import java.io.IOException;

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
    /*
        MAIN METHOD
    */
    public static void main(String[] args) {
        new Duke("duke.txt").run();

    }

    public void run() {
        ui.printWelcomeMessage();
        Boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand, tasks, ui);
            c.execute(tasks, ui, storage);
            isExit = c.isExit;
        }

        ui.printExitMessage();
    }



}
