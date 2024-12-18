package action.sorting;

import action.ConsoleActions;
import action.IAction;

public class ViewBooksByAvailabilityAction implements IAction {
    private final ConsoleActions consoleActions;

    public ViewBooksByAvailabilityAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.viewBooksByAvailability();
    }
}