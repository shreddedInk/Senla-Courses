package action.importing;

import action.ConsoleActions;
import action.IAction;

public class ImportBooksAction implements IAction {
    private final ConsoleActions consoleActions;

    public ImportBooksAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.importBooks();
    }
}
