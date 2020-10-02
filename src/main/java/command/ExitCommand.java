package command;

import exception.MoneyTrackerException;
import storage.Storage;
import transaction.TransactionList;
import ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoneyTrackerException {
        
    }
}
