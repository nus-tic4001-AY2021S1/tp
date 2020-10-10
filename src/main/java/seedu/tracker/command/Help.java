package seedu.tracker.command;

import seedu.tracker.ui.Ui;

/**
 * A command that shows all available commands.
 */
public class Help extends Command {
    public static final String word = "--help";

    public Help(Ui ui) {
        super(ui);
    }

    @Override
    public void execute() {
        ui.printHelp();
    }
}
