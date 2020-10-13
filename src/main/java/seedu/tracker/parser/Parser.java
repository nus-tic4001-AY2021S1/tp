package seedu.tracker.parser;

import seedu.tracker.command.*;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

/**
 * Parses the user input into meaningful details and returns the appropriate seedu.tracker.command.
 */
public class Parser {
    public Command parseInput(String line, Ui ui, ProjectList projects, Storage storage) {

        String commandWord = line.trim().split(" ",2)[0];
        String lineWithoutCommand = line.substring(line.indexOf(" ") + 1).trim();

        switch (commandWord) {
            case Help.word:
                return new Help(ui);
            case Delete.word:
                return new Delete(lineWithoutCommand, projects, ui, storage);
            case List.word:
                return new List(projects, ui);
            case Find.word:
                return new Find(lineWithoutCommand, projects, ui, storage);
            case Replace.word:
                return new Replace(line, projects, ui, storage);
            case Add.word:
                return new Add(line, projects, ui, storage);
            case Project.word:
                return new Project(line, projects, ui, storage);
            case Exit.word:
            case "":        // also exits when user input is empty
                return new Exit(ui);
            default:
                return new Invalid(ui);
        }
    }
}