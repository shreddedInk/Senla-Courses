package action.exporting;

import action.ConsoleActions;
import action.IAction;

public class ExportRequestsAction implements IAction {
    private final ConsoleActions consoleActions;

    public ExportRequestsAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.exportRequests();
    }
}
