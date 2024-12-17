package action.sorting;

import action.ConsoleActions;
import action.IAction;

public class ViewOrdersByDateAction implements IAction {
    private final ConsoleActions consoleActions;

    public ViewOrdersByDateAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.viewOrdersByDate();
    }
}