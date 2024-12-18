package action.sorting;

import action.ConsoleActions;
import action.IAction;

public class ViewBooksByNameAction implements IAction {
    private final ConsoleActions consoleActions;

    public ViewBooksByNameAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.viewBooksByName();
    }
}