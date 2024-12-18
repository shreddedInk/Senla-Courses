package action.getinfo;

import action.*;

public class GetTotalEarningsByPeriodAction implements IAction {
    private final ConsoleActions consoleActions;

    public GetTotalEarningsByPeriodAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.getTotalEarningsByPeriod();
    }
}
