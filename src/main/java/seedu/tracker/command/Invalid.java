package seedu.tracker.command;

import seedu.tracker.ui.Ui;

/**
 * A command that handles invalid commands.
 */
public class Invalid extends Command {
    public Invalid(Ui ui) {
        super(ui);
    }

    @Override
    public void execute() {
        ui.printInvalidCommand();
    }
}
