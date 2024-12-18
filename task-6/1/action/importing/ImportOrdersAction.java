package action.importing;

import action.ConsoleActions;
import action.IAction;

public class ImportOrdersAction implements IAction {
    private final ConsoleActions consoleActions;

    public ImportOrdersAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.importOrders();
    }
}
