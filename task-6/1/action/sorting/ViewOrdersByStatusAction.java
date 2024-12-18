package action.sorting;

import action.ConsoleActions;
import action.IAction;

public class ViewOrdersByStatusAction implements IAction {
    private final ConsoleActions consoleActions;

    public ViewOrdersByStatusAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.viewOrdersByStatus();
    }
}