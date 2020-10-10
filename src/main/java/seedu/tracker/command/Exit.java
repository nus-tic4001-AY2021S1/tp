package seedu.tracker.command;

import seedu.tracker.ui.Ui;
/**
 * A command to exit the program.
 */
public class Exit extends Command {
    public static final String word = "--exit";

    public Exit(Ui ui) {
        super(ui);
    }

    @Override
    public void execute() {
        ui.printFarewell();
    }
}
