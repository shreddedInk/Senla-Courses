package action.exporting;

import action.ConsoleActions;
import action.IAction;

public class ExportBooksAction implements IAction {
    private final ConsoleActions consoleActions;

    public ExportBooksAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.exportBooks();
    }
}