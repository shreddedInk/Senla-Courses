package action.getinfo;;

import action.*;

public class GetCompletedOrderCountByPeriodAction implements IAction {
    private final ConsoleActions consoleActions;

    public GetCompletedOrderCountByPeriodAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.getCompletedOrderCountByPeriod();
    }
}
