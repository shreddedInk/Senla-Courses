package action.getinfo;

import action.IAction;
import action.ConsoleActions;

public class GetCompletedOrdersByPeriodAction implements IAction {
    private final ConsoleActions consoleActions;

    public GetCompletedOrdersByPeriodAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.getCompletedOrdersByPeriod();
    }
}