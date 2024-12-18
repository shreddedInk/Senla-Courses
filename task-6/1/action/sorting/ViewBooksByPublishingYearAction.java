package action.sorting;

import action.ConsoleActions;
import action.IAction;

public class ViewBooksByPublishingYearAction implements IAction {
    private final ConsoleActions consoleActions;

    public ViewBooksByPublishingYearAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.viewBooksByPublishingYear();
    }
}