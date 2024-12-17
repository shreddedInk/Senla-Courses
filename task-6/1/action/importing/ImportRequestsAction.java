package action.importing;

import action.ConsoleActions;
import action.IAction;

public class ImportRequestsAction implements IAction {
    private final ConsoleActions consoleActions;

    public ImportRequestsAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.importRequests();
    }
}
