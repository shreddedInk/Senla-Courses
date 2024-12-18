package action.exporting;

import action.ConsoleActions;
import action.IAction;

public class ExportOrdersAction implements IAction {
    private final ConsoleActions consoleActions;

    public ExportOrdersAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.exportOrders();
    }
}